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
import java.util.Map;
import java.util.logging.Logger;

import static io.restassured.RestAssured.*;

public class CITRestAssured {

    static ValidatableResponse result;
    static Response response;

    static String BODY = null;
    static String endpoint_Rest = "";
    static String PUT = null;
    static String DELETE = null;
    public static Map<String, Object> PARAM = new HashMap<>();
    public static Map<String, Object> HEADERS = new HashMap<>();

    private static Logger LOGGER = Logger.getLogger("InfoLogging");
    public ReportPrivateBradesco report;

    public static StringWriter requestWriter;
    static PrintStream requestCapture;

    static void initReport() {
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);
    }

    static RequestSpecification GivenRest() {
        return given().filter(new RequestLoggingFilter(requestCapture));
    }

    public void RestEnvironment(String Endpoint) throws IOException {
        report = new ReportPrivateBradesco();
        initReport();
        try {
            RestAssured.reset();
            enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
            baseURI = Constantes.selecionaAmbiente() + Endpoint;
            RestAssured.useRelaxedHTTPSValidation();
        } finally {
            System.out.println("\n");
            LOGGER.info("Project for exclusive use of CI&T \n" +
                    "             and User: " + System.getProperty("user.name"));
        }
    }

    public void RestEnvironment() throws IOException {
        report = new ReportPrivateBradesco();
        initReport();
        try {
            RestAssured.reset();
            enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
            baseURI = Constantes.selecionaAmbiente();
            RestAssured.useRelaxedHTTPSValidation();
        } finally {
            System.out.println("\n");
            LOGGER.info("Project for exclusive use of CI&T \n" +
                    "             and User: " + System.getProperty("user.name"));
        }
    }

    public ValidatableResponse Get() throws IOException, BradescoException {
        try {
            result = GivenRest()
                    .urlEncodingEnabled(false).log().all()
                    .when()
                    .get()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            report.ReportBradescoGet();
        }
    }

    public ValidatableResponse GetEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        try {
            result = GivenRest()
                    .urlEncodingEnabled(false).log().all()
                    .when()
                    .get(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            report.ReportBradescoGet();
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
            report.ReportBradescoGet();
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
            report.ReportBradescoGet();
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
            report.ReportBradescoGet();
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
            report.ReportBradescoGet();
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
            report.ReportBradescoGet();
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
            report.ReportBradescoGet();
        }
    }

    public ValidatableResponse Post() throws IOException, BradescoException {
        BODY = "{}";
        try {
            result = given()
                    .contentType(ContentType.JSON)
                    .urlEncodingEnabled(false).log().all()
                    .when()
                    .post()
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;

        } finally {
            report.ReportBradescoPost();
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
            report.ReportBradescoPost();
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
            report.ReportBradescoPost();
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
            report.ReportBradescoPost();
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
            report.ReportBradescoPost();
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
            report.ReportBradescoPost();
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
            report.ReportBradescoPost();
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
            report.ReportBradescoPost();
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
            report.ReportBradescoPost();
        }
    }

    public ValidatableResponse PutBody(String body) throws IOException, BradescoException {
        BODY = body;
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
            report.ReportBradescoPut();
        }
    }

    public ValidatableResponse PutBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        BODY = body;
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
            report.ReportBradescoPut();
        }
    }

    public ValidatableResponse PutParamHeaderBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
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
                    .put(Endpoint)
                    .then()
                    .log().status().log().body().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            report.ReportBradescoPut();
        }
    }

    public ValidatableResponse PutParamHeaderBody(String body) throws IOException, BradescoException {
        BODY = body;
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
            report.ReportBradescoPut();
        }
    }

    public ValidatableResponse PutParamBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        BODY = body;
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
            report.ReportBradescoPut();
        }
    }

    public ValidatableResponse PutParamBody(String body) throws IOException, BradescoException {
        BODY = body;
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
            report.ReportBradescoPut();
        }
    }

    public ValidatableResponse PutHeaderBody(String body) throws IOException, BradescoException {
        BODY = body;
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
            report.ReportBradescoPut();
        }
    }

    public ValidatableResponse PutHeaderBodyEndpoint(String body, String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        BODY = body;
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
            report.ReportBradescoPut();
        }
    }

    public ValidatableResponse Delete() throws IOException, BradescoException {
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then()
            ;
            response = result.extract().response();
            return result;
        } finally {
            report.ReportBradescoDelete();
        }
    }

    public ValidatableResponse DeleteEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            report.ReportBradescoDelete();
        }
    }

    public ValidatableResponse DeleteParam() throws IOException, BradescoException {
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            report.ReportBradescoDelete();
        }
    }

    public ValidatableResponse DeleteParamEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            report.ReportBradescoDelete();
        }
    }

    public ValidatableResponse DeleteParamHeader() throws IOException, BradescoException {
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            report.ReportBradescoDelete();
        }
    }

    public ValidatableResponse DeleteHeader() throws IOException, BradescoException {
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            report.ReportBradescoDelete();
        }
    }

    public ValidatableResponse DeleteHeaderEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            report.ReportBradescoDelete();
        }
    }

    public ValidatableResponse DeleteParamHeaderEndpoint(String Endpoint) throws IOException, BradescoException {
        endpoint_Rest = Endpoint;
        try {
            result = given()
                    .log().all().urlEncodingEnabled(false)
                    .queryParams(PARAM.toString() == "{}" ? null : PARAM)
                    .headers(HEADERS.toString() == "{}" ? null : HEADERS)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            report.ReportBradescoDelete();
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


