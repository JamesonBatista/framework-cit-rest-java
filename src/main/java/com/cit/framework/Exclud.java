package com.cit.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import static util.FileProperties.GetProp;

public class Exclud {
    static File file;
    static File dest;
    static FileChannel sourceChannel = null;
    static FileChannel destinationChannel = null;


    static void FilesSystem() throws IOException, InterruptedException {
        if (!new File("src/test/resources").exists()) {
            System.out.println("               AVISO:    A pasta -resources- não existe no projeto, estamos configurando...\n" +
                    "                            Assim que terminar execute o projeto novamente.");

            new File("src/test/resources").mkdir();

        }

        if (!new File("src/test/resources/setup.properties").exists()) {
            System.out.println("              AVISO:  Arquivo setup.properties não existe no seu projeto, aguarde...\n" +
                    "                           Assim que terminar execute o projeto novamente.");

            file = new File("src/test/resources/fileSystem/setup.properties");
            dest = new File("src/test/resources/setup.properties");
            copyFiles(file, dest);
        }

        if (!new File("src/test/resources/leanft.properties").exists()) {
            System.out.println("              AVISO:  Arquivo leanft.properties não existe no seu projeto, aguarde...\n" +
                    "                           Assim que terminar execute o projeto novamente.");

            file = new File("src/test/resources/fileSystem/leanft.properties");
            dest = new File("src/test/resources/leanft.properties");
            copyFiles(file, dest);
        }

        file = new File("environment/data.properties");
        if (!file.exists()) {
            System.out.println("              AVISO:  Arquivo environment/data.properties não existe no seu projeto, aguarde...\n" +
                    "                           OK, a pasta foi criada com os Ambientes do Sistema.");

            new File("environment").mkdir();
            file = new File("src/test/resources/fileSystem/data.properties");
            dest = new File("environment/data.properties");
            copyFiles(file, dest);
        }
        if (!new File("src/test/resources/FrameworkCIT.md").exists()) {

            file = new File("src/test/resources/fileSystem/FrameworkCIT.md");
            dest = new File("src/test/resources/FrameworkCIT.md");
            copyFiles(file, dest);
        }
    }

    public static void copyFiles(File aqr, File destination) throws IOException {

        if (!destination.exists()) {
            try {
                sourceChannel = new FileInputStream(aqr).getChannel();
                destinationChannel = new FileOutputStream(destination).getChannel();
                sourceChannel.transferTo(0, sourceChannel.size(),
                        destinationChannel);
            } finally {
                if (sourceChannel != null && sourceChannel.isOpen())
                    sourceChannel.close();
                if (destinationChannel != null && destinationChannel.isOpen())
                    destinationChannel.close();
            }
        }

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
