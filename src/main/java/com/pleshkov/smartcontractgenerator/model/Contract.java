package com.pleshkov.smartcontractgenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Contract {

    public String id;
    public String code;

    public Contract(String id, String code) {
        this.id = id;
        this.code = code;
    }
}
