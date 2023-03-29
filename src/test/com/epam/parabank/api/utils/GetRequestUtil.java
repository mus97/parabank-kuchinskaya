package com.epam.parabank.api.utils;

import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class GetRequestUtil {

    public static Response makeRequestAndGetResponse(String endpoint) {
        return given()
                .get(endpoint);
    }

    public static Response makePostRequest(String endpoint) {
        return given()
                .post(endpoint);
    }

    public static Response makePostWithBodyRequest(String endpoint, JSONObject body) {
        return given()
                .contentType("application/json")
                .body(body.toString())
                .post(endpoint);
    }
}
