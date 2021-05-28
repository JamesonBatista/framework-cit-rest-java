<h1>Framework RestAssured CIT</h1>

<blockquote>O uso do framework no Bradesco necessita de alguns arquivos para executar
o framework CIT analisa se os arquivos existem no seu projeto, caso não, ele os cria.
Ao final de cada método chamado o Report Bradesco é gerado automaticamente.
</blockquote>
<h6>Seu Postman serve de referência para seu código, no Postman você pode ter:<h6>

- get com header, parametros, endpoint, etc.
- post com header, parametros, endpoint, body, etc.
- put ....
- delete ...

<h4>Como iniciar meu GET POST PUT DELETE?</h4>
<h6>Faremos validações no JSON de exemplo, no final do deste DOC</h6>

_Abaixo temos um exemplo completo de como usar um simples GET_

- Extenda o framework na classe do seu STEP como abaixo:
- Chame o métido InitEnvironmente(), nele você terá o start do RestAssured com todas as funcionalidades, dentro dele
  passe o endpoint que você quer.
- Get
    - validar statusCode
    - validar retorno do campo first_name no body
```androiddatabinding
public class GetUser extends CITRestAssured {

    @Given("^que seja feito GET na API \"([^\"]*)\"$")
    public void queSejaFeitoGETNaAPI(String endpoint) throws Throwable {

        InitEnvironment(endpoint);
    }

    @Then("^faco get$")
    public void facoGet() throws Throwable {

        Get();
    }

}
```

<h4>E no caso do endpoint precisar ser passado no get() post() delete() put() e não no InitEnvironment() ?</h4>
_Simples, basta usar o exemplo abaixo, retire o endpoint do InitEnvironment()_

- Se seu get tiver essa condição, chame o GetEndpoint(); conforme abaixo:
- E dentro dele passe o endpoint que você deseja.

```androiddatabinding
public class GetUser extends CITRestAssured {

    @Given("^que seja feito GET na API \"([^\"]*)\"$")
    public void queSejaFeitoGETNaAPI(String endpoint) throws Throwable {

        InitEnvironment();
    }

    @Then("^faco get$")
    public void facoGet() throws Throwable {

        GetEndpoint("users/7");
    }

}
```

<h4>Efetuando validações</h4>
_Simples, usando o step acima para efetuar validações é fácil_

- Depois do métido GetEndpoint() basta efetuar as validações como abaixo:

```androiddatabinding
   GetEndpoint("users/7")
                .statusCode(200)
        .body("data.first_name", Matchers.is("Michael"));
        
        GetEndpoint("users/7")
                .statusCode(200)
        .body("support.url", Matchers.is("https://reqres.in/#support-heading"));
```

- Caso seu endpoint esteja dentro do InitEnvironment(endpoint);

```androiddatabinding
 Get()
      .statusCode(200)
      .body("data.first_name", Matchers.is("Michael"));
        
 Get()
     .statusCode(200)
     .body("support.url", Matchers.is("https://reqres.in/#support-heading"));
```
<h4>E para extrair uma informação? Um valor?</h4>
```androiddatabinding
String value = Get().extract().path("data.first_name");
        System.out.println("valor extraído é: " + value);
```
<h4>Em caso de um Post?</h4>
_Nosso framework tem a cobertura dos possíveis posts_

*Exemplos de métodos incluídos no framework:*
- Post();
- PostBody();
- PostEndpoint();
- PostHeader();
- PostHeaderParam();

E tantos outros. Basta olhar seu Postman e ver a necessidade.
- Post
    - validar statusCode
    - validar retorno do body
    - passando body que será enviado, TODO post precisa enviar um body.

```androiddatabinding
    String body = "{}";
       PostBody(body).statusCode(201).body("data.first_name", Matchers.is("Michael"));
```

<h4>Em caso do meu post no Postman ter body, header, endpoint e param?</h4>
_Use a mesma lógica do código abaixo para sua necessidade de acordo com o Postman_
- No caso de ter um header e, ou param, você precisa chamar o params ou headers global, como abaixo.
- headers e params serão enviados diretamente para seu método.
- É obrigatório passar os valores, isso nos garante acertividade no método.
```androiddatabinding
        params.put("key", "value");
        params.put("key2", "value2");
        
        headers.put("key", "value");
        headers.put("key2", "value2");
        
        String body = "{}";
       PostParamHeaderBodyEndpoint("users/7", body)
       .body("data.first_name", Matchers.is("Michael"));
```
<h3>Use a mesma lógica de métodos acima para chamar o que você precisa, por exemplo:</h3>

- Basta chama esse e outros métodos de acordo com a necessidade.
```androiddatabinding
        GetHeader();
        GetParamHeader();
        GetParamHeaderEndpoint();
        GetParam();
        
        PostParamHeaderBodyEndpoint();
        PostBodyEndpoint();
        PostHeaderBody();
        
        PutBody();
        PutBodyEndpoint();
        PutHeaderBody();
        
        Delete();
        DeleteEndpoint();
        DeleteHeader();
```

<h4>Em caso de precisar ser feito um método Rest mais complexo?</h4>
- Chame o método Given() depois disso basta usar o when().post() , when().get, etc.
- Ao final do Given() será necessário chamar o método que cria o report, no Given() não é criado automaticamente
```androiddatabinding
Given().when().get();
ou
Given().formParam("key", "value")
.formParam("key", "value").when().post();  

ReportBradesco();
```

```cloudfoundry
{
    "data": {
        "id": 7,
        "email": "michael.lawson@reqres.in",
        "first_name": "Michael",
        "last_name": "Lawson",
        "avatar": "https://reqres.in/img/faces/7-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
```