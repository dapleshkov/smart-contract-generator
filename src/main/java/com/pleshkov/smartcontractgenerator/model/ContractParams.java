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

    private Boolean isPremintActive;

    private Boolean setCost;//done
    private Boolean setMaxSupply; //done
    private Boolean setWhitelist;
    private Boolean setReveal;
    private Boolean setMaxMint;//done
    private Boolean setBaseUri;//done

    private Boolean usePaymentSplitter;//optional
    private Boolean addSupportiveContracts;
}
