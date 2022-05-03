package com.pleshkov.smartcontractgenerator.service.impl;

import com.pleshkov.smartcontractgenerator.model.Contract;
import com.pleshkov.smartcontractgenerator.repo.ContractRepository;
import com.pleshkov.smartcontractgenerator.service.ContractLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ContractH2Loader implements ContractLoader {

    private final ContractRepository repository;

    @Override
    public Contract loadContract(String contractId) {
        return repository.select(contractId);
    }
}
