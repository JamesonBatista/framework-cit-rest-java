package com.cit.framework;

import java.io.*;
import java.nio.channels.FileChannel;

import static util.FileProperties.GetProp;
import static util.TextSystemFiles.*;

public class Exclud {

    static FileWriter file;
    static PrintWriter print;

    static void FilesSystem() throws IOException, InterruptedException {
        if (!new File("src/test/resources").exists()) {
            System.out.println("               AVISO:    A pasta -resources- não existe no projeto, estamos configurando...\n" +
                    "                            Assim que terminar execute o projeto novamente.");

            new File("src/test/resources").mkdir();

        }

        if (!new File("src/test/resources/setup.properties").exists()) {
            System.out.println("              AVISO:  Arquivo setup.properties não existe no seu projeto, aguarde...\n" +
                    "                           Assim que terminar execute o projeto novamente.");

            file = new FileWriter("src/test/resources/setup.properties");
            copyFiles(file, setup);
        }

        if (!new File("src/test/resources/leanft.properties").exists()) {
            System.out.println("              AVISO:  Arquivo leanft.properties não existe no seu projeto, aguarde...\n" +
                    "                           Assim que terminar execute o projeto novamente.");

            file = new FileWriter("src/test/resources/leanft.properties");
            copyFiles(file, leanft);
        }

        if (!new File("environment/data.properties").exists()) {
            System.out.println("              AVISO:  Arquivo environment/data.properties não existe no seu projeto, aguarde...\n" +
                    "                           OK, a pasta foi criada com os Ambientes do Sistema.");

            new File("environment").mkdir();
            file = new FileWriter("environment/data.properties");
            copyFiles(file, dataProperties);
        }
        if (!new File("src/test/resources/FrameworkCIT.md").exists()) {

            file = new FileWriter("src/test/resources/FrameworkCIT.md");
            copyFiles(file, framework);
        }
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

    public static void ConsoleDesigner(String DELETE) {
        System.out.println("\n\n");
        System.out.println("*                                        *********************************                                    *");
        System.out.println("*                                        ******* Iniciando Report ********                                    *");
        System.out.println("*                                        ********* Bradesco CI&T® ********                                    *");
        System.out.println("*                                        ********** " + DELETE + " ***********                                    *");
        System.out.println("*                                        *********************************                                    *");

    }
}
