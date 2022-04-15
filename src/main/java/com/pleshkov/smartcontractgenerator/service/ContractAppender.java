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

    public ContractResponse createContract(ContractParams params) {
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


        contract.append("}");
        return new ContractResponse(contract.toString(), gasTracker.getGas());
    }

    private String createFunctions(ContractParams params) {
        StringBuilder functions = new StringBuilder();

        functions.append(ContractFunction.PUBLIC_MINT);
        functions.append(String.format(ContractFunction.WITHDRAW_WITH_ADDRESS_PARAMETER, params.getOwnerAddress()));
        functions.append(ContractFunction.SET_COST);
        functions.append(ContractFunction.SET_MAX_MINT);
        functions.append(ContractFunction.SET_SUPPLY);
        functions.append(ContractFunction.SET_OWNER_ADDRESS);
        functions.append(ContractFunction.SET_BASE_URI);
        functions.append(ContractFunction.SET_COST);
        functions.append(ContractFunction.SET_MINT_ACTIVE);

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
            functions.append(ContractFunction.SET_BLIND_URI);
            functions.append(ContractFunction.REVEAL);
            functions.append(ContractFunction.GET_TOKEN_URI_WITH_REVEAL);
        } else {
            functions.append(ContractFunction.GET_TOKEN_URI_NO_REVEAL);
        }
        return functions.toString();
    }

    private String createFields(ContractParams params) {
        List<String> fields = new LinkedList<>();
        fields.add(new ContractFieldBuilder("string private", "uri", params.getBaseUri()).toString());
        fields.add(new ContractFieldBuilder("bool public", "isMintActive", String.valueOf(false)).toString());
        fields.add(new ContractFieldBuilder("bool public", "isPremintActive", String.valueOf(false)).toString());

        //if reveal
        fields.add(new ContractFieldBuilder("string private", "mockUri", params.getBaseUri()).toString());

        fields.add(new ContractFieldBuilder("uint256 public", "cost", params.getCost() + " ether").toString());
        fields.add(new ContractFieldBuilder("uint256 public", "supply", params.getSupply().toString()).toString());
        fields.add(new ContractFieldBuilder("address private", "ownerAddress", params.getOwnerAddress()).toString());
        fields.add(new ContractFieldBuilder("uint256 public", "maxMint", params.getMaxMint().toString()).toString());

        if (params.getSetWhitelist()) {
            if (params.getWhitelistType() == Whitelist.ARRAY) {
                fields.add("mapping(address => uint256) whitelist;\n");
            } else {
                fields.add(new ContractFieldBuilder("bytes32 public", "merkleRoot", params.getMerkleRoot()).toString());
            }
        }

        return fields.stream().reduce((a, b) -> a + b).get();
    }
}
