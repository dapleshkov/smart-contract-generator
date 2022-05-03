package com.pleshkov.smartcontractgenerator.service.impl;

import com.pleshkov.smartcontractgenerator.repo.ContractRepository;
import com.pleshkov.smartcontractgenerator.service.ContractSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ContractH2Saver implements ContractSaver {

    private final ContractRepository repository;

    @Override
    public void saveContract(String code, String id) {
        repository.save(code, id);
    }
}
