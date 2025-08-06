import org.web3j.codegen.SolidityFunctionWrapperGenerator;

import java.io.File;

public class GenerateContract {
    public static void main(String[] args) throws Exception {
        String abiPath = "./src/Ballot.abi";
        String binPath = "./src/Ballot.bin";
        String packageName = ".";
        String outputDir = "./src/main/java";

        File file = new File("./src/resources");
        System.out.println(file.getAbsolutePath());


        SolidityFunctionWrapperGenerator.main(new String[]{
                "--javaTypes",
                "-a", abiPath,
                "-b", binPath,
                "-p", packageName,
                "-o", outputDir
        });



        SolidityFunctionWrapperGenerator.main(new String[]{
                "--javaTypes",
                "-a", "./src/Greeter.abi",
                "-b", "./src/Greeter.abi",
                "-p", packageName,
                "-o", outputDir
        });

//        SolidityFunctionWrapperGenerator.main(new String[]{
//                "--javaTypes",         // 使用 Java 类型映射（推荐）
//                binPath,
//                abiPath,
//                "-p", packageName,
//                "-o", outputDir
//        });

        System.out.println("Java contract class generated.");
    }
}
