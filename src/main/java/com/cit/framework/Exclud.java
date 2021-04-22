package com.cit.framework;

import java.io.File;
import java.io.IOException;

import static util.FileProperties.GetProp;

public class Exclud {

    public static void ExcludReportBradesco() throws IOException {
        // Método irá excluir todos os Reports antigos
        File folder = new File(GetProp().getProperty("excludReport"));
        if (folder.isDirectory()) {
            File[] sun = folder.listFiles();
            for (File toDelete : sun) {
                toDelete.delete();
            }
        }
    }
}
