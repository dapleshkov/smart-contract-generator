package com.pleshkov.smartcontractgenerator.repo;

import com.pleshkov.smartcontractgenerator.model.Contract;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ContractRepository {

    private final NamedParameterJdbcTemplate template;
    public static final String SELECT_QUERY = "SELECT CONTRACT.CODE FROM CONTRACT WHERE id = :id";
    public static final String INSERT_QUERY = "INSERT into CONTRACT(id, code) VALUES ( :id, :code)";

    public String select(String contractId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", contractId);
        return template.queryForObject(SELECT_QUERY, parameters, String.class);
    }

    public void save(String code, String id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("code", code);
        parameters.put("id", id);
        template.update(INSERT_QUERY, parameters);
    }
}
