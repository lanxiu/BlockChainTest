第一步：项目结构准备
bash
Copy
Edit
mkdir my-dapp && cd my-dapp
npm init -y
npm install --save-dev hardhat
npx hardhat
选择 "Create a JavaScript project" → 默认选项 → 完成后项目结构如下：

arduino
Copy
Edit
my-dapp/
├── contracts/
│   └── MyContract.sol     // 你的合约
├── scripts/
│   └── deploy.js          // 部署脚本
├── hardhat.config.js
└── ...
第二步：写合约（比如 contracts/MyContract.sol）
solidity
Copy
Edit
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract MyContract {
    string public message = "Hello, Blockchain!";
}
第三步：写部署脚本（scripts/deploy.js）
js
Copy
Edit
async function main() {
  const [deployer] = await ethers.getSigners();
  console.log("Deploying contract with account:", deployer.address);

  const MyContract = await ethers.getContractFactory("MyContract");
  const contract = await MyContract.deploy();

  console.log("Contract deployed to:", contract.address);
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
第四步：部署到本地链（你已通过 Docker 启动）
bash
Copy
Edit
npx hardhat run scripts/deploy.js --network localhost
Hardhat 会连接到你 Docker 启动的本地节点，比如 http://127.0.0.1:8545。
