package util;

public class TextSystemFiles {

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

    public static String dataProperties= "tu=https://10.233.128.67:8443/\n" +
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

    public static String framework = "<h1>Framework RestAssured CIT</h1>\n" +
            "\n" +
            "<blockquote>O uso do framework no Bradesco necessita de alguns arquivos para executar\n" +
            "o framework CIT analisa se os arquivos existem no seu projeto, caso não, ele os cria.\n" +
            "Ao final de cada método chamado o Report Bradesco é gerado automaticamente.\n" +
            "</blockquote>\n" +
            "\n" +
            "*Além disso dentro de sua classe de execucão dos testes Ex: RegresstionTest*\n" +
            "\n" +
            "Ele irá excluir seus reports antigos a cada execução\n" +
            "\n" +
            "```androiddatabinding\n" +
            "public class RegressionTest {\n" +
            "    @BeforeClass\n" +
            "    public static void init() throws IOException, InterruptedException {\n" +
            "        ExcludReportBradesco();\n" +
            "    }\n" +
            "}\n" +
            "```\n" +
            "<h6>Seu Postman serve de referência para seu código, no Postman você pode ter:<h6>\n" +
            "\n" +
            "- get com header, parametros, endpoint, etc.\n" +
            "- post com header, parametros, endpoint, body, etc.\n" +
            "- put ....\n" +
            "- delete ...\n" +
            "\n" +
            "<h4>Como iniciar meu GET POST PUT DELETE?</h4>\n" +
            "<h6>Faremos validações no JSON de exemplo, no final do deste DOC</h6>\n" +
            "\n" +
            "_Abaixo temos um exemplo completo de como usar um simples GET_\n" +
            "\n" +
            "- Extenda o framework na classe do seu STEP como abaixo:\n" +
            "- Chame o métido InitEnvironmente(), nele você terá o start do RestAssured com todas as funcionalidades, dentro dele\n" +
            "  passe o endpoint que você quer.\n" +
            "- Get\n" +
            "  - validar statusCode\n" +
            "  - validar retorno do campo first_name no body\n" +
            "```androiddatabinding\n" +
            "public class GetUser extends CITRestAssured {\n" +
            "\n" +
            "    @Given(\"^que seja feito GET na API \\\"([^\\\"]*)\\\"$\")\n" +
            "    public void queSejaFeitoGETNaAPI(String endpoint) throws Throwable {\n" +
            "\n" +
            "        InitEnvironment(endpoint);\n" +
            "    }\n" +
            "\n" +
            "    @Then(\"^faco get$\")\n" +
            "    public void facoGet() throws Throwable {\n" +
            "\n" +
            "        Get();\n" +
            "    }\n" +
            "\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "<h4>E no caso do endpoint precisar ser passado no get() post() delete() put() e não no InitEnvironment() ?</h4>\n" +
            "_Simples, basta usar o exemplo abaixo, retire o endpoint do InitEnvironment()_\n" +
            "\n" +
            "- Se seu get tiver essa condição, chame o GetEndpoint(); conforme abaixo:\n" +
            "- E dentro dele passe o endpoint que você deseja.\n" +
            "\n" +
            "```androiddatabinding\n" +
            "public class GetUser extends CITRestAssured {\n" +
            "\n" +
            "    @Given(\"^que seja feito GET na API \\\"([^\\\"]*)\\\"$\")\n" +
            "    public void queSejaFeitoGETNaAPI(String endpoint) throws Throwable {\n" +
            "\n" +
            "        InitEnvironment();\n" +
            "    }\n" +
            "\n" +
            "    @Then(\"^faco get$\")\n" +
            "    public void facoGet() throws Throwable {\n" +
            "\n" +
            "        GetEndpoint(\"users/7\");\n" +
            "    }\n" +
            "\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "<h4>Efetuando validações</h4>\n" +
            "_Simples, usando o step acima para efetuar validações é fácil_\n" +
            "\n" +
            "- Depois do métido GetEndpoint() basta efetuar as validações como abaixo:\n" +
            "\n" +
            "```androiddatabinding\n" +
            "   GetEndpoint(\"users/7\")\n" +
            "                .statusCode(200)\n" +
            "        .body(\"data.first_name\", Matchers.is(\"Michael\"));\n" +
            "        \n" +
            "        GetEndpoint(\"users/7\")\n" +
            "                .statusCode(200)\n" +
            "        .body(\"support.url\", Matchers.is(\"https://reqres.in/#support-heading\"));\n" +
            "```\n" +
            "\n" +
            "- Caso seu endpoint esteja dentro do InitEnvironment(endpoint);\n" +
            "\n" +
            "```androiddatabinding\n" +
            " Get()\n" +
            "      .statusCode(200)\n" +
            "      .body(\"data.first_name\", Matchers.is(\"Michael\"));\n" +
            "        \n" +
            " Get()\n" +
            "     .statusCode(200)\n" +
            "     .body(\"support.url\", Matchers.is(\"https://reqres.in/#support-heading\"));\n" +
            "```\n" +
            "<h4>E para extrair uma informação? Um valor?</h4>\n" +
            "```androiddatabinding\n" +
            "String value = Get().extract().path(\"data.first_name\");\n" +
            "        System.out.println(\"valor extraído é: \" + value);\n" +
            "```\n" +
            "<h4>Em caso de um Post?</h4>\n" +
            "_Nosso framework tem a cobertura dos possíveis posts_\n" +
            "\n" +
            "*Exemplos de métodos incluídos no framework:*\n" +
            "- Post();\n" +
            "- PostBody();\n" +
            "- PostEndpoint();\n" +
            "- PostHeader();\n" +
            "- PostHeaderParam();\n" +
            " \n" +
            "E tantos outros. Basta olhar seu Postman e ver a necessidade. \n" +
            "- Post\n" +
            "  - validar statusCode\n" +
            "  - validar retorno do body\n" +
            "  - passando body que será enviado, TODO post precisa enviar um body.\n" +
            "  \n" +
            "```androiddatabinding\n" +
            "    String body = \"{}\";\n" +
            "       PostBody(body).statusCode(201).body(\"data.first_name\", Matchers.is(\"Michael\"));\n" +
            "```\n" +
            "\n" +
            "<h4>Em caso do meu post no Postman ter body, header, endpoint e param?</h4>\n" +
            "_Use a mesma lógica do código abaixo para sua necessidade de acordo com o Postman_\n" +
            "- No caso de ter um header e, ou param, você precisa chamar o params ou headers global, como abaixo.\n" +
            "- headers e params serão enviados diretamente para seu método.  \n" +
            "- É obrigatório passar os valores, isso nos garante acertividade no método.\n" +
            "```androiddatabinding\n" +
            "        params.put(\"key\", \"value\");\n" +
            "        params.put(\"key2\", \"value2\");\n" +
            "        \n" +
            "        headers.put(\"key\", \"value\");\n" +
            "        headers.put(\"key2\", \"value2\");\n" +
            "        \n" +
            "        String body = \"{}\";\n" +
            "       PostParamHeaderBodyEndpoint(\"users/7\", body)\n" +
            "       .body(\"data.first_name\", Matchers.is(\"Michael\"));\n" +
            "```\n" +
            "<h3>Use a mesma lógica de métodos acima para chamar o que você precisa, por exemplo:</h3>\n" +
            "\n" +
            "- Basta chama esse e outros métodos de acordo com a necessidade.\n" +
            "```androiddatabinding\n" +
            "        GetHeader();\n" +
            "        GetParamHeader();\n" +
            "        GetParamHeaderEndpoint();\n" +
            "        GetParam();\n" +
            "        \n" +
            "        PostParamHeaderBodyEndpoint();\n" +
            "        PostBodyEndpoint();\n" +
            "        PostHeaderBody();\n" +
            "        \n" +
            "        PutBody();\n" +
            "        PutBodyEndpoint();\n" +
            "        PutHeaderBody();\n" +
            "        \n" +
            "        Delete();\n" +
            "        DeleteEndpoint();\n" +
            "        DeleteHeader();\n" +
            "```\n" +
            "\n" +
            "<h4>Em caso de precisar ser feito um método Rest mais complexo?</h4>\n" +
            "- Chame o método Given() depois disso basta usar o when().post() , when().get, etc.\n" +
            "- Ao final do Given() será necessário chamar o método que cria o report, no Given() não é criado automaticamente\n" +
            "```androiddatabinding\n" +
            "Given().when().get();\n" +
            "ou\n" +
            "Given().formParam(\"key\", \"value\")\n" +
            ".formParam(\"key\", \"value\").when().post();  \n" +
            "\n" +
            "ReportBradesco();\n" +
            "```\n" +
            "\n" +
            "```cloudfoundry\n" +
            "{\n" +
            "    \"data\": {\n" +
            "        \"id\": 7,\n" +
            "        \"email\": \"michael.lawson@reqres.in\",\n" +
            "        \"first_name\": \"Michael\",\n" +
            "        \"last_name\": \"Lawson\",\n" +
            "        \"avatar\": \"https://reqres.in/img/faces/7-image.jpg\"\n" +
            "    },\n" +
            "    \"support\": {\n" +
            "        \"url\": \"https://reqres.in/#support-heading\",\n" +
            "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
            "    }\n" +
            "}\n" +
            "```";
}
