package com.cit.framework;

import com.bradesco.core.exception.BradescoException;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.io.IOException;

import static com.cit.framework.ClassReport.ExternalReport;
import static com.cit.framework.Exclud.FilesSystem;

class Test extends CITRestAssured {

    @org.junit.Test
    void TestFrame() throws IOException, BradescoException, InterruptedException {
        FilesSystem();
        InitEnvironment("users/7");
//        Get();

        ValidatableResponse res = GivenExternal(ContentType.JSON)
                .when().get().then();
        ExternalReport(res.extract().response());

//        ValidatableResponse res = GivenExternal(ContentType.JSON)
//                .when().delete("users/7").then();
//        ExternalReport(res.extract().response());

//        ValidatableResponse res = GivenExternal(ContentType.JSON)
//                .when().get("users/7").then();
//        ExternalReport();
//
//        Get();
//        ExternalReport();
    }


}
