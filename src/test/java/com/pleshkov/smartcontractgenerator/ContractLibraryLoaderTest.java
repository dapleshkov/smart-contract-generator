package com.pleshkov.smartcontractgenerator;

import com.pleshkov.smartcontractgenerator.model.ContractParams;
import com.pleshkov.smartcontractgenerator.service.ContractAppender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ContractLibraryLoaderTest extends AbstractContractTest{

    @Autowired
    ContractAppender appender;

    @Test
    void testSupportiveContracts() {
        ContractParams params = Mockito.mock(ContractParams.class);
        when(params.getAddSupportiveContracts()).thenReturn(true);

        String result = appender.createContract(params);

        assertTrue(result.contains("contract Ownable"));
    }
}
