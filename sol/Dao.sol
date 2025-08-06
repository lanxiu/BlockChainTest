// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract SimpleDAO {
    struct Proposal {
        string description;
        uint voteFor;
        uint voteAgainst;
        bool exists;
    }

    mapping(uint => Proposal) public proposals;
    mapping(address => mapping(uint => bool)) public voted; // 用户是否对某提案投票
    uint public proposalCount;

    event ProposalCreated(uint proposalId, string description);
    event Voted(address voter, uint proposalId, bool support);

    // 创建提案
    function createProposal(string memory description) external {
        proposals[proposalCount] = Proposal(description, 0, 0, true);
        emit ProposalCreated(proposalCount, description);
        proposalCount++;
    }

    // 投票，支持为true，反对为false
    function vote(uint proposalId, bool support) external {
        require(proposals[proposalId].exists, "Proposal does not exist");
        require(!voted[msg.sender][proposalId], "Already voted");

        voted[msg.sender][proposalId] = true;
        if (support) {
            proposals[proposalId].voteFor++;
        } else {
            proposals[proposalId].voteAgainst++;
        }

        emit Voted(msg.sender, proposalId, support);
    }

    // 获取投票结果
    function getVotes(uint proposalId) external view returns (uint forVotes, uint againstVotes) {
        require(proposals[proposalId].exists, "Proposal does not exist");
        Proposal memory p = proposals[proposalId];
        return (p.voteFor, p.voteAgainst);
    }

    // 获取提案描述
    function getProposalDescription(uint proposalId) external view returns (string memory) {
        require(proposals[proposalId].exists, "Proposal does not exist");
        return proposals[proposalId].description;
    }
}
