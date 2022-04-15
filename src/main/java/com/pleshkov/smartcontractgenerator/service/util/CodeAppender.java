package com.pleshkov.smartcontractgenerator.service.util;

import org.springframework.stereotype.Component;

@Component
public class CodeAppender {

    public void tryToAppend(Object field, String code, StringBuilder builder){
        if(field != null){
            builder.append(code);
        }
    }
}
