# framework-cit-rest-java

#### Para usar o Framework, basta extender o frame no seu step, Ex:
    
> public class StepUser extends CITFrameworkRestAssured{}
    
    
E chamar o metodo que você quer, Ex:
```
PostParam();
```
Isto é um Post com parametros given().queryparams().when.post().then();
    
Caso não tenha, crie uma pasta na raiz do projeto chamada environment e dentro um arquivo chamado data.properties, dentro dele estará
os valores dos Ambientes, EX: tu=http://11111111111111111. 

**Caso nao tenha o data.properties, crie e cole os valores abaixo**

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
- default=......

o **default=tu** será a base, caso queira mudar de ambiente.

Dentro do data.properties existe uma variavel chamada **excludReport=logs** será o path de exclusão do seu Report. Caso queira mudar o local que é gerado
o Report, dentro de test/resources existe (precisa existir) um arquivo chamado setup.propesties, dentro dele você altera onde será gerado,
seguida altere o **excludReport** para o local onde deixou no setup.properties

    
Todos os métodos chamados nos steps, precisa passar o valor para **RESPONSE** e terminar com **extract().response**.
Note, sem o extract().response(), você verá uma linha vermelha indicando erro.
Isso porque a variável **RESPONSE** precisa da extração para ser usada no Report
```
RESPONSE = Get().extract().response();
```
Isso fará com que o report capte o Body do response.
    
Automaticamente será decidido entre GET e POST a criação do Report. 

Em caso de POST, **SEMPRE** passar o valor do body dentro do método chamado Ex:
```
RESPONSE = PostHeaderBody(body).body("data.name", is("CIT")).extract().response();
```
    
Headers e Params, use dessa forma no seu step PARAM.put("key", "value") OBS: nao precisa passar dentro do metodo Ex: 
```
@When('usandoMetodoParaDarUmGet')
public void usandoMetodoParaDarUmGet(){

PARAM.put("key", "value");
RESPONSE = PostParamBody(body)statusCode(201).body("data.name", "default").extract().response();

HEADER.put("key", "value");
RESPONSE = GetHeader().statusCode(200).body("data.name", "default").extract().response;

PARAM.put("key", "value");
HEADER.put("key", "value");
RESPONSE = GetParamHeader().statusCode(200).body("data.name", "default").extract().response;

}  
``` 

O Report será chamado no seu Hooks, dentro do seu package *steps* crie uma classe chamada Hooks, dentro dela crie um metodo.
Conforme ex abaixo:


```
public class Hooks extends CITFrameworkRestAssured{
@After
public void after(Scenario scenario){
AfterScenarioStartReport(scenario);
}
}
```
   
 
