package util;

import com.bradesco.core.configuration.EnvironmentProperties;
import com.bradesco.core.exception.BradescoRuntimeException;
import org.apache.commons.lang3.StringUtils;

public interface Constantes {

    /**
     * Diretório para leitura dos arquivos CSV
     */
    String CANAIS_ELETRONICOS_CSV = "classpath:csv/Atendimento_Telefonico_*.csv";

    /**
     * Ambiente TU Bradesco Gateway https://api.openbanking.tu.teste.internet
     */
    //String BASE_URL_GATEWAY = "https://10.233.128.67:8443";

    /**
     * Ambiente TH Gateway https://api.openbanking.prebanco.com.br:8443
     */
    String BASE_URL_GATEWAY = selecionaAmbiente();

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

    public static String selecionaAmbiente() {
        EnvironmentProperties env = EnvironmentProperties.getGlobal();
        String environment = env.asString("environment", "tu");
        String url;
        if (StringUtils.equalsIgnoreCase(environment, "tu")) {
            url = "https://10.233.128.67:8443/";
        } else if (StringUtils.equalsIgnoreCase(environment, "ti")) {
            throw new BradescoRuntimeException("Ambiente desconhecido: " + environment);
        } else if (StringUtils.equalsIgnoreCase(environment, "th")) {
            url = "https://api.openbanking.prebanco.com.br";
        } else if (StringUtils.equalsIgnoreCase(environment, "tu_local")) {
            url = "http://10.233.129.43:8443/v1/";
        } else if (StringUtils.equalsIgnoreCase(environment, "local")) {
            url = "http://localhost:8080/";
        } else if (StringUtils.equalsIgnoreCase(environment, "prod")) {
            url = "https://api.bradesco.com";
        } else {
            throw new BradescoRuntimeException("Ambiente desconhecido: " + environment);
        }
        System.out.println("Ambiente selecionado: " + url + "(" + environment + ")");
        return url;
    }
}
