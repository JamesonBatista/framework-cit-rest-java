package util;

public class TextSystemFiles {

    public static String settings_XML = "<settings>\n" +
            "    <mirrors>\n" +
            "        <mirror>\n" +
            "            <id>central</id>\n" +
            "            <mirrorOf>central</mirrorOf>\n" +
            "            <name>Central Repository</name>\n" +
            "            <url>https://nexusrepository.bradesco.com.br:8443/repository/centrodecertificacaodesistemas-maven-central/\n" +
            "            </url>\n" +
            "        </mirror>\n" +
            "        <mirror>\n" +
            "            <id>central-no-ssl</id>\n" +
            "            <name>Central without ssl</name>\n" +
            "            <url>http://repo.maven.apache.org/maven2</url>\n" +
            "            <mirrorOf>central</mirrorOf>\n" +
            "        </mirror>\n" +
            "    </mirrors>\n" +
            "    <servers>\n" +
            "        <server>\n" +
            "            <id>central</id>\n" +
            "             <username>USER</username>\n" +
            "             <password>SENHA</password>\n" +
            "        </server>\n" +
            "        <server>\n" +
            "            <id>centrodecertificacaodesistemas</id>\n" +
            "            <username>USER</username>\n" +
            "            <password>SENHA</password>\n" +
            "            <configuration>\n" +
            "                <timeout>600000</timeout> <!-- 5 seconds -->\n" +
            "            </configuration>\n" +
            "        </server>\n" +
            "    </servers>\n" +
            "    <profiles>\n" +
            "        <profile>\n" +
            "            <id>properties</id>\n" +
            "            <activation>\n" +
            "                <activeByDefault>true</activeByDefault>\n" +
            "            </activation>\n" +
            "            <properties>\n" +
            "                <repository.base>https://nexusrepository.bradesco.com.br:8443/repository/\n" +
            "                </repository.base>\n" +
            "                <repository.maven.central>-maven-central/</repository.maven.central>\n" +
            "                <repository.maven.snapshot>-maven-snapshot/\n" +
            "                </repository.maven.snapshot>\n" +
            "                <repository.maven.release>-maven-release/</repository.maven.release>\n" +
            "                <repository_domain_name>centrodecertificacaodesistemas</repository_domain_name>\n" +
            "                <bamboo_inject_dominio>centrodecertificacaodesistemas</bamboo_inject_dominio>\n" +
            "            </properties>\n" +
            "        </profile>\n" +
            "    </profiles>\n" +
            "</settings>  ";

    public static String setup = "startup.web.browser=CHROME\n" +
            "env.var.webdriver.chrome.driver=C:/Temp/chromedriver_win32/chromedriver.exe\n" +
            "reporter.log_dir=target/logs\n" +
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
            "excludReport=target/logs";

    public static String framework =
            "<h1>Framework RestAssured CIT</h1>\n" +
                    "\n" +
                    ">>Acesse  link driver com o JAR, vídeos de validações, etc.  \n" +
                    "<a href=\"https://drive.google.com/drive/folders/10cRqFVxSy7iGjh5mmsoEbF41x-lt9nQZ?usp=sharing\">Acesse aqui</a>\n" +
                    "\n" +
                    "<blockquote>O uso do framework no Bradesco necessita de alguns arquivos para executar,\n" +
                    "o framework CIT analisa se os arquivos existem no seu projeto, caso não, ele os cria.\n" +
                    "Ao final de cada método chamado o Report Bradesco é gerado automaticamente.\n" +
                    "</blockquote>\n" +
                    "\n" +
                    "*Além disso dentro de sua classe de execucão dos testes Ex: RegresstionTest*\n" +
                    "\n" +
                    "Ele irá excluir seus reports antigos a cada execução\n" +
                    "\n" +
                    "> > Em caso de chamar métodos sem precisar usar o reportBradesco\n" +
                    ">\n" +
                    "> Basta apenas passar dentro do método como último parâmetro o **false**, exemplo:\n" +
                    ">\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    " GetEndpoint(\"users/7\", false);\n" +
                    " Get(false);\n" +
                    " PostHeader(false);\n" +
                    "\n" +
                    "```\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "public class RegressionTest {\n" +
                    "    @BeforeClass\n" +
                    "    public static void init() throws IOException, InterruptedException {\n" +
                    "        ExcludReportBradesco();\n" +
                    "    }\n" +
                    "}\n" +
                    "```\n" +
                    "\n" +
                    "***\n" +
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
                    "\n" +
                    "- Get\n" +
                    "\n" +
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
                    "***\n" +
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
                    "***\n" +
                    "<h4>Efetuando validações</h4>\n" +
                    "_Simples, usando o step acima para efetuar validações é fácil_\n" +
                    "\n" +
                    "- Depois do método GetEndpoint() basta efetuar as validações como abaixo:\n" +
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
                    "\n" +
                    "***\n" +
                    "\n" +
                    "* Para efetuar testes de Contrato ou Schema.\n" +
                    "\n" +
                    "> > Pode ser com qualquer um dos métodos do framework.\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "        Get().body(matchesJsonSchemaInClasspath(\"SCHEMA/jsontestado.json\"));\n" +
                    "\n" +
                    "```\n" +
                    "\n" +
                    "***\n" +
                    "<h4>E para extrair uma informação? Um valor?</h4>\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "String value = Get().extract().path(\"data.first_name\");\n" +
                    "        System.out.println(\"valor extraído é: \" + value);\n" +
                    "```\n" +
                    "\n" +
                    "***\n" +
                    "<h4>Em caso de um Post?</h4>\n" +
                    "_Nosso framework tem a cobertura dos possíveis posts_\n" +
                    "\n" +
                    "*Exemplos de métodos incluídos no framework:*\n" +
                    "\n" +
                    "- Post();\n" +
                    "- PostBody();\n" +
                    "- PostEndpoint();\n" +
                    "- PostHeader();\n" +
                    "- PostHeaderParam();\n" +
                    "\n" +
                    "E tantos outros. Basta olhar seu Postman e ver a necessidade.\n" +
                    "\n" +
                    "- Post\n" +
                    "    - validar statusCode\n" +
                    "    - validar retorno do body\n" +
                    "    - passando body que será enviado, TODO post precisa enviar um body.\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "    String body = \"{}\";\n" +
                    "       PostBody(body).statusCode(201).body(\"data.first_name\", Matchers.is(\"Michael\"));\n" +
                    "```\n" +
                    "\n" +
                    "***\n" +
                    "<h4>Em caso do meu post no Postman ter body, header, endpoint e param?</h4>\n" +
                    "_Use a mesma lógica do código abaixo para sua necessidade de acordo com o Postman_\n" +
                    "\n" +
                    "- No caso de ter um header e, ou param, você precisa chamar o params ou headers global, como abaixo.\n" +
                    "- headers e params serão enviados diretamente para seu método.\n" +
                    "- É obrigatório passar os valores, isso nos garante acertividade no método.\n" +
                    "\n" +
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
                    "\n" +
                    "***\n" +
                    "<h4>Use a mesma lógica de métodos acima para chamar o que você precisa, por exemplo:</h4>\n" +
                    "\n" +
                    "- Basta chama esse e outros métodos de acordo com a necessidade.\n" +
                    "\n" +
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
                    "***\n" +
                    "<h4>Quais métodos temos?</h4>\n" +
                    "\n" +
                    "- Gets\n" +
                    "    - Get\n" +
                    "    - GetEndpoint\n" +
                    "    - GetParamHeaderEndpoint\n" +
                    "    - GetParam\n" +
                    "    - GetParamEndpoint\n" +
                    "    - GetParamHeader\n" +
                    "    - GetHeader\n" +
                    "    - GetHeaderEndpoint\n" +
                    "\n" +
                    "- Posts\n" +
                    "    - Post\n" +
                    "    - PostBody\n" +
                    "    - PostBodyEndpoint\n" +
                    "    - PostParamHeaderBodyEndpoint\n" +
                    "    - PostParamHeaderBody\n" +
                    "    - PostParamBodyEndpoint\n" +
                    "    - PostParamBody\n" +
                    "    - PostHeaderBody\n" +
                    "    - PostHeaderBodyEndpoint\n" +
                    "\n" +
                    "- Puts\n" +
                    "    - PutBody\n" +
                    "    - PutBodyEndpoint\n" +
                    "    - PutParamHeaderBodyEndpoint\n" +
                    "    - PutParamHeaderBody\n" +
                    "    - PutParamBodyEndpoint\n" +
                    "    - PutParamBody\n" +
                    "    - PutHeaderBody\n" +
                    "    - PutHeaderBodyEndpoint\n" +
                    "\n" +
                    "- Deletes\n" +
                    "    - Delete\n" +
                    "    - DeleteEndpoint\n" +
                    "    - DeleteParam\n" +
                    "    - DeleteParamEndpoint\n" +
                    "    - DeleteParamHeader\n" +
                    "    - DeleteHeader\n" +
                    "    - DeleteHeaderEndpoint\n" +
                    "    - DeleteParamHeaderEndpoint\n" +
                    "\n" +
                    "- Métodos diversos\n" +
                    "    - CertificationSpec - Metodo que usar certificados\n" +
                    "    - JwtPS256 - Criar JWT PS256\n" +
                    "    - JwtHS256 - Criar JWT HS256\n" +
                    "\n" +
                    "***\n" +
                    "<h4>Método separado</h4>\n" +
                    "\n" +
                    "> Existe um método chamado GivenExternal();\n" +
                    "\n" +
                    "Para o caso de, você precisar usar uma chamada diferente, com caracteristicas que os métodos existentes não atendam. Em\n" +
                    "métodos separados, é necessário usar o GivenExternal do framework para que ele use os dados no Report.\n" +
                    "\n" +
                    "Ao final, chame o ExternalReport(); para que seja gerado\n" +
                    "\n" +
                    "**E como usar?**\n" +
                    "*Simples!*\n" +
                    "\n" +
                    "- *_Apenas em caso de DELETES dentro do ExternalReporte(); precisa ficar vazio_*\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "        Response res = GivenExternal(ContentType.JSON)\n" +
                    "                .when()\n" +
                    "                .get()\n" +
                    "                .then()([[Aqui poeria efetuar validações normalmente. Ex: then().body(\"path\"), is(\"CIT\")]\n" +
                    "                .extract().response();\n" +
                    "        ExternalReport(res);\n" +
                    "        \n" +
                    "        GivenExternal(ContentType.JSON)\n" +
                    "                .when()\n" +
                    "                .delete()\n" +
                    "                .then() ([[Aqui poeria efetuar validações normalmente. Ex: then().body(\"path\"), is(\"CIT\")]])'\n" +
                    "                .extract().response();\n" +
                    "       ExternalReport();\n" +
                    "```\n" +
                    "\n" +
                    "***\n" +
                    "\n" +
                    "<h4>Etraindo valor do método GivenExternal</h4>\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "\n" +
                    " ValidatableResponse res = GivenExternal(ContentType.JSON)\n" +
                    "                .when()\n" +
                    "                .get()\n" +
                    "                .then();\n" +
                    "        String value = res.extract().path(\"path que eu quero\");\n" +
                    "        \n" +
                    "        ## caso precise de report, use as lógicas já descrita abaixo.\n" +
                    "        ExternalReport(res.extract().response());\n" +
                    "```\n" +
                    "\n" +
                    "***\n" +
                    "<h4>Quais dados usar no ExternalReport(); ?</h4>\n" +
                    "\n" +
                    "- Gets - Basta enviar o resultado da requisição. Como no exemplo acima.\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "\n" +
                    "    @Then(\"^faco get$\")\n" +
                    "    public void facoGet() throws Throwable {\n" +
                    "        \n" +
                    "        ValidatableResponse res = GivenExternal(ContentType.JSON)\n" +
                    "                .when().get(\"users/7\").then();\n" +
                    "\n" +
                    "        ExternalReport(res.extract().response());\n" +
                    "\n" +
                    "    }\n" +
                    "```\n" +
                    "\n" +
                    "- Posts - você precisa passar primeiro o Body que está sendo enviado, depois o resultado da requisição.\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "String body = \"{body que será enviado}\";\n" +
                    "\n" +
                    "  Response res = GivenExternal(ContentType.JSON)\n" +
                    "                .body(body)\n" +
                    "                .when()\n" +
                    "                .post()\n" +
                    "                .then()([[Aqui poderia efetuar validações normalmente. Ex: then().body(\"path\"), is(\"CIT\")]\n" +
                    "                .extract().response();\n" +
                    "                \n" +
                    "             ExternalReport(body, res);\n" +
                    "                \n" +
                    "```\n" +
                    "\n" +
                    "- Puts - repetem a mesma lógica que o Post\n" +
                    "\n" +
                    "***\n" +
                    "<h4>Como ficam métodos GivenExternal com headers e parâmetros? </h4>\n" +
                    "\n" +
                    "*Simples!*\n" +
                    "\n" +
                    "- Basta usa-los dessa forma:\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "    Map<String, Object> param = new new HashMap<>();\n" +
                    "    param.put(\"key\", \"value\");\n" +
                    "        \n" +
                    "    Map<String, Object> header = new new HashMap<>(); \n" +
                    "    header.put(\"key\", \"value\");\n" +
                    "    \n" +
                    "    Response res = GivenExternal(ContentType.JSON)\n" +
                    "                .headers(header)\n" +
                    "                .queryParams(param)\n" +
                    "                .body(body)\n" +
                    "                .when()\n" +
                    "                .post()\n" +
                    "                .then()([[Aqui poeria efetuar validações normalmente. Ex: then().body(\"path\"), is(\"CIT\")]\n" +
                    "                .extract().response();\n" +
                    "                \n" +
                    "             ExternalReport(body, res);\n" +
                    "\n" +
                    "```\n" +
                    "\n" +
                    "*****\n" +
                    "<h4>Vamos usar alguns exemplos:</h4>\n" +
                    "> Extrair valores: Em caso do método não precisar usar o report Bradesco,\n" +
                    "> use o **false** dentro do método. Você pode trocar o Get() por qualquer outro método.\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "  String valorExtraido = Get().extract().path(\"data.first_name\");\n" +
                    "        System.out.println(\"Valor extraído:  \" + valorExtraido);\n" +
                    "```\n" +
                    "\n" +
                    "> Validar se no Response existe determinado Path, ou campo.\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "   Get().statusCode(HttpStatus.SC_OK)\n" +
                    "                .body(containsString(\"first_name\"));\n" +
                    "```\n" +
                    "\n" +
                    "> **Comparar valores:**\n" +
                    "\n" +
                    " ```androiddatabinding\n" +
                    " \n" +
                    "   Get().body(\"data.first_name\", Matchers.is(\"Michael\"));\n" +
                    "   \n" +
                    "```\n" +
                    "\n" +
                    "> Extraindo 2 valores e comparando\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "    Get();\n" +
                    "    \n" +
                    "    String value1 = ResponseBody().extract().path(\"data.first_name\");\n" +
                    "    \n" +
                    "    String value2 = ResponseBody().extract().path(\"data.email\");\n" +
                    "    \n" +
                    "    Assert.assertEquals(value1, value2);\n" +
                    "```\n" +
                    "\n" +
                    "*Use o Body() para efetuar valições de existências de campos, abaixo um exemplo de validação em alguns campos.*\n" +
                    "\n" +
                    "**Usando como o exemplo abaixo você está validando cada objeto dentro do array data.**\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "{\n" +
                    "    \"page\": 1,\n" +
                    "    \"per_page\": 6,\n" +
                    "    \"total\": 12,\n" +
                    "    \"total_pages\": 2,\n" +
                    "    \"data\": [\n" +
                    "        {\n" +
                    "            \"id\": 1,\n" +
                    "            \"name\": \"cerulean\",\n" +
                    "            \"year\": 2000,\n" +
                    "            \"color\": \"#98B2D1\",\n" +
                    "            \"pantone_value\": \"15-4020\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": 2,\n" +
                    "            \"name\": \"fuchsia rose\",\n" +
                    "            \"year\": 2001,\n" +
                    "            \"color\": \"#C74375\",\n" +
                    "            \"pantone_value\": \"17-2031\"\n" +
                    "        }]\n" +
                    "\n" +
                    "           Get();\n" +
                    "            \n" +
                    "          Body().root(\"data\")\n" +
                    "        .object(\"id\", \"name\")\n" +
                    "        \n" +
                    "        OU\n" +
                    "        \n" +
                    "                Body()\n" +
                    "                .contains(\"page\",\n" +
                    "                \"per_pag\",\n" +
                    "                \"total\",\n" +
                    "                \"total_pages\",\n" +
                    "                .root(\"data\")\n" +
                    "                .object(\"id\", \"name\", \"color\", \"pantone_value\");\n" +
                    "        \n" +
                    "        \n" +
                    "```\n" +
                    "\n" +
                    "> **Exemplo 2 - Agora usando um Array JSON**\n" +
                    "> *Quando sua validação for um Array JSON de objetos conforme abaixo, use conforme está o exemplo.*\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "[\n" +
                    "    {\n" +
                    "        \"id\": 1,\n" +
                    "        \"name\": \"Leanne Graham\",\n" +
                    "        \"username\": \"Bret\",\n" +
                    "        \"email\": \"Sincere@april.biz\",\n" +
                    "        \"address\": {\n" +
                    "            \"street\": \"Kulas Light\",\n" +
                    "            \"suite\": \"Apt. 556\",\n" +
                    "            \"city\": \"Gwenborough\",\n" +
                    "            \"zipcode\": \"92998-3874\",\n" +
                    "            \"geo\": {\n" +
                    "                \"lat\": \"-37.3159\",\n" +
                    "                \"lng\": \"81.1496\"\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"phone\": \"1-770-736-8031 x56442\",\n" +
                    "        \"website\": \"hildegard.org\",\n" +
                    "        \"company\": {\n" +
                    "            \"name\": \"Romaguera-Crona\",\n" +
                    "            \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                    "            \"bs\": \"harness real-time e-markets\"\n" +
                    "        }\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 2,\n" +
                    "        \"name\": \"Ervin Howell\",\n" +
                    "        \"username\": \"Antonette\",\n" +
                    "        \"email\": \"Shanna@melissa.tv\",\n" +
                    "        \"address\": {\n" +
                    "            \"street\": \"Victor Plains\",\n" +
                    "            \"suite\": \"Suite 879\",\n" +
                    "            \"city\": \"Wisokyburgh\",\n" +
                    "            \"zipcode\": \"90566-7771\",\n" +
                    "            \"geo\": {\n" +
                    "                \"lat\": \"-43.9509\",\n" +
                    "                \"lng\": \"-34.4618\"\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"phone\": \"010-692-6593 x09125\",\n" +
                    "        \"website\": \"anastasia.net\",\n" +
                    "        \"company\": {\n" +
                    "            \"name\": \"Deckow-Crist\",\n" +
                    "            \"catchPhrase\": \"Proactive didactic contingency\",\n" +
                    "            \"bs\": \"synergize scalable supply-chains\"\n" +
                    "        }\n" +
                    "    }]\n" +
                    "            Get();\n" +
                    "    \n" +
                    "            Body()\n" +
                    "                .root()\n" +
                    "                .object(\"id\", \"name\", \"email\")\n" +
                    "                .newObject(\"address\", \"street\")\n" +
                    "                .newObject(\"address geo\", \"lat\", \"lng\");\n" +
                    "```\n" +
                    "\n" +
                    "**Se deseja apenas validar em todo JSON se os valores existem**\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "\n" +
                    "        Body()\n" +
                    "              .contains(\"id\",\n" +
                    "              \"name\",\n" +
                    "              \"email\")\n" +
                    "```\n" +
                    "\n" +
                    "> Abaixo outro exemplo, analisando o JSON por completo.\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "{\n" +
                    "    \"page\": 1,\n" +
                    "    \"per_page\": 6,\n" +
                    "    \"total\": 12,\n" +
                    "    \"total_pages\": 2,\n" +
                    "    \"data\": [\n" +
                    "        {\n" +
                    "            \"id\": 1,\n" +
                    "            \"name\": \"cerulean\",\n" +
                    "            \"year\": 2000,\n" +
                    "            \"color\": \"#98B2D1\",\n" +
                    "            \"pantone_value\": \"15-4020\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": 2,\n" +
                    "            \"name\": \"fuchsia rose\",\n" +
                    "            \"year\": 2001,\n" +
                    "            \"color\": \"#C74375\",\n" +
                    "            \"pantone_value\": \"17-2031\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"support\": {\n" +
                    "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                    "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    " Body().contains(\"page\", \"per_page\")\n" +
                    "        .root(\"data\")\n" +
                    "        .object(\"id\", \"name\")\n" +
                    "        .root(Keyobject \"support\", path \"url\", equals \"https://reqres.in/#support-heading\")\n" +
                    "        .and(\"support\", \"text\", \"Olá estamos testando\" );\n" +
                    "        \n" +
                    "        No primeiro root, estamos avaliando os campos dentro do array de objetos.\n" +
                    "        No segundo, estamos entrando no objeto support, pegando o valor do campo URL\n" +
                    "        e comparando.\n" +
                    "        \n" +
                    "                *os nomes Keyobject, path, equals não devem ser digitados, são apenas referências.*\n" +
                    "                    .root(\"support\", \"url\", \"https://reqres.in/#support-heading\");\n" +
                    "\n" +
                    "```\n" +
                    "\n" +
                    "> Para usar o Body() no GivenExternal() precisa fazer conforme exemplo abaixo:\n" +
                    ">\n" +
                    ">Seguindo conforme nos exemplos acima de uso do Body() você efetuar as mesmas validações.\n" +
                    "\n" +
                    " ```androiddatabinding\n" +
                    "        ExternalContainsJSON = GivenExternal()\n" +
                    "                               .when().get().then().log().body();\n" +
                    "\n" +
                    "        Body().contains(\"id\", \"data\");\n" +
                    "\n" +
                    " ```\n" +
                    ">>Vamos a mais um exemplo complexo de JSON e como validar usando o Framework\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "{\n" +
                    "    \"totalSumary\": {\n" +
                    "        \"cardsCount\": 2,\n" +
                    "        \"limitAmount\": 0,\n" +
                    "        \"usedAmount\": 0,\n" +
                    "        \"availableAmount\": 0,\n" +
                    "        \"percentileAmount\": 0,\n" +
                    "        \"usedAmountFlexible\": 100000.0001\n" +
                    "    },\n" +
                    "    \"bradesco\": {\n" +
                    "        \"consentId\": \"\",\n" +
                    "        \"authorisationServerId\": \"\",\n" +
                    "        \"brandName\": \"BRADESCO\",\n" +
                    "        \"brandId\": \"237\",\n" +
                    "        \"imageName\": \"bradesco\",\n" +
                    "        \"color\": \"D11E45\",\n" +
                    "        \"creditCards\": [\n" +
                    "            {\n" +
                    "                \"creditCardInfo\": {\n" +
                    "                    \"consentId\": \"\",\n" +
                    "                    \"authorisationServerId\": \"\",\n" +
                    "                    \"creditCardAccountId\": \"4532119999910005\",\n" +
                    "                    \"identificationNumber\": \"0000\",\n" +
                    "                    \"creditCardNetwork\": \"VISA\",\n" +
                    "                    \"status\": \"ACTIVE\"\n" +
                    "                },\n" +
                    "                \"limits\": {\n" +
                    "                    \"isFlexible\": null,\n" +
                    "                    \"purchaseAvailableAmount\": null,\n" +
                    "                    \"purchaseUsedAmount\": null,\n" +
                    "                    \"purchaseTotalAmount\": null,\n" +
                    "                    \"purchaseUsedPercentile\": null,\n" +
                    "                    \"withdrawAvailableAmount\": null,\n" +
                    "                    \"withdrawUsedAmount\": null,\n" +
                    "                    \"withdrawTotalAmount\": null,\n" +
                    "                    \"withdrawUsedPercentile\": null,\n" +
                    "                    \"auditory\": null\n" +
                    "                },\n" +
                    "                \"lastBill\": null,\n" +
                    "                \"bills\": {\n" +
                    "                    \"bills\": [\n" +
                    "                        \n" +
                    "                    ],\n" +
                    "                    \"auditory\": null\n" +
                    "                },\n" +
                    "                \"hasMessageErrors\": 100.00,\n" +
                    "                \"oldestUpdateDateTime\": null\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    },\n" +
                    "    \"next\": {\n" +
                    "        \"consentId\": \"nextconsentid\",\n" +
                    "        \"authorisationServerId\": \"nextauthorisationserverid\",\n" +
                    "        \"brandName\": null,\n" +
                    "        \"brandId\": null,\n" +
                    "        \"imageName\": \"\",\n" +
                    "        \"color\": \"07FB4E\",\n" +
                    "        \"creditCards\": [\n" +
                    "            {\n" +
                    "                \"creditCardInfo\": {\n" +
                    "                    \"consentId\": \"nextconsentid\",\n" +
                    "                    \"authorisationServerId\": \"nextauthorisationserverid\",\n" +
                    "                    \"creditCardAccountId\": \"1020304051\",\n" +
                    "                    \"identificationNumber\": \"4454\",\n" +
                    "                    \"status\": \"ACTIVE\"\n" +
                    "                },\n" +
                    "                \"limits\": {\n" +
                    "                    \"isFlexible\": true,\n" +
                    "                    \"purchaseAvailableAmount\": 0,\n" +
                    "                    \"purchaseUsedAmount\": 100000.0001,\n" +
                    "                    \"purchaseTotalAmount\": 0,\n" +
                    "                    \"purchaseUsedPercentile\": 0,\n" +
                    "                    \"withdrawAvailableAmount\": null,\n" +
                    "                    \"withdrawUsedAmount\": null,\n" +
                    "                    \"withdrawTotalAmount\": null,\n" +
                    "                    \"withdrawUsedPercentile\": null,\n" +
                    "                    \"auditory\": null\n" +
                    "                },\n" +
                    "                \"lastBill\": {\n" +
                    "                    \"billId\": \"10\",\n" +
                    "                    \"billMonth\": \"2021-04-21\",\n" +
                    "                    \"dueDate\": \"2021-05-21\",\n" +
                    "                    \"billMinimumAmount\": 1000.04,\n" +
                    "                    \"billTotalAmount\": 100000.04,\n" +
                    "                    \"bestPurchaseDate\": null,\n" +
                    "                    \"billStatus\": \"PAGA\",\n" +
                    "                    \"automaticPayment\": null\n" +
                    "                },\n" +
                    "                \"bills\": {\n" +
                    "                    \"bills\": [\n" +
                    "                        {\n" +
                    "                            \"billId\": \"10\",\n" +
                    "                            \"billMonth\": \"2021-04-21\",\n" +
                    "                            \"dueDate\": \"2021-05-21\",\n" +
                    "                            \"billMinimumAmount\": 1000.04,\n" +
                    "                            \"billTotalAmount\": 100000.04,\n" +
                    "                            \"bestPurchaseDate\": null,\n" +
                    "                            \"billStatus\": \"PAGA\",\n" +
                    "                            \"automaticPayment\": null\n" +
                    "                        }\n" +
                    "                    ],\n" +
                    "                    \"auditory\": \"auditoryNext\"\n" +
                    "                },\n" +
                    "                \"hasMessageErrors\": 100.124,\n" +
                    "                \"oldestUpdateDateTime\": null\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    },\n" +
                    "    \"others\": [\n" +
                    "        \n" +
                    "    ],\n" +
                    "    \"bradescoBlocked\": {\n" +
                    "        \"consentId\": null,\n" +
                    "        \"authorisationServerId\": null,\n" +
                    "        \"brandName\": \"Bradesco\",\n" +
                    "        \"brandId\": null,\n" +
                    "        \"imageName\": \"bradesco\",\n" +
                    "        \"color\": \"D11E45\",\n" +
                    "        \"creditCards\": [\n" +
                    "            \n" +
                    "        ]\n" +
                    "    },\n" +
                    "    \"categoryMonthsAvailables\": [\n" +
                    "        {\n" +
                    "            \"dateMonth\": \"2021-04-21\",\n" +
                    "            \"totalAmount\": 100000.04,\n" +
                    "            \"cards\": [\n" +
                    "                {\n" +
                    "                    \"internalBrand\": \"NEXT\",\n" +
                    "                    \"consentId\": \"nextconsentid\",\n" +
                    "                    \"authorisationServerId\": \"nextauthorisationserverid\",\n" +
                    "                    \"creditCardId\": \"1020304051\",\n" +
                    "                    \"cardNumber\": \"4454\",\n" +
                    "                    \"billId\": \"10\",\n" +
                    "                    \"dueDate\": \"2021-05-21\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"lastUpdateDateTime\": \"2021-07-06T11:46:22.227421Z\",\n" +
                    "    \"messages\": [\n" +
                    "        \n" +
                    "    ]\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "        Get(false);\n" +
                    "\n" +
                    "        Body()\n" +
                    "                .root(\"totalSumary\", \"cardsCount\", 2)\n" +
                    "                .root(\"bradesco\", \"brandName\", \"BRADESCO\")\n" +
                    "                .objectForArray(\"next\", \"creditCards\", \"hasMessageErrors\")\n" +
                    "                .newObjectForArray(\"bills\", \"bills\", \"billId\")\n" +
                    "                .getObject(\"bills\", \"auditory\", \"auditoryNext\")\n" +
                    "                .root(\"next\", \"consentId\", \"nextconsentid\")\n" +
                    "                .getObject(\"creditCardInfo\", \"status\", \"ACTIVE\")\n" +
                    "                .root(\"categoryMonthsAvailables\")\n" +
                    "                .object(\"dateMonth\")\n" +
                    "                .getString(\"totalAmount\", 100000.04)\n" +
                    "                .newArray(\"cards\", \"authorisationServerId\")\n" +
                    "                .get(\"totalAmount\")\n" +
                    "                \n" +
                    "                O *get* ao final está pegando o valor do campo e transferindo o valor para o *StringGlobal*\n" +
                    "```\n" +
                    "***\n" +
                    "\n" +
                    ">>Maneira ainda mais simples de validar um campo ou um valor é usando o **mapping**\n" +
                    ">>e passando o caminho da estrutura, usando o   * **>**  *  servirá como caminho para o path.\n" +
                    ">\n" +
                    "> OBS: Validar valores dentro de um Array não é possível, porque eles podem variar, mas, é possível validar se o campo existe.\n" +
                    "```androiddatabinding\n" +
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
                    "        Body().mapping(\"data > id\", 7)\n" +
                    "              .mapping(\"support > url\", \"https://reqres.in/#support-heading\");\n" +
                    "```\n" +
                    ">>Validações usando o RestAssured, neste link estará o JSON que foi validado <a href=\"http://demo0623716.mockable.io/\">Acesse aqui</a>\n" +
                    "\n" +
                    "```androiddatabinding\n" +
                    "       ResponseBody().body(\"bradesco.brandName\", Matchers.is(\"BRADESCO\"),\n" +
                    "                \"bradesco.creditCards.creditCardInfo[0].creditCardNetwork\", is(\"VISA\"));\n" +
                    "\n" +
                    "        ResponseBody().body(\"bradesco.creditCards.bills[0].bills[0].billStatus\", is(\"FECHADA\"));\n" +
                    "\n" +
                    "        ResponseBody().body(\"others[0].creditCards.creditCardInfo[1].consentId\", is(\"itauconsentid\"));\n" +
                    "\n" +
                    "        ResponseBody().body(\"others[0].creditCards.bills[0].bills[0].billStatus\", is(\"PAGA\"));\n" +
                    "\n" +
                    "        ResponseBody().body(\"bradescoBlocked.creditCards.bills[0].auditory\", is(nullValue()));\n" +
                    "\n" +
                    "        String response = ResponseBody().extract().response().path(\"categoryMonthsAvailables[0].totalAmount\").toString();\n" +
                    "        BigDecimal bigDecimal = new BigDecimal(response);\n" +
                    "        Assert.assertThat(bigDecimal.doubleValue(), is(200000.08));\n" +
                    "\n" +
                    "        ResponseBody().body(\"categoryMonthsAvailables[0].cards.internalBrand[2]\", is(\"BRADESCO\"));\n" +
                    "\n" +
                    "        ResponseBody().body(\"categoryMonthsAvailables[0].cards.size()\", is(3));\n" +
                    "```\n" +
                    "\n" +
                    "\n" +
                    "> > Abaixo veja mais um exemplo usando o Body()\n" +
                    "\n" +
                    "```androiddatabinding\n" +
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
                    "        * Passando apenas 2 parâmetros no root, iremos verificar se o campo existe no JSON *\n" +
                    "         Body().root(\"data\", \"id\")\n" +
                    "            .and(\"support\", \"text\")\n" +
                    "            .and(\"support\", \"url\");\n" +
                    "    \n" +
                    "\n" +
                    "```";

    public static String textNull = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "    <head>\n" +
            "        <!-- Google Tag Manager -->\n" +
            "        <script>\n" +
            "            ;(function (w, d, s, l, i) {\n" +
            "                w[l] = w[l] || []\n" +
            "                w[l].push({\n" +
            "                    'gtm.start': new Date().getTime(),\n" +
            "                    event: 'gtm.js',\n" +
            "                })\n" +
            "                var f = d.getElementsByTagName(s)[0],\n" +
            "                    j = d.createElement(s),\n" +
            "                    dl = l != 'dataLayer' ? '&l=' + l : ''\n" +
            "                j.async = true\n" +
            "                j.src = 'https://www.googletagmanager.com/gtm.js?id=' + i + dl\n" +
            "                f.parentNode.insertBefore(j, f)\n" +
            "            })(window, document, 'script', 'dataLayer', 'GTM-KB622KF')\n" +
            "        </script>\n" +
            "        <!-- End Google Tag Manager -->\n" +
            "        <script src=\"https://js.stripe.com/v3/\"></script>\n" +
            "        <meta charset=\"utf-8\" />\n" +
            "        <meta\n" +
            "            name=\"viewport\"\n" +
            "            content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\"\n" +
            "        />\n" +
            "        <meta\n" +
            "            name=\"description\"\n" +
            "            content=\"A hosted REST-API ready to respond to your AJAX requests\"\n" +
            "        />\n" +
            "        <title>\n" +
            "            Reqres - A hosted REST-API ready to respond to your AJAX requests\n" +
            "        </title>\n" +
            "        <link\n" +
            "            href=\"//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css\"\n" +
            "            rel=\"stylesheet\"\n" +
            "        />\n" +
            "\n" +
            "        <link rel=\"stylesheet\" href=\"/css/app.css\" />\n" +
            "    </head>\n" +
            "    <body>\n" +
            "        <div class=\"header-content\">\n" +
            "            <header class=\"main-header\">\n" +
            "                <section class=\"wrap\">\n" +
            "                    <h1 class=\"logo\"><a href=\"/\">Req&#8201;|&#8201;Res</a></h1>\n" +
            "                </section>\n" +
            "            </header>\n" +
            "            <main class=\"the-sell\">\n" +
            "                <div class=\"wrap\">\n" +
            "                    <h2 class=\"tagline\">\n" +
            "                        Test your front-end against a real API\n" +
            "                    </h2>\n" +
            "                    <section class=\"key-sells\">\n" +
            "                        <div class=\"sell\">\n" +
            "                            <div class=\"v-center\">\n" +
            "                                <h3>Fake data</h3>\n" +
            "                                <p>\n" +
            "                                    No more tedious sample data creation, we've\n" +
            "                                    got it covered.\n" +
            "                                </p>\n" +
            "                            </div>\n" +
            "                            <i class=\"fa fa-cloud-download\"></i>\n" +
            "                        </div>\n" +
            "                        <div class=\"sell\">\n" +
            "                            <div class=\"v-center\">\n" +
            "                                <h3>Real responses</h3>\n" +
            "                                <p>\n" +
            "                                    Develop with real response codes. GET, POST,\n" +
            "                                    PUT &amp; DELETE supported.\n" +
            "                                </p>\n" +
            "                            </div>\n" +
            "                            <i class=\"fa fa-code\"></i>\n" +
            "                        </div>\n" +
            "                        <div class=\"sell\">\n" +
            "                            <div class=\"v-center\">\n" +
            "                                <h3>Always-on</h3>\n" +
            "                                <p>\n" +
            "                                    24/7 <strong><em>free</em></strong> access\n" +
            "                                    in your development phases. Go nuts.\n" +
            "                                </p>\n" +
            "                            </div>\n" +
            "                            <i class=\"fa fa-toggle-on\"></i>\n" +
            "                        </div>\n" +
            "                    </section>\n" +
            "                    <h2 class=\"tagline\">\n" +
            "                        A hosted REST-API ready to respond to your AJAX\n" +
            "                        requests.\n" +
            "                    </h2>\n" +
            "                    <div class=\"t-center main-arrow\">\n" +
            "                        <i class=\"fa fa-arrow-circle-down\"></i>\n" +
            "                    </div>\n" +
            "                    <script\n" +
            "                        async\n" +
            "                        type=\"text/javascript\"\n" +
            "                        src=\"//cdn.carbonads.com/carbon.js?serve=CE7D6K3E&placement=reqresin\"\n" +
            "                        id=\"_carbonads_js\"\n" +
            "                    ></script>\n" +
            "                </div>\n" +
            "            </main>\n" +
            "        </div>\n" +
            "        <div class=\"container\">404</div>\n" +
            "        <footer>\n" +
            "            <div class=\"wrap\">\n" +
            "                <hr class=\"dark\" />\n" +
            "                <p class=\"t-center\">\n" +
            "                    Made for developers and designers by\n" +
            "                    <a href=\"https://benhowdle.im\">Ben Howdle</a>\n" +
            "                </p>\n" +
            "            </div>\n" +
            "        </footer>\n" +
            "        <script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/fastclick/1.0.3/fastclick.min.js\"></script>\n" +
            "        <script src=\"/js/app.js\"></script>\n" +
            "        <!-- Global site tag (gtag.js) - Google Analytics -->\n" +
            "        <script\n" +
            "            async\n" +
            "            src=\"https://www.googletagmanager.com/gtag/js?id=UA-174008107-1\"\n" +
            "        ></script>\n" +
            "        <script>\n" +
            "            window.dataLayer = window.dataLayer || []\n" +
            "            function gtag() {\n" +
            "                dataLayer.push(arguments)\n" +
            "            }\n" +
            "            gtag('js', new Date())\n" +
            "\n" +
            "            gtag('config', 'UA-174008107-1')\n" +
            "        </script>\n" +
            "        <script>\n" +
            "            ;(function (i, s, o, g, r, a, m) {\n" +
            "                i['GoogleAnalyticsObject'] = r\n" +
            "                ;(i[r] =\n" +
            "                    i[r] ||\n" +
            "                    function () {\n" +
            "                        ;(i[r].q = i[r].q || []).push(arguments)\n" +
            "                    }),\n" +
            "                    (i[r].l = 1 * new Date())\n" +
            "                ;(a = s.createElement(o)), (m = s.getElementsByTagName(o)[0])\n" +
            "                a.async = 1\n" +
            "                a.src = g\n" +
            "                m.parentNode.insertBefore(a, m)\n" +
            "            })(\n" +
            "                window,\n" +
            "                document,\n" +
            "                'script',\n" +
            "                '//www.google-analytics.com/analytics.js',\n" +
            "                'ga'\n" +
            "            )\n" +
            "\n" +
            "            ga('create', 'UA-55888877-1', 'auto')\n" +
            "            ga('send', 'pageview')\n" +
            "        </script>\n" +
            "    </body>\n" +
            "</html>";

    public static String HTML_TUTORIAL = "<!DOCTYPE html>\n" +
            "<html lang=\"pt-br\">\n" +
            "\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <link rel=\"shortcut icon\" href=\"https://ciandt.com/themes/custom/ciandt_theme/favicon.ico\"\n" +
            "        type=\"image/vnd.microsoft.icon\">\n" +
            "    <title>CIT Framework</title>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "    <div>\n" +
            "        <h1 style=\"text-align: center;\">\n" +
            "            <img src=\"https://ciandt.com/themes/custom/ciandt_theme/logo.svg\" alt=\"logoCIT\">\n" +
            "            Configuração do Framework CI&T\n" +
            "        </h1>\n" +
            "    </div>\n" +
            "    <hr>\n" +
            "    <p> &#x26A0;<b> Obs: Para baixar o framework CI&T você precisará ter chave M Bradesco.</b>\n" +
            "        <br>\n" +
            "        <br>\n" +
            "    </p>\n" +
            "\n" +
            "    <ol>\n" +
            "        <li>Primeiro vamos baixar o JAR &nbsp;&nbsp;&nbsp;&nbsp;<a\n" +
            "                href=\"https://drive.google.com/drive/u/1/folders/10cRqFVxSy7iGjh5mmsoEbF41x-lt9nQZ\">Aqui</a>&nbsp;&nbsp;&nbsp;&nbsp;\n" +
            "            e coloca-lo dentro da pasta <b>src/test/java/resources/framework</b>\n" +
            "        </li>\n" +
            "        <br>\n" +
            "        <li>Crie um arquivo <b>settings.xml</b> dentro da sua pasta oculta <b>.m2</b> que fica dentro no seu usuário\n" +
            "            local\n" +
            "        </li>\n" +
            "        <br>\n" +
            "        <li>Copie as configurações desse link, e altere os dois campos\n" +
            "            <mark>USERNAME PASSWORD</mark>\n" +
            "            com sua chaveM e senha. &nbsp;&nbsp;&nbsp;&nbsp;\n" +
            "            <a href=\"settings.xml\">Aqui </a>\n" +
            "\n" +
            "        </li>\n" +
            "        <br>\n" +
            "        <li>Importe as dependências para o arquivo <b>pom.xml</b>\n" +
            "            que fica na raíz do seu projeto.&nbsp;&nbsp;&nbsp;&nbsp;\n" +
            "            <a href=\"dependencias.text\">Aqui </a>\n" +
            "        </li>\n" +
            "        <br>\n" +
            "        <li>Dentro do seu <b>pom.xml</b> comente todas as dependências, deixando apenas a do Bradesco liberada para\n" +
            "            baixar.\n" +
            "            <p>\n" +
            "                <a href=\"https://imgbb.com/\"><img src=\"https://i.ibb.co/KwkdWPt/Screen-Shot-2022-05-24-at-05-24-03.png\"\n" +
            "                        alt=\"Screen-Shot-2022-05-24-at-05-24-03\" border=\"0\"></a>\n" +
            "            </p>\n" +
            "        </li>\n" +
            "        <br>\n" +
            "        <li> <mark>Conecte sua VPN </mark>\n" +
            "            Faça o build do projeto para baixar a dependência Bradesco, assim que terminar, retire os comentários do\n" +
            "            pom.xml e refaça o build.\n" +
            "        </li>\n" +
            "        <br>\n" +
            "        <li>Importe as dependências do Framework CIT para <b>pom.xml</b>\n" +
            "            conforme passo 1:\n" +
            "            <p>\n" +
            "                <a href=\"https://ibb.co/MPHJYyR\"><img\n" +
            "                        src=\"https://i.ibb.co/BTJYXHg/Screen-Shot-2022-05-24-at-05-24-09.png\"\n" +
            "                        alt=\"Screen-Shot-2022-05-24-at-05-24-09\" border=\"0\"></a>\n" +
            "            </p>\n" +
            "        </li>\n" +
            "        <br>\n" +
            "        <li>\n" +
            "            Configurando sua classe <b>RegresstionTest</b>\n" +
            "            , conforme imagem abaixo, clique para expandir:\n" +
            "            <p>\n" +
            "                <a href=\"https://ibb.co/J3JL3tz\"><img\n" +
            "                        src=\"https://i.ibb.co/g4K149D/Screen-Shot-2022-05-24-at-05-29-44.png\"\n" +
            "                        alt=\"Screen-Shot-2022-05-24-at-05-29-44\" border=\"0\"></a>\n" +
            "                </a>\n" +
            "            </p>\n" +
            "            </div>\n" +
            "            <p>Na primeira execução, comente a linha do plugin, crie um método simples apenas para usar o BeforeClass\n" +
            "                pela primeira vez<br>\n" +
            "                para que os arquivos <b>setup.properties e leanft.properties</b>\n" +
            "                sejam criados automaticamente.\n" +
            "            </p>\n" +
            "\n" +
            "            <br>\n" +
            "            </p>\n" +
            "        </li>\n" +
            "\n" +
            "        <li>Para efetuar validações usando o Framework CI&T aqui está uma lista com vídeos mostando passo a passo\n" +
            "            &nbsp;&nbsp;&nbsp;&nbsp;\n" +
            "            <a href=\"https://drive.google.com/drive/u/1/folders/1JjX186HDmh6i-yPh_yCutPUIKJNLbh5g\">VIDEOS</a>\n" +
            "        </li>\n" +
            "        <br>\n" +
            "        <li>Caso na raiz do seu projeto a pasta <b>environment/data.properties</b> não exista, ao executar o primeiro\n" +
            "            teste,\n" +
            "            ela será criada.<br>\n" +
            "            Dentro do data.properties estão seus ambientes.\n" +
            "        </li>\n" +
            "    </ol>\n" +
            "    <hr>\n" +
            "    <p>\n" +
            "        </h2>\n" +
            "    </p>\n" +
            "\n" +
            "    <h2>Como iniciar o GET POST DELETE PUT?</h2>\n" +
            "    <ol>\n" +
            "        <li>Estenda o framework na classe do seu STEP como abaixo:</li>\n" +
            "        <li>Chame o método InitEnvironment(), nele você terá o start do RestAssured com todas as funcionalidades, dentro\n" +
            "            dele passe o endpoint que você quer.\n" +
            "        </li>\n" +
            "    </ol>\n" +
            "    <pre>\n" +
            "    <code>\n" +
            "        public class GetUser extends CITRestAssured {\n" +
            "\n" +
            "            @Given(\"^que seja feito GET na API \\\"([^\\\"]*)\\\"$\")\n" +
            "            public void queSejaFeitoGETNaAPI(String endpoint) throws Throwable {\n" +
            "\n" +
            "                InitEnvironment(endpoint);\n" +
            "            }\n" +
            "\n" +
            "            @Then(\"^faco get$\")\n" +
            "            public void facoGet() throws Throwable {\n" +
            "\n" +
            "                Get();\n" +
            "            }\n" +
            "        }\n" +
            "    </code>\n" +
            "</pre>\n" +
            "    <br>\n" +
            "    <hr>\n" +
            "    <br>\n" +
            "    <h2>E no caso do endpoint precisar ser passado no get() post() delete() put() e não no InitEnvironment()</h2>\n" +
            "    <pre><code>\n" +
            "    public class GetUser extends CITRestAssured {\n" +
            "\n" +
            "        @Given(\"^que seja feito GET na API \\\"([^\\\"]*)\\\"$\")\n" +
            "        public void queSejaFeitoGETNaAPI(String endpoint) throws Throwable {\n" +
            "\n" +
            "            InitEnvironment();\n" +
            "        }\n" +
            "        @Then(\"^faco get$\")\n" +
            "        public void facoGet() throws Throwable {\n" +
            "\n" +
            "            GetEndpoint(\"users/7\");\n" +
            "        }\n" +
            "    }\n" +
            "</code></pre>\n" +
            "    <br>\n" +
            "    <hr>\n" +
            "    <br>\n" +
            "\n" +
            "    <h2>Método <b>ResponseBody</b> extende do RestAssured e deve ser usado para validações em steps diferentes da\n" +
            "        chamada, Ex;\n" +
            "    </h2>\n" +
            "    <pre><code>\n" +
            "    public class GetUser extends CITRestAssured {\n" +
            "\n" +
            "        @Given(\"^que seja feito GET na API \\\"([^\\\"]*)\\\"$\")\n" +
            "        public void queSejaFeitoGETNaAPI(String endpoint) throws Throwable {\n" +
            "\n" +
            "            InitEnvironment();\n" +
            "            GetEndpoint(\"users/7\");\n" +
            "        }\n" +
            "        @Then(\"^faco get$\")\n" +
            "        public void facoGet() throws Throwable {\n" +
            "\n" +
            "            ResponseBody().body(\"name\", is(\"CI&T\"));\n" +
            "        }\n" +
            "    }\n" +
            "</code></pre>\n" +
            "    <br>\n" +
            "    <hr>\n" +
            "    <br>\n" +
            "    <h1>Métodos com parâmetros</h1>\n" +
            "    <p>Para usar <b>headers</b> ou <b>params</b> basta chamar a variável global, veja exemplo:</p>\n" +
            "    <pre><code>\n" +
            "        public class GetUser extends CITRestAssured {\n" +
            "\n" +
            "        @Given(\"^que seja feito GET na API \\\"([^\\\"]*)\\\"$\")\n" +
            "        public void queSejaFeitoGETNaAPI(String endpoint) throws Throwable {\n" +
            "\n" +
            "            InitEnvironment();\n" +
            "        }\n" +
            "        @Then(\"^faco get$\")\n" +
            "        public void facoGet() throws Throwable {\n" +
            "\n" +
            "            params.put(\"key\", \"value\");\n" +
            "\n" +
            "            GetParam(\"users/7\");\n" +
            "\n" +
            "            headers.put(\"key\", \"value\");\n" +
            "\n" +
            "            GetHeaders();\n" +
            "        }\n" +
            "    }\n" +
            "</code></pre>\n" +
            "    <br>\n" +
            "    <hr>\n" +
            "    <br>\n" +
            "    <h2>Método separado <b>GivenExternal();</b></h2>\n" +
            "    <p>Existe um método chamado GivenExternal();</p>\n" +
            "    <p>Para o caso de, você precisar usar uma chamada diferente, com características que os métodos existentes não\n" +
            "        atendam.<br> Em métodos separados, é necessário usar o GivenExternal do framework para que ele use os dados no\n" +
            "        Report.</p>\n" +
            "    <p>Ao final, chame o ExternalReport(); para que seja gerado.</p>\n" +
            "    <p>Apenas em caso de\n" +
            "        <mark>DELETES</mark>\n" +
            "        dentro do ExternalReporte(); precisa ficar vazio\n" +
            "    </p>\n" +
            "    <pre><code>\n" +
            "    Response res = GivenExternal(ContentType.JSON)\n" +
            "    .when()\n" +
            "    .get()\n" +
            "    .then()([[Aqui pode efetuar validações normalmente. Ex: then().body(\"path\"), is(\"CIT\")]\n" +
            "    .extract().response();\n" +
            "ExternalReport(res);\n" +
            "\n" +
            "GivenExternal(ContentType.JSON)\n" +
            "    .when()\n" +
            "    .delete()\n" +
            "    .then() ([[Aqui poeria efetuar validações normalmente. Ex: then().body(\"path\"), is(\"CIT\")]])'\n" +
            "    .extract().response();\n" +
            "ExternalReport();\n" +
            "</code></pre>\n" +
            "    <p>Extraindo valor do GivenExternal();</p>\n" +
            "    <pre><code>\n" +
            "    ValidatableResponse res = GivenExternal(ContentType.JSON)\n" +
            "    .when()\n" +
            "    .get()\n" +
            "    .then();\n" +
            "String value = res.extract().path(\"path que eu quero\");\n" +
            "\n" +
            "## caso precise de report, use as lógicas já descrita.\n" +
            "ExternalReport(res.extract().response());\n" +
            "</code></pre>\n" +
            "    <p>Quais dados usar no <b>ExternalReport()</b>?</p>\n" +
            "    <br>\n" +
            "    <p>Gets - Basta enviar o resultado da requisição. Como no exemplo acima.</p>\n" +
            "    <pre><code>\n" +
            "    @Then(\"^faco get$\")\n" +
            "    public void facoGet() throws Throwable {\n" +
            "\n" +
            "        ValidatableResponse res = GivenExternal(ContentType.JSON)\n" +
            "                .when().get(\"users/7\").then();\n" +
            "\n" +
            "        ExternalReport(res.extract().response());\n" +
            "    }\n" +
            "</code></pre>\n" +
            "    <br>\n" +
            "    <p>Posts - você precisa passar primeiro o Body que está sendo enviado, depois o resultado da requisição.</p>\n" +
            "    <pre><code>\n" +
            "    String body = \"{body que será enviado}\";\n" +
            "\n" +
            "  Response res = GivenExternal(ContentType.JSON)\n" +
            "                .body(body)\n" +
            "                .when()\n" +
            "                .post()\n" +
            "                .then()([[Aqui pode efetuar validações normalmente. Ex: then().body(\"path\"), is(\"CIT\")]\n" +
            "                .extract().response();\n" +
            "             ExternalReport(body, res);\n" +
            "</code></pre>\n" +
            "    <br>\n" +
            "    <h2>Como ficam métodos <b>ExternalReport()</b> com headers e parâmetros?</h2>\n" +
            "    <pre><code>\n" +
            "    Map<String, Object> param = new new HashMap<>();\n" +
            "    param.put(\"key\", \"value\");\n" +
            "    Map<String, Object> header = new new HashMap<>();\n" +
            "    header.put(\"key\", \"value\");\n" +
            "\n" +
            "    Response res = GivenExternal(ContentType.JSON)\n" +
            "                .headers(header)\n" +
            "                .queryParams(param)\n" +
            "                .body(body)\n" +
            "                .when()\n" +
            "                .post()\n" +
            "                .then()([[Aqui pode efetuar validações normalmente. Ex: then().body(\"path\"), is(\"CIT\")]\n" +
            "                .extract().response();\n" +
            "             ExternalReport(body, res);\n" +
            "</code></pre>\n" +
            "    <br>\n" +
            "    <hr>\n" +
            "    <br>\n" +
            "    <h2>Vamos efetuar validações de exemplo, de preferência use o método <b>Body().mapping()</b> nas suas validações:\n" +
            "    </h2>\n" +
            "    <p>Primeiro usando um JSON simples:</p>\n" +
            "    <pre>\n" +
            "    <code style=\"font-size:20px\">\n" +
            "        {\n" +
            "            \"data\": {\n" +
            "              \"id\": 7,\n" +
            "              \"email\": \"michael.lawson@reqres.in\",\n" +
            "              \"first_name\": \"Michael\",\n" +
            "              \"last_name\": \"Lawson\",\n" +
            "              \"avatar\": \"https://reqres.in/img/faces/7-image.jpg\"\n" +
            "            },\n" +
            "            \"support\": {\n" +
            "              \"url\": \"https://reqres.in/#support-heading\",\n" +
            "              \"text\": \"To keep ReqRes free,\n" +
            "              contributions towards server costs are appreciated!\"\n" +
            "            }\n" +
            "          }\n" +
            "    </code>\n" +
            "\n" +
            "    <code style=\"font-size:20px\">\n" +
            "        //Validação usando o Rest\n" +
            "        Get(false)\n" +
            "                .body(\"data.id\", Matchers.is(7),\n" +
            "                        \"data.email\", Matchers.is(\"michael.lawson@reqres.in\"),\n" +
            "                        \"support.url\", Matchers.is(\"https://reqres.in/#support-heading\"));\n" +
            "\n" +
            "        //Validação usando o framework CI&T\n" +
            "        Body()\n" +
            "                .mapping(\"data > id\", 7)\n" +
            "                .mapping(\"data > email\")\n" +
            "                .mapping(\"data > first_name\")\n" +
            "                .mapping(\"support > text\", \"To keep ReqRes free, \" +\n" +
            "                        \"contributions towards server costs are appreciated!\");\n" +
            "    </code>\n" +
            "</pre>\n" +
            "    <br>\n" +
            "    <hr>\n" +
            "    <br>\n" +
            "    <p style=\"font-size:30px\">Método <b>CONTAINS</b>:</p>\n" +
            "    <p style=\"font-size:25px\">O método <b>Body().contains()</b> é usado para validar existência de campos no seu\n" +
            "        JSON<br>\n" +
            "        independente de onde esses campos estejam. Usando o contains, você pode passar 1 ou mais campos.<br>\n" +
            "        Obs: O contais NAO valida estrutura do JSON, não valida se o campo existe no determinado caminho<br>\n" +
            "        valida apenas se existe no seu JSON. <br>\n" +
            "        EX:\n" +
            "\n" +
            "    <pre style=\"font-size:25px\">\n" +
            "    <code style=\"font-size:20px\">\n" +
            "        Body().contains(\"id\");\n" +
            "        Body().contains(\"id\", \"name\", \"lastname\");\n" +
            "    </code>\n" +
            "</pre>\n" +
            "    </p>\n" +
            "    <p style=\"font-size:30px\">Método <b>GET</b>:</p>\n" +
            "    <p style=\"font-size:25px\">\n" +
            "        Método <b>Body().get()</b> vai encontrar em todo seu JSON o campo informado, não validando estrutura nem\n" +
            "        caminho,\n" +
            "        apenas o valor do Path informado.<br>\n" +
            "        Método não valida paths dentro de Arrays, isso porque um array pode conter vários objetos com o campo informado\n" +
            "        e\n" +
            "        valores diferentes.<br>\n" +
            "        Mas se o Array tiver apenas um campo com o nome informado, ele mostrará o falor no print, e colocará o valor na\n" +
            "        variável global <b>StringGlobal</b><br>\n" +
            "        EX:\n" +
            "\n" +
            "    </p>\n" +
            "    <pre style=\"font-size:25px\">\n" +
            "    <code style=\"font-size:20px\">\n" +
            "        Body().get(\"id\");\n" +
            "        Assert.assertThat(StringGlobal, Matchers.is(\"Framework de Automação\"));\n" +
            "    </code>\n" +
            "</pre>\n" +
            "    <p>Você também pode usar o get para efetuar validações com mais de um valor, com tanto que seja STRING.</p>\n" +
            "    <p>Ele irá validar se existe algum desses valores no campo *usedAmountFlexible*. Ex:</p>\n" +
            "\n" +
            "    <pre><code>\n" +
            "        Body().get(\"usedAmountFlexible\", \"100000.0001n\", \"Automação\", \"Framework\");\n" +
            "</code></pre>\n" +
            "    <br>\n" +
            "    <hr>\n" +
            "    <br>\n" +
            "    <h2>Um JSON com complexidades maiores:</h2>\n" +
            "    <pre>\n" +
            "    </p>\n" +
            "    <a href=\"http://demo0623716.mockable.io/\" style=\"color: blue; font-size:16px\">LINK do JSON</a>\n" +
            "    <code style=\"font-size:20px\">\n" +
            "        //Validação usando o Framework\n" +
            "        Body()\n" +
            "                .mapping(\"totalSumary > cardsCount\", 2)\n" +
            "                .mapping(\"totalSumary > limitAmount\",  0)\n" +
            "                .mapping(\"bradesco > brandName\")\n" +
            "                .mapping(\"totalSumary > cardsCount\", \"cardsCount\", 1, 2)\n" +
            "                .mapping(\"bradesco > creditCards > creditCardInfo > creditCardNetwork\")\n" +
            "                .mapping(\"bradesco > creditCards > bills > bills > billStatus\", \"billStatus\", \"FECHADA\", \"PAGA\")\n" +
            "                .mapping(\"others > creditCards > creditCardInfo > consentId\", \"consentId\", \"itauconsentid\", \"bancodobrasil02consentid\")\n" +
            "                .mapping(\"others > creditCards > bills > bills > billStatus\", \"billStatus\", \"PAGA\", \"FECHADA\")\n" +
            "                .mapping(\"categoryMonthsAvailables > cards > internalBrand\", \"internalBrand\", \"OTHERS\", \"BRADESCO\")\n" +
            "                .mapping(\"categoryMonthsAvailables > totalAmount\", \"totalAmount\", 200000.08);\n" +
            "\n" +
            "                //Validação usando o Rest\n" +
            "        ResponseBody().body(\"bradesco.brandName\", Matchers.is(\"BRADESCO\"),\n" +
            "                        \"bradesco.creditCards.creditCardInfo[0].creditCardNetwork\", is(\"VISA\"));\n" +
            "                ResponseBody().body(\"bradesco.creditCards.bills[0].bills[0].billStatus\", is(\"FECHADA\"));\n" +
            "                ResponseBody().body(\"others[0].creditCards.creditCardInfo[1].consentId\", is(\"itauconsentid\"));\n" +
            "                ResponseBody().body(\"others[0].creditCards.bills[0].bills[0].billStatus\", is(\"PAGA\"));\n" +
            "                ResponseBody().body(\"bradescoBlocked.creditCards.bills[0].auditory\", is(nullValue()));\n" +
            "                String response = ResponseBody().extract().response().path(\"categoryMonthsAvailables[0].totalAmount\").toString();\n" +
            "                BigDecimal bigDecimal = new BigDecimal(response);\n" +
            "                Assert.assertThat(bigDecimal.doubleValue(), is(200000.08));\n" +
            "                ResponseBody().body(\"categoryMonthsAvailables[0].cards.internalBrand[2]\", is(\"BRADESCO\"));\n" +
            "                ResponseBody().body(\"categoryMonthsAvailables[0].cards.size()\", is(3));\n" +
            "    </code>\n" +
            "</pre>\n" +
            "    <br>\n" +
            "    <hr>\n" +
            "    <br>\n" +
            "    <p style=\"font-size:30px\">Detalhes do <b>mapping</b>:</p>\n" +
            "    <p>\n" +
            "        O método <b>Body.mapping()</b> vai auxiliar em TODA validação do JSON, seja comparar valores, ou certificar que\n" +
            "        o\n" +
            "        Path existe dentro do seu JSON.<br>\n" +
            "        <code>\n" +
            "            Body().mapping(\"totalSumary > cardsCount\", 2);\n" +
            "        </code>\n" +
            "    </p>\n" +
            "    <p>\n" +
            "        O método <b>Body.mapping()</b> pode ser usado para extrair um valor de uma campo passando apenas o caminho.\n" +
            "        <br>\n" +
            "    </p>\n" +
            "    <pre>\n" +
            "    <code>\n" +
            "        // Dessa forma o mapping irá validar se a key existe e colocar o valor dela na <b>StringGlobal</b>\n" +
            "        Body().mapping(\"totalSumary > cardsCount\");\n" +
            "    </code>\n" +
            "</pre>\n" +
            "    <p>Nesta validação acima o \"totalSumary\" é um objeto dentro do JSON, que contém um path chamado \"cardsCount\"<br>\n" +
            "        nele, estamos pegando o valor e comparando.<br>\n" +
            "        O sinal de <b> > </b> representa a entrada dentro da KEY, seja ela Objeto ou Array.\n" +
            "    </p>\n" +
            "\n" +
            "    <p>\n" +
            "        O método <b>Body.mapping()</b> pode ser usado para extrair um valor de uma campo passando apenas o caminho.\n" +
            "        Para o caso da KEY ser o valor de um array o Framework vai colocar todos os valores dentro da\n" +
            "        <b>StringGlobal</b>\n" +
            "        , após passar o caminho basta apenas usar o Assert passando a StringGlobal onde estará o valor.<br>\n" +
            "    </p>\n" +
            "    <pre>\n" +
            "    <code>\n" +
            "        Get();\n" +
            "        Body()\n" +
            "                         // others é um array, creditCard é um array, bills é um objeto, bills é um array, billMinimumAmount é uma key\n" +
            "                .mapping(\"others > creditCards > bills > bills > billMinimumAmount\");\n" +
            "        System.out.println(StringGlobal);\n" +
            "    </code>\n" +
            "</pre>\n" +
            "    <h2>Agora um exemplo com Array validando 2 valores:</h2>\n" +
            "    <pre>\n" +
            "    <code>\n" +
            "        // validação de 1 valor\n" +
            "        Body().mapping(\"bradesco > creditCards > bills > bills > billStatus\", \"FECHADA\");\n" +
            "\n" +
            "        // validação de 2 valores, ou seja, a key pode ser o valor FECHADA ou ABERTA, caso não, aprensentará erro\n" +
            "        Body().mapping(\"bradesco > creditCards > bills > bills > billStatus\", \"FECHADA\", \"PAGA\");\n" +
            "\n" +
            "        // validação 3 valores\n" +
            "        Body().mapping(\"bradesco > creditCards > bills > bills > billStatus\", \"FECHADA\", \"PAGA\", \"ABERTA\");\n" +
            "\n" +
            "    </code>\n" +
            "</pre>\n" +
            "    <p>\n" +
            "        Aqui temos o objecto \"bradesco\", depois um array \"creditCards\", depois um objeto \"bills\" e outro array \"bills\" e\n" +
            "        por\n" +
            "        fim o campo \"billStatus\"<br>\n" +
            "        Toda essa complexidade para encontrar o Array seria necessário usar o JSONObject e o JSONArray umas duas vezes,\n" +
            "        isso\n" +
            "        dentro de uns 4 FOR.<br>\n" +
            "        Usando o <b>mapping</b>, apenas precisamos informar o caminho até o path, depois passamos o path para o get<br>\n" +
            "        e nessa parte se torna mais interessante, temos dois valores diferentes, o framework vai buscar se existe um ou\n" +
            "        outro.<br>\n" +
            "        Como se trata de um Array o mesmo campo pode vir com valor diferente dependendo do objeto desse Array.<br>\n" +
            "        Usando o mapping você pode até passar <b>3</b> valores diferentes.\n" +
            "    </p>\n" +
            "    <br>\n" +
            "    <hr>\n" +
            "    <br>\n" +
            "    <p>O quanto o <b>mapping</b> consegue ir com arrays e objetos?</p>\n" +
            "    <br>\n" +
            "    <a href=\"https://ibb.co/PMmh2g5\"><img src=\"https://i.ibb.co/vhwPNkJ/Screen-Shot-2021-07-18-at-11-19-21.png\"\n" +
            "            alt=\"Screen-Shot-2021-07-18-at-11-19-21\" border=\"0\"></a>\n" +
            "    <pre><code>\n" +
            "        Body()\n" +
            "                .mapping(\"data > users > form > info > information > dataInfo > enterpriseInfo > enterpriseData > enterpriseBody >\" +\n" +
            "                        \" enterpriseBodyUser > enterPrime > enterPrime > enterRise > nameEnterprise\", \"CI&T\");\n" +
            "</code></pre>\n" +
            "    <br>\n" +
            "    <hr>\n" +
            "    <br>\n" +
            "    <h2>Vídeo usando o método <b>mapping</b></h2>\n" +
            "    <div class=\"video\">\n" +
            "        <div class=\"dvideo\">\n" +
            "            <iframe src=\"https://drive.google.com/file/d/1YyVMM8XxXzYfeYpF11BAREwkGd0STBcb/preview\" width=\"340\"\n" +
            "                height=\"180\" allow=\"autoplay\"></iframe>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <hr>\n" +
            "</body>\n" +
            "<br>\n" +
            "<footer>\n" +
            "\n" +
            "</footer>\n" +
            "\n" +
            "</html>\n" +
            "\n" +
            "<style>\n" +
            "    footer {\n" +
            "        margin-bottom: 40px\n" +
            "    }\n" +
            "\n" +
            "    textvideo {\n" +
            "        font-size: 20px\n" +
            "    }\n" +
            "\n" +
            "    code {\n" +
            "        font-size: 20px\n" +
            "    }\n" +
            "\n" +
            "    p {\n" +
            "        font-size: 25px\n" +
            "    }\n" +
            "\n" +
            "    li {\n" +
            "        font-size: 20px\n" +
            "    }\n" +
            "\n" +
            "    html {\n" +
            "        height: 100% !important;\n" +
            "        background-image: linear-gradient(to left bottom, #ffffff, #faf8fb,\n" +
            "                #f6f1f5, #f3e9ee, #f1e2e6, #e8d8da, #dfcdcf, #d6c3c3, #c5b4b4, #b5a5a5, #a59696, #958888);\n" +
            "        background-attachment: fixed;\n" +
            "        padding-bottom: 20px;\n" +
            "        overflow-x: hidden;\n" +
            "        padding: 20px;\n" +
            "        margin-left: 20px\n" +
            "    }\n" +
            "\n" +
            "    body {\n" +
            "        width: 100%;\n" +
            "        padding: 20px;\n" +
            "        height: 100%;\n" +
            "        bottom: 20px;\n" +
            "    }\n" +
            "\n" +
            "    pre {\n" +
            "        list-style-type: none;\n" +
            "        margin: 0;\n" +
            "        padding: 0;\n" +
            "        overflow: hidden;\n" +
            "    }\n" +
            "\n" +
            "    code {\n" +
            "        float: left;\n" +
            "        opacity: 0.5;\n" +
            "    }\n" +
            "\n" +
            "    b {\n" +
            "        color: blue;\n" +
            "    }\n" +
            "\n" +
            "    a {\n" +
            "        color: #fff;\n" +
            "    }\n" +
            "\n" +
            "    a.lightbox img {\n" +
            "        height: 150px;\n" +
            "        border: 3px solid white;\n" +
            "        box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);\n" +
            "    }\n" +
            "\n" +
            "    .dvideo {\n" +
            "        display: inline-block;\n" +
            "        *display: inline;\n" +
            "        zoom: 1;\n" +
            "        vertical-align: top;\n" +
            "        font-size: 20px;\n" +
            "        margin: 0px 0px 10px 10px\n" +
            "    }\n" +
            "\n" +
            "    /* Styles the lightbox, removes it from sight and adds the fade-in transition */\n" +
            "\n" +
            "    .lightbox-target {\n" +
            "        position: fixed;\n" +
            "        top: -100%;\n" +
            "        width: 70%;\n" +
            "        background: rgba(0, 0, 0, 0.7);\n" +
            "        width: 70%;\n" +
            "        opacity: 0;\n" +
            "        -webkit-transition: opacity 0.5s ease-in-out;\n" +
            "        -moz-transition: opacity 0.5s ease-in-out;\n" +
            "        -o-transition: opacity 0.5s ease-in-out;\n" +
            "        transition: opacity 0.5s ease-in-out;\n" +
            "        overflow: hidden;\n" +
            "    }\n" +
            "\n" +
            "    /* Styles the lightbox image, centers it vertically and horizontally, adds the zoom-in transition and makes it responsive using a combination of margin and absolute positioning */\n" +
            "\n" +
            "    .lightbox-target img {\n" +
            "        margin: auto;\n" +
            "        position: absolute;\n" +
            "        top: 0;\n" +
            "        left: 0;\n" +
            "        right: 0;\n" +
            "        bottom: 0;\n" +
            "        max-height: 0%;\n" +
            "        max-width: 0%;\n" +
            "        border: 3px solid white;\n" +
            "        box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);\n" +
            "        box-sizing: border-box;\n" +
            "        -webkit-transition: 0.5s ease-in-out;\n" +
            "        -moz-transition: 0.5s ease-in-out;\n" +
            "        -o-transition: 0.5s ease-in-out;\n" +
            "        transition: 0.5s ease-in-out;\n" +
            "    }\n" +
            "\n" +
            "    /* Styles the close link, adds the slide down transition */\n" +
            "\n" +
            "    a.lightbox-close {\n" +
            "        display: block;\n" +
            "        width: 50px;\n" +
            "        height: 50px;\n" +
            "        box-sizing: border-box;\n" +
            "        background: white;\n" +
            "        color: black;\n" +
            "        text-decoration: none;\n" +
            "        position: absolute;\n" +
            "        top: -60px;\n" +
            "        right: 0;\n" +
            "        -webkit-transition: 0.5s ease-in-out;\n" +
            "        -moz-transition: 0.5s ease-in-out;\n" +
            "        -o-transition: 0.5s ease-in-out;\n" +
            "        transition: 0.5s ease-in-out;\n" +
            "    }\n" +
            "\n" +
            "    /* Provides part of the \"X\" to eliminate an image from the close link */\n" +
            "\n" +
            "    a.lightbox-close:before {\n" +
            "        content: \"\";\n" +
            "        display: block;\n" +
            "        height: 30px;\n" +
            "        width: 1px;\n" +
            "        background: black;\n" +
            "        position: absolute;\n" +
            "        left: 26px;\n" +
            "        top: 10px;\n" +
            "        -webkit-transform: rotate(45deg);\n" +
            "        -moz-transform: rotate(45deg);\n" +
            "        -o-transform: rotate(45deg);\n" +
            "        transform: rotate(45deg);\n" +
            "    }\n" +
            "\n" +
            "    /* Provides part of the \"X\" to eliminate an image from the close link */\n" +
            "\n" +
            "    a.lightbox-close:after {\n" +
            "        content: \"\";\n" +
            "        display: block;\n" +
            "        height: 30px;\n" +
            "        width: 1px;\n" +
            "        background: black;\n" +
            "        position: absolute;\n" +
            "        left: 26px;\n" +
            "        top: 10px;\n" +
            "        -webkit-transform: rotate(-45deg);\n" +
            "        -moz-transform: rotate(-45deg);\n" +
            "        -o-transform: rotate(-45deg);\n" +
            "        transform: rotate(-45deg);\n" +
            "    }\n" +
            "\n" +
            "    /* Uses the :target pseudo-class to perform the animations upon clicking the .lightbox-target anchor */\n" +
            "\n" +
            "    .lightbox-target:target {\n" +
            "        opacity: 1;\n" +
            "        top: 0;\n" +
            "        bottom: 0;\n" +
            "        overflow: scroll;\n" +
            "    }\n" +
            "\n" +
            "    .lightbox-target:target img {\n" +
            "        max-height: 100%;\n" +
            "        max-width: 100%;\n" +
            "    }\n" +
            "\n" +
            "    .lightbox-target:target a.lightbox-close {\n" +
            "        top: 0;\n" +
            "    }\n" +
            "</style>";

    public static String HTML_DEP = "\n" +
            "    <dependencies>\n" +
            "        <dependency>\n" +
            "            <groupId>com.cit.framework</groupId>\n" +
            "            <artifactId>framework-cit-rest-java</artifactId>\n" +
            "            <version>3.3</version>\n" +
            "            <scope>system</scope>\n" +
            "            <systemPath>${basedir}/src/test/resources/framework/framework-cit-rest-java-3.3.jar</systemPath>\n" +
            "        </dependency>\n" +
            "\n" +
            "        <dependency>\n" +
            "            <groupId>br.com.bradesco.gccs.automacao</groupId>\n" +
            "            <artifactId>gccs-lib-framework-automacao</artifactId>\n" +
            "            <version>1.20.0</version>\n" +
            "        </dependency>\n" +
            "\n" +
            "        <dependency>\n" +
            "            <groupId>io.rest-assured</groupId>\n" +
            "            <artifactId>json-schema-validator</artifactId>\n" +
            "            <version>4.3.3</version>\n" +
            "        </dependency>\n" +
            "\n" +
            "        <dependency>\n" +
            "            <groupId>io.rest-assured</groupId>\n" +
            "            <artifactId>json-schema-validator</artifactId>\n" +
            "            <version>4.3.3</version>\n" +
            "        </dependency>\n" +
            "\n" +
            "        <dependency>\n" +
            "            <groupId>io.rest-assured</groupId>\n" +
            "            <artifactId>spring-mock-mvc</artifactId>\n" +
            "            <version>4.3.3</version>\n" +
            "            <scope>test</scope>\n" +
            "        </dependency>\n" +
            "\n" +
            "        <dependency>\n" +
            "            <groupId>io.rest-assured</groupId>\n" +
            "            <artifactId>rest-assured</artifactId>\n" +
            "            <version>4.3.3</version>\n" +
            "            <scope>compile</scope>\n" +
            "        </dependency>\n" +
            "\n" +
            "        <dependency>\n" +
            "            <groupId>com.nimbusds</groupId>\n" +
            "            <artifactId>nimbus-jose-jwt</artifactId>\n" +
            "            <version>9.9.2</version>\n" +
            "        </dependency>\n" +
            "        <dependency>\n" +
            "            <groupId>org.bouncycastle</groupId>\n" +
            "            <artifactId>bcprov-jdk15on</artifactId>\n" +
            "            <version>1.68</version>\n" +
            "        </dependency>\n" +
            "\n" +
            "        <dependency>\n" +
            "            <groupId>io.jsonwebtoken</groupId>\n" +
            "            <artifactId>jjwt</artifactId>\n" +
            "            <version>0.9.0</version>\n" +
            "        </dependency>\n" +
            "\n" +
            "        <dependency>\n" +
            "            <groupId>org.json</groupId>\n" +
            "            <artifactId>json</artifactId>\n" +
            "            <version>20210307</version>\n" +
            "        </dependency>\n" +
            "\n" +
            "    </dependencies>\n" +
            "\n";
};

