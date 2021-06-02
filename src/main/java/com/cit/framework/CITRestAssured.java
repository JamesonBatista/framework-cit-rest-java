package com.cit.framework;

import com.bradesco.core.exception.BradescoAssertionException;
import com.bradesco.core.exception.BradescoException;
import com.bradesco.core.exception.BradescoRuntimeException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jose.jca.JCASupport;
import com.nimbusds.jose.shaded.json.JSONObject;
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
import org.apache.poi.ss.formula.functions.T;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.cit.framework.ClassReport.*;
import static io.restassured.RestAssured.*;
import static util.FileProperties.GetProp;
import static util.TextSystemFiles.textNull;

public class CITRestAssured {

    static ValidatableResponse result;
    static Response response;
    static Boolean initReport = false;
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

    static void initReport(Boolean... logs) throws IOException, BradescoException {
        ValidationResponse();
        if (logs.length == 0) {
            ReportBradesco();
        } else {
            System.out.println("\n                                √  Report não solicitado.");
        }
    }

    static void ValidationResponse() {
        Response res = result.extract().response();
        if (res.asString().isEmpty() || res.asString().contains("{}") || res == null || res.asString().contains(textNull)) {
            System.out.println("                                                  ▁ ▂ ▃ ▄ ▅ ▆ ▇ ERROR: ▇ ▆ ▅ ▄ ▃ ▂ ▁\n");
            System.out.println("                                                   URI: " + URIFinal());
            throw new BradescoRuntimeException("\n\n Seu Response está NULL, talvez você tenha batido no Endpoint errado\n" +
                    "   Olhe dentro do environment/data.properties, ou na sua Feature e verifique se o endpoint está correto.\n" +
                    "   Ou, você não passou o parâmetro corretamente.\n" +
                    "   Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        } else {
            result.log().body();
        }
    }


    private RequestSpecification Given() {
        initReport = true;
        InitReport();
        return given().filter(new RequestLoggingFilter(requestCapture))
                .urlEncodingEnabled(false).contentType(ContentType.JSON)
                .log().all();
    }

    public RequestSpecification GivenExternal(ContentType type) {
        InitReport();
        return given().filter(new RequestLoggingFilter(requestCapture))
                .urlEncodingEnabled(false).contentType(type)
                .log().all();
    }

    public void InitEnvironment(String... Endpoint) throws IOException {
        RestAssured.reset();
        enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        if (Endpoint.length > 0) {
            for (String endpoint : Endpoint) {
                baseURI = Constantes.selecionaAmbiente() + endpoint;
            }
        } else {
            baseURI = Constantes.selecionaAmbiente();
        }
        RestAssured.useRelaxedHTTPSValidation();
        System.out.println("\n                                √  Ambiente selecionado: " + baseURI + " ** + " + GetProp().getProperty("default")
                .toUpperCase(Locale.ROOT) + " **");

    }

    public ValidatableResponse Get(Boolean... log) throws IOException, BradescoException {
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

    public ValidatableResponse GetEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
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

    public ValidatableResponse GetParamHeaderEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .headers(headers.toString() == "{}" ? null : headers)
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public ValidatableResponse GetParam(Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .when()
                    .get()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public ValidatableResponse GetParamEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public ValidatableResponse GetParamHeader(Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .headers(headers.toString() == "{}" ? null : headers)
                    .when()
                    .get()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public ValidatableResponse GetHeader(Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
                    .when()
                    .get()
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public ValidatableResponse GetHeaderEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
                    .when()
                    .get(Endpoint)
                    .then().assertThat();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public ValidatableResponse Post(Boolean... log) throws IOException, BradescoException {
        BODY = "{}";
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

    public ValidatableResponse PostBody(String body, Boolean... log) throws IOException, BradescoException {
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

    public ValidatableResponse PostBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
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

    public ValidatableResponse PostParamHeaderBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .headers(headers.toString() == "{}" ? null : headers)
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

    public ValidatableResponse PostParamHeaderBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .headers(headers.toString() == "{}" ? null : headers)
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

    public ValidatableResponse PostParamBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
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

    public ValidatableResponse PostParamBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
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

    public ValidatableResponse PostHeaderBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
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

    public ValidatableResponse PostHeaderBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
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

    public ValidatableResponse PutBody(String body, Boolean... log) throws IOException, BradescoException {
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

    public ValidatableResponse PutBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
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

    public ValidatableResponse PutParamHeaderBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .headers(headers.toString() == "{}" ? null : headers)
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

    public ValidatableResponse PutParamHeaderBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .headers(headers.toString() == "{}" ? null : headers)
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

    public ValidatableResponse PutParamBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
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

    public ValidatableResponse PutParamBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
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

    public ValidatableResponse PutHeaderBody(String body, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
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

    public ValidatableResponse PutHeaderBodyEndpoint(String body, String Endpoint, Boolean... log) throws IOException, BradescoException {
        BODY = body;
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
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

    public ValidatableResponse Delete(Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .when().delete()
                    .then();
            ValidationResponse();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public ValidatableResponse DeleteEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
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

    public ValidatableResponse DeleteParam(Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public ValidatableResponse DeleteParamEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .queryParams(params.toString() == "{}" ? null : params)
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public ValidatableResponse DeleteParamHeader(Boolean... log) throws IOException, BradescoException {
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
            initReport(log);
        }
    }

    public ValidatableResponse DeleteHeader(Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
                    .contentType(ContentType.JSON)
                    .when().delete()
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public ValidatableResponse DeleteHeaderEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
        try {
            result = Given()
                    .headers(headers.toString() == "{}" ? null : headers)
                    .contentType(ContentType.JSON)
                    .when().delete(Endpoint)
                    .then();
            response = result.extract().response();
            return result;
        } finally {
            initReport(log);
        }
    }

    public ValidatableResponse DeleteParamHeaderEndpoint(String Endpoint, Boolean... log) throws IOException, BradescoException {
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
            initReport(log);
        }
    }

    public static Object ValidationPathArrayListObjects(List<T> array, String getValue, String... pathAssertExist) throws BradescoAssertionException {
        JSONObject json;
        Object value = null;
        Object finalValue = new String();

        for (Object list : array) {
            json = new JSONObject((Map<String, ?>) list);
            for (String listArray : pathAssertExist) {
                value = json.get(listArray);
                if (value == null) {
                    throw new BradescoAssertionException("O valor 《《 " + listArray + " 》》 não existe na sua lista de Objetos.\n" +
                            "Object error: " + json);
                } else {
                    if (getValue != "") {
                        finalValue = json.get(getValue);
                    }
                }
            }
        }
        return finalValue;
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


