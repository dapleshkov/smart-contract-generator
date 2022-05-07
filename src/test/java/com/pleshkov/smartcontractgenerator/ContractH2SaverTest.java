package com.pleshkov.smartcontractgenerator;

import com.pleshkov.smartcontractgenerator.model.Contract;
import com.pleshkov.smartcontractgenerator.repo.ContractRepository;
import com.pleshkov.smartcontractgenerator.service.builder.ContractFieldBuilder;
import com.pleshkov.smartcontractgenerator.service.impl.ContractH2Saver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContractH2SaverTest extends AbstractContractTest{

    @Autowired
    ContractH2Saver saver;

    @Autowired
    ContractRepository repository;

    @Test
    void testSaveContract(){
        final String id = "id";
        final String code = "contract code";

        saver.saveContract(code, "id");
        final String actual = repository.select(id);

        assertEquals(code, actual);
    }
}
