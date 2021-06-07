package com.cit.framework;

import static com.cit.framework.ClassReport.ExternalReport;
import static com.cit.framework.Exclud.FilesSystem;

class Test extends CITRestAssured {

    @org.junit.Test
    void TestFrame() throws Exception {
        FilesSystem();
        InitEnvironment("unknown");
        Get();


        Body().root("data").object("id")
                .contains("true red");
//                .contains("page")
//                .and("per_page")
//                .and("total")
//                .and("total_pages")
//                .root("data")
//                .object("id", "name", "color", "pantone_value");
//
//        String body = "{\n" +
//                "    \"treinamento\":{\n" +
//                "        \"Primeiro\": \"Fabricio\",\n" +
//                "        \"Segundo\": \"Lucas\",\n" +
//                "        \"Terceiro\": \"Debora\",\n" +
//                "        \"Quarto\": \"Bruno\"\n" +
//                "    }\n" +
//                "}";
//        InitEnvironment("users/7");
//        PostBody(body);
//        Body()
//                .contains("treinamento")
//                .and("Primeiro")
//                .and("Fabricio")
//        ;


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
