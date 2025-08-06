describe("ballot test", function () {
    it("should pass", async function () {


        const signers = await ethers.getSigners();
        const deployer = signers[0];
        const voter1 = signers[1];
        const voter2 = signers[2];


        const values = [
            ethers.encodeBytes32String("hello"),
            ethers.encodeBytes32String("world")
        ];
        console.log("Deploying contracts with the account:", deployer.address);

        const Ballot = await ethers.getContractFactory("Ballot");
        const ballot = await Ballot.deploy(values);

        await ballot.waitForDeployment();

        console.log("Ballot deployed to:", await ballot.getAddress());

        await ballot.connect(deployer).giveRightToVote(voter1);

        await ballot.connect(deployer).delegate(voter1.address);

        await ballot.connect(voter1).vote(1);

        console.log("winningProposal:", await ballot.connect(deployer).winningProposal());
        console.log("winningProposal winnerName:", await ballot.connect(deployer).winnerName());


    });
});
