####  系统版本太老,nodejs 无法安装

~~~
nvm install --lts
Installing latest LTS version.
Downloading and installing node v22.17.1...
Downloading https://nodejs.org/dist/v22.17.1/node-v22.17.1-linux-x64.tar.xz...
######################################################################## 100.0%
Computing checksum with sha256sum
Checksums matched!
manpath: can't set the locale; make sure $LC_* and $LANG are correct
Now using node v22.17.1
Creating default alias: default -> lts/* (-> v22.17.1)
[root@localhost ~]# npm -v
node: /lib64/libstdc++.so.6: version GLIBCXX_3.4.21' not found (required by node)
node: /lib64/libstdc++.so.6: version GLIBCXX_3.4.20' not found (required by node)
node: /lib64/libstdc++.so.6: version CXXABI_1.3.9' not found (required by node)
node: /lib64/libm.so.6: version GLIBC_2.27' not found (required by node)
node: /lib64/libc.so.6: version GLIBC_2.27' not found (required by node)
node: /lib64/libc.so.6: version GLIBC_2.28' not found (required by node)
node: /lib64/libc.so.6: version GLIBC_2.25' not found (required by node)
[root@localhost ~]# node -v
node: /lib64/libstdc++.so.6: version GLIBCXX_3.4.21' not found (required by node)
node: /lib64/libstdc++.so.6: version GLIBCXX_3.4.20' not found (required by node)
node: /lib64/libstdc++.so.6: version CXXABI_1.3.9' not found (required by node)
node: /lib64/libm.so.6: version GLIBC_2.27' not found (required by node)
node: /lib64/libc.so.6: version GLIBC_2.27' not found (required by node)
node: /lib64/libc.so.6: version GLIBC_2.28' not found (required by node)
node: /lib64/libc.so.6: version GLIBC_2.25' not found (required by node)
~~~

升级系统或者使用docker 安装

docker pull node:18

docker run -it --rm node:18 bash
新建容器
docker exec -it <container_id> bash
使用既有容器

#### docker 服务未启动
~~~
docker pull node:18
Cannot connect to the Docker daemon at unix:///var/run/docker.sock. Is the docker daemon running?
 
~~~

systemctl start docker



#### docker 内部服务器端口外部无法看到,必须端口映射
docker run -it -p 8545:8545 -v $PWD:/app -w /app node:18 bash

#### docker 进入容器执行命令
docker ps
docker exec -it container_ID bash


#### deploy 方法不正确

~~~
root@70728ff479ea:/app# npx hardhat run scripts/deploy.js --network localhost
WARNING: You are currently using Node.js v18.20.8, which is not supported by Hardhat. This can lead to unexpected behavior. See https://hardhat.org/nodejs-versions


Downloading compiler 0.8.28
Compiled 2 Solidity files successfully (evm target: paris).
Deploying contracts with the account: 0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266
TypeError: greeter.deployed is not a function
    at main (/app/scripts/deploy.js:9:17)
    at processTicksAndRejections (node:internal/process/task_queues:95:5)
root@70728ff479ea:/app# cd scripts/
~~~

改为

~~~

  const Greeter = await ethers.getContractFactory("Greeter");
  const greeter = await Greeter.deploy("Hello, Hardhat!");

  await greeter.waitForDeployment();

~~~

#### 合约中的map storage  address(0) 什么意思

键值对,懒加载
mapping(address => Voter) public voters;

复制副本
Voter sender = voters[msg.sender];
原始数据
Voter storage sender = voters[msg.sender];

address(0)  0地址
address(0) == 0x0000000000000000000000000000000000000000


#### transfer 

 
 
buyer.transfer(value);
将之前买家锁定在合约中的押金 value 退还给买家。

 
seller.transfer(address(this).balance);
将合约中剩余的全部余额（买家支付的商品钱）转给卖家。


#### purchase.sol 里的 互相质押保证机制（mutual stake mechanism）

ai 解释的很烂

商品价值 value

卖家付钱 value*2
买家付钱 value*2

交易完成

卖家退钱 value
买家退钱 剩下的 = value*3


#### 没有相应的编译器版本

~~~
The Solidity version pragma statement in these files doesn't match any of the configured compilers in your config. Change the pragma or configure additional compiler versions in your hardhat config.

  * contracts/Purchase.sol (^0.4.22)
  * contracts/Voting.sol (^0.4.22)
~~~

编辑hardhat.config.js,添加旧版本支持

~~~
require("@nomicfoundation/hardhat-toolbox");

/** @type import('hardhat/config').HardhatUserConfig */
module.exports = {
  solidity: {
    compilers: [
      { version: "0.4.22" },
      { version: "0.8.28" },
    ],
  },
};

~~~


#### 执行脚本没找到
~~~
root@3cf4b11a3794:/app# npx hardhat test
WARNING: You are currently using Node.js v18.20.8, which is not supported by Hardhat. This can lead to unexpected behavior. See https://hardhat.org/nodejs-versions




  0 passing (1ms)

Deploying contracts with the account: 0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266
~~~

测试脚本必须添加

~~~
describe("Ballot contract", function () {
  it("should deploy correctly", async function () {
    // 测试逻辑
  });
});

~~~


#### 脚本调用了v5的方法,执行环境时v6

~~~
ballot test
    1) should pass


  0 passing (49ms)
  1 failing

  1) ballot test
       should pass:
     TypeError: Cannot read properties of undefined (reading 'formatBytes32String')
      at Context.<anonymous> (test/ballotTest.js:13:26)
~~~

formatBytes32String改成encodeBytes32String


#### okhttp 版本冲突

写的测试代码在一个springboot工程里,和web3j要求的okhttp版本冲突  
新建一个工程,无须指定okhttp,web3j包里有自动依赖

~~~
Exception in thread "main" java.lang.NoSuchMethodError: okhttp3.RequestBody.create(Ljava/lang/String;Lokhttp3/MediaType;)Lokhttp3/RequestBody;
	at org.web3j.protocol.http.HttpService.performIO(HttpService.java:153)
	at org.web3j.protocol.Service.send(Service.java:48)
	at org.web3j.protocol.core.Request.send(Request.java:87)
	at TestBlock.main(TestBlock.java:25)
~~~

~~~
Exception in thread "main" java.lang.NoSuchMethodError: okhttp3.RequestBody.create(Ljava/lang/String;Lokhttp3/MediaType;)Lokhttp3/RequestBody;
	at org.web3j.protocol.http.HttpService.performIO(HttpService.java:153)
	at org.web3j.protocol.Service.send(Service.java:48)
	at org.web3j.protocol.core.Request.send(Request.java:87)
~~~


#### java 编译版本低

idea中的工程 模块的有个level是5,工程是8
File → Project Structure  → project/module  → Language level
设置为8
~~~
static interface  language level 5 not support   what's the mean
~~~

#### target 版本未指定

~~~
source release8  require target release 8
~~~

添加
~~~
<properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
</properties>

<build>
    <plugins>
        <!-- Maven Compiler Plugin 确保添加 -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.10.1</version> <!-- 版本可用最新的 -->
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
    </plugins>
</build>

~~~

#### slf4j  未指定

~~~
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
~~~
添加
~~~
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-simple</artifactId>
  <version>1.7.36</version>
</dependency>

~~~


#### 钱包的文件问题

目录不能自动创建
~~~
户地址: 0x8626f6940e2eb28930efb4cef49b2d1f2c9c1199
direct get the address:
ETH Balance: 9999.999497213857421875
Exception in thread "main" java.io.FileNotFoundException: ./wallets/your-wallet-file.json (No such file or directory)
	at java.io.FileInputStream.open0(Native Method)
	at java.io.FileInputStream.open(FileInputStream.java:195)
	at java.io.FileInputStream.<init>(FileInputStream.java:138)
	at c
~~~


文件不需要创建
~~~
direct get the address:
ETH Balance: 9999.999497213857421875
Exception in thread "main" java.io.FileNotFoundException: ./wallets/your-wallet-file.json/UTC--2025-07-30T03-33-49.755000000Z--1060a24b2f3a57c7d8635174ab9d21d688eda0fe.json (No such file or directory)
	at java.io.FileOutputStream.open0(Native Method)
	at java.io.FileOutputStream.open(FileOutputStream.java:270)
	at java.io.FileOutputStream.<init>(FileOutputStream.java:213)
~~~


####  第二天重启后 leader20 服务器ping 不通  meta mask 钱包不能连接

重启chrome解决

~~~
lens-MacBook-Pro:~ lenchol$ ping leader20
PING leader20 (192.168.174.20): 56 data bytes
Request timeout for icmp_seq 0
Request timeout for icmp_seq 1
ping: sendto: No route to host
Request timeout for icmp_seq 2
ping: sendto: Host is down
Request timeout for icmp_seq 3
ping: sendto: Host is down
Request timeout for icmp_seq 4
ping: sendto: Host is down
Request timeout for icmp_seq 5
ping: sendto: Host is down
Request timeout for icmp_seq 6
ping: sendto: Host is down
Request timeout for icmp_seq 7
ping: sendto: Host is down
Request timeout for icmp_seq 8
^C
--- leader20 ping statistics ---
10 packets transmitted, 0 packets received, 100.0% packet loss
lens-MacBook-Pro:~ lenchol$ ping 192.168.174.20
PING 192.168.174.20 (192.168.174.20): 56 data bytes
64 bytes from 192.168.174.20: icmp_seq=0 ttl=128 time=70.860 ms
64 bytes from 192.168.174.20: icmp_seq=1 ttl=128 time=0.793 ms
64 bytes from 192.168.174.20: icmp_seq=2 ttl=128 time=1.635 ms
64 bytes from 192.168.174.20: icmp_seq=3 ttl=128 time=0.467 ms
64 bytes from 192.168.174.20: icmp_seq=4 ttl=128 time=0.466 ms
64 bytes from 192.168.174.20: icmp_seq=5 ttl=128 time=1.536 ms
64 bytes from 192.168.174.20: icmp_seq=6 ttl=128 time=0.437 ms
^C
--- 192.168.174.20 ping statistics ---
7 packets transmitted, 7 packets received, 0.0% packet loss
round-trip min/avg/max/stddev = 0.437/10.885/70.860/24.489 ms
lens-MacBook-Pro:~ lenchol$ ping leader20
PING leader20 (192.168.174.20): 56 data bytes
64 bytes from 192.168.174.20: icmp_seq=0 ttl=128 time=0.511 ms
64 bytes from 192.168.174.20: icmp_seq=1 ttl=128 time=0.609 ms
~~~


#### jq 找不到

~~~
root@localhost Voting.sol]# yum install -y jq
Failed to set locale, defaulting to C
Loaded plugins: fastestmirror, langpacks
Loading mirror speeds from cached hostfile
 * base: mirrors.aliyun.com
 * extras: mirrors.aliyun.com
 * updates: mirrors.aliyun.com
base                                                                                                                                                                                 | 3.6 kB  00:00:00     
docker-ce-stable                                                                                                                                                                     | 3.5 kB  00:00:00     
extras                                                                                                                                                                               | 2.9 kB  00:00:00     
updates                                                                                                                                                                              | 2.9 kB  00:00:00     
No package jq available.
Error: Nothing to do
~~~

#### byte32的问题

java侧报错
~~~

Exception in thread "main" org.web3j.abi.TypeMappingException: java.lang.reflect.InvocationTargetException
	at org.web3j.abi.Utils.typeMap(Utils.java:238)
	at Ballot.deploy(Ballot.java:174)
	at BallotCaller.main(BallotCaller.java:58)
Caused by: java.lang.reflect.InvocationTargetException
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at org.web3j.abi.Utils.typeMap(Utils.java:232)
	... 2 more
Caused by: java.lang.UnsupportedOperationException: Input byte array must be in range 0 < M <= 32 and length must match type
	at org.web3j.abi.datatypes.Bytes.<init>(Bytes.java:23)
	at org.web3j.abi.datatypes.generated.Bytes32.<init>(Bytes32.java:15)
	... 7 more
~~~
hardhat侧报错
~~~
eth_getTransactionCount
eth_sendRawTransaction
  Contract deployment: <UnrecognizedContract>
  Transaction:         0x7fea7aa0f69884ed623e159bd7f171e50854e37351cffc04bc459b99870e1c4b
  From:                0x8626f6940e2eb28930efb4cef49b2d1f2c9c1199
  Value:               0 ETH
  Gas used:            4700000 of 4700000
  Block #2:            0x84e2f936782efb292b7dc14c7b8327bfb8b924f1bb97685e4a3b0e63ab65dfa4

  TransactionExecutionError: StackUnderflow
~~~

~~~
irect get the address:
ETH Balance: 9999.718
Exception in thread "main" org.web3j.protocol.exceptions.TransactionException: {"message":"Error: Transaction reverted without a reason string","txHash":"0xf1109cb69106b28bb097bb76ca1cd2b55a8cedc5db2bc619e92e2cd50646f66f","data":"0x"}
	at org.web3j.tx.Contract.executeTransaction(Contract.java:421)
	at org.web3j.tx.Contract.create(Contract.java:479)
	at org.web3j.tx.Contract.deploy(Contract.java:513)
	at org.web3j.tx.Contract.lambda$deployRemoteCall$7(Contract.java:665)
	at org.web3j.protocol.core.RemoteCall.send(RemoteCall.java:42)
	at BallotCaller.main(BallotCaller.java:48)

~~~

~~~

direct get the address:
ETH Balance: 9999.436
Exception in thread "main" java.lang.RuntimeException: com.fasterxml.jackson.databind.JsonMappingException: Can not deserialize instance of java.lang.String out of START_OBJECT token
 at [Source: buffer(okhttp3.internal.http1.Http1Codec$ChunkedSource@78691363).inputStream(); line: 1, column: 118] (through reference chain: org.web3j.protocol.core.methods.response.EthSendTransaction["error"]->org.web3j.protocol.core.Response$Error["data"])
	at org.web3j.tx.Contract.deploy(Contract.java:302)
	at org.web3j.tx.Contract.lambda$deployRemoteCall$5(Contract.java:334)
	at org.web3j.protocol.core.RemoteCall.send(RemoteCall.java:30)
	at BallotCaller.main(BallotCaller.java:64)
Caused by: com.fasterxml.jackson.databind.JsonMappingException: Can not deserialize instance of java.lang.String out of START_OBJECT token
 at [Source: buffer(okhttp3.internal.http1.Http1Codec$ChunkedSource@78691363).inputStream(); line: 1, column: 118] (through reference chain: org.web3j.protocol.core.methods.response.EthSendTransaction["error"]->org.web3j.protocol.core.Response$Error["data"])
	at com.fasterxml.jackson.databind.JsonMappingException.from(JsonMappingException.java:270)
	at com.fasterxml.jackson.databind.DeserializationContext.reportMappingException(DeserializationContext.java:1234)
	at com.fasterxml.jackson.databind.DeserializationContext.handleUnexpectedToken(DeserializationContext.java:1122)
	at com.fasterxml.jackson.databind.DeserializationContext.handleUnexpectedToken(DeserializationContext.java:1075)
	at com.fasterxml.jackson.databind.deser.std.StringDeserializer.deserialize(StringDeserializer.java:60)
	at com.fasterxml.jackson.databind.deser.std.StringDeserializer.deserialize(StringDeserializer.java:11)
	at com.fasterxml.jackson.databind.deser.SettableBeanProperty.deserialize(SettableBeanProperty.java:499)
	at com.fasterxml.jackson.databind.deser.impl.MethodProperty.deserializeAndSet(MethodProperty.java:101)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.vanillaDeserialize(BeanDeserializer.java:276)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:140)
	at com.fasterxml.jackson.databind.deser.SettableBeanProperty.deserialize(SettableBeanProperty.java:499)
	at com.fasterxml.jackson.databind.deser.impl.MethodProperty.deserializeAndSet(MethodProperty.java:101)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.vanillaDeserialize(BeanDeserializer.java:276)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:140)
	at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:3798)
	at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:2908)
	at org.web3j.protocol.Service.send(Service.java:33)
	at org.web3j.protocol.core.Request.send(Request.java:71)
	at org.web3j.tx.RawTransactionManager.signAndSend(RawTransactionManager.java:107)
	at org.web3j.tx.RawTransactionManager.sendTransaction(RawTransactionManager.java:91)
	at org.web3j.tx.TransactionManager.executeTransaction(TransactionManager.java:49)
	at org.web3j.tx.ManagedTransaction.send(ManagedTransaction.java:83)
	at org.web3j.tx.Contract.executeTransaction(Contract.java:242)
	at org.web3j.tx.Contract.create(Contract.java:271)
	at org.web3j.tx.Contract.deploy(Contract.java:300)
	... 3 more

Process finished with exit code 1
~~~

AI十分喜欢拿着es5的写法给es6环境用,如果不强调的话
~~~

        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new DynamicArray<>(Bytes32.class, bytes32List)));

new DynamicArray<>  report error can not infer arguments
~~~

解决方案1:失败

因为合约是0.4编译,java用的web3j 4.x 支持的是0.8  
所以我回退 web3j 3.x ,但是3.x不是地址不正确,就是编译失败  
这些都是AI给我的答案.使用了3.x后,  
又按照3.x的写法更改 javatype和gas 消耗,还是失败

#### provider获取的版本问题

V5  
const provider = new ethers.providers.Web3Provider(window.ethereum);

V6  
const provider = new ethers.BrowserProvider(window.ethereum);



~~~
export 'ethers'.'providers' (imported as 'ethers') was not found in 'ethers' (possible exports: AbiCoder, AbstractProvider, AbstractSigner, AlchemyProvider, AnkrProvider, BaseContract, BaseWallet, Block, BlockscoutProvider, BrowserProvider, ChainstackProvider, CloudflareProvider, ConstructorFragment, Contract, ContractEventPayload, ContractFactory, ContractTransactionReceipt, ContractTransactionResponse, ContractUnknownEventPayload, EnsPlugin, EnsResolver, ErrorDescription, ErrorFragment, EtherSymbol, Ethers

~~~


#### 重复请求问题

防止重复调用

~~~
const [isConnecting, setIsConnecting] = useState(false);

async function connectWallet() {
  if (isConnecting) return; // 防止重复
  setIsConnecting(true);
  try {
    await provider.send("eth_requestAccounts", []);
    // 其他逻辑
  } catch (e) {
    // 处理异常
  } finally {
    setIsConnecting(false);
  }
}

~~~

~~~
could not coalesce error (error={ "code": -32002, "message": "Request of type 'wallet_requestPermissions' already pending for origin http://leader20:3000. Please wait." }, payload={ "id": 2, "jsonrpc": "2.0", "method": "eth_requestAccounts", "params": [  ] }, code=UNKNOWN_ERROR, version=6.15.0)
    at makeError (http://leader20:3000/static/js/bundle.js:17807:15)
    at BrowserProvider.getRpcError (http://leader20:3000/static/js/bundle.js:13839:70)
    at BrowserProvider.getRpcError (http://leader20:3000/static/js/bundle.js:12849:18)
    at http://leader20:3000/static/js/bundle.js:13321:29
~~~



#### ENS解析问题

没用到ENS,但是方法里有ENS寻址

~~~
创建失败：network does not support ENS (operation="getEnsAddress", info={ "network": { "chainId": "31337", "name": "unknown" } }, code=UNSUPPORTED_OPERATION, version=6.15.0)
~~~

问题的来源其实是我把服务器地址8545当作合约地址填写进去了,
由此了解到前端并不需要知道服务器地址,有合约地址就可以操作

#### react 局部变量和全局变量的作用域问题

React 的 setContract 是异步更新状态，不会马上改变 contract 变量的值

如果你紧接着写 contract.on(...)，此时 contract 还是旧值（可能是 null 或 undefined）

监听函数挂载失败，自然收不到事件


#### 监听事件无效问题

1 未在ABI中声明事件
2 使用了为初始化的全局域
3 声明后漏了半个引号
4 复制在本地可以使用,在远程机器vim上无法使用  蛋疼的问题,未解决

~~~

const DAO_ABI = [
  "function proposalCount() view returns (uint256)",
  "function createProposal(string description)",
  "function vote(uint256 proposalId, bool support)",
  "function getVotes(uint256 proposalId) view returns (uint256 forVotes, uint256 againstVotes)",
  "function getProposalDescription(uint256 proposalId) view returns (string)",
  "event Voted(address voter,uint proposalId,bool support)"
];

~~~

####   复制在本地可以使用,在远程机器vim上无法使用  蛋疼的问题,未解决


不得不说gpt时真坑人
