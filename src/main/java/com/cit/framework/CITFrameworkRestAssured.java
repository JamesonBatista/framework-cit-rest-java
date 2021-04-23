package com.cit.framework;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import util.Constantes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class CITFrameworkRestAssured {

    static String BODY = null;
    static String endpoint_Rest = "";
    static String PUT = null;
    public static Map<String, Object> PARAM = new HashMap<>();
    public static Map<String, Object> HEADERS = new HashMap<>();


    public void RestEnvironment(String Endpoint) throws IOException {
        RestAssured.reset();
        enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        baseURI = Constantes.selecionaAmbiente() + Endpoint;
        RestAssured.useRelaxedHTTPSValidation();
    }

    public void RestEnvironment() throws IOException {
        RestAssured.reset();
        enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        baseURI = Constantes.selecionaAmbiente();
        RestAssured.useRelaxedHTTPSValidation();
    }


    public ValidatableResponse Get() throws IOException {
        try {
            return given()
                    .urlEncodingEnabled(false).log().all()
                    .when()
                    .get()
                    .then()
                    .log().status().log().body().assertThat();

        } finally {

        }


    }

    public ValidatableResponse GetEndpoint(String Endpoint) throws IOException{
        endpoint_Rest = Endpoint;

        try {
            return given()
                    .urlEncodingEnabled(false).log().all()
                    .when()
                    .get(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();


        } finally {
        }


    }

    public ValidatableResponse GetParamHeaderEndpoint(String Endpoint) throws IOException {
        endpoint_Rest = Endpoint;

        try {
            return given()
                    .urlEncodingEnabled(false)
                    .log().all()
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .when()
                    .get(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();


        } finally {
        }


    }

    public ValidatableResponse GetParam() throws IOException{
        try {
            return given()
                    .urlEncodingEnabled(false)
                    .log().all()
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .when()
                    .get()
                    .then()
                    .log().status().log().body().assertThat();


        } finally {
        }


    }

    public ValidatableResponse GetParamEndpoint(String Endpoint) throws IOException {
        endpoint_Rest = Endpoint;
        try {
            return given().log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .when()
                    .get(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();


        } finally {
        }


    }

    public ValidatableResponse GetParamHeader() throws IOException{
        try {
            return given()
                    .urlEncodingEnabled(false)
                    .log().all()
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .when()
                    .get()
                    .then()
                    .log().status().log().body().assertThat();

        } finally {
        }


    }

    public ValidatableResponse GetHeader() throws IOException{
        try {
            return given()
                    .urlEncodingEnabled(false)
                    .log().all()
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .when()
                    .get()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse GetHeaderEndpoint(String Endpoint) throws IOException{
        endpoint_Rest = Endpoint;
        try {
            return given().log().all()
                    .urlEncodingEnabled(false)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .when()
                    .get(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse PostBody(String body) throws IOException {
        BODY = body;
        try {
            return given()
                    .urlEncodingEnabled(false).log().all()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse PostBodyEndpoint(String body, String Endpoint) throws IOException {
        endpoint_Rest = Endpoint;
        BODY = body;
        try {
            return given()
                    .urlEncodingEnabled(false).log().all()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();

        } finally {
        }


    }

    public ValidatableResponse PostParamHeaderBodyEndpoint(String body, String Endpoint) throws IOException{
        endpoint_Rest = Endpoint;
        BODY = body;
        try {
            return given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();

        } finally {
        }


    }

    public ValidatableResponse PostParamHeaderBody(String body) throws IOException {
        BODY = body;
        try {
            return given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse PostParamBodyEndpoint(String body, String Endpoint) throws IOException {
        endpoint_Rest = Endpoint;
        BODY = body;
        try {
            return given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse PostParamBody(String body) throws IOException {
        BODY = body;
        try {
            return given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .body(body)
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse PostHeaderBody(String body) throws IOException {
        BODY = body;
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse PostHeaderBodyEndpoint(String body, String Endpoint) throws IOException {
        endpoint_Rest = Endpoint;
        BODY = body;
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }


    public ValidatableResponse PutBody(String body) throws IOException {
        BODY = body;
        PUT = "put";
        try {
            return given()
                    .urlEncodingEnabled(false).log().all()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .put()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse PutBodyEndpoint(String body, String Endpoint) throws IOException{
        endpoint_Rest = Endpoint;
        BODY = body;
        PUT = "put";
        try {
            return given()
                    .urlEncodingEnabled(false).log().all()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse PutParamHeaderBodyEndpoint(String body, String Endpoint) throws IOException {
        endpoint_Rest = Endpoint;
        BODY = body;
        PUT = "put";
        try {
            return given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse PutParamHeaderBody(String body) throws IOException {
        BODY = body;
        PUT = "put";
        try {
            return given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .put()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse PutParamBodyEndpoint(String body, String Endpoint) throws IOException {
        endpoint_Rest = Endpoint;
        BODY = body;
        PUT = "put";
        try {
            return given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse PutParamBody(String body) throws IOException{
        BODY = body;
        PUT = "put";
        try {
            return given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .body(body)
                    .when()
                    .put()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse PutHeaderBody(String body) throws IOException{
        BODY = body;
        PUT = "put";
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .put()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse PutHeaderBodyEndpoint(String body, String Endpoint) throws IOException {
        endpoint_Rest = Endpoint;
        BODY = body;
        PUT = "put";
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse Delete() throws IOException {
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse DeleteEndpoint(String Endpoint) throws IOException {
        endpoint_Rest = Endpoint;
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse DeleteParam() throws IOException{
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse DeleteParamEndpoint(String Endpoint) throws IOException{
        endpoint_Rest = Endpoint;
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse DeleteParamHeader() throws IOException {
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse DeleteHeader() throws IOException{
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse DeleteHeaderEndpoint(String Endpoint) throws IOException {
        endpoint_Rest = Endpoint;
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }

    public ValidatableResponse DeleteParamHeaderEndpoint(String Endpoint) throws IOException{
        endpoint_Rest = Endpoint;
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
        }


    }


}


