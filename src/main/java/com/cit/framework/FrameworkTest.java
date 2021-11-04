package com.cit.framework;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

public class FrameworkTest extends CITRestAssured {


    @Test
    public void TestFrame() throws Exception {

//        InitEnvironment("users/7");
        InitEnvironment();
//        Environment("env3");
//        body(String path, Matcher<?> matcher, Object... additionalKeyMatcherPairs);
//        params.put("a", "s");


        //Validação usando o Rest
//        params.put("validation", "validação");
//        GetParam();
        Get();
//        Body().mapping("address > others");

//        Body().contains("AP401")

//        Body().mapping("address > geo > state > planet", "Earth");

//             .rootPath("users").body("[0].name", is("Margareth"));


//        Body()
//                .mapping("data > users > form > info > information > dataInfo > enterpriseInfo > enterpriseData > enterpriseBody >" +
//                        " enterpriseBodyUser > enterPrime > enterPrime > enterRise > nameEnterprise", "CI&T");

//                .body("data.id", Matchers.is(7),
//                        "data.email", Matchers.is("michael.lawson@reqres.in"),
//                        "support.url", Matchers.is("https://reqres.in/#support-heading"));
//
//        //Validação usando o framework CI&T
//        Body()
//                .mapping("data > id", 7)
//                .mapping("data > email")
//                .mapping("data > first_name")
//                .mapping("support > text", "To keep ReqRes free, " +
//                        "contributions towards server costs are appreciated!");


//        Body()
//                .contains("brandName").get("brandName")
//        ;
//        Body()
//                .mapping("id", "id",1, 10)
//
//                ;
//        Validação usando o Framework
//        Body()
//                .mapping("totalSumary > cardsCount", 2)
//                .mapping("totalSumary > limitAmount", 0)
//                .mapping("bradesco > brandName")
//                .mapping("totalSumary > cardsCount", "cardsCount", 1, 2)
//                .mapping("bradesco > creditCards > creditCardInfo > creditCardNetwork", "VISA")
//                .mapping("bradesco > creditCards > bills > bills > billStatus", "billStatus", "FECHADA", "PAGA")
//                .mapping("bradesco > creditCards > bills > bills > automaticPayment", false)
//                .mapping("others > creditCards > bills > bills > automaticPayment", false)
//                .mapping("lastUpdateDateTime", "2021-07-06T11:46:22.227421Z")
//                .mapping("others > creditCards > creditCardInfo > consentId", "consentId", "itauconsentid", "bancodobrasil02consentid")
//                .mapping("others > creditCards > bills > bills > billStatus", "billStatus", "PAGA", "FECHADA")
//                .mapping("categoryMonthsAvailables > cards > internalBrand", "internalBrand", "OTHERS", "BRADESCO")
//                .mapping("categoryMonthsAvailables > totalAmount", "totalAmount", 200000.08);
        ;

//
//        //Validação usando o Rest
//        ResponseBody().body("bradesco.brandName", Matchers.is("BRADESCO"),
//                "bradesco.creditCards.creditCardInfo[0].creditCardNetwork", is("VISA"));
//        ResponseBody().body("bradesco.creditCards.bills[0].bills[0].billStatus", is("FECHADA"));
//        ResponseBody().body("others[0].creditCards.creditCardInfo[1].consentId", is("itauconsentid"));
//        ResponseBody().body("others[0].creditCards.bills[0].bills[0].billStatus", is("PAGA"));
//        ResponseBody().body("bradescoBlocked.creditCards.bills[0].auditory", is(nullValue()));
//        String response = ResponseBody().extract().response().path("categoryMonthsAvailables[0].totalAmount").toString();
//        BigDecimal bigDecimal = new BigDecimal(response);
//        Assert.assertThat(bigDecimal.doubleValue(), is(200000.08));
//        ResponseBody().body("categoryMonthsAvailables[0].cards.internalBrand[2]", is("BRADESCO"));
//        ResponseBody().body("categoryMonthsAvailables[0].cards.size()", is(3));


    }


//       List<Object> response = ResponseBody().extract().jsonPath().getList("bradesco.creditCards.bills[0].bills[0].findAll");
//        System.out.println(response);

//        ResponseBody().body("bradesco.creditCards.bills[0].bills.billStatus", is("FECHADA"));


//        Body()
//                .pathRoot("bradescoBlocked")

//        Map<String, ?> list= StringGlobal;


//                .mapping("totalSumary > cardsCount", 2)
//                .mapping("bradesco > imageName", "bradesco")
//        ;


//        Body()
//                .root("totalSumary", "cardsCount", 2)
//                .root("bradesco", "brandName", "BRADESCO")
//                .objectForArray("next", "creditCards", "hasMessageErrors")
//                .newObjectForArray("bills", "bills", "billId")
//                .getObject("bills", "auditory", "auditoryNext")
//                .root("next", "consentId", "nextconsentid")
//                .getObject("creditCardInfo", "status", "ACTIVE")
//                .root("categoryMonthsAvailables")
//                .object("dateMonth")
//                .getString("totalAmount", 100000.04)
//                .newArray("cards", "authorisationServerId")
//                .get("totalAmount")
//        ;
//        System.out.println("Método: " + StringGlobal);


//        Assert.assertThat(StringGlobal, Matchers.is(100000.04));
//        .body("", Matchers.is(""));

//        ResponseBody().body("data.id", Matchers.is("Jam"));


//        Body()
////                .root("data", "id", "Jam")
//                .root("container", "costumer");
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

//    }
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
