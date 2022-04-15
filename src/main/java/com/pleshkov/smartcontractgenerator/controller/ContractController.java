package com.pleshkov.smartcontractgenerator.controller;

import com.pleshkov.smartcontractgenerator.model.ContractParams;
import com.pleshkov.smartcontractgenerator.model.response.ContractResponse;
import com.pleshkov.smartcontractgenerator.service.ContractAppender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("api/v1/")
@RequiredArgsConstructor
public class ContractController {

    private final ContractAppender appender;

    @GetMapping("contract")
    public ResponseEntity<ContractResponse> getContract(ContractParams params){

//        log.info("trying to create contract with parameters: " + params);
        var a = appender.createContract(params);
        log.info(a.toString());
        return new ResponseEntity<>( a, HttpStatus.OK);
    }

    @GetMapping("contract2")
    public String getContract2(){
        return "\nabc\n";
    }
}
