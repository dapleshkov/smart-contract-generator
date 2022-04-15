package com.pleshkov.smartcontractgenerator.model.solidity;

public class ContractFunction {

    public static final String GET_TOKEN_URI_NO_REVEAL = "function tokenURI(uint256 _tokenId) public view virtual override\n" +
            "\t\treturns (string memory) \n" +
            "\t{\n" +
            "\t\trequire(_exists(_tokenId), \"ERC721Metadata: URI query for nonexistent token\");\n" +
            "\t\treturn string(abi.encodePacked(uri, _tokenId.toString()));\n" +
            "\t}\n";

    public static final String GET_TOKEN_URI_WITH_REVEAL = "function tokenURI(uint256 _tokenId) public view virtual override\n" +
            "\t\treturns (string memory) \n" +
            "\t{\n" +
            "\t\trequire(_exists(_tokenId), \"ERC721Metadata: URI query for nonexistent token\");\n" +
            "\t\tif (!reveal) {\n" +
            " \t\t\treturn string(abi.encodePacked(mockUri));\n" +
            "\t\t} else {\n" +
            "\t\t\treturn string(abi.encodePacked(uri, _tokenId.toString()));\n" +
            "\t\t}\n" +
            "\t}\n";

    public static final String PUBLIC_MINT = "function publicMint(\n" +
            "        uint256 _numOfTokens\n" +
            "    )\n" +
            "        public\n" +
            "        payable\n" +
            "    {\n" +
            "        require(isMintActive, 'Public mint is not active now!');\n" +
            "        require(_numOfTokens <= maxMint, \"You are trying to mint too many tokens!\");\n" +
            "        require(totalSupply().add(_numOfTokens) <= MAX_NFT, \"There are less non minted tokens than you are trying to mint!\");\n" +
            "        require(cost.mul(_numOfTokens) == msg.value, \"Ether amount is not correct\");\n" +
            "        \n" +
            "        for(uint j = 0; j < _numOfTokens; j++) {\n" +
            "            _safeMint(msg.sender, totalSupply());\n" +
            "        }\n" +
            "    }\n";

    public static final String WITHDRAW_WITH_ADDRESS_PARAMETER = "\tfunction withdraw() public onlyOwner \n" +
            "\t{\n" +
            "\t\tuint balance = address(this).balance;\n" +
            "\t\trequire(balance > 0, \"Balance should be more then zero\");\n" +
            "\t\tpayable(address(%s)).transfer(balance);\n" +
            "\t}\n";

    //set functions

    public static final String SET_COST = "\tfunction setCost(uint256 _newCost) public onlyOwner {\n" +
            "\tcost = _newCost;\n" +
            "\t}\n";
    public static final String SET_MAX_MINT = "\tfunction setMaxMintAmount(uint256 _newMaxMintAmount) public onlyOwner {\n" +
            "\tmaxMint = _newMaxMintAmount;\n" +
            "\t}\n";
    public static final String SET_SUPPLY = "\tfunction setSupply(uint256 _newSupply) public onlyOwner {\n" +
            "\tsupply = _newSupply;\n" +
            "\t}\n";
    public static final String SET_OWNER_ADDRESS = "\tfunction setOwnerAddress(address _newOwnerAddress) public onlyOwner {\n" +
            "\townerAddress = _newOwnerAddress;\n" +
            "\t}\n";
    public static final String SET_BASE_URI = "\tfunction setUri(string memory _newUri) public onlyOwner {\n" +
            "\turi = _newUri;\n" +
            "\t}\n";


    public static final String SET_MERKLE_ROOT = "\tfunction setRoot(uint256 _root) onlyOwner() public {\n" +
            "\tmerkleRoot = bytes32(_root);\n" +
            "\t}\n";
    public static final String SET_BLIND_URI = "\tfunction setBlindUri(string memory _newBlindUri) public onlyOwner {\n" +
            "\tblindUri = _newBlindUri;\n" +
            "\t}\n";
    public static final String SET_MINT_ACTIVE = "\tfunction setMintActive(bool _isMintActive) onlyOwner() public {\n" +
            "\tisMintActive = _isMintActive;\n" +
            "\t}\n";

    //optional

    //if you want to use merkle than you can't configure whole mint amount
    public static final String PRESALE_MINT_MERKLE = "function mintNFTDuringPresale(\n" +
            "        uint256 _numOfTokens,\n" +
            "        bytes32[] memory _proof\n" +
            "    ) \n" +
            "        public \n" +
            "        payable\n" +
            "    {\n" +
            "        require(isPresaleActive, 'Premint is not active now!');\n" +
            "        require(verifyMerkleProof(_proof, bytes32(uint256(uint160(msg.sender)))), \"You are not allowed to mint at this phase!\");\n" +
            "        require(totalSupply() < MAX_NFT, 'All tokens have been minted');\n" +
            "        require(_numOfTokens <= 1, \"You are trying to mint too many tokens!\");\n" +
            "        whiteListMinted[msg.sender] = true;\n" +
            "        _safeMint(msg.sender, totalSupply());\n" +
            "    }\n";

    public static final String PRESALE_MINT_ARRAY = "function mintNFTDuringPresale(\n" +
            "        uint256 _numOfTokens,\n" +
            "    ) \n" +
            "        public \n" +
            "        payable\n" +
            "    {\n" +
            "        require(isPresaleActive, 'Premint is not active now!');\n" +
            "        require(whitelist[msg.sender] >= _numOfTokens), \"You are not allowed to mint this amount of tokens!\");\n" +
            "        require(totalSupply() < MAX_NFT, 'All tokens have been minted');\n" +
            "        whiteListMinted[msg.sender] = true;\n" +
            "        _safeMint(msg.sender, totalSupply());\n" +
            "    }\n";

    public static final String VERIFY_MERKLE_PROOF = "function verifyMerkleProof(bytes32[] memory proof, bytes32 leaf) public view returns (bool) {\n" +
            "        bytes32 currentHash = leaf;\n" +
            "        for (uint256 j = 0; j < proof.length; j++) {\n" +
            "            bytes32 currentProof = proof[j];\n" +
            "            \n" +
            "            if (currentHash <= currentProof) {\n" +
            "                currentHash = sha256(abi.encodePacked(currentHash, currentProof));\n" +
            "            } else {\n" +
            "                currentHash = sha256(abi.encodePacked(currentProof, currentHash));\n" +
            "            }\n" +
            "        }\n" +
            "        return currentHash == merkleRoot;\n" +
            "    }\n";

    public static final String REVEAL = "\tfunction reveal() external onlyOwner \n" +
            "\t{\n" +
            "\t\treveal = true;\n" +
            "\t}\n";
    public static final String SET_PREMINT_ACTIVE = "\tfunction setPremintActive(bool _isPremintActive) onlyOwner() public {\n" +
            "\tisPremintActive = _isPremintActive;\n" +
            "\t}\n";

    public static final String ADD_TO_WHITELIST = "    function addToWhitelist(\n" +
            "        address[] calldata _addresses,\n" +
            "        uint256[] calldata _amounts\n" +
            "    ) public onlyOwner {\n" +
            "        for (uint256 j; j < _addresses.length; j++) {\n" +
            "            whitelist[_addresses[j]] = _amounts[j];\n" +
            "        }\n" +
            "    }\n";
}
