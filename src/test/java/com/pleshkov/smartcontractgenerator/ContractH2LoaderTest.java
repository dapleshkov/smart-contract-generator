package com.pleshkov.smartcontractgenerator;

import com.pleshkov.smartcontractgenerator.model.Contract;
import com.pleshkov.smartcontractgenerator.repo.ContractRepository;
import com.pleshkov.smartcontractgenerator.service.impl.ContractH2Loader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContractH2LoaderTest extends AbstractContractTest{

    @Autowired
    ContractH2Loader loader;

    @Autowired
    ContractRepository repository;

    @Test
    void testSaveContract(){
        final String id = "id";
        final String code = "contract code";

        repository.save(code, id);
        Contract contract = loader.loadContract(id);

        assertEquals(code, contract.getCode());
        assertEquals(id, contract.getId());
    }
}
