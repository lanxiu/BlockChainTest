jq '.abi' Greeter.json > Greeter.abi

jq -r '.bytecode' Greeter.json  > Greeter.bin

docker  exec -it 3cf4b11a3794 bash

docker run -it -p 8545:8545 -v $PWD:/app -w /app node:18 bash

scp root@leader20:/root/hardhat-chain/artifacts/contracts/Voting.sol/Ballot.abi /Users/lenchol/IdeaProjects/blockTest/src

scp root@leader20:/root/hardhat-chain/artifacts/contracts/Voting.sol/Ballot.bin /Users/lenchol/IdeaProjects/blockTest/src



scp root@leader20:/root/hardhat-chain/artifacts/contracts/Greeter.sol/Greeter.abi /Users/lenchol/IdeaProjects/blockTest/src

scp root@leader20:/root/hardhat-chain/artifacts/contracts/Greeter.sol/Greeter.bin /Users/lenchol/IdeaProjects/blockTest/src

  String version = web3j.web3ClientVersion().send().getWeb3ClientVersion();

scp /Users/lenchol/Downloads/jdk-21_linux-x64_bin.tar.gz root@leader20:/root
       

export JAVA_HOME=/root/java/jdk-21.0.8

export PATH=$JAVA_HOME/bin:$PATH
vim /etc/profile
source /etc/profile


error  web3j solidity generate --solidityTypes Greeter_sol_Greeter.bin Greeter_sol_Greeter.abi -o . -p .

error web3j solidity generate Greeter_sol_Greeter.bin Greeter_sol_Greeter.abi -o . -p .

web3j generate solidity -b Greeter_sol_Greeter.bin -a Greeter_sol_Greeter.abi -o . -p .


npm install -g solc

solcjs Greeter.sol --bin --abi --optimize -o .

solcjs Voting.sol --bin --abi --optimize -o .


web3j generate solidity -b Voting_sol_Ballot.bin -a Voting_sol_Ballot.abi -o . -p .

docker run -it -p 8545:8545 -p 3000:3000 -v $PWD:/app -w /app node:18 bash


 npx hardhat node



$ npm install -g cnpm --registry=https://registry.npmmirror.com
$ npm config set registry https://registry.npmmirror.com
$ cnpm install -g create-react-app
$ create-react-app my-app
$ cd my-app/
$ npm start


链接合约的方式

使用metamask 
直接使用服务器地址

npx hardhat run scripts/deploy.js

npx hardhat run scripts/daoDeploy.js --network localhost

npx hardhat verify 0x5FbDB2315678afecb367f032d93F642f64180aa3


ssh-keygen -t rsa -b 4096  
ssh-copy-id root@leader20  


