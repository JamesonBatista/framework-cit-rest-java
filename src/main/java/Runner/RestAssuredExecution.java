package Runner;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

public class RestAssuredExecution {

    public ValidatableResponse Get() {
        return given().when().get().then();
    }
}
