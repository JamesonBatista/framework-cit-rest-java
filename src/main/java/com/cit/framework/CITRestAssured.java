package com.cit.framework;

import com.bradesco.core.exception.BradescoException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jose.jca.JCASupport;
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
import static io.restassured.RestAssured.*;
import static util.FileProperties.GetProp;

public class CITRestAssured {

    static ValidatableResponse result;
    static Response response;

    static String BODY = null;
    static String PUT = null;
    static String DELETE = null;
    public static Map<String, Object> params = new HashMap<>();
    public static Map<String, Object> headers = new HashMap<>();

    static StringWriter requestWriter;
    static PrintStream requestCapture;

    static void InitReport() {
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);
    }

    public RequestSpecification Given() {
        return given().filter(new RequestLoggingFilter(requestCapture))
                .urlEncodingEnabled(false).contentType(ContentType.JSON)
                .log().all();
    }

    public RequestSpecification Given(ContentType type) {
        return given().filter(new RequestLoggingFilter(requestCapture))
                .urlEncodingEnabled(false).contentType(type)
                .log().all();
    }

    public void InitEnvironment(String Endpoint) throws IOException, InterruptedException {
        Exclud.ExcludReportBradesco();
        InitReport();
        RestAssured.reset();
        enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        baseURI = Constantes.selecionaAmbiente();
        basePath = Endpoint;
        RestAssured.useRelaxedHTTPSValidation();
        System.out.println("\n                                √  Ambiente selecionado: " + baseURI + " ** + " + GetProp().getProperty("default")
                .toUpperCase(Locale.ROOT) + " **");
        System.out.println("                                     Report Bradesco gerado no path: *** " + GetProp().getProperty("excludReport") + " ***\n");

    }

    public void InitEnvironment() throws IOException, InterruptedException {
        Exclud.ExcludReportBradesco();
        InitReport();
        RestAssured.reset();
        enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        baseURI = Constantes.selecionaAmbiente();
        RestAssured.useRelaxedHTTPSValidation();
        System.out.println("\n                                √  Ambiente selecionado: " + baseURI + " ** + " + GetProp().getProperty("default")
                .toUpperCase(Locale.ROOT) + " **");
        System.out.println("                                     Report Bradesco gerado no path: *** " + GetProp().getProperty("excludReport") + " ***\n");

    }

    public ValidatableResponse Get() throws IOException, BradescoException {
        try {
            result = Given()
                    .when()
                    .get()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse GetEndpoint(String Endpoint) throws IOException, BradescoException {
        try {
            result = Given()
                    .when()
                    .get(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse GetParamHeaderEndpoint(String Endpoint) throws IOException, BradescoException {
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .headers(headers.toString() == "{}" ? null : headers)
                    .when()
                    .get(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse GetParam() throws IOException, BradescoException {
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .when()
                    .get()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse GetParamEndpoint(String Endpoint) throws IOException, BradescoException {
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .when()
                    .get(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse GetParamHeader() throws IOException, BradescoException {
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .headers(headers.toString() == "{}" ? null : headers)
                    .when()
                    .get()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse GetHeader() throws IOException, BradescoException {
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
                    .when()
                    .get()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse GetHeaderEndpoint(String Endpoint) throws IOException, BradescoException {
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
                    .when()
                    .get(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse Post() throws IOException, BradescoException {
        BODY = "{}";
        try {
            result = Given()
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;

        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PostBody(String body) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .body(body)
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PostBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PostParamHeaderBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .headers(headers.toString() == "{}" ? null : headers)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PostParamHeaderBody(String body) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .headers(headers.toString() == "{}" ? null : headers)
                    .body(body)
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PostParamBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PostParamBody(String body) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .body(body)
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PostHeaderBody(String body) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
                    .body(body)
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PostHeaderBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
                    .body(body)
                    .when()
                    .post(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PutBody(String body) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .body(body)
                    .when()
                    .put()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PutBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PutParamHeaderBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .headers(headers.toString() == "{}" ? null : headers)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PutParamHeaderBody(String body) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .headers(headers.toString() == "{}" ? null : headers)
                    .body(body)
                    .when()
                    .put()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PutParamBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PutParamBody(String body) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .body(body)
                    .when()
                    .put()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PutHeaderBody(String body) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
                    .body(body)
                    .when()
                    .put()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse PutHeaderBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
                    .body(body)
                    .when()
                    .put(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse Delete() throws IOException, BradescoException {
        try {
            result = Given()
                    .when().delete()
                    .then()
            ;
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse DeleteEndpoint(String Endpoint) throws IOException, BradescoException {
        try {
            result = Given()
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse DeleteParam() throws IOException, BradescoException {
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse DeleteParamEndpoint(String Endpoint) throws IOException, BradescoException {
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse DeleteParamHeader() throws IOException, BradescoException {
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .headers(headers.toString() == "{}" ? null : headers)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse DeleteHeader() throws IOException, BradescoException {
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse DeleteHeaderEndpoint(String Endpoint) throws IOException, BradescoException {
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public ValidatableResponse DeleteParamHeaderEndpoint(String Endpoint) throws IOException, BradescoException {
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .headers(headers.toString() == "{}" ? null : headers)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            ReportBradesco();
        }
    }

    public RequestSpecification CertificationSpec(String keyPathFormatP12, String keyPass,
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


