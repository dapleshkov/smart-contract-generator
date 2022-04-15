package com.pleshkov.smartcontractgenerator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedCaseInsensitiveMap;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class LibraryLoader {

    private static final String BASE_PATH = "src/main/resources/contracts/";

    private final Map<String, String> libraryContracts = new LinkedCaseInsensitiveMap<>();

    private final List<String> contracts = List.of("IERC165.sol", "IERC721.sol", "IERC721Receiver.sol",
            "IERC721Metadata.sol", "Address.sol", "Context.sol",
            "Strings.sol", "ERC165.sol", "ERC721.sol", "IERC721Enumerable.sol",
            "ERC721Enumerable.sol", "SafeMath.sol", "Ownable.sol");

    @PostConstruct
    public void loadContracts() {

        contracts.forEach( name -> {
            String pathName = BASE_PATH + name;
            Path path = Paths.get(pathName);
            try {
                libraryContracts.put(name, Files.readString(path));
            } catch (IOException e) {
                log.error("Error occurred during {} contract reading", pathName);
                e.printStackTrace();
            }
        });
    }
}
