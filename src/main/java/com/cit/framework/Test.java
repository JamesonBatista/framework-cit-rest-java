package com.cit.framework;

import com.bradesco.core.exception.BradescoException;

import java.io.IOException;

class Test extends CITRestAssured {


    @org.junit.Test
    void TestFrame() throws IOException, BradescoException {

        InitEnvironment("users/7");
        Get();

    }

}
