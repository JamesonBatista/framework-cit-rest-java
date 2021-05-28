package com.cit.framework;

import com.bradesco.core.exception.BradescoException;
import com.bradesco.core.exception.BradescoRuntimeException;
import com.bradesco.core.report.BradescoReporter;
import com.bradesco.core.report.model.Event;
import com.bradesco.core.report.model.HttpRequestEvent;
import com.bradesco.core.sdk.enums.ReportStatus;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import static com.cit.framework.CITRestAssured.*;
import static util.FileProperties.GetProp;

class ClassReport {
    private static String report;

    public static void ReportBradesco() throws IOException, BradescoException {
        Scanner sc = new Scanner(requestWriter.toString());

        while (sc.hasNext()) {
            sc.next();
            report = sc.next();
            if (report.contains("method:")) {
                report = sc.next();
            }
            if (report.contains("GET")) {
                GetReport();
            } else if (report.contains("POST")) {
                PostReport();
            } else if (report.contains("DELETE")) {
                DeleteReport();
            } else if (report.contains("PUT")) {
                PutReport();
            } else {
                throw new BradescoRuntimeException("\n                             Método RestAssured Desconhecido: " + report);
            }
            break;
        }
    }

    private static String URIFinal() {
        String result = null;
        Scanner sc = new Scanner(requestWriter.toString());
        while (sc.hasNext()) {
            result = sc.next();
            if (result.contains("URI:")) {
                result = sc.next();
                break;
            }
        }
        return result;
    }

    private static void GetReport() throws BradescoException, IOException {
        Exclud.ConsoleDesigner("    GET   ");
        BradescoReporter.report(ReportStatus.PASSED, "GET executado, abaixo evidências:");
        BradescoReporter.reportEvent(HttpRequestEvent.getRequest(URIFinal(), response.asString()));
        Finish();
    }

    private static void PostReport() throws BradescoException, IOException {
        Exclud.ConsoleDesigner("   POST   ");

        BradescoReporter.report(ReportStatus.PASSED, "POST executado, abaixo evidências:");
        BradescoReporter.reportEvent(HttpRequestEvent.postRequest(URIFinal(),
                BODY == null ? "{Body POST está vazio, verifique seu método.}" : BODY,
                response.asString()));
        Finish();
    }

    private static void PutReport() throws BradescoException, IOException {
        Exclud.ConsoleDesigner("    PUT   ");

        BradescoReporter.report(ReportStatus.PASSED, "PUT executado, abaixo evidências:");
        BradescoReporter.reportEvent(PutRequest(URIFinal(),
                BODY == null ? "{Body PUT está vazio, verifique seu método.}" : BODY,
                response.asString()));
        Finish();
    }

    private static void DeleteReport() throws BradescoException, IOException {
        Exclud.ConsoleDesigner(" DELETE   ");

        BradescoReporter.report(ReportStatus.PASSED, "DELETE executado. Não há evidências JSON, apenas Status OK.");
        BradescoReporter.reportEvent(DeleteRequest(URIFinal()));
        Finish();
    }

    private static void Finish() throws BradescoException, IOException {
        System.out.println("\n");

        System.out.println("Report Bradesco gerado no path: *** " + GetProp().getProperty("excludReport") + " ***\n\n");

        params.clear();
        headers.clear();
        report = null;
        response = null;
        BODY = null;
        PUT = null;
        DELETE = null;
    }


    private static Event PutRequest(String url, String bodyAsString, String response) {
        return new HttpRequestEvent(ReportStatus.OK, "PUT", url, Optional.of(bodyAsString), response);
    }

    private static Event DeleteRequest(String url) {
        String status = result.extract().statusLine();
        return new HttpRequestEvent(ReportStatus.OK, "DELETE", url, Optional.empty(), "Status: " + status);
    }
}
