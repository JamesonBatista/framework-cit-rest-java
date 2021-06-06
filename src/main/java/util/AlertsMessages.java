package util;

import com.bradesco.core.exception.BradescoRuntimeException;
import com.cit.framework.ClassReport;

public class AlertsMessages extends ClassReport {

    public void AlertReturnIsEmpty() {
        System.out.println("                                                  ▁ ▂ ▃ ▄ ▅ ▆ ▇ AVISO: ▇ ▆ ▅ ▄ ▃ ▂ ▁\n");
        System.err.println("Seu retorno do método " + ReturnMetodo() + " está vazio.  \n" +
                "Veja se está acessando o Endpoint correto abaixo:\n" +
                "Endpoint: " + URIFinal());
    }

    public void ResponseNull() {
        System.out.println("                                                  ▁ ▂ ▃ ▄ ▅ ▆ ▇ ERROR: ▇ ▆ ▅ ▄ ▃ ▂ ▁\n");
        System.out.println("                                                   URI: " + URIFinal());
        throw new BradescoRuntimeException("\n\n Seu Response está NULL, talvez você tenha batido no Endpoint errado\n" +
                "   Olhe dentro do environment/data.properties, ou na sua Feature e verifique se o endpoint está correto.\n" +
                "   Ou, você não passou o parâmetro corretamente.\n" +
                "   Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
    }

    public void ErroUseGivenExternal() {
        System.out.println("                                                  ▁ ▂ ▃ ▄ ▅ ▆ ▇ ERROR: ▇ ▆ ▅ ▄ ▃ ▂ ▁\n");
        System.out.println("                                                   URI: " + URIFinal());
        throw new BradescoRuntimeException("\n\n O ExternalReport() só pode ser usado pelo GivenExternal\n" +
                " Olhe o DOC FrameworkCIT dentro " +
                "da pasta 《《 src/test/resources/FrameworkCIT.md 》》 para entender como usar.");
    }

    public void SendPostOffBody() {
        System.out.println("                                                  ▁ ▂ ▃ ▄ ▅ ▆ ▇ AVISO: ▇ ▆ ▅ ▄ ▃ ▂ ▁\n");
        System.err.println("A chamada POST normalmente precisa que no ExternalReport seja passado o body enviado \n" +
                "Ex: ExternalReport(body, response.extract().response);\n\n");
    }

    public void UseExportReportFill() {
        System.out.println("                                                  ▁ ▂ ▃ ▄ ▅ ▆ ▇ ERROR: ▇ ▆ ▅ ▄ ▃ ▂ ▁\n");
        throw new BradescoRuntimeException("\n\nMétodo DELETE não pode ser usado com o ExternalReport(response.extract().response()); preenchido,\n" +
                "Por favor deixe o ExternalReport();  vazio.\nEm caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
    }
}
