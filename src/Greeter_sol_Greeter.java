import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class Greeter_sol_Greeter extends Contract {
    public static final String BINARY = "608060405234801561000f575f5ffd5b506040516105c93803806105c983398101604081905261002e91610054565b5f6100398282610188565b5050610242565b634e487b7160e01b5f52604160045260245ffd5b5f60208284031215610064575f5ffd5b81516001600160401b03811115610079575f5ffd5b8201601f81018413610089575f5ffd5b80516001600160401b038111156100a2576100a2610040565b604051601f8201601f19908116603f011681016001600160401b03811182821017156100d0576100d0610040565b6040528181528282016020018610156100e7575f5ffd5b8160208401602083015e5f91810160200191909152949350505050565b600181811c9082168061011857607f821691505b60208210810361013657634e487b7160e01b5f52602260045260245ffd5b50919050565b601f82111561018357805f5260205f20601f840160051c810160208510156101615750805b601f840160051c820191505b81811015610180575f815560010161016d565b50505b505050565b81516001600160401b038111156101a1576101a1610040565b6101b5816101af8454610104565b8461013c565b6020601f8211600181146101e7575f83156101d05750848201515b5f19600385901b1c1916600184901b178455610180565b5f84815260208120601f198516915b8281101561021657878501518255602094850194600190920191016101f6565b508482101561023357868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b61037a8061024f5f395ff3fe608060405234801561000f575f5ffd5b5060043610610034575f3560e01c8063a413686214610038578063cfae32171461004d575b5f5ffd5b61004b61004636600461011d565b61006b565b005b61005561007a565b60405161006291906101d0565b60405180910390f35b5f6100768282610289565b5050565b60605f805461008890610205565b80601f01602080910402602001604051908101604052809291908181526020018280546100b490610205565b80156100ff5780601f106100d6576101008083540402835291602001916100ff565b820191905f5260205f20905b8154815290600101906020018083116100e257829003601f168201915b5050505050905090565b634e487b7160e01b5f52604160045260245ffd5b5f6020828403121561012d575f5ffd5b813567ffffffffffffffff811115610143575f5ffd5b8201601f81018413610153575f5ffd5b803567ffffffffffffffff81111561016d5761016d610109565b604051601f8201601f19908116603f0116810167ffffffffffffffff8111828210171561019c5761019c610109565b6040528181528282016020018610156101b3575f5ffd5b816020840160208301375f91810160200191909152949350505050565b602081525f82518060208401528060208501604085015e5f604082850101526040601f19601f83011684010191505092915050565b600181811c9082168061021957607f821691505b60208210810361023757634e487b7160e01b5f52602260045260245ffd5b50919050565b601f82111561028457805f5260205f20601f840160051c810160208510156102625750805b601f840160051c820191505b81811015610281575f815560010161026e565b50505b505050565b815167ffffffffffffffff8111156102a3576102a3610109565b6102b7816102b18454610205565b8461023d565b6020601f8211600181146102e9575f83156102d25750848201515b5f19600385901b1c1916600184901b178455610281565b5f84815260208120601f198516915b8281101561031857878501518255602094850194600190920191016102f8565b508482101561033557868401515f19600387901b60f8161c191681555b50505050600190811b0190555056fea264697066735822122071e4366520fd0818db2d4a88a47437d632b6e72cc4030a530f91c85e567d169f64736f6c634300081e0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_GREET = "greet";

    public static final String FUNC_SETGREETING = "setGreeting";

    @Deprecated
    protected Greeter_sol_Greeter(String contractAddress, Web3j web3j, Credentials credentials,
                                  BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Greeter_sol_Greeter(String contractAddress, Web3j web3j, Credentials credentials,
                                  ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Greeter_sol_Greeter(String contractAddress, Web3j web3j,
                                  TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Greeter_sol_Greeter(String contractAddress, Web3j web3j,
                                  TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> greet() {
        final Function function = new Function(FUNC_GREET,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setGreeting(String _greeting) {
        final Function function = new Function(
                FUNC_SETGREETING,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greeting)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Greeter_sol_Greeter load(String contractAddress, Web3j web3j,
                                           Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Greeter_sol_Greeter(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Greeter_sol_Greeter load(String contractAddress, Web3j web3j,
                                           TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Greeter_sol_Greeter(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Greeter_sol_Greeter load(String contractAddress, Web3j web3j,
                                           Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Greeter_sol_Greeter(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Greeter_sol_Greeter load(String contractAddress, Web3j web3j,
                                           TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Greeter_sol_Greeter(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Greeter_sol_Greeter> deploy(Web3j web3j, Credentials credentials,
                                                         ContractGasProvider contractGasProvider, String _greeting) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greeting)));
        return deployRemoteCall(Greeter_sol_Greeter.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<Greeter_sol_Greeter> deploy(Web3j web3j,
                                                         TransactionManager transactionManager, ContractGasProvider contractGasProvider,
                                                         String _greeting) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greeting)));
        return deployRemoteCall(Greeter_sol_Greeter.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Greeter_sol_Greeter> deploy(Web3j web3j, Credentials credentials,
                                                         BigInteger gasPrice, BigInteger gasLimit, String _greeting) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greeting)));
        return deployRemoteCall(Greeter_sol_Greeter.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Greeter_sol_Greeter> deploy(Web3j web3j,
                                                         TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
                                                         String _greeting) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greeting)));
        return deployRemoteCall(Greeter_sol_Greeter.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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
}Greeter_sol_Greeter
