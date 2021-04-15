#### A versão 3.0 é a versão sem o Report a 3.1 é com o Report
```bazaar
        <dependency>
            <groupId>com.cit.framework</groupId>
            <artifactId>framework-cit-rest-java</artifactId>
            <version>3.0</version>
        </dependency>
```

# framework-cit-rest-java

*Para baixar o maven 3.1, necessário ter o arquivo settings.xml configurado, dentro de sua pasta .m2 .*

*Se sua Chave não tiver acesso , solicite, do contrário o maven não baixará as dependências
necessárias*

#### Para usar o Framework, basta extender o frame no seu step, Ex:

> public class StepUser extends CITFrameworkRestAssured{}

###### No seu step onde normalmente fica o **endpoint** você chama o *RestEnvironment*
```
    @Given("^que seja acessado o endpoint \"([^\"]*)\"$")
    public void queSejaAcessadoOEndpoint(String endpoint) throws Throwable {
        RestEnvironment(endpoint);
    }

```

*Caso você precise passar o endpoint no método chamado Ex:*
```
GetEndpoint("api/users/id").body("users.id", Matchers.is(10));
```

*Seu Given, onde normalmente fica o endpoint vindo da feature, ficaria dessa forma:*

```
 @Given("^que seja acessado o endpoint \"([^\"]*)\"$")
    public void queSejaAcessadoOEndpoint(String endpoint) throws Throwable {
    
        RestEnvironment();
    }

 @Then("^for efetuado um get$")
    public void forEfetuadoUmGet() throws Throwable {

        GetEndpoint("api/users/id").body("users.id", Matchers.is(10));
    }
```

**Você precisará ter na raiz do seu projeto uma pasta chamada environment**

*Caso não tenha, crie uma pasta na raiz do projeto chamada environment e dentro um arquivo chamado data.properties,
dentro dele estará os valores dos Ambientes, EX: tu=http://11111111111111111.*

**crie e cole os valores abaixo**

*copie apenas os valores abaixo*

- tu=....
- ti=.....
- th=....
- tu_local=.....
- local=.....
- prod=......
- env1=.........
- env2=.........
- env3=.........
- env4=.........
- default=tu......

o **default=tu** será a base, caso queira mudar de ambiente.

Dentro do data.properties existe uma variável chamada **excludReport=logs** será o path de exclusão do seu Report. Caso
queira mudar o local que é gerado o Report, dentro de test/resources existe (precisa existir) um arquivo chamado
setup.properties, dentro dele você altera onde será gerado, seguida altere o **excludReport** para o local onde deixou
no setup.properties

Automaticamente será decidido entre GET e POST a criação do Report.

###### Em caso de POST, **SEMPRE** passar o valor do body dentro do método chamado Ex:
```
PostHeaderBody(body).body("data.name", is("CIT"));
```
###### Headers e Params, use dessa forma no seu step PARAM.put("key", "value") OBS: nao precisa passar dentro do metodo Ex:

```
@When('usandoMetodoParaDarUmGet')
public void usandoMetodoParaDarUmGet(){

PARAM.put("key", "value");
PostParamBody(body).statusCode(201).body("data.name", "default");

HEADER.put("key", "value");
GetHeader().statusCode(200).body("data.name", "default");

PARAM.put("key", "value");
HEADER.put("key", "value");
GetParamHeader().statusCode(200).body("data.name", "default");

}  
```
###### *Abaixo, alguns exemplos que podem ser usados:*

```
String value = Get().extract().path("data.name").toString();
Assert.assertEquals(value, Matchers.is("default"));

PARAM.put("key", "value");
HEADER.put("key", "value");
PostParamHeaderBodyEndpoint(body, "api/user/id").body("users.id", Matchers.is(10));

Get().body("users.id", Matchers.is(10));

GetEndpoint("api/users/id").body("users.id", Matchers.is(10));

PostBodyEndpoint(body, "api/users/id").statusCode(201).body("users.id", Matchers.is(10));
```
### Aqui um Step completo de Exemplo
```
package steps.Gets;

import com.cit.framework.CITFrameworkRestAssured;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class GetUser extends CITFrameworkRestAssured {


    @Given("^que seja acessado o endpoint \"([^\"]*)\"$")
    public void queSejaAcessadoOEndpoint(String endpoint) throws Throwable {
        RestEnvironment(endpoint);
    }

    @Then("^for efetuado um get$")
    public void forEfetuadoUmGet() throws Throwable {
        String body = "{\"data\":\n" +
                "{\"name\":\"CIT\"}\n" +
                "}";
          
    HEADER.put("key","value");      
    PostParamHeaderBody(body).statusCode(201).body("users.id", Matchers.is(10));

    }

}
```
## OU
```
public class GetUser extends CITFrameworkRestAssured {


    @Given("^que seja acessado o endpoint \"([^\"]*)\"$")
    public void queSejaAcessadoOEndpoint() throws Throwable {
        RestEnvironment();
    }

    @Then("^for efetuado um get$")
    public void forEfetuadoUmGet() throws Throwable {
        String body = "{\"data\":\n" +
                "{\"name\":\"CIT\"}\n" +
                "}";
        PARAM.put("key","value");        
        PostParamBodyEndpoint(body, "api/user/id").statusCode(201).......

    }

}
```
> *Ao final de cada método chamado, o ReportBradesco será executado automaticamente*
   
 >> Todas as funções da versão 3.0 estão na 3.1, única mudança é que a 3.1 usa o Report no final.
 > Caso não precise usar o Report, use a 3.0.
