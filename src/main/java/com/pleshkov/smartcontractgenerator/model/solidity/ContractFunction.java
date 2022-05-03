package com.pleshkov.smartcontractgenerator.model.solidity;

public class ContractFunction {

    public static final String GET_TOKEN_URI_NO_REVEAL = "\tfunction tokenURI(uint256 _tokenId) public view virtual override\n" +
            "\t\treturns (string memory) \n" +
            "\t{\n" +
            "\t\trequire(_exists(_tokenId), \"ERC721Metadata: URI query for nonexistent token\");\n" +
            "\t\treturn string(abi.encodePacked(uri, _tokenId.toString()));\n" +
            "\t}\n";

    public static final String GET_TOKEN_URI_WITH_REVEAL = "\tfunction tokenURI(uint256 _tokenId) public view virtual override\n" +
            "\t\treturns (string memory) \n" +
            "\t{\n" +
            "\t\trequire(_exists(_tokenId), \"ERC721Metadata: URI query for nonexistent token\");\n" +
            "\t\tif (!reveal) {\n" +
            " \t\t\treturn string(abi.encodePacked(mockUri));\n" +
            "\t\t} else {\n" +
            "\t\t\treturn string(abi.encodePacked(uri, _tokenId.toString()));\n" +
            "\t\t}\n" +
            "\t}\n";

    public static final String PUBLIC_MINT = "\tfunction publicMint(\n" +
            "\t\tuint256 _numOfTokens\n" +
            "\t)\n" +
            "\t\tpublic\n" +
            "\t\tpayable\n" +
            "\t{\n" +
            "\t\trequire(isMintActive, 'Public mint is not active now!');\n" +
            "\t\trequire(_numOfTokens <= maxMint, \"You are trying to mint too many tokens!\");\n" +
            "\t\trequire(totalSupply().add(_numOfTokens) <= supply, \"There are less non minted tokens than you are trying to mint!\");\n" +
            "\t\trequire(cost.mul(_numOfTokens) == msg.value, \"Ether amount is not correct\");\n" +
            "        \n" +
            "\t\tfor(uint j = 0; j < _numOfTokens; j++) {\n" +
            "\t\t\t_safeMint(msg.sender, totalSupply());\n" +
            "\t\t}\n" +
            "\t}\n";

    public static final String WITHDRAW_WITH_ADDRESS_PARAMETER = "\tfunction withdraw() public onlyOwner \n" +
            "\t{\n" +
            "\t\tuint balance = address(this).balance;\n" +
            "\t\trequire(balance > 0, \"Balance should be more then zero\");\n" +
            "\t\tpayable(address(%s)).transfer(balance);\n" +
            "\t}\n";

    //set functions

    public static final String SET_COST = "\tfunction setCost(uint256 _newCost) public onlyOwner {\n" +
            "\t\tcost = _newCost;\n" +
            "\t}\n";
    public static final String SET_MAX_MINT = "\tfunction setMaxMintAmount(uint256 _newMaxMintAmount) public onlyOwner {\n" +
            "\t\tmaxMint = _newMaxMintAmount;\n" +
            "\t}\n";
    public static final String SET_SUPPLY = "\tfunction setSupply(uint256 _newSupply) public onlyOwner {\n" +
            "\t\tsupply = _newSupply;\n" +
            "\t}\n";
    public static final String SET_OWNER_ADDRESS = "\tfunction setOwnerAddress(address _newOwnerAddress) public onlyOwner {\n" +
            "\t\townerAddress = _newOwnerAddress;\n" +
            "\t}\n";
    public static final String SET_BASE_URI = "\tfunction setUri(string memory _newUri) public onlyOwner {\n" +
            "\t\turi = _newUri;\n" +
            "\t}\n";


    public static final String SET_MERKLE_ROOT = "\tfunction setRoot(uint256 _root) onlyOwner() public {\n" +
            "\t\tmerkleRoot = bytes32(_root);\n" +
            "\t}\n";
    public static final String SET_MOCK_URI = "\tfunction setMockUri(string memory _newMockUri) public onlyOwner {\n" +
            "\t\tmockUri = _newMockUri;\n" +
            "\t}\n";
    public static final String SET_MINT_ACTIVE = "\tfunction setMintActive(bool _isMintActive) onlyOwner() public {\n" +
            "\t\tisMintActive = _isMintActive;\n" +
            "\t}\n";

    //optional

    //if you want to use merkle than you can't configure whole mint amount
    public static final String PRESALE_MINT_MERKLE = "\tfunction mintNFTDuringPresale(\n" +
            "\t\tuint256 _numOfTokens,\n" +
            "\t\tbytes32[] memory _proof\n" +
            "\t) \n" +
            "\t\tpublic \n" +
            "\t\tpayable\n" +
            "\t{\n" +
            "\t\trequire(isPremintActive, 'Premint is not active now!');\n" +
            "\t\trequire(verifyMerkleProof(_proof, bytes32(uint256(uint160(msg.sender)))), \"You are not allowed to mint at this phase!\");\n" +
            "\t\trequire(totalSupply().add(1) <= supply, 'There are less non minted tokens than you are trying to mint!');\n" +
            "\t\trequire(_numOfTokens <= 1, \"You are trying to mint too many tokens!\");\n" +
            "\t\t_safeMint(msg.sender, totalSupply());\n" +
            "\t}\n";

    public static final String PRESALE_MINT_ARRAY = "\tfunction mintNFTDuringPresale(\n" +
            "\t\tuint256 _numOfTokens\n" +
            "\t) \n" +
            "\t\tpublic \n" +
            "\t\t\tpayable\n" +
            "\t{\n" +
            "\t\trequire(isPremintActive, 'Premint is not active now!');\n" +
            "\t\trequire(whitelist[msg.sender] >= _numOfTokens, \"You are not allowed to mint this amount of tokens!\");\n" +
            "\t\trequire(totalSupply().add(_numOfTokens) <= supply, \"There are less non minted tokens than you are trying to mint!\");\n" +
            "\t\twhitelist[msg.sender] = whitelist[msg.sender] + _numOfTokens;\n" +
            "\t\t_safeMint(msg.sender, totalSupply());\n" +
            "\t}\n";

    public static final String VERIFY_MERKLE_PROOF = "\tfunction verifyMerkleProof(bytes32[] memory proof, bytes32 leaf) public view returns (bool) {\n" +
            "\t\tbytes32 currentHash = leaf;\n" +
            "\t\tfor (uint256 j = 0; j < proof.length; j++) {\n" +
            "\t\t\tbytes32 currentProof = proof[j];\n" +
            "            \n" +
            "\t\t\t\tif (currentHash <= currentProof) {\n" +
            "\t\t\t\t\tcurrentHash = sha256(abi.encodePacked(currentHash, currentProof));\n" +
            "\t\t\t\t} else {\n" +
            "\t\t\t\t\tcurrentHash = sha256(abi.encodePacked(currentProof, currentHash));\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\treturn currentHash == merkleRoot;\n" +
            "\t}\n";

    public static final String REVEAL = "\tfunction makeReveal() external onlyOwner \n" +
            "\t{\n" +
            "\t\treveal = true;\n" +
            "\t}\n";
    public static final String SET_PREMINT_ACTIVE = "\tfunction setPremintActive(bool _isPremintActive) onlyOwner() public {\n" +
            "\tisPremintActive = _isPremintActive;\n" +
            "\t}\n";

    public static final String ADD_TO_WHITELIST = "\tfunction addToWhitelist(\n" +
            "\t\taddress[] calldata _addresses,\n" +
            "\t\tuint256[] calldata _amounts\n" +
            "\t) public onlyOwner {\n" +
            "\t\tfor (uint256 j; j < _addresses.length; j++) {\n" +
            "\t\t\twhitelist[_addresses[j]] = _amounts[j];\n" +
            "\t\t}\n" +
            "\t}\n";

    public static final String STANDART_FUNCTIONS_TO_OVERRIDE = "\tfunction supportsInterface(\n" +
            "\t\tbytes4 _interfaceId\n" +
            "\t) \n" +
            "\t\tpublic\n" +
            "\t\tview \n" +
            "\t\toverride (ERC721, ERC721Enumerable) \n" +
            "\t\treturns (bool) \n" +
            "\t{\n" +
            "\t\treturn super.supportsInterface(_interfaceId);\n" +
            "\t}\n" +
            "\n" +
            "\tfunction _beforeTokenTransfer(\n" +
            "\t\taddress _from, \n" +
            "\t\taddress _to, \n" +
            "\t\tuint256 _tokenId\n" +
            "\t) \n" +
            "\t\tinternal \n" +
            "\t\t override(ERC721, ERC721Enumerable) \n" +
            "\t{\n" +
            "\t\tsuper._beforeTokenTransfer(_from, _to, _tokenId);\n" +
            "\t}\n";
}
