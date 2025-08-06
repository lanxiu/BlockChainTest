import React, { useEffect, useState } from "react";
import { ethers } from "ethers";

const DAO_ABI = [
  "function proposalCount() view returns (uint256)",
  "function createProposal(string description)",
  "function vote(uint256 proposalId, bool support)",
  "function getVotes(uint256 proposalId) view returns (uint256 forVotes, uint256 againstVotes)",
  "function getProposalDescription(uint256 proposalId) view returns (string)"
];

const DAO_ADDRESS = "0x5FbDB2315678afecb367f032d93F642f64180aa3"

export default function SimpleDAO() {
  const [provider, setProvider] = useState(null);
  const [signer, setSigner] = useState(null);
  const [contract, setContract] = useState(null);
  const [account, setAccount] = useState(null);

  const [proposalCount, setProposalCount] = useState(0);
  const [proposals, setProposals] = useState([]);
  const [newProposalDesc, setNewProposalDesc] = useState("");
  const [isConnecting, setIsConnecting] = useState(false);
  const [error, setError] = useState(null);

  // 连接钱包函数，防重复调用
  async function connectWallet() {
    if (isConnecting) return;
    setError(null);
    setIsConnecting(true);
    try {
      if (!window.ethereum) {
        setError("请安装 MetaMask 钱包");
        setIsConnecting(false);
        return;
      }
console.log("hello world");
      const prov = new ethers.BrowserProvider(window.ethereum);


      await prov.send("eth_requestAccounts", []);
const accounts = await prov.send("eth_accounts", []);
const addr2 = accounts[0];
console.log("地址:", addr2);
      const sign = await prov.getSigner();
//      const addr = await sign.getAddress({ noEns: true });
const addr = await sign.getAddress();
console.log("地址:", addr);
      const contractInstance = new ethers.Contract(DAO_ADDRESS, DAO_ABI, sign);

      setProvider(prov);
      setSigner(sign);
      setAccount(addr);
      setContract(contractInstance);

      const count = await contractInstance.proposalCount();
      setProposalCount(count);
    } catch (e) {
      setError(e.message || "连接失败");
    } finally {
      setIsConnecting(false);
    }
  }

  // 读取所有提案数据
  useEffect(() => {
    async function fetchProposals() {
      if (!contract) return;

      let list = [];
      for (let i = 0; i < proposalCount; i++) {
        const description = await contract.getProposalDescription(i);
        const votes = await contract.getVotes(i);


      const forVotes = votes.forVotes ?? votes[0];
      const againstVotes = votes.againstVotes ?? votes[1];

console.log("forvotes: "+ forVotes);
console.log("againstVotes: "+ againstVotes);

        list.push({
          id: i,
          description,
          forVotes: forVotes,

          againstVotes: againstVotes
        });
      }
      setProposals(list);
    }

    fetchProposals();
  }, [contract, proposalCount]);

  async function handleCreateProposal() {
    if (!newProposalDesc.trim()) {
      alert("请输入提案描述");
      return;
    }
    try {
console.log("start 11");
     // const tx = await contract.createProposal(newProposalDesc);
//const contractWithSigner = contract.connect(signer);
const tx = await contract.connect(signer).createProposal(newProposalDesc);


console.log("start 22222222222222222222222222222222222222222222");
      await tx.wait();
      alert("提案创建成功");
      setNewProposalDesc("");
console.log("3333");

 const code33 = await provider.getCode(DAO_ADDRESS);
               console.log("3333" + DAO_ADDRESS);
               if (code33 === "0x") {
                     console.log("当前地址上并没有合约！");
                   } else {
                     console.log("合约已部署，可以调用。");
                   }







      const count = await contract.connect(signer).proposalCount();

console.log("4444");
      setProposalCount(count);
    } catch (e) {
      alert("创建失败：" + e.message);
    }
  }

  async function handleVote(proposalId, support) {
    try {
      const tx = await contract.vote(proposalId, support);
      await tx.wait();
      alert("投票成功");
      const votes = await contract.getVotes(proposalId);



      const forVotes = votes.forVotes ?? votes[0];
      const againstVotes = votes.againstVotes ?? votes[1];




      setProposals((prev) =>
        prev.map((p) =>
          p.id === proposalId
            ? {
                ...p,
                forVotes: forVotes,
                againstVotes: againstVotes
              }
            : p
        )
      );
    } catch (e) {
      alert("投票失败：" + e.message);
    }
  }

  return (
    <div style={{ padding: 20, maxWidth: 600 }}>
      <h1>简单 DAO 投票系统（ethers v6）</h1>

      {!account ? (
        <div>
          <button onClick={connectWallet} disabled={isConnecting}>
            {isConnecting ? "连接中..." : "连接钱包"}
          </button>
          {error && <p style={{ color: "red" }}>{error}</p>}
        </div>
      ) : (
        <p>当前账户: {account}</p>
      )}

      <section>
        <h3>创建提案</h3>
        <input
          type="text"
          value={newProposalDesc}
          onChange={(e) => setNewProposalDesc(e.target.value)}
          placeholder="提案描述"
          style={{ width: "80%", marginRight: 8 }}
          disabled={!account}
        />
        <button onClick={handleCreateProposal} disabled={!account}>
          创建
        </button>
      </section>

      <hr />

      <section>
        <h3>提案列表</h3>
        {proposals.length === 0 && <p>暂无提案</p>}
        {proposals.map((p) => (
          <div
            key={p.id}
            style={{
              border: "1px solid #ddd",
              padding: 12,
              marginBottom: 12,
              borderRadius: 6
            }}
          >
            <p>
              <b>提案 #{p.id}:</b> {p.description}
            </p>
            <p>
              支持票: {p.forVotes} &nbsp;&nbsp; 反对票: {p.againstVotes}
            </p>
            <button onClick={() => handleVote(p.id, true)} disabled={!account} style={{ marginRight: 8 }}>
              支持
            </button>
            <button onClick={() => handleVote(p.id, false)} disabled={!account}>
              反对
            </button>
          </div>
        ))}
      </section>
    </div>
  );
}
