async function main() {
  const [deployer] = await ethers.getSigners();

  console.log("Deploying contracts with the account:", deployer.address);

  const Greeter = await ethers.getContractFactory("Greeter");
  const greeter = await Greeter.deploy("Hello, Hardhat!");

await greeter.waitForDeployment();  // 替代 greeter.deployed()
console.log("Greeter deployed to:", await greeter.getAddress());

}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
