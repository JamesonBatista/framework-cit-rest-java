package com.cit.framework;

import org.junit.jupiter.api.Test;


 class TestRest extends CITRestAssured {

    @Test
     void TestFrame() throws Exception {
        InitEnvironment("users/7");
//        Get();
        ExternalContainsJSON = GivenExternal()
                .when().get().then();
        Body()
                .contains("data");
    }
}
