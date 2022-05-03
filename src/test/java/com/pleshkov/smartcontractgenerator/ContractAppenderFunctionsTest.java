package com.pleshkov.smartcontractgenerator;

import com.pleshkov.smartcontractgenerator.model.ContractParams;
import com.pleshkov.smartcontractgenerator.model.Whitelist;
import com.pleshkov.smartcontractgenerator.service.ContractAppender;
import com.pleshkov.smartcontractgenerator.service.GasTracker;
import com.pleshkov.smartcontractgenerator.service.LibraryLoader;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ContractAppenderFunctionsTest {

    @MockBean
    GasTracker gasTracker;
    @MockBean
    LibraryLoader libraryLoader;

    @InjectMocks
    private ContractAppender appender ;

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

        assertTrue(result.contains("setBlindUri"));
        assertTrue(result.contains("reveal"));
    }
}
