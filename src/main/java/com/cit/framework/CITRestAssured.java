package com.cit.framework;

import com.bradesco.core.exception.BradescoException;
import com.bradesco.core.report.BradescoReporter;
import com.bradesco.core.report.model.Event;
import com.bradesco.core.report.model.HttpRequestEvent;
import com.bradesco.core.sdk.enums.ReportStatus;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import util.Constantes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.*;
import static util.FileProperties.GetProp;


public class CITRestAssured {

    static ValidatableResponse result;
    static Response response;

    static String BODY = null;
    static String endpoint_Rest = "";
    static String PUT = null;
    static String DELETE = null;
    public static Map<String, Object> PARAM = new HashMap<>();
    public static Map<String, Object> HEADERS = new HashMap<>();


    public void RestEnvironment(String Endpoint) throws IOException {
        try {
            RestAssured.reset();
            enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
            baseURI = Constantes.selecionaAmbiente() + Endpoint;
            RestAssured.useRelaxedHTTPSValidation();
        } finally {


        }

    }

    public void RestEnvironment() throws IOException {
        try {
            RestAssured.reset();
            enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
            baseURI = Constantes.selecionaAmbiente();
            RestAssured.useRelaxedHTTPSValidation();
        } finally {

        }

    }


    public ValidatableResponse Get() throws IOException, BradescoException {
        try {
            result = given()
                    .urlEncodingEnabled(false).log().all()
                    .when()
                    .get()
                    .then()
                    .log().status().log().body().assertThat();

            response = result.extract().response();
            return result;

        } finally {
            ReportBradescoGet();
        }
    }

    public ValidatableResponse GetEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;

        try {
            result = given()
                    .urlEncodingEnabled(false).log().all()
                    .when()
                    .get(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;

        } finally {
            ReportBradescoGet();

        }


    }

    public ValidatableResponse GetParamHeaderEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;

        try {
            result = given()
                    .urlEncodingEnabled(false)
                    .log().all()
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .when()
                    .get(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;

        } finally {
            ReportBradescoGet();
        }


    }

    public ValidatableResponse GetParam() throws IOException, BradescoException {
        try {
            result = given()
                    .urlEncodingEnabled(false)
                    .log().all()
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .when()
                    .get()
                    .then()
                    .log().status().log().body().assertThat();

            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoGet();
        }


    }

    public ValidatableResponse GetParamEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        try {
            result = given().log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .when()
                    .get(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;

        } finally {
            ReportBradescoGet();
        }


    }

    public ValidatableResponse GetParamHeader() throws IOException, BradescoException {
        try {
            result = given()
                    .urlEncodingEnabled(false)
                    .log().all()
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .when()
                    .get()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoGet();

        }


    }

    public ValidatableResponse GetHeader() throws IOException, BradescoException {
        try {
            result = given()
                    .urlEncodingEnabled(false)
                    .log().all()
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .when()
                    .get()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoGet();

        }


    }

    public ValidatableResponse GetHeaderEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        try {
            result = given().log().all()
                    .urlEncodingEnabled(false)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .when()
                    .get(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoGet();

        }


    }

    public ValidatableResponse Post() throws IOException, BradescoException {
        BODY = "{}";
        try {
            result = given()
                    .urlEncodingEnabled(false).log().all()
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;

        } finally {
            ReportBradescoPost();

        }
    }

    public ValidatableResponse PostBody(String body) throws IOException, BradescoException {
        BODY = body;
        try {
            result = given()
                    .urlEncodingEnabled(false).log().all()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoPost();
        }


    }

    public ValidatableResponse PostBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        BODY = body;
        try {
            result = given()
                    .urlEncodingEnabled(false).log().all()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;

        } finally {
            ReportBradescoPost();

        }


    }

    public ValidatableResponse PostParamHeaderBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        BODY = body;
        try {
            result = given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;

        } finally {
            ReportBradescoPost();

        }
    }

    public ValidatableResponse PostParamHeaderBody(String body) throws IOException, BradescoException {
        BODY = body;
        try {
            result = given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoPost();

        }
    }

    public ValidatableResponse PostParamBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        BODY = body;
        try {
            result = given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoPost();

        }
    }

    public ValidatableResponse PostParamBody(String body) throws IOException, BradescoException {
        BODY = body;
        try {
            result = given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .body(body)
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoPost();

        }
    }

    public ValidatableResponse PostHeaderBody(String body) throws IOException, BradescoException {
        BODY = body;
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoPost();

        }
    }

    public ValidatableResponse PostHeaderBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        BODY = body;
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoPost();

        }
    }


    public ValidatableResponse PutBody(String body) throws IOException, BradescoException {
        BODY = body;
        PUT = "put";
        try {
            result = given()
                    .urlEncodingEnabled(false).log().all()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .put()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoPut();
        }
    }

    public ValidatableResponse PutBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        BODY = body;
        PUT = "put";
        try {
            result = given()
                    .urlEncodingEnabled(false).log().all()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoPut();

        }
    }

    public ValidatableResponse PutParamHeaderBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        BODY = body;
        PUT = "put";
        try {
            result = given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoPut();

        }
    }

    public ValidatableResponse PutParamHeaderBody(String body) throws IOException, BradescoException {
        BODY = body;
        PUT = "put";
        try {
            result = given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .put()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoPut();

        }
    }

    public ValidatableResponse PutParamBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        BODY = body;
        PUT = "put";
        try {
            result = given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoPut();

        }
    }

    public ValidatableResponse PutParamBody(String body) throws IOException, BradescoException {
        BODY = body;
        PUT = "put";
        try {
            result = given()
                    .contentType(ContentType.JSON).log().all()
                    .urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .body(body)
                    .when()
                    .put()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoPut();

        }
    }

    public ValidatableResponse PutHeaderBody(String body) throws IOException, BradescoException {
        BODY = body;
        PUT = "put";
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .put()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoPut();

        }
    }

    public ValidatableResponse PutHeaderBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        BODY = body;
        PUT = "put";
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoPut();

        }
    }

    public ValidatableResponse Delete() throws IOException, BradescoException {
        DELETE = "delete";
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoDelete();
        }
    }

    public ValidatableResponse DeleteEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        DELETE = "delete";
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoDelete();

        }
    }

    public ValidatableResponse DeleteParam() throws IOException, BradescoException {
        DELETE = "delete";
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoDelete();

        }
    }

    public ValidatableResponse DeleteParamEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        DELETE = "delete";
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoDelete();

        }
    }

    public ValidatableResponse DeleteParamHeader() throws IOException, BradescoException {
        DELETE = "delete";
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoDelete();

        }
    }

    public ValidatableResponse DeleteHeader() throws IOException, BradescoException {
        DELETE = "delete";
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoDelete();

        }
    }

    public ValidatableResponse DeleteHeaderEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        DELETE = "delete";
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoDelete();

        }
    }

    public ValidatableResponse DeleteParamHeaderEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        DELETE = "delete";
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradescoDelete();

        }
    }

    static void ReportBradescoGet() throws BradescoException, IOException {
        System.out.println("----------------------------------------\n     Iniciando Report CI&T Bradesco GET\n----------------------------------------");
        BradescoReporter.report(ReportStatus.PASSED, "GET executado, abaixo evidências:");
        BradescoReporter.reportEvent(HttpRequestEvent.getRequest(endpoint_Rest == "" ? baseURI : baseURI + endpoint_Rest, response.asString()));
        Finish();
    }


    static void ReportBradescoPost() throws BradescoException, IOException {
        System.out.println("----------------------------------------\n     Iniciando Report CI&T Bradesco POST\n----------------------------------------");
        BradescoReporter.report(ReportStatus.PASSED, "POST executado, abaixo evidências:");
        BradescoReporter.reportEvent(HttpRequestEvent.postRequest(endpoint_Rest == "" ? baseURI : baseURI + endpoint_Rest, BODY, response.asString()));
        Finish();
    }

    static void ReportBradescoPut() throws BradescoException, IOException {
        System.out.println("----------------------------------------\n     Iniciando Report CI&T Bradesco PUT\n----------------------------------------");
        BradescoReporter.report(ReportStatus.PASSED, "PUT executado, abaixo evidências:");
        BradescoReporter.reportEvent(PutRequest(endpoint_Rest == "" ? baseURI : baseURI + endpoint_Rest, BODY, response.asString()));
        Finish();
    }

    static void ReportBradescoDelete() throws BradescoException, IOException {
        System.out.println("----------------------------------------\n     Iniciando Report CI&T Bradesco DELETE\n----------------------------------------");
        BradescoReporter.report(ReportStatus.PASSED, "DELETE executado. Não há evidências JSON, apenas Status OK.");
        BradescoReporter.reportEvent(DeleteRequest(endpoint_Rest == "" ? baseURI : baseURI + endpoint_Rest));
        Finish();
    }

    static void Finish() throws BradescoException, IOException {

        System.out.println("Report Bradesco gerado no path: *** " + GetProp().getProperty("excludReport") + " ***");

        PARAM.clear();
        HEADERS.clear();
        result = null;
        response = null;
        BODY = null;
        PUT = null;
        DELETE = null;
    }


    static Event PutRequest(String url, String bodyAsString, String response) {
        return new HttpRequestEvent(ReportStatus.OK, "PUT", url, Optional.of(bodyAsString), response);
    }

    static Event DeleteRequest(String url) {
        return new HttpRequestEvent(ReportStatus.OK, "DELETE", url, Optional.empty(), "Status: " + HttpStatus.SC_OK);
    }

}


