package com.cit.framework;

import static com.cit.framework.ClassReport.ExternalReport;
import static com.cit.framework.Exclud.FilesSystem;

public class Test extends CITRestAssured {

    @org.junit.Test
    public void TestFrame() throws Exception {
        FilesSystem();
        InitEnvironment("unknown");
        Get();

        Body()
                .contains("page")
                .and("per_pag")
                .and("total")
                .and("total_pages")
                .root("data")
                .object("id", "name", "color", "pantone_value");
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
