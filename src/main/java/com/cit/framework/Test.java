package com.cit.framework;

import com.bradesco.core.exception.BradescoException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.io.IOException;

import static com.cit.framework.ClassReport.ExternalReport;
import static com.cit.framework.ClassReport.ReportBradesco;
import static com.cit.framework.Exclud.FilesSystem;

 class Test extends CITRestAssured {

    @org.junit.Test
     void TestFrame() throws IOException, BradescoException, InterruptedException {
        FilesSystem();

        InitEnvironment("users/7");
        GivenExternal(ContentType.JSON)
                .when().get();
//        Get();
        ExternalReport();
    }


}
