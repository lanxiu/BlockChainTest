import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.7.0.
 */
@SuppressWarnings("rawtypes")
public class Voting_sol_Ballot extends Contract {
    public static final String BINARY = "608060405234801561000f575f5ffd5b5060405161089738038061089783398101604081905261002e916100d2565b5f80546001600160a01b03191633908117825581526001602081905260408220555b81518110156100b7576002604051806040016040528084848151811061007857610078610199565b60209081029190910181015182525f91810182905283546001818101865594835291819020835160029093020191825591909101519082015501610050565b50506101ad565b634e487b7160e01b5f52604160045260245ffd5b5f602082840312156100e2575f5ffd5b81516001600160401b038111156100f7575f5ffd5b8201601f81018413610107575f5ffd5b80516001600160401b03811115610120576101206100be565b604051600582901b90603f8201601f191681016001600160401b038111828210171561014e5761014e6100be565b60405291825260208184018101929081018784111561016b575f5ffd5b6020850194505b8385101561018e57845180825260209586019590935001610172565b509695505050505050565b634e487b7160e01b5f52603260045260245ffd5b6106dd806101ba5f395ff3fe608060405234801561000f575f5ffd5b5060043610610085575f3560e01c8063609ff1bd11610058578063609ff1bd146101085780639e7b8d611461011e578063a3ec138d14610131578063e2ba53f0146101a1575f5ffd5b80630121b93f14610089578063013cf08b1461009e5780632e4176cf146100cb5780635c19a95c146100f5575b5f5ffd5b61009c61009736600461062a565b6101a9565b005b6100b16100ac36600461062a565b610259565b604080519283526020830191909152015b60405180910390f35b5f546100dd906001600160a01b031681565b6040516001600160a01b0390911681526020016100c2565b61009c610103366004610641565b610285565b61011061047a565b6040519081526020016100c2565b61009c61012c366004610641565b6104e8565b61017261013f366004610641565b600160208190525f918252604090912080549181015460029091015460ff82169161010090046001600160a01b03169084565b6040516100c2949392919093845291151560208401526001600160a01b03166040830152606082015260800190565b6101106105fb565b335f9081526001602081905260409091209081015460ff16156102045760405162461bcd60e51b815260206004820152600e60248201526d20b63932b0b23c903b37ba32b21760911b60448201526064015b60405180910390fd5b6001818101805460ff19169091179055600280820183905581548154909190849081106102335761023361066e565b905f5260205f2090600202016001015f8282546102509190610682565b90915550505050565b60028181548110610268575f80fd5b5f9182526020909120600290910201805460019091015490915082565b335f9081526001602081905260409091209081015460ff16156102df5760405162461bcd60e51b81526020600482015260126024820152712cb7ba9030b63932b0b23c903b37ba32b21760711b60448201526064016101fb565b336001600160a01b038316036103375760405162461bcd60e51b815260206004820152601e60248201527f53656c662d64656c65676174696f6e20697320646973616c6c6f7765642e000060448201526064016101fb565b6001600160a01b038281165f9081526001602081905260409091200154610100900416156103d9576001600160a01b039182165f90815260016020819052604090912001546101009004909116903382036103d45760405162461bcd60e51b815260206004820152601960248201527f466f756e64206c6f6f7020696e2064656c65676174696f6e2e0000000000000060448201526064016101fb565b610337565b600181810180546001600160a81b0319166101006001600160a01b0386169081029190911783179091555f9081526020829052604090209081015460ff161561045c578154600282810154815481106104345761043461066e565b905f5260205f2090600202016001015f8282546104519190610682565b909155506104759050565b8154815482905f9061046f908490610682565b90915550505b505050565b5f80805b6002548110156104e357816002828154811061049c5761049c61066e565b905f5260205f2090600202016001015411156104db57600281815481106104c5576104c561066e565b905f5260205f2090600202016001015491508092505b60010161047e565b505090565b5f546001600160a01b031633146105525760405162461bcd60e51b815260206004820152602860248201527f4f6e6c79206368616972706572736f6e2063616e2067697665207269676874206044820152673a37903b37ba329760c11b60648201526084016101fb565b6001600160a01b0381165f908152600160208190526040909120015460ff16156105be5760405162461bcd60e51b815260206004820152601860248201527f54686520766f74657220616c726561647920766f7465642e000000000000000060448201526064016101fb565b6001600160a01b0381165f90815260016020526040902054156105df575f5ffd5b6001600160a01b03165f90815260016020819052604090912055565b5f600261060661047a565b815481106106165761061661066e565b905f5260205f2090600202015f0154905090565b5f6020828403121561063a575f5ffd5b5035919050565b5f60208284031215610651575f5ffd5b81356001600160a01b0381168114610667575f5ffd5b9392505050565b634e487b7160e01b5f52603260045260245ffd5b808201808211156106a157634e487b7160e01b5f52601160045260245ffd5b9291505056fea2646970667358221220d8a02a35a033908250816894e3531ffc5aa309d613f2bffc7007e9b46364f9f364736f6c634300081e0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_CHAIRPERSON = "chairperson";

    public static final String FUNC_DELEGATE = "delegate";

    public static final String FUNC_GIVERIGHTTOVOTE = "giveRightToVote";

    public static final String FUNC_PROPOSALS = "proposals";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_VOTERS = "voters";

    public static final String FUNC_WINNERNAME = "winnerName";

    public static final String FUNC_WINNINGPROPOSAL = "winningProposal";

    @Deprecated
    protected Voting_sol_Ballot(String contractAddress, Web3j web3j, Credentials credentials,
                                BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Voting_sol_Ballot(String contractAddress, Web3j web3j, Credentials credentials,
                                ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Voting_sol_Ballot(String contractAddress, Web3j web3j,
                                TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Voting_sol_Ballot(String contractAddress, Web3j web3j,
                                TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> chairperson() {
        final Function function = new Function(FUNC_CHAIRPERSON,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> delegate(String to) {
        final Function function = new Function(
                FUNC_DELEGATE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> giveRightToVote(String voter) {
        final Function function = new Function(
                FUNC_GIVERIGHTTOVOTE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, voter)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<byte[], BigInteger>> proposals(BigInteger param0) {
        final Function function = new Function(FUNC_PROPOSALS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<byte[], BigInteger>>(function,
                new Callable<Tuple2<byte[], BigInteger>>() {
                    @Override
                    public Tuple2<byte[], BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<byte[], BigInteger>(
                                (byte[]) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> vote(BigInteger proposal) {
        final Function function = new Function(
                FUNC_VOTE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(proposal)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, Boolean, String, BigInteger>> voters(
            String param0) {
        final Function function = new Function(FUNC_VOTERS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, Boolean, String, BigInteger>>(function,
                new Callable<Tuple4<BigInteger, Boolean, String, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, Boolean, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, Boolean, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(),
                                (Boolean) results.get(1).getValue(),
                                (String) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<byte[]> winnerName() {
        final Function function = new Function(FUNC_WINNERNAME,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<BigInteger> winningProposal() {
        final Function function = new Function(FUNC_WINNINGPROPOSAL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static Voting_sol_Ballot load(String contractAddress, Web3j web3j,
                                         Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting_sol_Ballot(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Voting_sol_Ballot load(String contractAddress, Web3j web3j,
                                         TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting_sol_Ballot(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Voting_sol_Ballot load(String contractAddress, Web3j web3j,
                                         Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Voting_sol_Ballot(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Voting_sol_Ballot load(String contractAddress, Web3j web3j,
                                         TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Voting_sol_Ballot(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Voting_sol_Ballot> deploy(Web3j web3j, Credentials credentials,
                                                       ContractGasProvider contractGasProvider, List<byte[]> proposalNames) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                org.web3j.abi.datatypes.generated.Bytes32.class,
                org.web3j.abi.Utils.typeMap(proposalNames, org.web3j.abi.datatypes.generated.Bytes32.class))));
        return deployRemoteCall(Voting_sol_Ballot.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<Voting_sol_Ballot> deploy(Web3j web3j,
                                                       TransactionManager transactionManager, ContractGasProvider contractGasProvider,
                                                       List<byte[]> proposalNames) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                org.web3j.abi.datatypes.generated.Bytes32.class,
                org.web3j.abi.Utils.typeMap(proposalNames, org.web3j.abi.datatypes.generated.Bytes32.class))));
        return deployRemoteCall(Voting_sol_Ballot.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Voting_sol_Ballot> deploy(Web3j web3j, Credentials credentials,
                                                       BigInteger gasPrice, BigInteger gasLimit, List<byte[]> proposalNames) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                org.web3j.abi.datatypes.generated.Bytes32.class,
                org.web3j.abi.Utils.typeMap(proposalNames, org.web3j.abi.datatypes.generated.Bytes32.class))));
        return deployRemoteCall(Voting_sol_Ballot.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Voting_sol_Ballot> deploy(Web3j web3j,
                                                       TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
                                                       List<byte[]> proposalNames) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                org.web3j.abi.datatypes.generated.Bytes32.class,
                org.web3j.abi.Utils.typeMap(proposalNames, org.web3j.abi.datatypes.generated.Bytes32.class))));
        return deployRemoteCall(Voting_sol_Ballot.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

//    public static void linkLibraries(List<Contract.LinkReference> references) {
//        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
//    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }
}
