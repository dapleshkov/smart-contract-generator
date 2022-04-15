package com.pleshkov.smartcontractgenerator.service.builder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContractFieldBuilder {

    private String type;
    private String name;
    private String value;

    @Override
    public String toString() {
        return new StringBuilder(type)
                .append(" ")
                .append(name)
                .append(" = ")
                .append(value)
                .append(";\n").toString();
    }
}
