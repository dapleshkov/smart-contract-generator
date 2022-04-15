package com.pleshkov.smartcontractgenerator.service.builder;

import lombok.Builder;
import lombok.Data;

import java.text.MessageFormat;

@Data
@Builder
public class ContractCoreBuilder {

    private String contractName;
    private String collectionName;
    private String ticker;
    private String uri;

    public static final String PRAGMA = "pragma solidity 0.8.7;\n\n";
    public static final String USING = "using Strings for uint256;\n" +
                                        "using SafeMath for uint256;\n\n";

    private static final String CONTRACT_HEADER = "contract %s is ERC721(\"%s\", \"%s\"), ERC721Enumerable, Ownable {\n";

    public String buildContractHeader(){
        return String.format(CONTRACT_HEADER, contractName, collectionName, ticker);
    }
}

