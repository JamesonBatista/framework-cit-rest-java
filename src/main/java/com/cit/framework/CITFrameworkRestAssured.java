package com.cit.framework;

import com.bradesco.core.exception.BradescoException;
import com.bradesco.core.report.BradescoReporter;
import com.bradesco.core.report.model.HttpRequestEvent;
import com.bradesco.core.sdk.enums.ReportStatus;
import cucumber.api.Scenario;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import util.Constantes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static util.FileProperties.GetProp;


public class CITFrameworkRestAssured {

    public static String MENSAGEM_REPORT_ERROR = "";
    public static String BODY = null;
    public static String ENDPOINT = null;
    static String endpoint_Rest = "";
    static String PUT = null;
    public static Map<String, Object> PARAM = new HashMap<>();
    public static Map<String, Object> HEADERS = new HashMap<>();


    public void InitialEndpoint(String Endpoint) throws IOException {
        ExcludReportBradesco();
        enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        baseURI = Constantes.selecionaAmbiente() + Endpoint;
        ENDPOINT = baseURI;
        RestAssured.useRelaxedHTTPSValidation();
    }

    public ValidatableResponse Get() {
        return given()
                .urlEncodingEnabled(false).log().all()
                .when()
                .get()
                .then()
                .log().status().log().body().assertThat();

    }

    public ValidatableResponse GetEndpoint(String Endpoint) {
        endpoint_Rest = Endpoint;
        return given()
                .urlEncodingEnabled(false).log().all()
                .when()
                .get(Endpoint)
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse GetParamHeaderEndpoint(String Endpoint) {
        endpoint_Rest = Endpoint;
        return given()
                .urlEncodingEnabled(false)
                .log().all()
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                .when()
                .get(Endpoint)
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse GetParam() {
        return given()
                .urlEncodingEnabled(false)
                .log().all()
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .when()
                .get()
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse GetParamEndpoint(String Endpoint) {
        endpoint_Rest = Endpoint;

        return given().log().all()
                .urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .when()
                .get(Endpoint)
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse GetParamHeader() {
        return given()
                .urlEncodingEnabled(false)
                .log().all()
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                .when()
                .get()
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse GetHeader() {
        return given()
                .urlEncodingEnabled(false)
                .log().all()
                .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                .when()
                .get()
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse GetHeaderEndpoint(String Endpoint) {
        endpoint_Rest = Endpoint;

        return given().log().all()
                .urlEncodingEnabled(false)
                .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                .when()
                .get(Endpoint)
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse PostBody(String body) {
        BODY = body;
        return given()
                .urlEncodingEnabled(false).log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post()
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse PostBodyEndpoint(String body, String Endpoint) {
        endpoint_Rest = Endpoint;
        BODY = body;

        return given()
                .urlEncodingEnabled(false).log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(Endpoint)
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse PostParamHeaderBodyEndpoint(String body, String Endpoint) {
        endpoint_Rest = Endpoint;
        BODY = body;
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


    }

    public ValidatableResponse PostParamHeaderBody(String body) {
        BODY = body;
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


    }

    public ValidatableResponse PostParamBodyEndpoint(String body, String Endpoint) {
        endpoint_Rest = Endpoint;
        BODY = body;
        return given()
                .contentType(ContentType.JSON).log().all()
                .urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .body(body)
                .when()
                .post(Endpoint)
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse PostParamBody(String body) {
        BODY = body;
        return given()
                .contentType(ContentType.JSON).log().all()
                .urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .body(body)
                .when()
                .post()
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse PostHeaderBody(String body) {
        BODY = body;
        return given()
                .log().all().urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                .body(body)
                .when()
                .post()
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse PostHeaderBodyEndpoint(String body, String Endpoint) {
        endpoint_Rest = Endpoint;
        BODY = body;
        return given()
                .log().all().urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                .body(body)
                .when()
                .post(Endpoint)
                .then()
                .log().status().log().body().assertThat();


    }


    public ValidatableResponse PutBody(String body) {
        BODY = body;
        PUT = "put";
        return given()
                .urlEncodingEnabled(false).log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post()
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse PutBodyEndpoint(String body, String Endpoint) {
        endpoint_Rest = Endpoint;
        BODY = body;
        PUT = "put";

        return given()
                .urlEncodingEnabled(false).log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(Endpoint)
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse PutParamHeaderBodyEndpoint(String body, String Endpoint) {
        endpoint_Rest = Endpoint;
        BODY = body;
        PUT = "put";

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


    }

    public ValidatableResponse PutParamHeaderBody(String body) {
        BODY = body;
        PUT = "put";

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


    }

    public ValidatableResponse PutParamBodyEndpoint(String body, String Endpoint) {
        endpoint_Rest = Endpoint;
        BODY = body;
        PUT = "put";

        return given()
                .contentType(ContentType.JSON).log().all()
                .urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .body(body)
                .when()
                .post(Endpoint)
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse PutParamBody(String body) {
        BODY = body;
        PUT = "put";

        return given()
                .contentType(ContentType.JSON).log().all()
                .urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .body(body)
                .when()
                .post()
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse PutHeaderBody(String body) {
        BODY = body;
        PUT = "put";

        return given()
                .log().all().urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                .body(body)
                .when()
                .post()
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse PutHeaderBodyEndpoint(String body, String Endpoint) {
        endpoint_Rest = Endpoint;
        BODY = body;
        PUT = "put";

        return given()
                .log().all().urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                .body(body)
                .when()
                .post(Endpoint)
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse Delete() {
        return given()
                .log().all().urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .when().delete()
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse DeleteEndpoint(String Endpoint) {
        endpoint_Rest = Endpoint;
        return given()
                .log().all().urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .when().delete(Endpoint)
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse DeleteParam() {
        return given()
                .log().all().urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .contentType(ContentType.JSON)
                .when().delete()
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse DeleteParamEndpoint(String Endpoint) {
        endpoint_Rest = Endpoint;
        return given()
                .log().all().urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .contentType(ContentType.JSON)
                .when().delete(Endpoint)
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse DeleteParamHeader() {
        return given()
                .log().all().urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                .contentType(ContentType.JSON)
                .when().delete()
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse DeleteHeader() {
        return given()
                .log().all().urlEncodingEnabled(false)
                .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                .contentType(ContentType.JSON)
                .when().delete()
                .then()
                .log().status().log().body().assertThat();


    }

    public ValidatableResponse DeleteHeaderEndpoint(String Endpoint) {
        endpoint_Rest = Endpoint;
        return given()
                .log().all().urlEncodingEnabled(false)
                .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                .contentType(ContentType.JSON)
                .when().delete(Endpoint)
                .then()
                .log().status().log().body().assertThat();

    }

    public ValidatableResponse DeleteParamHeaderEndpoint(String Endpoint) {
        endpoint_Rest = Endpoint;
        return given()
                .log().all().urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                .contentType(ContentType.JSON)
                .when().delete(Endpoint)
                .then()
                .log().status().log().body().assertThat();

    }

    public void ReportBradesco(Scenario scenario) throws BradescoException, IOException {

        Response response;
        System.out.println("-------------------------------------\n Iniciando Report CI&T Bradesco...  " + scenario.getStatus().toUpperCase() + "\n" + scenario.getName().toUpperCase() + "   \n-------------------------------------");

        if (scenario.isFailed()) {

            if (MENSAGEM_REPORT_ERROR != "") {
                BradescoReporter.report(ReportStatus.ERROR, MENSAGEM_REPORT_ERROR);
            }
        } else {
            if (BODY == null) {
                System.out.println("Report GET sendo executado...");
                response = given().urlEncodingEnabled(false).queryParams(PARAM).headers(HEADERS).when().get(endpoint_Rest).then().extract().response();
                BradescoReporter.reportEvent(HttpRequestEvent.getRequest(ENDPOINT, response.getBody().asString()));
            } else if (PUT != null) {
                System.out.println("Report PUT sendo executado...");
                response = given().urlEncodingEnabled(false).queryParams(PARAM).headers(HEADERS).body(BODY).when().put(endpoint_Rest).then().extract().response();
                BradescoReporter.reportEvent(HttpRequestEvent.postRequest(ENDPOINT, BODY, response.getBody().asString()));

            } else {
                System.out.println("Report POST sendo executado...");
                response = given().urlEncodingEnabled(false).queryParams(PARAM).headers(HEADERS).body(BODY).when().post(endpoint_Rest).then().extract().response();
                BradescoReporter.reportEvent(HttpRequestEvent.postRequest(ENDPOINT, BODY, response.getBody().asString()));
            }
            System.out.println("Report Bradesco gerado no path: " + GetProp().getProperty("excludReport"));

        }
        PARAM.clear();
        HEADERS.clear();
        BODY = null;
        ENDPOINT = null;
        RestAssured.reset();
    }

    public static void ExcludReportBradesco() throws IOException {
        // Método irá excluir todos os Reports antigos
        File folder = new File(GetProp().getProperty("excludReport"));
        if (folder.isDirectory()) {
            File[] sun = folder.listFiles();
            for (File toDelete : sun) {
                toDelete.delete();
            }
        }
    }
}


