package util;

public class StringFilesSystem {

    public static String setup = "startup.web.browser=CHROME\n" +
            "env.var.webdriver.chrome.driver=C:/Temp/chromedriver_win32/chromedriver.exe\n" +
            "reporter.log_dir=target/logs/\n" +
            "reporter.evidence_dir=target/screenshots/\n" +
            "reporter.formatter=pdfFormatter\n" +
            "reporter.fmt_export={date}-{testStatus}-{testId}\n" +
            "reporter.plugin.render_strategy=AUTO_AT_END_OF_SCENARIO";

    public static String leanft = "autoLaunch=false\n" +
            "title=CCS - Centro Certificação de Sistemas\n" +
            "description=Mobile Pessoa Fisica";

    public static String dataProperties = "tu=https://10.233.128.67:8443/\n" +
            "ti=coloqueAquiSeuAmbienteSemAspasDuplas\n" +
            "th=https://api.openbanking.prebanco.com.br\n" +
            "tu_local=http://10.233.129.43:8443/v1/\n" +
            "local=http://localhost:8080/\n" +
            "prod=https://api.bradesco.com\n" +
            "url.local=http://localhost:8080/\n" +
            "env1=http://demo0623716/2345\n" +
            "env2=https://reqres.in/api/\n" +
            "env3=\n" +
            "env4=https://3lkjfnsgalsfnagl\n" +
            "default= env2\n" +
            "excludReport=target/logs/";
}
