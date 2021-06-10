<h2>Framework CITRestAssured</h2>

>Para usar o framework CI&T siga os passos seguintes:

* Dentro da pasta src/test/resources crie uma pasta chamada framework

* Baixe o arquivo *framework-cit-rest-java-3.3.jar* e coloque dentro dela.

* Dentro do seu pom.xml dentro de *<dependencies>* cole da seguinte forma:

```androiddatabinding
        <dependency>
            <groupId>com.cit.framework</groupId>
            <artifactId>framework-cit-rest-java</artifactId>
            <version>3.3</version>
            <scope>system</scope>
            <systemPath>${basedir}/src/test/resources/framework/framework-cit-rest-java-3.3.jar</systemPath>
        </dependency>
```

* Ainda dentro de *<dependencies>* cole as seguintes dependências:

```androiddatabinding
  <dependency>
            <groupId>br.com.bradesco.gccs.automacao</groupId>
            <artifactId>gccs-lib-framework-automacao</artifactId>
            <version>1.20.0</version>
        </dependency>

        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-schema-validator</artifactId>
            <version>4.3.3</version>
        </dependency>

        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-schema-validator</artifactId>
            <version>4.3.3</version>
        </dependency>

        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>spring-mock-mvc</artifactId>
            <version>4.3.3</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>4.3.3</version>
            <scope>compile</scope>
        </dependency>

        <dependency>
            <groupId>com.nimbusds</groupId>
            <artifactId>nimbus-jose-jwt</artifactId>
            <version>9.9.2</version>
        </dependency>
        <dependency>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcprov-jdk15on</artifactId>
            <version>1.68</version>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.0</version>
        </dependency>

        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20210307</version>
        </dependency>

```
* No seu intelliJ File -> Invalid cache, clique em Restart
* Após, dentro da sua classe *RegressionTest* faça exatamente como abaixo:

```androiddatabinding
@RunWith(OctaneCucumber.class)
@CucumberOptions(
        features = {"features"},
        glue = {"steps"},
        plugin = {"junit:cucumberTestingResults.xml", "com.bradesco.report.CucumberReporterPlugin"},
        snippets = SnippetType.CAMELCASE
        ,
        tags = "@get, @post"
)
public class RegressionTest {
    @BeforeClass
    public static void init() throws IOException, InterruptedException {
        ExcludReportBradesco();
    }
}


* Assim que terminar, rode seu projeto, e dentro de *src/test/java/resources* o arquivo
FrameworkCIT será gerado, contendo como usar o framework.
```