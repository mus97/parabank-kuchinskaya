package com.epam.parabank.api;

import com.epam.parabank.api.service.JaxbModelCreator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_BASE_ENDPOINT;
import static io.restassured.RestAssured.given;

public class RestBaseService {

    private final JaxbModelCreator jaxbModelCreator;

    public  RestBaseService() {
        jaxbModelCreator = new JaxbModelCreator();
    }

    protected RequestSpecification getSpecification() {
        return given()
                .baseUri(PARABANK_BASE_ENDPOINT.getPropertyName());
    }

    public <T> T createModel(Response response, Class<T> clazz) {
        return jaxbModelCreator.createJaxbModel(response.asString(), clazz);
    }
}
