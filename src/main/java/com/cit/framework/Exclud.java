package com.cit.framework;

import cucumber.api.java.cs.A;

import java.io.*;
import java.nio.channels.FileChannel;

import static util.FileProperties.GetProp;
import static util.TextSystemFiles.*;

public class Exclud {
    static File search;
    static FileWriter file;
    static PrintWriter print;

    static void FilesSystem() throws IOException, InterruptedException {
        search = new File("src/test/resources");
        if (!search.exists()) {
            AlertFiles(search);
            new File("src/test/resources").mkdir();

        }
        search = new File("src/test/resources/setup.properties");
        if (!search.exists()) {
            AlertFiles(search);
            file = new FileWriter("src/test/resources/setup.properties");
            copyFiles(file, setup);
        }
        search = new File("src/test/resources/leanft.properties");
        if (!search.exists()) {
            AlertFiles(search);
            file = new FileWriter("src/test/resources/leanft.properties");
            copyFiles(file, leanft);
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

    public static void ExcludReportBradesco() throws IOException, InterruptedException {
        FilesSystem();
        // Método irá excluir todos os Reports antigos
        File folder = new File(GetProp().getProperty("excludReport"));
        if (folder.isDirectory()) {
            File[] sun = folder.listFiles();
            for (File toDelete : sun) {
                toDelete.delete();
            }
        }
    }

    public static void ConsoleDesigner(String DELETE) throws IOException {
        System.out.println("\n");
        System.out.println("*                                        ******* Iniciando Report® *******                                    *");
        System.out.println("*                                        ********** " + DELETE + " ***********                                    *\n");
        System.out.println("                                  √  Report Bradesco gerado no path: *** " + GetProp().getProperty("excludReport") + " ***\n");
    }
}
