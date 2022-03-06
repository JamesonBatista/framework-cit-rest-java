package util;

import com.bradesco.core.configuration.EnvironmentProperties;
import com.bradesco.core.exception.BradescoRuntimeException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static util.FileProperties.GetProp;

public interface Constantes {

    /**
     * Diretório para leitura dos arquivos CSV
     */
    String CANAIS_ELETRONICOS_CSV = "classpath:csv/Atendimento_Telefonico_*.csv";

    /**
     * Ambiente TU Bradesco Gateway https://api.openbanking.tu.teste.internet
     */
//    String BASE_URL_GATEWAY = selecionaAmbiente();

    /**
     * Ambiente TH Gateway https://api.openbanking.prebanco.com.br:8443
     */

    /**
     * Ambiente PROD Gateway https://api.bradesco.com
     */
    //String BASE_URL_GATEWAY = "191.234.204.22";

    /**
     * Ambientes DEV CI&T microserviços
     */
//    String BASE_URL_CA = "http://52.224.147.14";
//    String BASE_URL_PS = "http://40.76.156.91";

    /**
     * Ambientes TU Bradesco micro serviços
     */
//    String BASE_URL_CA = "http://10.233.129.38:8443";
//    String BASE_URL_PS = "http://10.233.129.39:8443";

    String PATH = "SCHEMA/";

    static String selecionaAmbiente() throws IOException {
        EnvironmentProperties env = EnvironmentProperties.getGlobal();
        String environment = env.asString("environment", "tu");
        String url;
        if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "tu")) {
            url = GetProp().getProperty("tu");
        } else if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "ti")) {
            url = GetProp().getProperty("ti");
        } else if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "th")) {
            url = GetProp().getProperty("th");
        } else if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "tu_local")) {
            url = GetProp().getProperty("tu_local");
        } else if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "local")) {
            url = GetProp().getProperty("local");
        } else if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "prod")) {
            url = GetProp().getProperty("prod");
        } else if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "env1")) {
            url = GetProp().getProperty("env1");
        } else if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "env2")) {
            url = GetProp().getProperty("env2");
        } else if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "env3")) {
            url = GetProp().getProperty("env3");
        } else if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "env4")) {
            url = GetProp().getProperty("env4");
        } else if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "env5")) {
            url = GetProp().getProperty("env5");
        } else if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "env6")) {
            url = GetProp().getProperty("env6");
        } else if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "env7")) {
            url = GetProp().getProperty("env7");
        } else if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "env8")) {
            url = GetProp().getProperty("env8");
        } else if (StringUtils.equalsIgnoreCase(GetProp().getProperty("default"), "env9")) {
            url = GetProp().getProperty("env9");
        } else {
            throw new BradescoRuntimeException("\n                             Ambiente desconhecido: " + environment);
        }
        return url;
    }
}
