package com.pleshkov.smartcontractgenerator.controller;

import com.pleshkov.smartcontractgenerator.model.Contract;
import com.pleshkov.smartcontractgenerator.model.ContractParams;
import com.pleshkov.smartcontractgenerator.model.response.ContractResponse;
import com.pleshkov.smartcontractgenerator.service.ContractAppender;
import com.pleshkov.smartcontractgenerator.service.ContractCreator;
import com.pleshkov.smartcontractgenerator.service.ContractLoader;
import com.pleshkov.smartcontractgenerator.service.ContractSaver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("api/v1/")
@RequiredArgsConstructor
public class ContractController {

    private final ContractCreator creator;
    private final ContractLoader loader;
    private final ContractSaver saver;

    @GetMapping("/gett")
    public ResponseEntity<Contract> getContract(String id){
        Contract contract = loader.loadContract(id);
        return new ResponseEntity<>( contract, HttpStatus.OK);
    }


    @GetMapping("new")
    public void saveContract(String id, String code){
        saver.saveContract(code, id);
    }

    @PostMapping("/contract")
    public ResponseEntity<ContractResponse> getContract(ContractParams params){

//        log.info("trying to create contract with parameters: " + params);
        ContractResponse a = creator.createContract(params);
        log.info(a.toString());
        return new ResponseEntity<>( a, HttpStatus.OK);
    }

    @GetMapping("contract2")
    public String getContract2(){
        throw new RuntimeException("asdf");
//        return "\nabc\n";
    }
}
