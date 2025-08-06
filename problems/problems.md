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

不得不说gpt时真坑人
