async function main() {
  const [deployer] = await ethers.getSigners();

  console.log("Deploying contracts with the account:", deployer.address);

  const SimpleDao= await ethers.getContractFactory("SimpleDAO");
  const simpleDao= await SimpleDao.deploy();

await simpleDao.waitForDeployment();  // 替代 greeter.deployed()
console.log("simpleDao deployed to:", await simpleDao.getAddress());

}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});

