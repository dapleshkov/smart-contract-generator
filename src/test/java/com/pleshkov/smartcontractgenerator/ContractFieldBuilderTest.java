package com.pleshkov.smartcontractgenerator;

import com.pleshkov.smartcontractgenerator.service.builder.ContractFieldBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContractFieldBuilderTest {

    @Test
    void testFieldBuild(){
        ContractFieldBuilder builder = new ContractFieldBuilder("uint256", "cost", "10");

        String expected = "\tuint256 cost = 10;\n";
        String actual = builder.toString();

        assertEquals(expected, actual);
    }
}
