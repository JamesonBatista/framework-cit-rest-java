package com.cit.framework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static util.FileProperties.GetProp;
import static util.StringFilesSystem.*;

public class Exclud {
    static File file;


    static void FilesSystem() throws IOException, InterruptedException {
        if (!new File("src/test/resources").exists()) {
            System.out.println("               AVISO:    A pasta -resources- não existe no projeto, estamos configurando...\n" +
                    "                            Assim que terminar execute o projeto novamente.");
            new File("src/test/resources").mkdir();

        }
        file = new File("src/test/resources/setup.properties");
        if (!file.exists()) {
            System.out.println("              AVISO:  Arquivo setup.properties não existe no seu projeto, aguarde...\n" +
                    "                           Assim que terminar execute o projeto novamente.");
            generateFilesSystem(setup);
        }

        file = new File("src/test/resources/leanft.properties");
        if (!file.exists()) {
            System.out.println("              AVISO:  Arquivo leanft.properties não existe no seu projeto, aguarde...\n" +
                    "                           Assim que terminar execute o projeto novamente.");
            generateFilesSystem(leanft);
        }

        file = new File("environment/data.properties");
        if (!file.exists()) {
            System.out.println("              AVISO:  Arquivo environment/data.properties não existe no seu projeto, aguarde...\n" +
                    "                           OK, a pasta foi criada com os Ambientes do Sistema.");
            new File("environment").mkdir();
            generateFilesSystem(dataProperties);
        }
    }

    static void generateFilesSystem(String archive) throws IOException, InterruptedException {
        FileWriter writer = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.printf(archive);
        writer.close();
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
