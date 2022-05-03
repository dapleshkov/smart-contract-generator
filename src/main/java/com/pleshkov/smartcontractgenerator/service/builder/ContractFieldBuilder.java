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
        boolean isString = type.startsWith("string") || type.startsWith("bytes32");
        StringBuilder builder = new StringBuilder("\t")
                .append(type)
                .append(" ")
                .append(name)
                .append(" = ");

        if (isString) {
            builder.append("\"");
        }

        builder.append(value);

        if (isString) {
            builder.append("\"");
        }

        builder.append(";\n");
        return builder.toString();
    }
}
