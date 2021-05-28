package com.cit.framework;

import com.bradesco.core.exception.BradescoException;

import java.io.IOException;

import static com.cit.framework.Exclud.FilesSystem;

 class Test extends CITRestAssured {

    @org.junit.Test
     void TestFrame() throws IOException, BradescoException, InterruptedException {
        FilesSystem();

        InitEnvironment("users/7");
        Get();
    }


}
