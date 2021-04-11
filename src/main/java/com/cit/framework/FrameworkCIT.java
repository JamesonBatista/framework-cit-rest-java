package com.cit.framework;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class FrameworkCIT {


    public Response REST_RESPONSE = null;
    public String BODY = null;
    public String GLOBAL_ENDPOINT = null;
    public Map<String, Object> REST_PARAM = new HashMap<>();
    public Map<String, Object> REST_HEADER = new HashMap<>();

    public void InitalEndpoint(String Endpoint){
        baseURI = Endpoint;
        GLOBAL_ENDPOINT = baseURI;
    }

    public ValidatableResponse Get() {
        return given().log().all().when().get().then();
    }
    public ValidatableResponse GetParamHeaderEndpoint(String Endpoint ){
        return given().log().all().queryParams(REST_PARAM).headers(REST_HEADER).when().get(Endpoint).then();
    }

}
