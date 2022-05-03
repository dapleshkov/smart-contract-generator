package com.pleshkov.smartcontractgenerator.service;

import com.pleshkov.smartcontractgenerator.model.Contract;
import org.springframework.stereotype.Service;

public interface ContractLoader {

    Contract loadContract(String contractId);
}
