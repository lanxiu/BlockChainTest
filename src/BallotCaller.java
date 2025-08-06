
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;
//import org.web3j.tx.gas.ContractGasProvider;
//import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.File;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BallotCaller {
    public static void main(String[] args) throws Exception {
        // 链接到本地 Hardhat 节点
        Web3j web3j = Web3j.build(new HttpService("http://leader20:8545"));
        String version = web3j.web3ClientVersion().send().getWeb3ClientVersion();
        System.out.println(version);


        String privateKey = "0xdf57089febbacf7ba0bc227dafbffa9fc08a93fdc68e1e42411a14efcf23656e";
        Credentials credentials = Credentials.create(privateKey);

        EthGetBalance balanceWei = web3j.ethGetBalance("0x8626f6940E2eb28930eFb4CeF49B2d1F2C9C1199", DefaultBlockParameterName.LATEST).send();
        BigInteger balance = balanceWei.getBalance();
        System.out.println("direct get the address:");
        System.out.println("ETH Balance: " + Convert.fromWei(balance.toString(), Convert.Unit.ETHER));


        List<byte[]> proposalNames = new ArrayList<>();

// 把字符串填充到32字节

        proposalNames.add(toBytes32("Alice"));
        proposalNames.add(toBytes32("Bob"));


        // 转换成 List<Bytes32>
        List<Bytes32> bytes32List = new ArrayList<>();

        for (byte[] name : proposalNames) {
            byte[] nameBytes = name;
            byte[] padded = new byte[32];
            System.arraycopy(nameBytes, 0, padded, 0, Math.min(nameBytes.length, 32));
            bytes32List.add(new Bytes32(padded));
        }



        // 设置 gas 策略
        ContractGasProvider gasProvider = new StaticGasProvider(
                BigInteger.valueOf(20_000_000_000L), // gas price
                BigInteger.valueOf(3_000_000)        // gas limit
        );


        String encodedConstructor = FunctionEncoder.encodeConstructor(
                Arrays.<Type>asList(new DynamicArray<>(
                        Bytes32.class,
                        proposalNames.stream().map(Bytes32::new).collect(Collectors.toList())
                ))
        );


//        Greeter_sol_Greeter greeter = Greeter_sol_Greeter.deploy(web3j,credentials,gasProvider,"Hello, World!" ).send();

//        Greeter receipt = Contract.deployRemoteCall(
//                Greeter.class,
//                web3j,
//                credentials,
//                gasProvider,
//                Greeter.BINARY,
//                encodedConstructor
//        ).send();




// 部署合约，传入 List<Bytes32>
        Voting_sol_Ballot ballot = Voting_sol_Ballot.deploy(web3j, credentials, gasProvider,proposalNames).send();
//        Ballot ballot = Ballot.deploy(web3j, credentials, bigInteger,bigIntegerLimit, bytes32List).send();
        System.out.println("Deployed to: " + ballot.getContractAddress());

        ballot.vote(BigInteger.valueOf(1)).send();

       //  调用合约方法（如查询 winner）
        byte[] winnerNameBytes = ballot.winnerName().send();
        String winnerName = new String(winnerNameBytes).trim();
        System.out.println("Winning proposal name: " + winnerName);

    }

    public static byte[] toBytes32(String str) {
        byte[] byteValue = str.getBytes(StandardCharsets.UTF_8);
        byte[] byteValue32 = new byte[32];
        if (byteValue.length > 32) {
            throw new RuntimeException("String too long to fit in bytes32: " + str);
        }
        System.arraycopy(byteValue, 0, byteValue32, 0, byteValue.length);
        return byteValue32;
    }
}
