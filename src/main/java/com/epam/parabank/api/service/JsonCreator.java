package com.epam.parabank.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.json.JSONObject;

public class JsonCreator {

    @SneakyThrows
    public JSONObject createJson(Object object){
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);
        return new JSONObject(json);
    }
}
