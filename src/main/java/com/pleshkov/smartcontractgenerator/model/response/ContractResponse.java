package com.pleshkov.smartcontractgenerator.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContractResponse {

    private String contractCode;
    private Double currentCost;
    private String contractId;
}
