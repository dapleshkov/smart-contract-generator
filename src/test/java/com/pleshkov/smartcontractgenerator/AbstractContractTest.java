package com.pleshkov.smartcontractgenerator;

import com.pleshkov.smartcontractgenerator.service.GasTracker;
import com.pleshkov.smartcontractgenerator.service.LibraryLoader;
import com.pleshkov.smartcontractgenerator.service.util.CodeAppender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Slf4j
@SpringBootTest
public abstract class AbstractContractTest {

    public static final String TRUNCATE_QUERY= "TRUNCATE TABLE CONTRACT";
    @Autowired
    protected GasTracker gasTracker;
    @Autowired
    protected LibraryLoader libraryLoader;
    @Autowired
    protected CodeAppender codeAppender;
    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @AfterEach
    void truncateDatabase(){
        log.info(">>> Truncating table...");
        JdbcTemplate template = namedParameterJdbcTemplate.getJdbcTemplate();
        template.execute(TRUNCATE_QUERY);
    }
}
