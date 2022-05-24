package com.pleshkov.smartcontractgenerator;

import com.pleshkov.smartcontractgenerator.model.ContractParams;
import com.pleshkov.smartcontractgenerator.service.ContractAppender;
import com.pleshkov.smartcontractgenerator.repo.LibraryLoader;
import com.pleshkov.smartcontractgenerator.service.util.CodeAppender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContractAppenderTest {

    private final CodeAppender codeAppender = new CodeAppender();
    private final LibraryLoader libraryLoader = new LibraryLoader();

    private ContractAppender appender = new ContractAppender(libraryLoader, codeAppender);

    @Test
    void testERC721Arguments() {
        ContractParams params = Mockito.mock(ContractParams.class);
        when(params.getCollectionName()).thenReturn("Unique name");
        when(params.getTicker()).thenReturn("UNCOL");

        String result = appender.createContract(params);

        assertTrue(result.contains("ERC721(\"Unique name\", \"UNCOL\")"));
    }

    @Test
    void testContractName() {
        ContractParams params = Mockito.mock(ContractParams.class);
        when(params.getContractName()).thenReturn("UniqueContract");

        String result = appender.createContract(params);

        assertTrue(result.contains("UniqueContract"));
    }


}
