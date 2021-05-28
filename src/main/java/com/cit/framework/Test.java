package com.cit.framework;

import com.bradesco.core.exception.BradescoException;

import java.io.IOException;

public class Test extends CITRestAssured {
    @org.junit.Test
    public void TestFrame() throws IOException, BradescoException {
        RestEnvironment("users/7");
        Get();
    }

}
