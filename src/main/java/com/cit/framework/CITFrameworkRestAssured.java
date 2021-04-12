package com.cit.framework;

import com.bradesco.core.exception.BradescoRuntimeException;
import com.bradesco.core.sdk.enums.ReportStatus;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import com.bradesco.core.exception.BradescoException;
import com.bradesco.core.report.BradescoReporter;
import com.bradesco.core.report.model.HttpRequestEvent;
import cucumber.api.Scenario;
import util.Constantes;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

/*
Lembrete:
TODO:
    Para usar o Framework, basta extender o frame no seu step, Ex: ** public class StepUser extends CITFrameworkRestAssured{} **
    Caso não tenha, crie uma pasta na raiz do projeto chamada environment e dentro um arquivo chamado data.properties, dentro dele estará
    os valores dos Ambientes, EX: Env.tu=http://11111111111111111.  **caso nao tenha o data.properties, crie e cole o valor que está no final desta classe**
    No AterClass é necessário chamar a variável AMBIENTE = "Env.tu" e passar o valor.
    Todos métodos chamados nos steps, precisa passar o valor para RESPONSE: RESPONSE = given()........then().extract().response();
    Isso fará com que o report capte o Body.
    Automaticamente será decidido entre GET e POST a criação do Report. Caso seja passado valor para variável BODY, será um Post, caso não, GET
    Em caso de POST, **SEMPRE** passar o valor do body enviado no Post para BODY:   BODY = {"data":"valor", "key":"value"}, BODY = variavel.body
     Headers e Params, use dessa forma no seu step PARAM.put("key", "value") OBS: nao precisa passar dentro do metodo Ex: PARAM.put("key", "value"); e abaixo o
     metodo é chamado GetParam(); ...NAO PRECISA PASSAR DENTRO TO METODO.
     O Report será chamado no seu Hooks, dentro do seu package *steps* crie uma classe chamada Hooks, dentro dela crie um metodo.
     Conforme ex abaixo:
     .
     public class Hooks extends CITFrameworkRestAssured{
     @After
     public void after(Scenario scenario){
     AfterScenarioStartReport(scenario);
        }
     }

 */

public class CITFrameworkRestAssured {

    public static String MENSAGEM_REPORT_ERROR = "";

    public static String AMBIENTE = null;

    public static Response RESPONSE = null;

    public static String BODY = null;

    public static String ENDPOINT = null;


    public static Map<String, Object> PARAM = new HashMap<>();
    public static Map<String, Object> HEADER = new HashMap<>();

    public void InitalEndpoint(String Endpoint) throws IOException {
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
                .then();
    }

    public ValidatableResponse GetEndpoint(String Endpoint) {
        return given()
                .urlEncodingEnabled(false).log().all()
                .when()
                .get(Endpoint)
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

    public ValidatableResponse GetParam() {
        return given()
                .urlEncodingEnabled(false)
                .log().all()
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
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

    public ValidatableResponse Delete() {
        return given()
                .log().all().urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .when().delete().then();
    }

    public ValidatableResponse DeleteEndpoint(String Endpoint) {
        return given()
                .log().all().urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .when().delete(Endpoint).then();
    }

    public ValidatableResponse DeleteParam() {
        return given()
                .log().all().urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .contentType(ContentType.JSON)
                .when().delete().then();
    }

    public ValidatableResponse DeleteParamEndpoint(String Endpoint) {
        return given()
                .log().all().urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .contentType(ContentType.JSON)
                .when().delete(Endpoint).then();
    }

    public ValidatableResponse DeleteParamHeader() {
        return given()
                .log().all().urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .headers(HEADER.toString() == "{}" ? null : HEADER)
                .contentType(ContentType.JSON)
                .when().delete().then();
    }

    public ValidatableResponse DeleteHeader() {
        return given()
                .log().all().urlEncodingEnabled(false)
                .headers(HEADER.toString() == "{}" ? null : HEADER)
                .contentType(ContentType.JSON)
                .when().delete().then();
    }

    public ValidatableResponse DeleteHeaderEndpoint(String Endpoint) {
        return given()
                .log().all().urlEncodingEnabled(false)
                .headers(HEADER.toString() == "{}" ? null : HEADER)
                .contentType(ContentType.JSON)
                .when().delete(Endpoint).then();
    }

    public ValidatableResponse DeleteParamHeaderEndpoint(String Endpoint) {
        return given()
                .log().all().urlEncodingEnabled(false)
                .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                .headers(HEADER.toString() == "{}" ? null : HEADER)
                .contentType(ContentType.JSON)
                .when().delete(Endpoint).then();
    }

    public void AfterScenarioStartReport(Scenario scenario) throws BradescoException {

        System.out.println("-------------------------------------\n Iniciando Report CI&T Bradesco..." + scenario.getStatus().toUpperCase() + "\n" + scenario.getName().toUpperCase() + "   \n-------------------------------------");

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

/*
TODO: copie apenas os valores abaixo, sem o TODO.
    Env.tu=....
    Env.ti=.....
    Env.th=....
    Env.tu_local=.....
    Env.local=.....
    Env.prod=......
    Env.env1=.........
    Env.env2=.........
    Env.env3=.........
    Env.env4=.........
 */
