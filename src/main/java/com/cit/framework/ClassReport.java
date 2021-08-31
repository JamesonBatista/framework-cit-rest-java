package com.cit.framework;

import com.bradesco.core.exception.BradescoException;
import com.bradesco.core.exception.BradescoRuntimeException;
import com.bradesco.core.report.BradescoReporter;
import com.bradesco.core.report.model.Event;
import com.bradesco.core.report.model.HttpRequestEvent;
import com.bradesco.core.sdk.enums.ReportStatus;
import io.restassured.response.Response;
import util.AlertsMessages;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static util.TextSystemFiles.textNull;

public class ClassReport extends CITRestAssured {
    private static String report;
    private static Scanner sc;
    private static AlertsMessages messages;

    protected static String ReturnMethod() {
        sc = new Scanner(requestWriter.toString());
        while (sc.hasNext()) {
            sc.next();
            report = sc.next();
            if (report.contains("method:")) {
                report = sc.next();
            }
            break;
        }
        return report;
    }

    static String responseCreateDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime agora = LocalDateTime.now();
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String agoraFormatado = agora.format(formatter);
        return agoraFormatado;
    }

    private static void StartReport() throws IOException, BradescoException {
        sc = new Scanner(requestWriter.toString());
        while (sc.hasNext()) {
            sc.next();
            report = sc.next();
            if (report.contains("method:")) {
                report = sc.next();
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
            }
            break;
        }

    }

    static String bodyNotSend() {
        return "{\n" +
                "  \"information_report\": \"Endpoint não envia nenhum body(corpo) para requisição\",\n" +
                "  \"method\": \"" + ReturnMethod() + "\",\n" +
                "  \"endpoint\": \"" + URIFinal() + "\",\n" +
                "  \"date\": \"" + responseCreateDateTime() + "\"\n" +
                "}";
    }

    public static String getParams() {
        List<String> list = new ArrayList<>();
        String result = null;
        Scanner sc = new Scanner(requestWriter.toString());
//        System.out.println(requestWriter.toString());
        while (sc.hasNext()) {
            result = sc.next();
            if (result.equals("Form")) {
                result = sc.next();
                result = sc.next();

                if (!result.trim().equals("<none>"))
                    while (!result.trim().equals("Path")) {
                        list.add("Form params: " + result + "\n");
                        result = sc.next();
                    }

            } else if (result.equals("Headers:")) {
                result = sc.next();

                if (result.equals("Accept=*/*"))
                    result = sc.next();

                while (!result.equals("Cookies:") &&
                        !result.equals("charset=ISO-8859-1") &&
                        !result.equals("charset=UTF-8") &&
                        !result.equals("Accept=*/*")) {
                    list.add("Headers: " + result + "\n");
                    result = sc.next();
                }
                break;
            }
        }
        return list.toString();
    }


    private static void StartReportExternal(String body, Response responses) throws IOException, BradescoException {
        response = responses;
        BODY = body;
        messages = new AlertsMessages();

        if (initReport) {
            messages.ErroUseGivenExternal();
        }
        if (response.asString().contains(textNull) || response == null) {
            messages.ResponseNull();
        }

        if (response.asString().isEmpty() || response.asString().contains("{}") && !ReturnMethod().contains("DELETE")) {
            messages.AlertReturnIsEmpty();
        }

        if (ReturnMethod().contains("GET")) {
            GetReport();
        } else if (ReturnMethod().contains("POST")) {
            messages.SendPostOffBody();
            PostReport();
        } else if (ReturnMethod().contains("PUT")) {
            PutReport();
        } else {
            messages.UseExportReportFill();
        }
    }

    public static void ReportBradesco() throws IOException, BradescoException {

        StartReport();
    }

    public static void ExternalReport(String body, Response responses) throws IOException, BradescoException {
        StartReportExternal(body, responses);
        Finish();
    }

    public static void ExternalReport(Response responses) throws IOException, BradescoException {
        StartReportExternal("", responses);
        Finish();
    }

    public static void ExternalReport() throws IOException, BradescoException {

        if (initReport) {
            System.err.println("                                                  ▁ ▂ ▃ ▄ ▅ ▆ ▇ ERROR: ▇ ▆ ▅ ▄ ▃ ▂ ▁\n");
            throw new BradescoRuntimeException("\n\n O ExternalReport() só pode ser usado pelo GivenExternal os métodos normais não precisa do ExternalReport()\n" +
                    " Olhe o DOC FrameworkCIT dentro " +
                    "da pasta 《《 src/test/resources/FrameworkCIT.md 》》 para entender como usar.\n\n");
        }

        if (!ReturnMethod().contains("DELETE")) {
            System.err.println("                                                  ▁ ▂ ▃ ▄ ▅ ▆ ▇ ERROR: ▇ ▆ ▅ ▄ ▃ ▂ ▁\n");
            throw new BradescoRuntimeException("\n\n O método " + ReturnMethod() + " não poder usado pelo ExternalReport(); vazio.\n" +
                    " Olhe o DOC FrameworkCIT dentro " +
                    "da pasta 《《 src/test/resources/FrameworkCIT.md 》》 para entender como usar.\n\n");
        } else {
            Exclud.ConsoleDesigner(" DELETE   ");
            BradescoReporter.report(ReportStatus.PASSED, "DELETE executado. Não há evidências JSON, apenas Status OK.");
            BradescoReporter.reportEvent(DeleteRequest(URIFinal()));
        }

        Finish();
    }


    public static String URIFinal() {
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

        BradescoReporter.report(ReportStatus.WARNING, "Abaixo informações sobre os Parâmetros enviados: \n");
        String string = getParams().replaceAll("[\\[\\](){}]", "");
        String virgula = string.replaceAll(",", "");
        BradescoReporter.report(ReportStatus.OK, virgula);
        BradescoReporter.report(ReportStatus.WARNING, "<--------------------------------------------------->");

        Exclud.ConsoleDesigner("    GET   ");
        BradescoReporter.report(ReportStatus.PASSED, "GET executado, abaixo evidências:");
        BradescoReporter.reportEvent(HttpRequestEvent.getRequest(URIFinal(), response.asString()));
        Finish();
    }

    private static void PostReport() throws BradescoException, IOException {

        BradescoReporter.report(ReportStatus.WARNING, "Abaixo informações sobre os Parâmetros enviados: \n");
        String string = getParams().replaceAll("[\\[\\](){}]", "");
        String virgula = string.replaceAll(",", "");
        BradescoReporter.report(ReportStatus.OK, virgula);
        BradescoReporter.report(ReportStatus.WARNING, "");

        Exclud.ConsoleDesigner("   POST   ");

        BradescoReporter.report(ReportStatus.PASSED, "POST executado, abaixo evidências:");
        BradescoReporter.reportEvent(HttpRequestEvent.postRequest(URIFinal(),
                BODY,
                response.asString()));
        Finish();
    }

    private static void PutReport() throws BradescoException, IOException {

        BradescoReporter.report(ReportStatus.WARNING, "Abaixo informações sobre os Parâmetros enviados: \n");
        String string = getParams().replaceAll("[\\[\\](){}]", "");
        String virgula = string.replaceAll(",", "");
        BradescoReporter.report(ReportStatus.OK, virgula);
        BradescoReporter.report(ReportStatus.WARNING, "");

        Exclud.ConsoleDesigner("    PUT   ");

        BradescoReporter.report(ReportStatus.PASSED, "PUT executado, abaixo evidências:");
        BradescoReporter.reportEvent(PutRequest(URIFinal(),
                BODY,
                response.asString()));
        Finish();
    }

    private static void DeleteReport() throws BradescoException, IOException {

        BradescoReporter.report(ReportStatus.WARNING, "Abaixo informações sobre os Parâmetros enviados: \n");
        String string = getParams().replaceAll("[\\[\\](){}]", "");
        String virgula = string.replaceAll(",", "");
        BradescoReporter.report(ReportStatus.OK, virgula);
        BradescoReporter.report(ReportStatus.WARNING, "");

        Exclud.ConsoleDesigner(" DELETE   ");

        BradescoReporter.report(ReportStatus.PASSED, "DELETE executado. Não há evidências JSON, apenas Status OK.");
        BradescoReporter.reportEvent(DeleteRequest(URIFinal()));
        Finish();
    }

    private static void Finish() throws BradescoException, IOException {
        params.clear();
        headers.clear();
        report = null;
        BODY = null;
        PUT = null;
        DELETE = null;
        sc.close();
    }


    private static Event PutRequest(String url, String bodyAsString, String response) {


        return new HttpRequestEvent(ReportStatus.OK, "PUT", url, Optional.of(bodyAsString), response);
    }

    private static Event DeleteRequest(String url) {
        String status = result == null ? "OK" : result.extract().statusLine();
        return new HttpRequestEvent(ReportStatus.OK, "DELETE", url, Optional.empty(), "Status: " + status);
    }
}
