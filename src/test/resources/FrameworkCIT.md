<h1>Framework RestAssured CIT</h1>

>>Acesse  link driver com o JAR, vídeos de validações, etc.  
<a href="https://drive.google.com/drive/folders/10cRqFVxSy7iGjh5mmsoEbF41x-lt9nQZ?usp=sharing">Acesse aqui</a>

<blockquote>O uso do framework no Bradesco necessita de alguns arquivos para executar,
o framework CIT analisa se os arquivos existem no seu projeto, caso não, ele os cria.
Ao final de cada método chamado o Report Bradesco é gerado automaticamente.
</blockquote>

*Além disso dentro de sua classe de execucão dos testes Ex: RegresstionTest*

Ele irá excluir seus reports antigos a cada execução

> > Em caso de chamar métodos sem precisar usar o reportBradesco
>
> Basta apenas passar dentro do método como último parâmetro o **false**, exemplo:
>

```androiddatabinding
 GetEndpoint("users/7", false);
 Get(false);
 PostHeader(false);

```

```androiddatabinding
public class RegressionTest {
    @BeforeClass
    public static void init() throws IOException, InterruptedException {
        ExcludReportBradesco();
    }
}
```

***
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

***
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

***
<h4>Efetuando validações</h4>
_Simples, usando o step acima para efetuar validações é fácil_

- Depois do método GetEndpoint() basta efetuar as validações como abaixo:

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

***

* Para efetuar testes de Contrato ou Schema.

> > Pode ser com qualquer um dos métodos do framework.

```androiddatabinding
        Get().body(matchesJsonSchemaInClasspath("SCHEMA/jsontestado.json"));

```

***
<h4>E para extrair uma informação? Um valor?</h4>

```androiddatabinding
String value = Get().extract().path("data.first_name");
        System.out.println("valor extraído é: " + value);
```

***
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

***
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

***
<h4>Use a mesma lógica de métodos acima para chamar o que você precisa, por exemplo:</h4>

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

***
<h4>Quais métodos temos?</h4>

- Gets
    - Get
    - GetEndpoint
    - GetParamHeaderEndpoint
    - GetParam
    - GetParamEndpoint
    - GetParamHeader
    - GetHeader
    - GetHeaderEndpoint

- Posts
    - Post
    - PostBody
    - PostBodyEndpoint
    - PostParamHeaderBodyEndpoint
    - PostParamHeaderBody
    - PostParamBodyEndpoint
    - PostParamBody
    - PostHeaderBody
    - PostHeaderBodyEndpoint

- Puts
    - PutBody
    - PutBodyEndpoint
    - PutParamHeaderBodyEndpoint
    - PutParamHeaderBody
    - PutParamBodyEndpoint
    - PutParamBody
    - PutHeaderBody
    - PutHeaderBodyEndpoint

- Deletes
    - Delete
    - DeleteEndpoint
    - DeleteParam
    - DeleteParamEndpoint
    - DeleteParamHeader
    - DeleteHeader
    - DeleteHeaderEndpoint
    - DeleteParamHeaderEndpoint

- Métodos diversos
    - CertificationSpec - Metodo que usar certificados
    - JwtPS256 - Criar JWT PS256
    - JwtHS256 - Criar JWT HS256

***
<h4>Método separado</h4>

> Existe um método chamado GivenExternal();

Para o caso de, você precisar usar uma chamada diferente, com caracteristicas que os métodos existentes não atendam. Em
métodos separados, é necessário usar o GivenExternal do framework para que ele use os dados no Report.

Ao final, chame o ExternalReport(); para que seja gerado

**E como usar?**
*Simples!*

- *_Apenas em caso de DELETES dentro do ExternalReporte(); precisa ficar vazio_*

```androiddatabinding
        Response res = GivenExternal(ContentType.JSON)
                .when()
                .get()
                .then()([[Aqui poeria efetuar validações normalmente. Ex: then().body("path"), is("CIT")]
                .extract().response();
        ExternalReport(res);
        
        GivenExternal(ContentType.JSON)
                .when()
                .delete()
                .then() ([[Aqui poeria efetuar validações normalmente. Ex: then().body("path"), is("CIT")]])'
                .extract().response();
       ExternalReport();
```

***

<h4>Etraindo valor do método GivenExternal</h4>

```androiddatabinding

 ValidatableResponse res = GivenExternal(ContentType.JSON)
                .when()
                .get()
                .then();
        String value = res.extract().path("path que eu quero");
        
        ## caso precise de report, use as lógicas já descrita abaixo.
        ExternalReport(res.extract().response());
```

***
<h4>Quais dados usar no ExternalReport(); ?</h4>

- Gets - Basta enviar o resultado da requisição. Como no exemplo acima.

```androiddatabinding

    @Then("^faco get$")
    public void facoGet() throws Throwable {
        
        ValidatableResponse res = GivenExternal(ContentType.JSON)
                .when().get("users/7").then();

        ExternalReport(res.extract().response());

    }
```

- Posts - você precisa passar primeiro o Body que está sendo enviado, depois o resultado da requisição.

```androiddatabinding
String body = "{body que será enviado}";

  Response res = GivenExternal(ContentType.JSON)
                .body(body)
                .when()
                .post()
                .then()([[Aqui poderia efetuar validações normalmente. Ex: then().body("path"), is("CIT")]
                .extract().response();
                
             ExternalReport(body, res);
                
```

- Puts - repetem a mesma lógica que o Post

***
<h4>Como ficam métodos GivenExternal com headers e parâmetros? </h4>

*Simples!*

- Basta usa-los dessa forma:

```androiddatabinding
    Map<String, Object> param = new new HashMap<>();
    param.put("key", "value");
        
    Map<String, Object> header = new new HashMap<>(); 
    header.put("key", "value");
    
    Response res = GivenExternal(ContentType.JSON)
                .headers(header)
                .queryParams(param)
                .body(body)
                .when()
                .post()
                .then()([[Aqui poeria efetuar validações normalmente. Ex: then().body("path"), is("CIT")]
                .extract().response();
                
             ExternalReport(body, res);

```

*****
<h4>Vamos usar alguns exemplos:</h4>
> Extrair valores: Em caso do método não precisar usar o report Bradesco,
> use o **false** dentro do método. Você pode trocar o Get() por qualquer outro método.

```androiddatabinding
  String valorExtraido = Get().extract().path("data.first_name");
        System.out.println("Valor extraído:  " + valorExtraido);
```

> Validar se no Response existe determinado Path, ou campo.

```androiddatabinding
   Get().statusCode(HttpStatus.SC_OK)
                .body(containsString("first_name"));
```

> **Comparar valores:**

 ```androiddatabinding
 
   Get().body("data.first_name", Matchers.is("Michael"));
   
```

> Extraindo 2 valores e comparando

```androiddatabinding
    Get();
    
    String value1 = ResponseBody().extract().path("data.first_name");
    
    String value2 = ResponseBody().extract().path("data.email");
    
    Assert.assertEquals(value1, value2);
```

*Use o Body() para efetuar valições de existências de campos, abaixo um exemplo de validação em alguns campos.*

**Usando como o exemplo abaixo você está validando cada objeto dentro do array data.**

```androiddatabinding
{
    "page": 1,
    "per_page": 6,
    "total": 12,
    "total_pages": 2,
    "data": [
        {
            "id": 1,
            "name": "cerulean",
            "year": 2000,
            "color": "#98B2D1",
            "pantone_value": "15-4020"
        },
        {
            "id": 2,
            "name": "fuchsia rose",
            "year": 2001,
            "color": "#C74375",
            "pantone_value": "17-2031"
        }]

           Get();
            
          Body().root("data")
        .object("id", "name")
        
        OU
        
                Body()
                .contains("page",
                "per_pag",
                "total",
                "total_pages",
                .root("data")
                .object("id", "name", "color", "pantone_value");
        
        
```

> **Exemplo 2 - Agora usando um Array JSON**
> *Quando sua validação for um Array JSON de objetos conforme abaixo, use conforme está o exemplo.*

```androiddatabinding
[
    {
        "id": 1,
        "name": "Leanne Graham",
        "username": "Bret",
        "email": "Sincere@april.biz",
        "address": {
            "street": "Kulas Light",
            "suite": "Apt. 556",
            "city": "Gwenborough",
            "zipcode": "92998-3874",
            "geo": {
                "lat": "-37.3159",
                "lng": "81.1496"
            }
        },
        "phone": "1-770-736-8031 x56442",
        "website": "hildegard.org",
        "company": {
            "name": "Romaguera-Crona",
            "catchPhrase": "Multi-layered client-server neural-net",
            "bs": "harness real-time e-markets"
        }
    },
    {
        "id": 2,
        "name": "Ervin Howell",
        "username": "Antonette",
        "email": "Shanna@melissa.tv",
        "address": {
            "street": "Victor Plains",
            "suite": "Suite 879",
            "city": "Wisokyburgh",
            "zipcode": "90566-7771",
            "geo": {
                "lat": "-43.9509",
                "lng": "-34.4618"
            }
        },
        "phone": "010-692-6593 x09125",
        "website": "anastasia.net",
        "company": {
            "name": "Deckow-Crist",
            "catchPhrase": "Proactive didactic contingency",
            "bs": "synergize scalable supply-chains"
        }
    }]
            Get();
    
            Body()
                .root()
                .object("id", "name", "email")
                .newObject("address", "street")
                .newObject("address geo", "lat", "lng");
```

**Se deseja apenas validar em todo JSON se os valores existem**

```androiddatabinding

        Body()
              .contains("id",
              "name",
              "email")
```

> Abaixo outro exemplo, analisando o JSON por completo.

```androiddatabinding
{
    "page": 1,
    "per_page": 6,
    "total": 12,
    "total_pages": 2,
    "data": [
        {
            "id": 1,
            "name": "cerulean",
            "year": 2000,
            "color": "#98B2D1",
            "pantone_value": "15-4020"
        },
        {
            "id": 2,
            "name": "fuchsia rose",
            "year": 2001,
            "color": "#C74375",
            "pantone_value": "17-2031"
        }
    ],
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}

 Body().contains("page", "per_page")
        .root("data")
        .object("id", "name")
        .root(Keyobject "support", path "url", equals "https://reqres.in/#support-heading")
        .and("support", "text", "Olá estamos testando" );
        
        No primeiro root, estamos avaliando os campos dentro do array de objetos.
        No segundo, estamos entrando no objeto support, pegando o valor do campo URL
        e comparando.
        
                *os nomes Keyobject, path, equals não devem ser digitados, são apenas referências.*
                    .root("support", "url", "https://reqres.in/#support-heading");

```

> Para usar o Body() no GivenExternal() precisa fazer conforme exemplo abaixo:
>
>Seguindo conforme nos exemplos acima de uso do Body() você efetuar as mesmas validações.

 ```androiddatabinding
        ExternalContainsJSON = GivenExternal()
                               .when().get().then().log().body();

        Body().contains("id", "data");

 ```
>>Vamos a mais um exemplo complexo de JSON e como validar usando o Framework

```androiddatabinding
{
    "totalSumary": {
        "cardsCount": 2,
        "limitAmount": 0,
        "usedAmount": 0,
        "availableAmount": 0,
        "percentileAmount": 0,
        "usedAmountFlexible": 100000.0001
    },
    "bradesco": {
        "consentId": "",
        "authorisationServerId": "",
        "brandName": "BRADESCO",
        "brandId": "237",
        "imageName": "bradesco",
        "color": "D11E45",
        "creditCards": [
            {
                "creditCardInfo": {
                    "consentId": "",
                    "authorisationServerId": "",
                    "creditCardAccountId": "4532119999910005",
                    "identificationNumber": "0000",
                    "creditCardNetwork": "VISA",
                    "status": "ACTIVE"
                },
                "limits": {
                    "isFlexible": null,
                    "purchaseAvailableAmount": null,
                    "purchaseUsedAmount": null,
                    "purchaseTotalAmount": null,
                    "purchaseUsedPercentile": null,
                    "withdrawAvailableAmount": null,
                    "withdrawUsedAmount": null,
                    "withdrawTotalAmount": null,
                    "withdrawUsedPercentile": null,
                    "auditory": null
                },
                "lastBill": null,
                "bills": {
                    "bills": [
                        
                    ],
                    "auditory": null
                },
                "hasMessageErrors": 100.00,
                "oldestUpdateDateTime": null
            }
        ]
    },
    "next": {
        "consentId": "nextconsentid",
        "authorisationServerId": "nextauthorisationserverid",
        "brandName": null,
        "brandId": null,
        "imageName": "",
        "color": "07FB4E",
        "creditCards": [
            {
                "creditCardInfo": {
                    "consentId": "nextconsentid",
                    "authorisationServerId": "nextauthorisationserverid",
                    "creditCardAccountId": "1020304051",
                    "identificationNumber": "4454",
                    "status": "ACTIVE"
                },
                "limits": {
                    "isFlexible": true,
                    "purchaseAvailableAmount": 0,
                    "purchaseUsedAmount": 100000.0001,
                    "purchaseTotalAmount": 0,
                    "purchaseUsedPercentile": 0,
                    "withdrawAvailableAmount": null,
                    "withdrawUsedAmount": null,
                    "withdrawTotalAmount": null,
                    "withdrawUsedPercentile": null,
                    "auditory": null
                },
                "lastBill": {
                    "billId": "10",
                    "billMonth": "2021-04-21",
                    "dueDate": "2021-05-21",
                    "billMinimumAmount": 1000.04,
                    "billTotalAmount": 100000.04,
                    "bestPurchaseDate": null,
                    "billStatus": "PAGA",
                    "automaticPayment": null
                },
                "bills": {
                    "bills": [
                        {
                            "billId": "10",
                            "billMonth": "2021-04-21",
                            "dueDate": "2021-05-21",
                            "billMinimumAmount": 1000.04,
                            "billTotalAmount": 100000.04,
                            "bestPurchaseDate": null,
                            "billStatus": "PAGA",
                            "automaticPayment": null
                        }
                    ],
                    "auditory": "auditoryNext"
                },
                "hasMessageErrors": 100.124,
                "oldestUpdateDateTime": null
            }
        ]
    },
    "others": [
        
    ],
    "bradescoBlocked": {
        "consentId": null,
        "authorisationServerId": null,
        "brandName": "Bradesco",
        "brandId": null,
        "imageName": "bradesco",
        "color": "D11E45",
        "creditCards": [
            
        ]
    },
    "categoryMonthsAvailables": [
        {
            "dateMonth": "2021-04-21",
            "totalAmount": 100000.04,
            "cards": [
                {
                    "internalBrand": "NEXT",
                    "consentId": "nextconsentid",
                    "authorisationServerId": "nextauthorisationserverid",
                    "creditCardId": "1020304051",
                    "cardNumber": "4454",
                    "billId": "10",
                    "dueDate": "2021-05-21"
                }
            ]
        }
    ],
    "lastUpdateDateTime": "2021-07-06T11:46:22.227421Z",
    "messages": [
        
    ]
}


        Get(false);

        Body()
                .root("totalSumary", "cardsCount", 2)
                .root("bradesco", "brandName", "BRADESCO")
                .objectForArray("next", "creditCards", "hasMessageErrors")
                .newObjectForArray("bills", "bills", "billId")
                .getObject("bills", "auditory", "auditoryNext")
                .root("next", "consentId", "nextconsentid")
                .getObject("creditCardInfo", "status", "ACTIVE")
                .root("categoryMonthsAvailables")
                .object("dateMonth")
                .getString("totalAmount", 100000.04)
                .newArray("cards", "authorisationServerId")
                .get("totalAmount")
                
                O *get* ao final está pegando o valor do campo e transferindo o valor para o *StringGlobal*
```
***

>>Maneira ainda mais simples de validar um campo ou um valor é usando o **mapping**
>>e passando o caminho da estrutura, usando o   * **>**  *  servirá como caminho para o path.
>
> OBS: Validar valores dentro de um Array não é possível, porque eles podem variar, mas, é possível validar se o campo existe.
```androiddatabinding
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
        Body().mapping("data > id", 7)
              .mapping("support > url", "https://reqres.in/#support-heading");
```
>>Validações usando o RestAssured, neste link estará o JSON que foi validado <a href="http://demo0623716.mockable.io/">Acesse aqui</a>

```androiddatabinding
       ResponseBody().body("bradesco.brandName", Matchers.is("BRADESCO"),
                "bradesco.creditCards.creditCardInfo[0].creditCardNetwork", is("VISA"));

        ResponseBody().body("bradesco.creditCards.bills[0].bills[0].billStatus", is("FECHADA"));

        ResponseBody().body("others[0].creditCards.creditCardInfo[1].consentId", is("itauconsentid"));

        ResponseBody().body("others[0].creditCards.bills[0].bills[0].billStatus", is("PAGA"));

        ResponseBody().body("bradescoBlocked.creditCards.bills[0].auditory", is(nullValue()));

        String response = ResponseBody().extract().response().path("categoryMonthsAvailables[0].totalAmount").toString();
        BigDecimal bigDecimal = new BigDecimal(response);
        Assert.assertThat(bigDecimal.doubleValue(), is(200000.08));

        ResponseBody().body("categoryMonthsAvailables[0].cards.internalBrand[2]", is("BRADESCO"));

        ResponseBody().body("categoryMonthsAvailables[0].cards.size()", is(3));
```


> > Abaixo veja mais um exemplo usando o Body()

```androiddatabinding
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
        * Passando apenas 2 parâmetros no root, iremos verificar se o campo existe no JSON *
         Body().root("data", "id")
            .and("support", "text")
            .and("support", "url");
    

```