package com.cit.framework;

import com.bradesco.core.exception.BradescoException;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.util.List;

import static com.cit.framework.ClassReport.ExternalReport;
import static com.cit.framework.Exclud.FilesSystem;

class Test extends CITRestAssured {

    @org.junit.Test
    void TestFrame() throws Exception {
        FilesSystem();
        InitEnvironment();
        String valorDoCampoQueQueroPegar = "name";
        List<T> extract = Get().extract().response().jsonPath().getList("$");
        ValidationPathArrayListObjects(extract, "", "id", "name");

//        ValidatableResponse res = GivenExternal(ContentType.JSON)
//                .when().delete().then();
//        ExternalReport(res.extract().response());

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
