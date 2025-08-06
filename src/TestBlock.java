import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

public class TestBlock {


    public static void main(String[] args) throws Exception {

        Web3j web3 = Web3j.build(new HttpService("http://leader20:8545"));

// 获取测试账户地址
        EthAccounts accounts = web3.ethAccounts().send();

        List<String> addresses = accounts.getAccounts();
        for (String addr : addresses) {
            System.out.println("账户地址: " + addr);
        }
        String address = addresses.get(10);

        EthGetBalance balanceWei = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        BigInteger balance = balanceWei.getBalance();
        System.out.println("direct get the address:");
        System.out.println("ETH Balance: " + Convert.fromWei(balance.toString(), Convert.Unit.ETHER));


        String password = "yourStrongPassword";
        String walletFilePath = "/Users/lenchol/work/wallet";


        String walletFileName = WalletUtils.generateFullNewWalletFile(password, new File(walletFilePath));
        System.out.println("Wallet file generated: " + walletFileName);


        File oldFile = new File(walletFilePath, walletFileName);

        Credentials credentials = WalletUtils.loadCredentials(password, oldFile);
        String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
        System.out.println("create wallet");
        System.out.println("Address: " + credentials.getAddress());
        System.out.println("Private Key: " + privateKey);


/**

 VoteContract contract = VoteContract.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT).send();

 contract.vote("World").send();

 BigInteger result = contract.totalVotesFor("World").send();
 System.out.println("Votes: " + result);

 contract.valueUpdatedEventFlowable(startBlock, endBlock)
 .subscribe(event -> System.out.println("新值: " + event.newValue));

 */
    }

}
