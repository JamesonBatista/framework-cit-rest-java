package com.cit.framework;

import com.bradesco.core.exception.BradescoException;
import io.restassured.filter.log.RequestLoggingFilter;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Scanner;

import static io.restassured.RestAssured.given;

class Test extends CITRestAssured {
    static StringWriter requestWriter;
    static PrintStream requestCapture;

    @org.junit.Test
    void TestFrame() throws IOException, BradescoException {
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);
        RestEnvironment("users/7");
//        Get();
        given().filter(new RequestLoggingFilter(requestCapture)).when().get();
        String result;
        Scanner sc = new Scanner(requestWriter.toString());
        while (sc.hasNext()) {
            result = sc.next();
            if (result.contains("URI:")) {
                result = sc.next();
                System.out.println("uri " + result);
                break;
            }
        }
    }

}
