# 基于区块链的投票系统

### 目的:
完成了一个完整功能链路的投票系统.  
基于 react(前端) + ethers v6(合约操作) + MetaMask(钱包管理) + hardhat(本地链)

### 目录介绍:

 
| 目录  | 用途 |
| ------------- | ------------- |
|js       |前端页面功能,基于react编写  |
|problems |问题整理及解决方案  |
|scripts  |区块链的合约部署脚本 | 
|sol      |区块链的合约源文件  |
|src      |基于web3j的java语言下的功能调用,包含部署 | 
|test     |区块链上的合约测试脚本  |

Command.md: 用到的各种命令  
pom.xml:    java web3j的依赖  


### 环境:
nvm     nodejs版本管理  
nodejs  js引擎  
hardhat 本地区块链服务



#### metamask 安装

谷歌商店,搜索metamask,注意不要被诱拐安装其它的,看准点赞最多的  

#### nvm nodejs install

~~~
# 下载 NVM 安装脚本
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash

# 重启 shell 或运行
source ~/.bashrc   # 或 ~/.zshrc，视你用 bash 还是 zsh 而定

# 安装 Node.js 最新 LTS 版本
nvm install --lts

# 查看已安装版本
node -v
npm -v

~~~

##### 遇到的问题
系统太老无法安装最新版,使用docker解决  
***新手没必要使用docker 本文所有的docker命令都不是必须的***
docker 未启动  
docker 未进入容器  

#### hardhat install

~~~
// 创建目录
mkdir hardhat-chain
cd hardhat-chain
// 使用docker 创建hardhat 环境,不用docker的可省略
docker run -it -p 8545:8545 -v $PWD:/app -w /app node:18 bash
// 安装hardhat
npm init -y
npm install --save-dev hardhat
// 初始化区块链,选择"Create a JavaScript project"
npx hardhat
// 启动区块链
npx hardhat node

~~~

这将:  
在 http://127.0.0.1:8545 地址上启动区块链  
提供20个带有虚拟币的账户  

#### 编译合约,部署合约

编写合约,如果没有的话,拷贝工程目录下的
~~~
cd contracts  
touch greeter.sol
~~~ 

进入容器执行命令
~~~
docker ps  
docker exec -it 《container id》 bash
~~~
编译  
~~~
npx hardhat compile  
~~~

部署

~~~
mkdir -p scripts
cd scripts
touch greeterDeploy.js
// 部署到链上
npx hardhat run scripts/greeterDeploy.js --network localhost

npx hardhat run scripts/daoDeploy.js --network localhost
记下这个部署打印的合约地址,做Dapp展示时会用到


~~~

*具体文件内容参考工程下的文件*



#### metamask 添加本地网络

右上角点击后选择“add a custom network" ,填写本地地址  
如 http://ip:8545  
chain ID: 31337  
Currency Symbol: ETH   

然后从启动服务器的控制台里复制几个账户,导入到metamask里   
*注意是 private key*  



#### hardhat  测试脚本
测试部署后的合约各方法调用
~~~
npx hardhat test
npx hardhat test test/ballot.test.js
~~~


#### 创建 Dapp前端简易界面

安装react环境

~~~
 npm install -g cnpm --registry=https://registry.npmmirror.com
 npm config set registry https://registry.npmmirror.com
 cnpm install -g create-react-app
 create-react-app my-app
 cd my-app/src
// 用本工程下的替换App.js,并且将文件中的DAO_ADDRESS 的地址替换为上边部署合约时生成的地址
//重新进入my-app目录,启动服务器
cd ..
npm start
~~~

访问http://ip:3000 应该能看到页面
连接钱包,添加提案,投票,查看结果


https://solidity-cn.readthedocs.io/zh/develop/solidity-by-example.html#voting
