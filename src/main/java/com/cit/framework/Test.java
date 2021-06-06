package com.cit.framework;

import static com.cit.framework.ClassReport.ExternalReport;
import static com.cit.framework.Exclud.FilesSystem;

 class Test extends CITRestAssured {

    @org.junit.Test
     void TestFrame() throws Exception {
        FilesSystem();
        InitEnvironment();
        Get();

//        Body()
//              .contains("id")
//        .and("name")
//        .and("email")
//
        ;
//        String valorDoCampoQueQueroPegar = "name";
//        List<T> extract = Get().extract().response().jsonPath().getList("$");
//        ValidationPathArrayListObjects(extract, "", "id", "name");

//        ValidatableResponse res = GivenExternal(ContentType.JSON)
//                .when().delete().then();
//        ExternalReport(res.extract().response());
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


}
