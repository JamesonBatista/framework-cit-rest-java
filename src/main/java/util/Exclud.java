package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


import java.io.*;
import java.nio.channels.FileChannel;

import static util.FileProperties.GetProp;
import static util.TextSystemFiles.*;

public class Exclud {
    static File search;
    static FileWriter file;
    static PrintWriter print;

    public static void FilesSystem() throws IOException {
        search = new File("src/test/resources");
        if (!search.exists()) {
            AlertFiles(search);
            new File("src/test/resources").mkdir();
        }

        search = new File("environment/data.properties");
        if (!search.exists()) {
            AlertFiles(search);
            new File("environment").mkdir();
            file = new FileWriter("environment/data.properties");
            copyFiles(file, dataProperties);
        }
        search = new File("src/test/resources/FrameworkCIT.md");
        if (!search.exists()) {
            file = new FileWriter("src/test/resources/FrameworkCIT.md");
            copyFiles(file, framework);
        }
    }

    static void AlertFiles(File files) {
        System.out.println("                                                  ▁ ▂ ▃ ▄ ▅ ▆ ▇ AVISO: ▇ ▆ ▅ ▄ ▃ ▂ ▁\n");
        System.out.println("                                        Arquivo 《《" + files + "》》 não existe no seu projeto, aguarde...\n" +
                "                                                     Assim que terminar execute o projeto novamente.\n");
    }

    public static void copyFiles(FileWriter file, String text) throws IOException {
        print = new PrintWriter(file);
        print.printf(text);
        file.close();
    }

}
