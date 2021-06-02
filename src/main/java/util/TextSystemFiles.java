package util;

public class TextSystemFiles {

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
            "excludReport=target/logs";

    public static String framework = "<h1>Framework RestAssured CIT</h1>\n" +
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
            "    - CertificationSpec\n" +
            "    - JwtPS256\n" +
            "    - JwtHS256\n" +
            "\n" +
            "***\n" +
            "<h4>Método separado</h4>\n" +
            "\n" +
            "Existe um método chamado GivenExternal();\n" +
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
            "                .then()([[Aqui poeria efetuar validações normalmente. Ex: then().body(\"path\"), is(\"CIT\")]\n" +
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
            "> Comparar valores\n" +
            "\n" +
            " ```androiddatabinding\n" +
            "   Get().body(\"data.first_name\", Matchers.is(\"Michael\"));\n" +
            "```\n" +
            "\n" +
            "> Extraindo 2 valores e comparando\n" +
            "\n" +
            "```androiddatabinding\n" +
            "    ValidatableResponse root = Get();\n" +
            "    \n" +
            "    String value1 = root.extract().path(\"data.first_name\");\n" +
            "    \n" +
            "    String value2 = root.extract().path(\"data.email\");\n" +
            "    \n" +
            "    Assert.assertEquals(value1, value2);\n" +
            "```\n" +
            "\n" +
            "> Caso você tenha um Array com vários objetos dentro, mas quer validar se em casa objeto exite um determinado campo.\n" +
            "> Ou pegar o valor desse campo, com determinado valor, etc.\n" +
            "> Usaremos o exemplo de JSON abaixo:\n" +
            "\n" +
            "*O método ValidationPathArrayListObjects() vai validar a existância dos campos(paths) que você precisa validar dentro de\n" +
            "um Array de Objetos. OBS: no exemplo abaixo, foram passados 3 campos, mas você pode passar quantos achar necessário.*\n" +
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
            "        String valorDoCampoQueQueroPegar = \"name\";\n" +
            "        List<T> extract = Get().extract().response().jsonPath().getList(\"data\");\n" +
            "       Object valor = ValidationPathArrayListObjects(extract, valorDoCampoQueQueroPegar, \"id\", \"address\");\n" +
            "       \n" +
            "       **OBS** : Caso seu Array retorne apenas um objeto e você queira validar existência e pegar o valor\n" +
            "       basta passar ele conforme acima, caso não:\n" +
            "       \n" +
            "       Object valor = ValidationPathArrayListObjects(extract, \"\", \"id\", \"address\")\n" +
            "        \n" +
            "        \n" +
            "        \n" +
            "```\n" +
            "\n" +
            "> Em caso onde seu retorno não tem um RootPath como no exemplo acima é o **data**:\n" +
            "\n" +
            "```androiddatabinding\n" +
            "[\n" +
            "  {\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Leanne Graham\",\n" +
            "    \"username\": \"Bret\",\n" +
            "    \"email\": \"Sincere@april.biz\",\n" +
            "    \"address\": {\n" +
            "      \"street\": \"Kulas Light\",\n" +
            "      \"suite\": \"Apt. 556\",\n" +
            "      \"city\": \"Gwenborough\",\n" +
            "      \"zipcode\": \"92998-3874\",\n" +
            "      \"geo\": {\n" +
            "        \"lat\": \"-37.3159\",\n" +
            "        \"lng\": \"81.1496\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"phone\": \"1-770-736-8031 x56442\",\n" +
            "    \"website\": \"hildegard.org\",\n" +
            "    \"company\": {\n" +
            "      \"name\": \"Romaguera-Crona\",\n" +
            "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
            "      \"bs\": \"harness real-time e-markets\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 2,\n" +
            "    \"name\": \"Ervin Howell\",\n" +
            "    \"username\": \"Antonette\",\n" +
            "    \"email\": \"Shanna@melissa.tv\",\n" +
            "    \"address\": {\n" +
            "      \"street\": \"Victor Plains\",\n" +
            "      \"suite\": \"Suite 879\",\n" +
            "      \"city\": \"Wisokyburgh\",\n" +
            "      \"zipcode\": \"90566-7771\",\n" +
            "      \"geo\": {\n" +
            "        \"lat\": \"-43.9509\",\n" +
            "        \"lng\": \"-34.4618\"\n" +
            "      }\n" +
            "    }]\n" +
            "    \n" +
            "        String valorDoCampoQueQueroPegar = \"name\";\n" +
            "        List<T> extract = Get().extract().response().jsonPath().getList(\"$\");\n" +
            "       Object valor = ValidationPathArrayListObjects(extract, valorDoCampoQueQueroPegar, \"id\", \"address\");\n" +
            "       \n" +
            "       **OBS** : Caso seu Array retorne apenas um objeto e você queira validar existência e pegar o valor\n" +
            "       basta passar ele conforme acima, caso não:\n" +
            "       \n" +
            "       Object valor = ValidationPathArrayListObjects(extract, \"\", \"id\", \"address\")\n" +
            "        \n" +
            "```\n" +
            "\n" +
            "***\n" +
            "> Abaixo, JSON usado para montar o Doc.\n" +
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
}
