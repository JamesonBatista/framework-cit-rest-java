package com.cit.framework;

import com.bradesco.core.exception.BradescoException;

import java.io.IOException;

import static com.cit.framework.Exclud.FilesSystem;

public class Test extends CITRestAssured {

    @org.junit.Test
    public void TestFrame() throws IOException, BradescoException, InterruptedException {
        FilesSystem();

        InitEnvironment("users/7");
//        Get();
        PostBody("{}");
//        PutBody("{}");
//        Delete();
    }


}
