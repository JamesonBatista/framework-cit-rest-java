package com.cit.framework;

import com.bradesco.core.exception.BradescoRuntimeException;
import com.bradesco.core.sdk.enums.ReportStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import com.bradesco.core.exception.BradescoException;
import com.bradesco.core.report.BradescoReporter;
import com.bradesco.core.report.model.HttpRequestEvent;
import cucumber.api.Scenario;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class CITFrameworkRestAssured {
    public static String MENSAGEM_REPORT_ERROR = "";


    public static Response RESPONSE = null;
    public static String BODY = null;
    public static String ENDPOINT = null;
    public static Map<String, Object> PARAM = new HashMap<>();
    public static Map<String, Object> HEADER = new HashMap<>();

    public void InitalEndpoint(String Endpoint) {
        enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = Endpoint;
        ENDPOINT = baseURI;
        RestAssured.useRelaxedHTTPSValidation();
    }

    public ValidatableResponse Get() {
        return given()
                .urlEncodingEnabled(false).log().all()
                .when()
                .get()
                .then();
    }

    public ValidatableResponse GetEndpoint(String Endpoint) {
        return given()
                .urlEncodingEnabled(false).log().all()
                .when()
                .get(Endpoint)
                .then();
    }

    public ValidatableResponse GetParam() {
        return given()
                .urlEncodingEnabled(false)
                .log().all()
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .when()
                .get()
                .then();

    }

    public ValidatableResponse GetParamHeaderEndpoint(String Endpoint) {
        return given()
                .urlEncodingEnabled(false)
                .log().all()
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .headers(HEADER.toString() == "{}" ? null : HEADER)
                .when()
                .get(Endpoint)
                .then();
    }

    public ValidatableResponse GetParamHeader() {
        return given()
                .urlEncodingEnabled(false)
                .log().all()
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .headers(HEADER.toString() == "{}" ? null : HEADER)
                .when()
                .get()
                .then();
    }

    public ValidatableResponse GetHeader() {
        return given()
                .urlEncodingEnabled(false)
                .log().all()
                .headers(HEADER.toString() == "{}" ? null : HEADER)
                .when()
                .get()
                .then();
    }

    public ValidatableResponse GetParamEndpoint(String Endpoint) {
        return given().log().all()
                .urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .when()
                .get(Endpoint)
                .then();
    }

    public ValidatableResponse GetHeaderEndpoint(String Endpoint) {
        return given().log().all()
                .urlEncodingEnabled(false)
                .headers(HEADER.toString() == "{}" ? null : HEADER)
                .when()
                .get(Endpoint)
                .then();
    }

    public ValidatableResponse PostBody(String body) {
        return given()
                .urlEncodingEnabled(false).log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post()
                .then();
    }

    public ValidatableResponse PostBodyEndpoint(String body, String Endpoint) {
        return given()
                .urlEncodingEnabled(false).log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(Endpoint)
                .then();
    }

    public ValidatableResponse PostParamHeaderBodyEndpoint(String body, String Endpoint) {
        return given()
                .contentType(ContentType.JSON).log().all()
                .urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .headers(HEADER.toString() == "{}" ? null : HEADER)
                .body(body)
                .when()
                .post(Endpoint)
                .then();
    }

    public ValidatableResponse PostParamHeaderBody(String body) {
        return given()
                .contentType(ContentType.JSON).log().all()
                .urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .headers(HEADER.toString() == "{}" ? null : HEADER)
                .body(body)
                .when()
                .post()
                .then();
    }

    public ValidatableResponse PostParamBodyEndpoint(String body, String Endpoint) {
        return given()
                .contentType(ContentType.JSON).log().all()
                .urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .body(body)
                .when()
                .post(Endpoint)
                .then();
    }

    public ValidatableResponse PostParamBody(String body) {
        return given()
                .contentType(ContentType.JSON).log().all()
                .urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .body(body)
                .when()
                .post()
                .then();
    }

    public ValidatableResponse PostHeaderBody(String body) {
        return given()
                .log().all().urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .headers(HEADER.toString() == "{}" ? null : HEADER)
                .body(body)
                .when()
                .post()
                .then();
    }

    public ValidatableResponse PostHeaderBodyEndpoint(String body, String Endpoint) {
        return given()
                .log().all().urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .headers(HEADER.toString() == "{}" ? null : HEADER)
                .body(body)
                .when()
                .post(Endpoint)
                .then();
    }


    public void AfterScenarioStartReport(Scenario scenario) throws BradescoException {

        System.out.println("-------------------------------------\n Iniciando Report Bradesco..." + scenario.getStatus().toUpperCase() + "\n" + scenario.getName().toUpperCase() + "   \n-------------------------------------");

        if (scenario.isFailed()) {

            if (MENSAGEM_REPORT_ERROR != "") {
                BradescoReporter.report(ReportStatus.ERROR, MENSAGEM_REPORT_ERROR);
            }
        } else {
            if (BODY == null) {
                BradescoReporter.reportEvent(HttpRequestEvent.getRequest(ENDPOINT, RESPONSE.getBody().asString()));
            } else {
                BradescoReporter.reportEvent(HttpRequestEvent.postRequest(ENDPOINT, BODY, RESPONSE.getBody().asString()));
            }
        }
        PARAM.clear();
        HEADER.clear();
        BODY = null;
        RESPONSE = null;
        ENDPOINT = null;
        RestAssured.reset();
    }
}
