package com.cit.framework;

import com.bradesco.core.exception.BradescoException;
import com.bradesco.core.report.BradescoReporter;
import com.bradesco.core.report.model.Event;
import com.bradesco.core.report.model.HttpRequestEvent;
import com.bradesco.core.sdk.enums.ReportStatus;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.Optional;

import static io.restassured.RestAssured.baseURI;
import static util.FileProperties.GetProp;

class ReportPrivateBradesco extends CITRestAssured {

    public void ReportBradescoGet() throws BradescoException, IOException {
        Exclud.ConsoleDesigner("    GET   ");
        BradescoReporter.report(ReportStatus.PASSED, "GET executado, abaixo evidências:");
        BradescoReporter.reportEvent(HttpRequestEvent.getRequest(endpoint_Rest == "" ? baseURI : baseURI + endpoint_Rest, response.asString()));
        Finish();
    }

    public void ReportBradescoPost() throws BradescoException, IOException {
        Exclud.ConsoleDesigner("   POST   ");

        BradescoReporter.report(ReportStatus.PASSED, "POST executado, abaixo evidências:");
        BradescoReporter.reportEvent(HttpRequestEvent.postRequest(endpoint_Rest == "" ? baseURI : baseURI + endpoint_Rest,
                BODY == null ? "{Body POST está vazio, verifique seu método.}" : BODY,
                response.asString()));
        Finish();
    }

    public void ReportBradescoPut() throws BradescoException, IOException {
        Exclud.ConsoleDesigner("    PUT   ");

        BradescoReporter.report(ReportStatus.PASSED, "PUT executado, abaixo evidências:");
        BradescoReporter.reportEvent(PutRequest(endpoint_Rest == "" ? baseURI : baseURI + endpoint_Rest,
                BODY == null ? "{Body PUT está vazio, verifique seu método.}" : BODY,
                response.asString()));
        Finish();
    }

    public void ReportBradescoDelete() throws BradescoException, IOException {
        Exclud.ConsoleDesigner(" DELETE   ");

        BradescoReporter.report(ReportStatus.PASSED, "DELETE executado. Não há evidências JSON, apenas Status OK.");
        BradescoReporter.reportEvent(DeleteRequest(endpoint_Rest == "" ? baseURI : baseURI + endpoint_Rest));
        Finish();
    }

    public void Finish() throws BradescoException, IOException {
        System.out.println("\n");

        System.out.println("Report Bradesco gerado no path: *** " + GetProp().getProperty("excludReport") + " ***\n\n");

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
        ValidatableResponse status = result.log().status();
        return new HttpRequestEvent(ReportStatus.OK, "DELETE", url, Optional.empty(), "Status: " + status);
    }
}
