package com.pleshkov.smartcontractgenerator.service;

import com.pleshkov.smartcontractgenerator.model.ContractParams;
import com.pleshkov.smartcontractgenerator.model.response.ContractResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractCreator {
     private final ContractSaver saver;
     private final ContractAppender appender;

     public ContractResponse createContract(ContractParams params){
         String contractCode = appender.createContract(params);
         String contractId = UUID.randomUUID().toString();

         saver.saveContract(contractCode, contractId);

         return new ContractResponse(contractCode, contractId);
     }
}
