package com.pleshkov.smartcontractgenerator.controller;

import com.pleshkov.smartcontractgenerator.model.Contract;
import com.pleshkov.smartcontractgenerator.model.ContractParams;
import com.pleshkov.smartcontractgenerator.model.Whitelist;
import com.pleshkov.smartcontractgenerator.model.response.ContractResponse;
import com.pleshkov.smartcontractgenerator.service.ContractAppender;
import com.pleshkov.smartcontractgenerator.service.ContractCreator;
import com.pleshkov.smartcontractgenerator.service.ContractLoader;
import com.pleshkov.smartcontractgenerator.service.ContractSaver;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("api/v1/")
@RequiredArgsConstructor
public class ContractController {

    private final ContractCreator creator;
    private final ContractLoader loader;

    @GetMapping("/contract")
    @ApiOperation("Returns generated contract by ID")
    public ResponseEntity<Contract> getContract(String id) {
        Contract contract = loader.loadContract(id);
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }


    @PostMapping("/contract")
    @ApiOperation("Returns generated contract based on specified parameters")
    public ResponseEntity<ContractResponse> getContract(
            @ApiParam(value = "Set contract name in code of smart contract")
            @RequestParam(name = "contract name") String contractName,
            @ApiParam(value = "Set collection name", example = "The best collection")
            @RequestParam(name = "collection name") String collectionName,
            @ApiParam(value = "Set Ethereum network address to withdraw Ether from contract", example = "0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266")
            @RequestParam(name = "owner address") String ownerAddress,
            @ApiParam(value = "Set the ticker of collection", example = "TBC")
            @RequestParam(name = "collection ticker") String ticker,
            @ApiParam(value = "Set the maximum amount of tokens in collection", example = "7777")
            @RequestParam(name = "total supply") Long supply,
            @ApiParam(value = "Set cost of collection token in Ether", example = "0.5")
            @RequestParam(name = "token cost") Double cost,
            @ApiParam(value = "Set the amount of tokens which can be minted in one transaction during public mint", example = "3")
            @RequestParam(name = "maximum mint amount", required = true) Long maxMint,
            @ApiParam(value = "Set the root of merkle with whitelisted addresses, required if Merkle whitelist chosen", example = "0xb2ace09ed57c757479aaafd383d1e396d15e283c208f406955c389d6ae1ee216")
            @RequestParam(name = "Merkle root", required = false) String merkleRoot,
            @ApiParam(value = "Set the URI of tokens metadata, will be used only after reveal", example = "http://www.collection.com/metadata")
            @RequestParam(name = "base URI", required = true) String baseUri,
            @ApiParam(value = "Set the URI of metadata which will be used before reveal", example = "http://www.collection.com/mockdata")
            @RequestParam(name = "mock URI", required = false) String mockUri,
            @ApiParam(value = "The method with will be used for authorization during premint")
            @RequestParam(name = "type of whitelist", required = false) Whitelist whitelistType,
            @ApiParam(value = "If true contract will contain functions for delayed reveal")
            @RequestParam(name = "Add delayed reveal", required = true, defaultValue = "true") Boolean setReveal,
            @ApiParam(value = "If true contract will contain function for setting the cost")
            @RequestParam(name = "Add function for setting cost", required = true, defaultValue = "true") Boolean setCost,
            @ApiParam(value = "TIf true contract will contain function for setting the total supply")
            @RequestParam(name = "Add function for setting total supply", required = true, defaultValue = "true") Boolean setMaxSupply,
            @ApiParam(value = "If true contract will contain functions for premint, type of whitelist should be chosen too")
            @RequestParam(name = "Add premint", required = true, defaultValue = "true") Boolean setWhitelist,
            @ApiParam(value = "If true contract will contain function for setting maximum mint amount per transaction")
            @RequestParam(name = "Add function for setting maximum mint amount", required = true, defaultValue = "true") Boolean setMaxMint,
            @ApiParam(value = "If true contract will contain function for setting the base URI")
            @RequestParam(name = "Add function for setting base URI", required = true, defaultValue = "true") boolean setBaseUri,
            @ApiParam(value = "If true response will contain all used libraries, contracts and interfaces")
            @RequestParam(name = "Add supportive contracts", required = true, defaultValue = "true") Boolean addSupportiveContracts
    ) {
        ContractParams parameters = ContractParams.builder()
                .contractName(contractName)
                .addSupportiveContracts(addSupportiveContracts)
                .setBaseUri(setBaseUri)
                .collectionName(collectionName)
                .cost(cost)
                .maxMint(maxMint)
                .merkleRoot(merkleRoot)
                .mockUri(mockUri)
                .setMaxSupply(setMaxSupply)
                .setReveal(setReveal)
                .ownerAddress(ownerAddress)
                .supply(supply)
                .ticker(ticker)
                .baseUri(baseUri)
                .whitelistType(whitelistType)
                .setCost(setCost)
                .setWhitelist(setWhitelist)
                .setMaxMint(setMaxMint)
                .build();
        ContractResponse response = creator.createContract(parameters);
        log.info(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
