package com.cit.framework;

import com.bradesco.core.exception.BradescoException;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.cit.framework.ClassReport.ExternalReport;
import static com.cit.framework.Exclud.FilesSystem;
import static io.restassured.RestAssured.expect;

public class FrameworkTest extends CITRestAssured {
    @BeforeClass
    public static void setup() throws IOException {
        FilesSystem();
    }

    @Test
    public void TestFrame() throws Exception {
        InitEnvironment("users/7");

        Get();

        Body()
                .root("data", "email");


//        Delete(false);
//        Assert.assertEquals(valor, Matchers.is(valor2));

//        Body().root("form").contains("chamada");
//
//        Get(false);
//        Body().contains("page").get("total").root("data").object("id", "name");

    }
//
//    @Test
//    public void TestUsers7() throws IOException, BradescoException {
//        InitEnvironment("users/7");
//        Body()
//                .contains("data", "id")
//        ;
//    }
//
//    @Test
//    public void TestExternal() throws IOException, BradescoException {
//        InitEnvironment("unknown");
//        ExternalContainsJSON = GivenExternal(ContentType.JSON)
//                .when().get().then().log().body();
//
//        Body().root("data").object("id", "name");
//
//        ValidatableResponse res = GivenExternal(ContentType.JSON)
//                .when().delete().then();
//        ExternalReport();
//
//    }

//        Body()
//                .root().contains("id").get("street");
//                .root("data")
//                .object("id").get("name")
//        ;
//        Body()
//                .contains("id", "data")
//                .get("avatar");


//
//        ValidatableResponse res = GivenExternal(ContentType.JSON)
//                .when().post("eeraadsv/ava").then();
//        ExternalReport(res.extract().response());

//        ValidatableResponse res = GivenExternal(ContentType.JSON)
//                .when().get("users/7").then();
//        ExternalReport();
//
//        Get();
//        ExternalReport();
//        GetEndpoint("gaharh");


}
