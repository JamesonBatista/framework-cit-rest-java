package com.cit.framework;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static com.cit.framework.ClassReport.ExternalReport;
import static com.cit.framework.Exclud.FilesSystem;

public class FrameworkTest extends CITRestAssured {
    @BeforeClass
    public static void setup() throws IOException {
        FilesSystem();
    }

    @Test
    public void TestFrame() throws Exception {
        InitEnvironment();
//
//        double numer = 500;
//        DecimalFormat twoPlaces = new DecimalFormat("0.00");
//        System.out.println(twoPlaces.format(numer));

        Get(false);
        Body().root("data", "id","Jam");
//        Body()
//                .root()
//                .object("id", "name")
//                .newObject("address", "street", "suite", "city")
//                .newObject("address geo", "lat", "lng");
//
//        Body()
//                .root("data", "id", 7)
//                .and("support", "text", "To keep ReqRes free, contributions towards server costs are appreciated!");


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
