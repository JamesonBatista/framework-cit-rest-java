package com.cit.framework;

import com.bradesco.core.exception.BradescoAssertionException;
import com.bradesco.core.exception.BradescoException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jose.jca.JCASupport;
import jsonvalidation.validationResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.output.WriterOutputStream;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.util.ResourceUtils;
import util.AlertsMessages;
import util.Constantes;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.cit.framework.ClassReport.ReportBradesco;
import static com.cit.framework.ClassReport.ReturnMethod;
import static io.restassured.RestAssured.*;
import static util.FileProperties.GetProp;
import static util.TextSystemFiles.textNull;

public class CITRestAssured extends validationResponse {

    static ValidatableResponse result = null;
    public static ValidatableResponse ExternalContainsJSON;
    public static Object StringGlobal;
    public static Response response;
    static Boolean initReport = false;
    static String BODY = null;
    static String PUT = null;
    static String DELETE = null;
    public static Map<String, Object> params = new HashMap<>();
    public static Map<String, Object> headers = new HashMap<>();

    static StringWriter requestWriter;
    static PrintStream requestCapture;
    private static AlertsMessages messages;


    static void InitReport() {
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);
    }

    static void initReport(Boolean... logs) throws IOException, BradescoException {
        if (result == null) {
            System.err.println("                                                 Seu retorno está NULL.");
            messages.ResponseNull();
        }
        ExternalContainsJSON = result;
        ValidationResponse();
        if (logs.length == 0) {
            ReportBradesco();
        } else {
            System.out.println("\n                                √  Report não solicitado.");
        }
    }

    static void ValidationResponse() {
        messages = new AlertsMessages();
        Response res = result.extract().response();
        if (response.asString().isEmpty() || response.asString().contains("{}") && !ReturnMethod().contains("DELETE")) {
            messages.AlertReturnIsEmpty();
        }
        if (res == null || res.asString().contains(textNull)) {
            messages.ResponseNull();
        } else {
             result.log().body();

        }
    }

    public static ValidatableResponse ResponseBody() throws BradescoAssertionException {
        if (result == null)
            throw new BradescoAssertionException("\n\nO ResponseBody precisa que algum método Get, Post, Delete, Put, seja iniciado.");

        return result;
    }

    private static RequestSpecification Given() throws IOException {

        initReport = true;
        InitReport();
        return given().relaxedHTTPSValidation().filter(new RequestLoggingFilter(requestCapture))
                .urlEncodingEnabled(false).contentType(ContentType.JSON)
                .log().all()
                ;
    }

    static void CheckParams() throws BradescoAssertionException {
        if (params.toString() == "{}")
            throw new BradescoAssertionException("\n\nSeu método precisa do params.put('KEY', 'VALUE'), e está em branco\n" +
                    "então será considerado como NULL.\n" +
                    "Você precisa enviar um parâmetro ou mais de um, ou o método é inadequado.");
    }

    static void CheckFormParams() throws BradescoAssertionException {
        if (params.toString() == "{}")
            throw new BradescoAssertionException("\n\nSeu método precisa do params.put('KEY', 'VALUE'), e está em branco\n" +
                    "então será considerado como NULL.\n" +
                    "Você precisa enviar um parâmetro ou mais de um, ou o método é inadequado.");
    }

    static void CheckHeaders() throws BradescoAssertionException {
        if (headers.toString() == "{}")
            throw new BradescoAssertionException("\n\nSeu método precisa do headers.put('KEY', 'VALUE'), e está em branco\n" +
                    "então será considerado como NULL.\n" +
                    "Você precisa enviar um parâmetro ou mais de um, ou o método é inadequado.");

    }

    public static RequestSpecification GivenExternal() throws IOException {
        InitReport();
        return given().filter(new RequestLoggingFilter(requestCapture))
                .log().all();
    }

    public static void InitEnvironment(String... Endpoint) throws IOException, BradescoAssertionException {
        RestAssured.reset();
        enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        String env = new String();
        if (Endpoint.length == 1) {
            for (String endpoint : Endpoint) {
                env = endpoint;
                baseURI = Constantes.selecionaAmbiente() + endpoint;
            }
        } else if (Endpoint.length > 1) {
            throw new BradescoAssertionException("\n\nO InitEnvironment não pode conter mais de 1 valor no Endpoint, use desta forma\n. " +
                    "Ex: InitEnvironment('users/7'); ou  InitEnvironment(endpoint);");
        } else {
            baseURI = Constantes.selecionaAmbiente();
        }
        RestAssured.useRelaxedHTTPSValidation();
        System.out.println("\n                                √  Ambiente selecionado: " + baseURI + " **  " + env
                + " **");
    }

    public static void Environment(String environment, String... Endpoint) throws IOException, BradescoAssertionException {
        RestAssured.reset();
        enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        if (Endpoint.length == 1) {
            for (String endpoint : Endpoint) {
                baseURI = GetProp().getProperty(environment) + endpoint;
            }
        } else if (Endpoint.length > 1) {
            throw new BradescoAssertionException("\n\nO Endpoint não pode conter mais de 1 valor no Endpoint, use desta forma\n. " +
                    "Ex: Environment('local', 'users/7'); ou  Environment('local', endpoint);");
        } else {
            baseURI = GetProp().getProperty(environment);
        }
        RestAssured.useRelaxedHTTPSValidation();
        System.out.println("\n                                √  Ambiente selecionado: " + baseURI + " ** + " + environment
                .toUpperCase(Locale.ROOT) + " **");

    }

    public static ValidatableResponse Get(Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .when()
                    .get()
                    .then()
                    .assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetBody(String body, Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .body(body)
                    .when()
                    .get()
                    .then()
                    .assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetBodyEndpoint(String Endpoint, String body, Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .body(body)
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetParamHeaderEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .queryParams(params)
                    .headers(headers)
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetFormParamHeaderEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        CheckFormParams();
        CheckHeaders();
        try {
            result = Given()
                    .formParams(params)
                    .headers(headers)
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetBodyParamHeaderEndpoint(String Endpoint, String body, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .queryParams(params)
                    .headers(headers)
                    .body(body)
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetBodyFormParamHeaderEndpoint(String Endpoint, String body, Boolean... log) throws IOException, BradescoException {
        CheckFormParams();
        CheckHeaders();
        try {
            result = Given()
                    .formParams(params)
                    .headers(headers)
                    .body(body)
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetParam(Boolean... log) throws IOException, BradescoException {
        CheckParams();
        try {
            result = Given()
                    .queryParams(params)
                    .when()
                    .get()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetFormParam(Boolean... log) throws IOException, BradescoException {
        CheckFormParams();
        try {
            result = Given()
                    .formParams(params)
                    .when()
                    .get()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetBodyParam(String body, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        try {
            result = Given()
                    .queryParams(params)
                    .body(body)
                    .when()
                    .get()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetBodyFormParam(String body, Boolean... log) throws IOException, BradescoException {
        CheckFormParams();
        try {
            result = Given()
                    .formParams(params)
                    .body(body)
                    .when()
                    .get()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetParamEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        CheckParams();

        try {
            result = Given()
                    .queryParams(params)
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetFormParamEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        CheckFormParams();

        try {
            result = Given()
                    .formParams(params)
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetBodyParamEndpoint(String Endpoint, String body, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        try {
            result = Given()
                    .queryParams(params)
                    .body(body)
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetBodyFormParamEndpoint(String Endpoint, String body, Boolean... log) throws IOException, BradescoException {
        CheckFormParams();
        try {
            result = Given()
                    .formParams(params)
                    .body(body)
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetParamHeader(Boolean... log) throws IOException, BradescoException {
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .queryParams(params)
                    .headers(headers)
                    .when()
                    .get()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }


    public static ValidatableResponse GetFormParamHeader(Boolean... log) throws IOException, BradescoException {
        CheckFormParams();
        CheckHeaders();
        try {
            result = Given()
                    .formParams(params)
                    .headers(headers)
                    .when()
                    .get()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetBodyParamHeader(String body, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .queryParams(params)
                    .headers(headers)
                    .body(body)
                    .when()
                    .get()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetBodyFormParamHeader(String body, Boolean... log) throws IOException, BradescoException {
        CheckFormParams();
        CheckHeaders();
        try {
            result = Given()
                    .formParams(params)
                    .headers(headers)
                    .body(body)
                    .when()
                    .get()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetHeader(Boolean... log) throws IOException, BradescoException {
        CheckHeaders();
        try {
            result = Given()
                    .headers(headers)
                    .when()
                    .get()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetBodyHeader(String body, Boolean... log) throws IOException, BradescoException {
        CheckHeaders();
        try {
            result = Given()
                    .headers(headers)
                    .body(body)
                    .when()
                    .get()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetHeaderEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        CheckHeaders();
        try {
            result = Given()
                    .headers(headers)
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse GetBodyHeaderEndpoint(String Endpoint, String body, Boolean... log) throws IOException, BradescoException {
        CheckHeaders();
        try {
            result = Given()
                    .headers(headers)
                    .body(body)
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse Post(Boolean... log) throws IOException, BradescoException {
        BODY = "POST SEM BODY ENVIADO";
        try {
            result = Given()
                    .when()
                    .post()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .body(body)
                    .when()
                    .post()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostParamHeaderBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .queryParams(params)
                    .headers(headers)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostFormParamHeaderBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckFormParams();
        CheckHeaders();
        try {
            result = Given()
                    .formParams(params)
                    .headers(headers)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostParamHeaderBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .queryParams(params)
                    .headers(headers)
                    .body(body)
                    .when()
                    .post()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostFormParamHeaderBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckFormParams();
        CheckHeaders();
        try {
            result = Given()
                    .formParams(params)
                    .headers(headers)
                    .body(body)
                    .when()
                    .post()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostParamBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckParams();
        try {
            result = Given()
                    .queryParams(params)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostFormParamBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckFormParams();
        try {
            result = Given()
                    .formParams(params)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostParamBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckParams();
        try {
            result = Given()
                    .queryParams(params)
                    .body(body)
                    .when()
                    .post()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostFormParamBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckFormParams();
        try {
            result = Given()
                    .formParams(params)
                    .body(body)
                    .when()
                    .post()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostHeaderBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckHeaders();
        try {
            result = Given()
                    .headers(headers)
                    .body(body)
                    .when()
                    .post()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostHeaderBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckHeaders();
        try {
            result = Given()
                    .headers(headers)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }


    public static ValidatableResponse PostEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = "POST SEM BODY ENVIADO";
        try {
            result = Given()
                    .when()
                    .post(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostParamHeaderEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = "POST SEM BODY ENVIADO";
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .queryParams(params)
                    .headers(headers)
                    .when()
                    .post(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostFormParamHeaderEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = "POST SEM BODY ENVIADO";
        CheckFormParams();
        CheckHeaders();
        try {
            result = Given()
                    .formParams(params)
                    .headers(headers)
                    .when()
                    .post(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostParamHeader(Boolean... log) throws IOException, BradescoException {
        BODY = "POST SEM BODY ENVIADO";
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .queryParams(params)
                    .headers(headers)
                    .when()
                    .post()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostFormParamHeader(Boolean... log) throws IOException, BradescoException {
        BODY = "POST SEM BODY ENVIADO";
        CheckFormParams();
        CheckHeaders();
        try {
            result = Given()
                    .formParams(params)
                    .headers(headers)
                    .when()
                    .post()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostParamEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = "POST SEM BODY ENVIADO";
        CheckParams();
        try {
            result = Given()
                    .queryParams(params)
                    .when()
                    .post(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostFormParamEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = "POST SEM BODY ENVIADO";
        CheckFormParams();
        try {
            result = Given()
                    .formParams(params)
                    .when()
                    .post(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }


    public static ValidatableResponse PostParam(Boolean... log) throws IOException, BradescoException {
        BODY = "POST SEM BODY ENVIADO";
        CheckParams();
        try {
            result = Given()
                    .queryParams(params)
                    .when()
                    .post()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostFormParam(Boolean... log) throws IOException, BradescoException {
        BODY = "POST SEM BODY ENVIADO";
        CheckFormParams();
        try {
            result = Given()
                    .formParams(params)
                    .when()
                    .post()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostHeader(Boolean... log) throws IOException, BradescoException {
        BODY = "POST SEM BODY ENVIADO";
        CheckHeaders();
        try {
            result = Given()
                    .headers(headers)
                    .when()
                    .post()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PostHeaderEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = "POST SEM BODY ENVIADO";
        CheckHeaders();
        try {
            result = Given()
                    .headers(headers)
                    .when()
                    .post(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }


    public static ValidatableResponse PutBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .body(body)
                    .when()
                    .put()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PutBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PutParamHeaderBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .queryParams(params)
                    .headers(headers)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PutFormParamHeaderBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckFormParams();
        CheckHeaders();
        try {
            result = Given()
                    .formParams(params)
                    .headers(headers)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PutParamHeaderBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .queryParams(params)
                    .headers(headers)
                    .body(body)
                    .when()
                    .put()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PutFormParamHeaderBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckFormParams();
        CheckHeaders();
        try {
            result = Given()
                    .formParams(params)
                    .headers(headers)
                    .body(body)
                    .when()
                    .put()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PutParamBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckParams();
        try {
            result = Given()
                    .queryParams(params)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PutFormParamBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckFormParams();
        try {
            result = Given()
                    .formParams(params)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PutParamBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckParams();
        try {
            result = Given()
                    .queryParams(params)
                    .body(body)
                    .when()
                    .put()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PutFormParamBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckFormParams();
        try {
            result = Given()
                    .formParams(params)
                    .body(body)
                    .when()
                    .put()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PutHeaderBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckHeaders();
        try {
            result = Given()
                    .headers(headers)
                    .body(body)
                    .when()
                    .put()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse PutHeaderBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        CheckHeaders();
        try {
            result = Given()
                    .headers(headers)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse Delete(Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteBody(String body, Boolean... log) throws IOException, BradescoException {
        try {
            result = Given().contentType(ContentType.JSON)
                    .body(body)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteBodyEndpoint(String body, String endpoint, Boolean... log) throws IOException, BradescoException {
        try {
            result = Given().contentType(ContentType.JSON)
                    .body(body)
                    .when().delete(endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteParam(Boolean... log) throws IOException, BradescoException {
        CheckParams();
        try {
            result = Given()
                    .queryParams(params)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteFormParam(Boolean... log) throws IOException, BradescoException {
        CheckFormParams();
        try {
            result = Given()
                    .formParams(params)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteBodyParam(String body, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        try {
            result = Given()
                    .body(body)
                    .queryParams(params)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteBodyFormParam(String body, Boolean... log) throws IOException, BradescoException {
        CheckFormParams();
        try {
            result = Given()
                    .body(body)
                    .formParams(params)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteBodyParamEndpoint(String endpoint, String body, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        try {
            result = Given()
                    .body(body)
                    .queryParams(params)
                    .when().delete(endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteBodyFormParamEndpoint(String endpoint, String body, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        try {
            result = Given()
                    .body(body)
                    .formParams(params)
                    .when().delete(endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteParamEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        try {
            result = Given()
                    .queryParams(params)
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteFormParamEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        try {
            result = Given()
                    .formParams(params)
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteParamHeader(Boolean... log) throws IOException, BradescoException {
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .queryParams(params)
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteFormParamHeader(Boolean... log) throws IOException, BradescoException {
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .formParams(params)
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteBodyParamHeader(String body, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .body(body)
                    .queryParams(params)
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteBodyFormParamHeader(String body, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .body(body)
                    .formParams(params)
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteBodyParamHeaderEndpoint(String endpoint, String body, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .body(body)
                    .queryParams(params)
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .when().delete(endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteBodyFormParamHeaderEndpoint(String endpoint, String body, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .body(body)
                    .formParams(params)
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .when().delete(endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteHeader(Boolean... log) throws IOException, BradescoException {
        CheckHeaders();
        try {
            result = Given()
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteBodyHeader(String body, Boolean... log) throws IOException, BradescoException {
        CheckHeaders();
        try {
            result = Given()
                    .body(body)
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteBodyHeaderEndpoint(String endpoint, String body, Boolean... log) throws IOException, BradescoException {
        CheckHeaders();
        try {
            result = Given()
                    .body(body)
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .when().delete(endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteHeaderEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        CheckHeaders();
        try {
            result = Given()
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteParamHeaderEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .queryParams(params)
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static ValidatableResponse DeleteFormParamHeaderEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        CheckParams();
        CheckHeaders();
        try {
            result = Given()
                    .formParams(params)
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public static RequestSpecification CertificationSpec(String keyPathFormatP12, String keyPass,
                                                  String trustPathFormatP12, String trustPass) throws Exception {

        char[] keyStorePassword = keyPass.toCharArray();
        KeyStore keyStore = loadKeyStore(keyPathFormatP12, keyStorePassword);
        KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyFactory.init(keyStore, keyStorePassword);

        char[] trustStorePassword = trustPass.toCharArray();
        KeyStore trustStore = loadKeyStore(trustPathFormatP12, trustStorePassword);
        TrustManagerFactory trustFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustFactory.init(trustStore);

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(keyFactory.getKeyManagers(), trustFactory.getTrustManagers(), new SecureRandom());
        SSLContext.setDefault(sslContext);

        RestAssuredConfig config = RestAssured.config()
                .sslConfig(new SSLConfig().sslSocketFactory(new SSLSocketFactory(sslContext)));

        return RestAssured.given()
                .config(config);
    }

    static KeyStore loadKeyStore(String file, char[] password) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        File key = ResourceUtils.getFile(file);
        InputStream in = new FileInputStream(key);
        keyStore.load(in, password);
        return keyStore;
    }

    public String JwtPS256(Map<String, Object> header, String PathKeyPrivateFormatPEM, String payload) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println("                                    *******************\n" +
                "                                    Iniciando JWT PS256\n" +
                "                                    *******************\n");
        File file = new File(PathKeyPrivateFormatPEM);
        String key = new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset());
        String privateKeyPEM = key
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "");
        byte[] encoded = Base64.decodeBase64(privateKeyPEM);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        PrivateKey keypr = keyFactory.generatePrivate(keySpec);

        try {
            Security.addProvider(BouncyCastleProviderSingleton.getInstance());
            JCASupport.isSupported(JWSAlgorithm.PS256);
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.PS256;
            return Jwts
                    .builder()
                    .setHeader(header)
                    .setPayload(payload)
                    .signWith(signatureAlgorithm, keypr)
                    .compact();

        } catch (Exception e) {
            throw new RuntimeException("Unable to generate a JWT token. " + e.getMessage());
        }
    }

    public String JwtHS256(Map<String, Object> header, String secret, String payload) {
        System.out.println("                                    *******************\n" +
                "                                    Iniciando JWT HS256\n" +
                "                                    *******************\n");
        try {
            return Jwts
                    .builder()
                    .setHeaderParams(header)
                    .setPayload(payload)
                    .signWith(SignatureAlgorithm.HS256, secret.getBytes(StandardCharsets.UTF_8))
                    .compact();

        } catch (Exception e) {
            throw new RuntimeException("Unable to generate a JWT token. " + e.getMessage());
        }
    }
}


