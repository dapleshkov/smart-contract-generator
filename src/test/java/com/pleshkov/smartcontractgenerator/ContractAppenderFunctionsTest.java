package com.pleshkov.smartcontractgenerator;

import com.pleshkov.smartcontractgenerator.model.ContractParams;
import com.pleshkov.smartcontractgenerator.model.Whitelist;
import com.pleshkov.smartcontractgenerator.service.ContractAppender;
import com.pleshkov.smartcontractgenerator.repo.LibraryLoader;
import com.pleshkov.smartcontractgenerator.service.util.CodeAppender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContractAppenderFunctionsTest {

    private final LibraryLoader libraryLoader = new LibraryLoader();

    private final CodeAppender codeAppender = new CodeAppender();

    private ContractAppender appender = new ContractAppender(libraryLoader, codeAppender);

    @Test
    void testPremintFunctions_WhenPremintMerkleActive(){
        ContractParams params = Mockito.mock(ContractParams.class);
        when(params.getWhitelistType()).thenReturn(Whitelist.MERKLE);
        when(params.getSetWhitelist()).thenReturn(true);

        String result = appender.createFunctions(params);

        assertTrue(result.contains("setRoot"));
        assertTrue(result.contains("mintNFTDuringPresale"));
        assertTrue(result.contains("verifyMerkleProof"));
    }


    @Test
    void testPremintFunctions_WhenPremintArrayActive(){
        ContractParams params = Mockito.mock(ContractParams.class);
        when(params.getWhitelistType()).thenReturn(Whitelist.ARRAY);
        when(params.getSetWhitelist()).thenReturn(true);

        String result = appender.createFunctions(params);

        assertTrue(result.contains("mintNFTDuringPresale"));
        assertTrue(result.contains("addToWhitelist"));
    }


    @Test
    void testRevealFunctions_WhenRevealActive(){
        ContractParams params = Mockito.mock(ContractParams.class);
        when(params.getSetReveal()).thenReturn(true);

        String result = appender.createFunctions(params);

        assertTrue(result.contains("setMockUri"));
        assertTrue(result.contains("reveal"));
    }

    @Test
    void testRequiredFunctions(){
        ContractParams params = Mockito.mock(ContractParams.class);

        String result = appender.createFunctions(params);

        assertTrue(result.contains("publicMint"));
        assertTrue(result.contains("withdraw"));
        assertTrue(result.contains("setMintActive"));
    }
}
