package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileProperties {
    public static String ReturnEnvironment() {
        String vEnvironment;
        vEnvironment = System.getProperty("environment");
        return vEnvironment;

    }

    public static Properties GetProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./environment/data.properties");
        props.load(file);
        return props;
    }
}

