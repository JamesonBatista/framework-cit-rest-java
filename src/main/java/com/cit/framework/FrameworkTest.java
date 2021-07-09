package com.cit.framework;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

public class FrameworkTest extends CITRestAssured {


    @Test
    public void TestFrame() throws Exception {
        InitEnvironment();

//        body(String path, Matcher<?> matcher, Object... additionalKeyMatcherPairs);
//        params.put("a", "s");
        Get(false);

        ResponseBody().body("bradesco.brandName", Matchers.is("BRADESCO"),
                "bradesco.creditCards.creditCardInfo[0].creditCardNetwork", is("VISA"));

        ResponseBody().body("bradesco.creditCards.bills[0].bills[0].billStatus", is("FECHADA"));

        ResponseBody().body("others[0].creditCards.creditCardInfo[1].consentId", is("itauconsentid"));

        ResponseBody().body("others[0].creditCards.bills[0].bills[0].billStatus", is("PAGA"));

        ResponseBody().body("bradescoBlocked.creditCards.bills[0].auditory", is(nullValue()));

        String response = ResponseBody().extract().response().path("categoryMonthsAvailables[0].totalAmount").toString();
        BigDecimal bigDecimal = new BigDecimal(response);
        Assert.assertThat(bigDecimal.doubleValue(), is(200000.08));

        ResponseBody().body("categoryMonthsAvailables[0].cards.internalBrand[2]", is("BRADESCO"));

        ResponseBody().body("categoryMonthsAvailables[0].cards.size()", is(3));
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
        System.out.println("Método: " + StringGlobal);


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
