package com.pleshkov.smartcontractgenerator.model;

import lombok.Data;

@Data
public class ContractParams {

    private String contractName;
    private String collectionName;
    private String ownerAddress;
    private String ticker;
    private Long supply;
    private Double cost;
    private Long maxMint;
    private String merkleRoot;
    private String baseUri;
    private String mockUri;
    private Whitelist whitelistType;


    private Boolean setReveal;
    private Boolean setCost;//done
    private Boolean setMaxSupply; //done
    private Boolean setWhitelist;
    private Boolean setMaxMint;//done
    private Boolean setBaseUri;//done

    private Boolean usePaymentSplitter;//optional
    private Boolean addSupportiveContracts;

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Long getSupply() {
        return supply;
    }

    public void setSupply(Long supply) {
        this.supply = supply;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Long getMaxMint() {
        return maxMint;
    }

    public void setMaxMint(Long maxMint) {
        this.maxMint = maxMint;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getMockUri() {
        return mockUri;
    }

    public void setMockUri(String mockUri) {
        this.mockUri = mockUri;
    }

    public Whitelist getWhitelistType() {
        return whitelistType;
    }

    public void setWhitelistType(Whitelist whitelistType) {
        this.whitelistType = whitelistType;
    }

    public Boolean getSetReveal() {
        return setReveal;
    }

    public void setSetReveal(Boolean setReveal) {
        this.setReveal = setReveal;
    }

    public Boolean getSetCost() {
        return setCost;
    }

    public void setSetCost(Boolean setCost) {
        this.setCost = setCost;
    }

    public Boolean getSetMaxSupply() {
        return setMaxSupply;
    }

    public void setSetMaxSupply(Boolean setMaxSupply) {
        this.setMaxSupply = setMaxSupply;
    }

    public Boolean getSetWhitelist() {
        return setWhitelist;
    }

    public void setSetWhitelist(Boolean setWhitelist) {
        this.setWhitelist = setWhitelist;
    }

    public Boolean getSetMaxMint() {
        return setMaxMint;
    }

    public void setSetMaxMint(Boolean setMaxMint) {
        this.setMaxMint = setMaxMint;
    }

    public Boolean getSetBaseUri() {
        return setBaseUri;
    }

    public void setSetBaseUri(Boolean setBaseUri) {
        this.setBaseUri = setBaseUri;
    }

    public Boolean getUsePaymentSplitter() {
        return usePaymentSplitter;
    }

    public void setUsePaymentSplitter(Boolean usePaymentSplitter) {
        this.usePaymentSplitter = usePaymentSplitter;
    }

    public Boolean getAddSupportiveContracts() {
        return addSupportiveContracts;
    }

    public void setAddSupportiveContracts(Boolean addSupportiveContracts) {
        this.addSupportiveContracts = addSupportiveContracts;
    }
}
