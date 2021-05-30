package com.cit.framework;

import com.bradesco.core.exception.BradescoException;
import com.bradesco.core.exception.BradescoRuntimeException;
import com.bradesco.core.report.BradescoReporter;
import com.bradesco.core.report.model.Event;
import com.bradesco.core.report.model.HttpRequestEvent;
import com.bradesco.core.sdk.enums.ReportStatus;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import static com.cit.framework.CITRestAssured.*;
import static io.restassured.RestAssured.given;

public class ClassReport {
    private static String report;
    static Scanner sc;

    private static void StartReport() throws IOException, BradescoException {
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

    private static void StartReportExternal(String body, Response responses) throws IOException, BradescoException {
        String metodo = null;
        sc = new Scanner(requestWriter.toString());

        if (responses == null && !metodo.contains("DELETE")) {

            System.out.println("                                                  ▁ ▂ ▃ ▄ ▅ ▆ ▇ AVISO: ▇ ▆ ▅ ▄ ▃ ▂ ▁");
            System.out.println("                                                   URI: " + URIFinal());
            throw new BradescoRuntimeException("\n\n Seu Response está NULL, talvez você tenha batido no Endpoint errado\n" +
                    "   Olhe dentro do data.properties, ou na sua Feature e verifique se o endpoint está correto.\n" +
                    "   Ou, você não passou o parâmetro corretamente.  Seu método " + metodo + " precisa ser feito como exemplo abaixo:\n" +
                    "   Ex: Response response =  GivenExternal(JSON).when().get().then().extract().response();\n" +
                    "   Ao final chame o ExternalReport(response); em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        while (sc.hasNext()) {
            sc.next();
            report = sc.next();
            if (report.contains("method:")) {
                report = sc.next();
                if (report.contains("GET")) {
                    Exclud.ConsoleDesigner("    GET   ");
                    BradescoReporter.report(ReportStatus.PASSED, "GET executado, abaixo evidências:");
                    BradescoReporter.reportEvent(HttpRequestEvent.getRequest(URIFinal(), responses.asString()));
                } else if (report.contains("POST")) {
                    Exclud.ConsoleDesigner("   POST   ");
                    BradescoReporter.reportEvent(HttpRequestEvent.postRequest(URIFinal(),
                            body,
                            responses.asString()));
                } else if (report.contains("DELETE")) {
                    Exclud.ConsoleDesigner(" DELETE   ");
                    BradescoReporter.report(ReportStatus.PASSED, "DELETE executado. Não há evidências JSON, apenas Status OK.");
                    BradescoReporter.reportEvent(DeleteRequest(URIFinal()));
                } else if (report.contains("PUT")) {
                    Exclud.ConsoleDesigner("    PUT   ");
                    BradescoReporter.report(ReportStatus.PASSED, "PUT executado, abaixo evidências:");
                    BradescoReporter.reportEvent(PutRequest(URIFinal(),
                            body,
                            responses.asString()));
                } else {
                    throw new BradescoRuntimeException("\n                             Método RestAssured Desconhecido: " + report);
                }
            }
            break;
        }

    }

    public static void ReportBradesco() throws IOException, BradescoException {
        sc = new Scanner(requestWriter.toString());
        if (response == null) {
            System.out.println("                                                  ▁ ▂ ▃ ▄ ▅ ▆ ▇ AVISO: ▇ ▆ ▅ ▄ ▃ ▂ ▁");
            System.out.println("                                                   URI: " + URIFinal());
            throw new BradescoRuntimeException("\nSeu Response está NULL, talvez você tenha batido no Endpoint errado\n" +
                    "Olhe dentro do data.properties, ou na sua Feature e verifique se o endpoint está correto, dúvidas olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        StartReport();
        sc.close();
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
        sc = new Scanner(requestWriter.toString());
        String metodo = null;
        while (sc.hasNext()) {
            sc.next();
            report = sc.next();
            if (report.contains("method:")) {
                report = sc.next();
                metodo = report;
                if (!metodo.contains("DELETE")) {
                    System.out.println("                                                  ▁ ▂ ▃ ▄ ▅ ▆ ▇ AVISO: ▇ ▆ ▅ ▄ ▃ ▂ ▁\n");
                    throw new BradescoRuntimeException("\n\n O método " + metodo + " não poder usado pelo ExternalReport(); vazio.\n" +
                            " Olhe o DOC FrameworkCIT dentro " +
                            "da pasta 《《 src/test/resources/FrameworkCIT.md 》》 para entender como usar.");
                }
                break;
            }
        }
        Finish();
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
                BODY,
                response.asString()));
        Finish();
    }

    private static void PutReport() throws BradescoException, IOException {
        Exclud.ConsoleDesigner("    PUT   ");

        BradescoReporter.report(ReportStatus.PASSED, "PUT executado, abaixo evidências:");
        BradescoReporter.reportEvent(PutRequest(URIFinal(),
                BODY,
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
