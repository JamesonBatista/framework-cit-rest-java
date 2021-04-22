package com.cit.framework;

import com.bradesco.core.exception.BradescoException;
import com.bradesco.core.report.BradescoReporter;
import com.bradesco.core.report.model.Event;
import com.bradesco.core.report.model.HttpRequestEvent;
import com.bradesco.core.sdk.enums.ReportStatus;
import com.bradesco.http.RestResponse;
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
import java.util.Optional;

import static io.restassured.RestAssured.*;
import static util.FileProperties.GetProp;


public class CITFrameworkRestAssured {

    static String BODY = null;
    static String endpoint_Rest = "";
    static String PUT = null;
    static String DELETE = null;
    public static Map<String, Object> PARAM = new HashMap<>();
    public static Map<String, Object> HEADERS = new HashMap<>();


    public void RestEnvironment(String Endpoint) throws IOException {
        ExcludReportBradesco();
        enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        baseURI = Constantes.selecionaAmbiente() + Endpoint;
        RestAssured.useRelaxedHTTPSValidation();
    }

    public void RestEnvironment() throws IOException {
        ExcludReportBradesco();
        enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        baseURI = Constantes.selecionaAmbiente();
        RestAssured.useRelaxedHTTPSValidation();
    }


    public ValidatableResponse Get() throws IOException, BradescoException {
        try {
            return given()
                    .urlEncodingEnabled(false).log().all()
                    .when()
                    .get()
                    .then()
                    .log().status().log().body().assertThat();

        } finally {
            ReportBradesco();

        }


    }

    public ValidatableResponse GetEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;

        try {
            return given()
                    .urlEncodingEnabled(false).log().all()
                    .when()
                    .get(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();


        } finally {
            ReportBradesco();
        }


    }

    public ValidatableResponse GetParamHeaderEndpoint(String Endpoint) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse GetParam() throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse GetParamEndpoint(String Endpoint) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse GetParamHeader() throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse GetHeader() throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse GetHeaderEndpoint(String Endpoint) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PostBody(String body) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PostBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PostParamHeaderBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PostParamHeaderBody(String body) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PostParamBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PostParamBody(String body) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PostHeaderBody(String body) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PostHeaderBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }


    public ValidatableResponse PutBody(String body) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PutBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PutParamHeaderBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PutParamHeaderBody(String body) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PutParamBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PutParamBody(String body) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PutHeaderBody(String body) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse PutHeaderBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
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
            ReportBradesco();
        }


    }

    public ValidatableResponse Delete() throws IOException, BradescoException {
        DELETE = "delete";
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
            ReportBradesco();
        }


    }

    public ValidatableResponse DeleteEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        DELETE = "delete";
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
            ReportBradesco();
        }


    }

    public ValidatableResponse DeleteParam() throws IOException, BradescoException {
        DELETE = "delete";
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
            ReportBradesco();
        }


    }

    public ValidatableResponse DeleteParamEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        DELETE = "delete";
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
            ReportBradesco();
        }


    }

    public ValidatableResponse DeleteParamHeader() throws IOException, BradescoException {
        DELETE = "delete";
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
            ReportBradesco();
        }


    }

    public ValidatableResponse DeleteHeader() throws IOException, BradescoException {
        DELETE = "delete";
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
            ReportBradesco();
        }


    }

    public ValidatableResponse DeleteHeaderEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        DELETE = "delete";
        try {
            return given()
                    .log().all().urlEncodingEnabled(false)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
        } finally {
            ReportBradesco();
        }


    }

    public ValidatableResponse DeleteParamHeaderEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        DELETE = "delete";
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
            ReportBradesco();
        }


    }

    static void ReportBradesco() throws BradescoException, IOException {

        Response response = null;
        System.out.println("----------------------------------------\n     Iniciando Report CI&T Bradesco\n----------------------------------------");

        if (BODY == null && DELETE == null && PUT == null) {
            System.out.println("Report GET sendo executado...");
            response = given().urlEncodingEnabled(false).queryParams(PARAM).headers(HEADERS).when().get(endpoint_Rest).then().extract().response();
            BradescoReporter.report(ReportStatus.PASSED, "GET executado, abaixo evidências:");
            BradescoReporter.reportEvent(HttpRequestEvent.getRequest(baseURI, response.getBody().asString()));
        } else if (PUT != null) {
            System.out.println("Report PUT sendo executado...");
            response = given().urlEncodingEnabled(false).queryParams(PARAM).headers(HEADERS).body(BODY).when().put(endpoint_Rest).then().extract().response();
            BradescoReporter.report(ReportStatus.PASSED, "PUT executado, abaixo evidências:");
            BradescoReporter.reportEvent(HttpRequestEvent.postRequest(baseURI, BODY, response.getBody().asString()));

        } else if (DELETE != null) {
            System.out.println("Report DELETE sendo executado...\n Não existe Report Bradesco para Delete.");
            BradescoReporter.report(ReportStatus.PASSED, "DELETE executado, ainda não há report Bradesco para o Delete");
//             new HttpRequestEvent(ReportStatus.OK, "DELETE", endpoint_Rest, response.getBody().asString());
        } else {
            System.out.println("Report POST sendo executado...");
            response = given().urlEncodingEnabled(false).queryParams(PARAM).headers(HEADERS).body(BODY).when().post(endpoint_Rest).then().extract().response();
            BradescoReporter.report(ReportStatus.PASSED, "POST executado, abaixo evidências:");
            BradescoReporter.reportEvent(HttpRequestEvent.postRequest(baseURI, BODY, response.getBody().asString()));
        }
        System.out.println("Report Bradesco gerado no path: *** " + GetProp().getProperty("excludReport") + " ***");

//        public static Event getRequest(String url, RestResponse response) throws BradescoException {
//            return new HttpRequestEvent(ReportStatus.OK, "GET", url, Optional.empty(), (String)response.getBodyAs(String.class));
//        }
//
//        public static Event postRequest(String url, String bodyAsString, RestResponse response) throws BradescoException {
//            return new HttpRequestEvent(ReportStatus.OK, "POST", url, Optional.of(bodyAsString), (String)response.getBodyAs(String.class));
//        }


        PARAM.clear();
        HEADERS.clear();
        BODY = null;
        PUT = null;
        DELETE = null;
    }

    static void ExcludReportBradesco() throws IOException {
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


