package com.pleshkov.smartcontractgenerator.service.impl;

import com.pleshkov.smartcontractgenerator.repo.ContractRepository;
import com.pleshkov.smartcontractgenerator.service.ContractSaver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractH2Saver implements ContractSaver {

    private final ContractRepository repository;

    @Override
    public void saveContract(String code, String id) {
        log.info("Trying to save contract with ID {}", id);
        repository.save(code, id);
        log.debug("Contract saved successfully");
    }
}
