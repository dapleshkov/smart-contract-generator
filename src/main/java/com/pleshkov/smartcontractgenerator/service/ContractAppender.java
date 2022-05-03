package com.pleshkov.smartcontractgenerator.service;

import com.pleshkov.smartcontractgenerator.model.ContractParams;
import com.pleshkov.smartcontractgenerator.model.Whitelist;
import com.pleshkov.smartcontractgenerator.model.response.ContractResponse;
import com.pleshkov.smartcontractgenerator.model.solidity.ContractFunction;
import com.pleshkov.smartcontractgenerator.service.builder.ContractCoreBuilder;
import com.pleshkov.smartcontractgenerator.service.builder.ContractFieldBuilder;
import com.pleshkov.smartcontractgenerator.service.util.CodeAppender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractAppender {

    private final GasTracker gasTracker;
    private final LibraryLoader libraryLoader;
    private final CodeAppender codeAppender;

    public String createContract(ContractParams params) {
        StringBuilder contract = new StringBuilder();
        ContractCoreBuilder coreBuilder = ContractCoreBuilder.builder()
                .contractName(params.getContractName())
                .collectionName(params.getCollectionName())
                .ticker(params.getTicker()).build();

        contract.append(ContractCoreBuilder.PRAGMA);
        contract.append(coreBuilder.buildContractHeader());
        contract.append(ContractCoreBuilder.USING);

        contract.append(createFields(params));
        contract.append(createFunctions(params));


        contract.append("}\n");

        if(params.getAddSupportiveContracts()) {
            libraryLoader.getLibraryContracts().forEach((key, value) -> contract.append(value));
        }
        return contract.toString();
    }

    public String createFunctions(ContractParams params) {
        StringBuilder functions = new StringBuilder();

        functions.append(ContractFunction.PUBLIC_MINT);
        functions.append(String.format(ContractFunction.WITHDRAW_WITH_ADDRESS_PARAMETER, params.getOwnerAddress()));
        functions.append(ContractFunction.SET_MINT_ACTIVE);

        codeAppender.tryToAppend(
                params.getSetCost(),
                ContractFunction.SET_COST,
                functions
        );
        codeAppender.tryToAppend(
                params.getSetCost(),
                ContractFunction.SET_MAX_MINT,
                functions
        );
        codeAppender.tryToAppend(
                params.getSetCost(),
                ContractFunction.SET_SUPPLY,
                functions
        );
        codeAppender.tryToAppend(
                params.getSetCost(),
                ContractFunction.SET_OWNER_ADDRESS,
                functions
        );
        codeAppender.tryToAppend(
                params.getSetCost(),
                ContractFunction.SET_BASE_URI,
                functions
        );

        //add functions if premint is active
        if (params.getSetWhitelist()) {
            functions.append(ContractFunction.SET_PREMINT_ACTIVE);
            if (params.getWhitelistType() == Whitelist.ARRAY) {
                functions.append(ContractFunction.ADD_TO_WHITELIST);
                functions.append(ContractFunction.PRESALE_MINT_ARRAY);
            } else {
                functions.append(ContractFunction.VERIFY_MERKLE_PROOF);
                functions.append(ContractFunction.PRESALE_MINT_MERKLE);
                functions.append(ContractFunction.SET_MERKLE_ROOT);
            }
        }

        //add functions if reveal is active
        if (params.getSetReveal()) {
            functions.append(ContractFunction.SET_MOCK_URI);
            functions.append(ContractFunction.REVEAL);
            functions.append(ContractFunction.GET_TOKEN_URI_WITH_REVEAL);
        } else {
            functions.append(ContractFunction.GET_TOKEN_URI_NO_REVEAL);
        }
        functions.append(ContractFunction.STANDART_FUNCTIONS_TO_OVERRIDE);
        return functions.toString();
    }

    public String createFields(ContractParams params) {
        List<String> fields = new LinkedList<>();
        fields.add(new ContractFieldBuilder("string private", "uri", params.getBaseUri()).toString());
        fields.add(new ContractFieldBuilder("bool public", "isMintActive", String.valueOf(false)).toString());

        //if reveal
        fields.add(new ContractFieldBuilder("string private", "mockUri", params.getBaseUri()).toString());

        fields.add(new ContractFieldBuilder("uint256 public", "cost", params.getCost() + " ether").toString());
        fields.add(new ContractFieldBuilder("uint256 public", "supply", params.getSupply().toString()).toString());
        fields.add(new ContractFieldBuilder("address private", "ownerAddress", params.getOwnerAddress()).toString());
        fields.add(new ContractFieldBuilder("uint256 public", "maxMint", params.getMaxMint().toString()).toString());

        if (params.getSetWhitelist()) {
            if (params.getWhitelistType() == Whitelist.ARRAY) {
                fields.add("\tmapping(address => uint256) whitelist;\n");
            } else {
                fields.add(new ContractFieldBuilder("bytes32 public", "merkleRoot", params.getMerkleRoot()).toString());
            }
        }
        if(params.getSetWhitelist()){
            fields.add(new ContractFieldBuilder("bool public", "isPremintActive", String.valueOf(false)).toString());
        }
        if(params.getSetReveal()){
            fields.add(new ContractFieldBuilder("bool public", "reveal", String.valueOf(false)).toString());
        }
        fields.add("\n");

        return fields.stream().reduce((a, b) -> a + b).get();
    }
}
