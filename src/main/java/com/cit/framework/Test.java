package com.cit.framework;

import com.bradesco.core.exception.BradescoException;

import java.io.File;
import java.io.IOException;

public class Test extends CITRestAssured {
    File file;

    @org.junit.Test
    public void TestFrame() throws IOException, BradescoException, InterruptedException {


        InitEnvironment("users/7");
        Get();
    }


}
