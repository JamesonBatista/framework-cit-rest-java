package jsonvalidation;


import com.bradesco.core.exception.BradescoAssertionException;
import com.bradesco.core.exception.BradescoRuntimeException;
import com.bradesco.core.report.BradescoReporter;
import com.bradesco.core.sdk.enums.ReportStatus;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.cit.framework.CITRestAssured.*;

public class validationResponse {
    static String bodyValidation;
    static Boolean containsGet = false;
    static Boolean bodyStart = false;
    Boolean mapping = false;

    public validationResponse Body() {
        bodyStart = true;
        return this;
    }

    static String[] ReplaceJson() {
        bodyValidation = ExternalContainsJSON.extract().response().asString();
        String retorno = new Gson().toJson(bodyValidation);
        String[] stringFormat;
        String repla = retorno.replaceAll("[\\\\(\\\\)\\\\[\\\\]\\\\{\\\\}]", "");
        String rep = repla.replaceAll(":", "");
        String asp = rep.replaceAll("\"", "  ");
        String replac = asp.replaceAll(",", "  ");
        String replacp = replac.replaceAll("=", "  ");

        stringFormat = replacp.split("  ");
        return stringFormat;
    }

    public validationResponse contains(String... keys) throws BradescoAssertionException {
        if (!bodyStart) {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        } else {
            containsGet = true;
            for (String list : keys) {
                forValidation(list);
            }
        }
        return this;
    }


    public <T extends Object> validationResponse get(String key) throws BradescoAssertionException {
        Boolean verify = false;
        containsGet = true;
        int y = 0;
        if (containsGet) {
            for (int i = 0; i < ReplaceJson().length; i++) {
                if (ReplaceJson()[i].trim().equalsIgnoreCase(key.trim())) {
                    verify = true;
                    innerLoop:
                    for (int x = i; x < ReplaceJson().length; x++) {
                        y = x;
                        String delete = StringUtils.deleteWhitespace(ReplaceJson()[x]);
                        if (!delete.trim().equalsIgnoreCase(key) && !delete.trim().isEmpty()) {
                            StringGlobal = ReplaceJson()[x].trim();
                            if (ReplaceJson()[y].trim().startsWith("[") || ReplaceJson()[y].trim().startsWith("{")) {
                                throw new BradescoAssertionException("\n\nO método get() não pode ser usado para extrair valor de um Array ou Objetos, apenas campos. \n" +
                                        "ex: data:[]    [não permitido]\n" +
                                        "ou Objeto,\n" +
                                        "ex: data:{}    [não permitido]\n" +
                                        "apenas de campos.\n" +
                                        "ex: name: CI&T ou id: 7 ou valor: 10000, etc." +
                                        "\n\n");
                            } else {
                                System.out.println("\nValor atribuído a StringGlobal: " + StringGlobal + "\n");
                            }
                            break;
                        }
                    }
                }
            }
        } else {
            throw new BradescoAssertionException("\n\nO método get() só pode ser usado depois do contains \nEx: Body().contains('id').get('id')");

        }
        if (!verify) {
            throw new BradescoAssertionException("\n\nO valor 《《 " + key + " 》》 não foi encontrado no seu JSON");
        }
        if (ReplaceJson()[y].trim().startsWith("[") || ReplaceJson()[y].trim().startsWith("{")) {

        } else {
            System.out.println("Caso precise fazer um Assert, use da seguinte forma:  Assert.assertThat(StringGlobal, Matchers.is('CI&T'))");
        }

        return this;
    }

    public <T extends Object> validationResponse get(String key, T equals) throws BradescoAssertionException {
        Boolean verify = false;
        containsGet = true;
        int y = 0;
        if (containsGet) {
            for (int i = 0; i < ReplaceJson().length; i++) {
                if (ReplaceJson()[i].trim().equalsIgnoreCase(key.trim())) {
                    verify = true;
                    innerLoop:
                    for (int x = i; x < ReplaceJson().length; x++) {
                        String delete = StringUtils.deleteWhitespace(ReplaceJson()[x]);
                        if (!delete.trim().equalsIgnoreCase(key) && !delete.trim().isEmpty()) {
                            StringGlobal = ReplaceJson()[x].trim();
                            if (StringGlobal.getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                    StringGlobal.getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                BigDecimal bigDecimal = new BigDecimal(StringGlobal.toString());
                                Assert.assertThat(bigDecimal.doubleValue(), Matchers.is(equals));
                            } else if (!StringGlobal.equals(equals)) {
                                throw new BradescoAssertionException("\n\nValor informado: [ " + equals + " ]\nValor encontrado: " + StringGlobal);
                            }
                            System.out.println("Valor atribuído a StringGlobal: " + StringGlobal);
                            if (ReplaceJson()[y].trim().startsWith("[") || ReplaceJson()[y].trim().startsWith("{")) {
                                throw new BradescoAssertionException("\n\nO método get() não pode ser usado para extrair valor de um Array ou Objetos, apenas campos. \n" +
                                        "ex: data:[]    [não permitido]\n" +
                                        "ou Objeto,\n" +
                                        "ex: data:{}    [não permitido]\n" +
                                        "apenas de campos.\n" +
                                        "ex: name: CI&T ou id: 7 ou valor: 10000, etc." +
                                        "\n\n");
                            } else {
                                System.out.println("\nValor atribuído a StringGlobal: " + StringGlobal + "\n");
                            }
                            break;
                        }
                    }
                }
            }
        } else {
            throw new BradescoAssertionException("\n\nO método get() só pode ser usado depois do contains \nEx: Body().contains('id').get('id')");

        }
        if (!verify) {
            throw new BradescoAssertionException("\n\nO valor 《《 " + key + " 》》 não foi encontrado no seu JSON");
        }
        if (ReplaceJson()[y].trim().startsWith("[") || ReplaceJson()[y].trim().startsWith("{")) {

        } else {
            System.out.println("Caso precise fazer um Assert, use da seguinte forma:  Assert.assertThat(StringGlobal, Matchers.is('CI&T'))");
        }
        return this;
    }

    public <T extends Object> validationResponse get(String key, T equals, T equalsTo) throws BradescoAssertionException {
        Boolean verify = false;
        containsGet = true;
        int y = 0;
        if (containsGet) {
            for (int i = 0; i < ReplaceJson().length; i++) {
                if (ReplaceJson()[i].trim().equalsIgnoreCase(key.trim())) {
                    verify = true;
                    innerLoop:
                    for (int x = i; x < ReplaceJson().length; x++) {
                        String delete = StringUtils.deleteWhitespace(ReplaceJson()[x]);
                        if (!delete.trim().equalsIgnoreCase(key) && !delete.trim().isEmpty()) {
                            StringGlobal = ReplaceJson()[x].trim();
                            if (!StringGlobal.equals(equals)) {
                                if (!StringGlobal.equals(equalsTo))
                                    throw new BradescoAssertionException("\n\nValor informado: [ " + equals + " ]  [ " + equalsTo + " ] \nValor encontrado: " + StringGlobal);
                            }
                            System.out.println("Valor atribuído a StringGlobal: " + StringGlobal);
                            if (ReplaceJson()[y].trim().startsWith("[") || ReplaceJson()[y].trim().startsWith("{")) {
                                throw new BradescoAssertionException("\n\nO método get() não pode ser usado para extrair valor de um Array ou Objetos, apenas campos. \n" +
                                        "ex: data:[]    [não permitido]\n" +
                                        "ou Objeto,\n" +
                                        "ex: data:{}    [não permitido]\n" +
                                        "apenas de campos.\n" +
                                        "ex: name: CI&T ou id: 7 ou valor: 10000, etc." +
                                        "\n\n");
                            } else {
                                System.out.println("\nValor atribuído a StringGlobal: " + StringGlobal + "\n");
                            }
                            break;
                        }
                    }
                }
            }
        } else {
            throw new BradescoAssertionException("\n\nO método get() só pode ser usado depois do contains \nEx: Body().contains('id').get('id')");

        }
        if (!verify) {
            throw new BradescoAssertionException("\n\nO valor 《《 " + key + " 》》 não foi encontrado no seu JSON");
        }
        if (ReplaceJson()[y].trim().startsWith("[") || ReplaceJson()[y].trim().startsWith("{")) {

        } else {
            System.out.println("Caso precise fazer um Assert, use da seguinte forma:  Assert.assertThat(StringGlobal, Matchers.is('CI&T'))");
        }
        return this;
    }

    public <T extends Object> validationResponse get(String key, String equals, String equalsTo, String toEquals) throws BradescoAssertionException {
        Boolean verify = false;
        containsGet = true;
        int y = 0;
        if (containsGet) {
            for (int i = 0; i < ReplaceJson().length; i++) {
                if (ReplaceJson()[i].trim().equalsIgnoreCase(key.trim())) {
                    verify = true;
                    innerLoop:
                    for (int x = i; x < ReplaceJson().length; x++) {
                        String delete = StringUtils.deleteWhitespace(ReplaceJson()[x]);
                        if (!delete.trim().equalsIgnoreCase(key) && !delete.trim().isEmpty()) {
                            StringGlobal = ReplaceJson()[x].trim();
                            if (!StringGlobal.equals(equals)) {
                                if (!StringGlobal.equals(equalsTo)) {
                                    if (!StringGlobal.equals(toEquals))
                                        throw new BradescoAssertionException("\n\nValor informado: [ " + equals + " ]  [ " + equalsTo + " ]  [ " + toEquals + " ]  \nValor encontrado: " + StringGlobal);
                                }
                            }
                            System.out.println("Valor atribuído a StringGlobal: " + StringGlobal);
                            if (ReplaceJson()[y].trim().startsWith("[") || ReplaceJson()[y].trim().startsWith("{")) {
                                throw new BradescoAssertionException("\n\nO método get() não pode ser usado para extrair valor de um Array ou Objetos, apenas campos. \n" +
                                        "ex: data:[]    [não permitido]\n" +
                                        "ou Objeto,\n" +
                                        "ex: data:{}    [não permitido]\n" +
                                        "apenas de campos.\n" +
                                        "ex: name: CI&T ou id: 7 ou valor: 10000, etc." +
                                        "\n\n");
                            } else {
                                System.out.println("\nValor atribuído a StringGlobal: " + StringGlobal + "\n");
                            }
                            break;
                        }
                    }
                }
            }
        } else {
            throw new BradescoAssertionException("\n\nO método get() só pode ser usado depois do contains \nEx: Body().contains('id').get('id')");
        }
        if (!verify) {
            throw new BradescoAssertionException("\n\nO valor 《《 " + key + " 》》 não foi encontrado no seu JSON");
        }
        if (ReplaceJson()[y].trim().startsWith("[") || ReplaceJson()[y].trim().startsWith("{")) {

        } else {
            System.out.println("Caso precise fazer um Assert, use da seguinte forma:  Assert.assertThat(StringGlobal, Matchers.is('CI&T'))\n\nou Body().get('name', 'CI&T')");
        }
        return this;
    }

    JSONArray jsonArray;
    JSONObject jsonObjectValidation = null;

    // mapping achar valor do campo
    public <T extends Object> validationResponse mapping(String key) throws BradescoAssertionException {
        ArrayList<Object> arrayValue = null;
        arrayValue = new ArrayList<>();

        if (bodyStart) {
            if (response.asString().startsWith("[")) {
                System.out.println(jsonArray);
                for (Object list : jsonArray) {
                    jsonObjectValidation = new JSONObject(list.toString());
                }
            } else {
                jsonObjectValidation = new JSONObject(response.asString());
            }
            String[] arrayList = key.split(" > ");
            for (String array : arrayList) {
                array = array.trim();

                boolean exist = jsonObjectValidation.has(array);
                String value = arrayList[arrayList.length - 1];

                if (exist) {
                    if (jsonObjectValidation.get(array) instanceof JSONObject) {
                        jsonObjectValidation = jsonObjectValidation.getJSONObject(array);
                        StringGlobal = jsonObjectValidation;

                    } else if (jsonObjectValidation.get(array) instanceof JSONArray) {
                        jsonArray = jsonObjectValidation.getJSONArray(array);

                        for (Object l : jsonArray) {

                            if (!l.getClass().getSimpleName().equalsIgnoreCase("String")) {
                                jsonObjectValidation = new JSONObject(l.toString());
                                StringGlobal = jsonObjectValidation;

                            } else {
                                StringGlobal = l.toString();
                            }

                            if (jsonObjectValidation.has(value) && jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("String")) {
                                arrayValue.add(jsonObjectValidation.get(value));
                            }
                        }
                    } else {
                        StringGlobal = jsonObjectValidation.get(value);
                    }
                } else {
                    throw new BradescoAssertionException("\n\nO path  [ " + array + " ]   não existe no seu JSON, ou o caminho está errado.\n\n" + jsonObjectValidation);
                }
            }
        } else {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        if (!arrayValue.isEmpty())
            StringGlobal = arrayValue;

        mapping = true;

        return this;
    }

    // validar 1 valor
    public <T extends Object> validationResponse mapping(String key, T equals) throws BradescoAssertionException {
        if (bodyStart) {
            if (response.asString().startsWith("[")) {
                System.out.println(jsonArray);
                for (Object list : jsonArray) {
                    jsonObjectValidation = new JSONObject(list.toString());
                }
            } else {
                jsonObjectValidation = new JSONObject(response.asString());
            }
            String[] arrayList = key.split(" > ");
            for (String array : arrayList) {
                array = array.trim();
                boolean exist = jsonObjectValidation.has(array);
                String value = arrayList[arrayList.length - 1];

                if (exist) {
                    if (jsonObjectValidation.get(array) instanceof JSONObject) {
                        jsonObjectValidation = jsonObjectValidation.getJSONObject(array);

                        if (jsonObjectValidation.has(value)) {
                            if (jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                    jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(value).toString());
                                Assert.assertThat(bigDecimal.doubleValue(), Matchers.is(equals));
                            } else {
                                if (!jsonObjectValidation.has(value))
                                    Assert.assertThat(jsonObjectValidation.get(value), Matchers.is(equals));
                            }

                        }
                    } else if (jsonObjectValidation.get(array) instanceof JSONArray) {
                        jsonArray = jsonObjectValidation.getJSONArray(array);
                        boolean stringVerify = false;
                        for (Object l : jsonArray) {

                            if (!l.getClass().getSimpleName().equalsIgnoreCase("String")) {
                                jsonObjectValidation = new JSONObject(l.toString());
                                StringGlobal = jsonObjectValidation;

                            } else {

                                StringGlobal = l.toString();
                                stringVerify = true;
                            }

                            if (jsonObjectValidation.has(value)) {
                                if (jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                        jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                    BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(value).toString());
                                    Assert.assertThat(bigDecimal.doubleValue(), Matchers.is(equals));
                                } else {
                                    if (stringVerify) {
                                        Assert.assertEquals(equals.toString(), l.toString());
                                    } else {
                                        Assert.assertEquals(jsonObjectValidation.get(value).toString(), equals.toString());
                                    }
                                }
                            }
                        }
                    } else {

                        if (jsonObjectValidation.has(value))
                            if (jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                    jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(value).toString());
                                Assert.assertThat(bigDecimal.doubleValue(), Matchers.is(equals));
                            } else {
                                Assert.assertThat(jsonObjectValidation.get(value), Matchers.is(equals));
                            }
                        else {
                            throw new BradescoAssertionException("\n\nO path  [ " + array + " ]  não existe no seu JSON, ou o caminho está errado.");
                        }
                    }
                } else {
                    throw new BradescoAssertionException("\n\nO path  [ " + array + " ]   não existe no seu JSON, ou o caminho está errado.");
                }
            }
        } else {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        mapping = true;


        return this;
    }


    // validar 2 valores
    public <T extends Object> validationResponse mapping(String key, T equals, T equalsTo) throws BradescoAssertionException {
        ArrayList<Object> arrayValue = null;
        arrayValue = new ArrayList<>();

        if (bodyStart) {
            if (response.asString().startsWith("[")) {
                System.out.println(jsonArray);
                for (Object list : jsonArray) {
                    jsonObjectValidation = new JSONObject(list.toString());
                }
            } else {
                jsonObjectValidation = new JSONObject(response.asString());
            }
            String[] arrayList = key.split(" > ");
            for (String array : arrayList) {
                array = array.trim();
                String value = arrayList[arrayList.length - 1];

                boolean exist = jsonObjectValidation.has(array);
                if (exist) {
                    if (jsonObjectValidation.get(array) instanceof JSONObject) {
                        jsonObjectValidation = jsonObjectValidation.getJSONObject(array);

                        if (jsonObjectValidation.has(value)) {
                            if (jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                    jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(array).toString());
                                BigDecimal bigEquals = new BigDecimal(equals.toString());
                                BigDecimal bigEqualsTo = new BigDecimal(equalsTo.toString());
                                if (bigDecimal.doubleValue() != bigEquals.doubleValue()) {
                                    if (bigDecimal.doubleValue() != bigEqualsTo.doubleValue())
                                        throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [ " + bigEquals.doubleValue() + " ] [ " + bigEqualsTo.doubleValue() + " ]\nValor encontrado no JSON:  " + jsonObjectValidation);
                                }
                            } else {
                                if (jsonObjectValidation.has(value))
                                    AssertJsonNO(value, equals, equalsTo);
                            }
                        }

                    } else if (jsonObjectValidation.get(array) instanceof JSONArray) {
                        jsonArray = jsonObjectValidation.getJSONArray(array);

                        for (Object l : jsonArray) {
                            jsonObjectValidation = new JSONObject(l.toString());

                            StringGlobal = jsonObjectValidation;
                            if (jsonObjectValidation.has(value) && jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("String")) {
                                if (jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                        jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                    BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(value).toString());
                                    BigDecimal bigEquals = new BigDecimal(equals.toString());
                                    BigDecimal bigEqualsTo = new BigDecimal(equalsTo.toString());
                                    if (bigDecimal.doubleValue() != bigEquals.doubleValue()) {
                                        if (bigDecimal.doubleValue() != bigEqualsTo.doubleValue())
                                            throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [ " + bigEquals.doubleValue() + " ] [ " + bigEqualsTo.doubleValue() + " ]\nValor encontrado no JSON:  " + jsonObjectValidation);
                                    }
                                } else {
                                    if (jsonObjectValidation.has(value))
                                        AssertJsonNO(value, equals, equalsTo);
                                }

                            }
                        }
                    } else {
                        AssertJsonNO(value, equals, equalsTo);
                    }
                } else {
                    throw new BradescoAssertionException("\n\nO path  [ " + array + " ]   não existe no seu JSON, ou o caminho está errado.\n\n" + jsonObjectValidation);
                }
            }
        } else {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        mapping = true;


        if (!arrayValue.isEmpty())
            StringGlobal = arrayValue;

        return this;
    }


    //validar 3 valores
    public <T extends Object> validationResponse mapping(String key, T equals, T equalsTo, T equalsTos) throws BradescoAssertionException {
        ArrayList<Object> arrayValue = null;
        arrayValue = new ArrayList<>();

        if (bodyStart) {
            if (response.asString().startsWith("[")) {
                System.out.println(jsonArray);
                for (Object list : jsonArray) {
                    jsonObjectValidation = new JSONObject(list.toString());
                }
            } else {
                jsonObjectValidation = new JSONObject(response.asString());
            }
            String[] arrayList = key.split(" > ");
            for (String array : arrayList) {
                array = array.trim();
                String value = arrayList[arrayList.length - 1];

                boolean exist = jsonObjectValidation.has(array);
                if (exist) {
                    if (jsonObjectValidation.get(array) instanceof JSONObject) {
                        jsonObjectValidation = jsonObjectValidation.getJSONObject(array);
                        StringGlobal = jsonObjectValidation;
                        if (jsonObjectValidation.has(value))
                            AssertJsonThree(value, equals, equalsTo, equalsTos);

                        if (jsonObjectValidation.has(value)) {
                            if (jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                    jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(value).toString());
                                BigDecimal equals1 = new BigDecimal(equals.toString());
                                BigDecimal equals2 = new BigDecimal(equalsTo.toString());
                                BigDecimal equals3 = new BigDecimal(equalsTos.toString());
                                if (bigDecimal.doubleValue() != equals1.doubleValue()) {
                                    if (bigDecimal.doubleValue() != equals2.doubleValue()) {
                                        if (bigDecimal.doubleValue() != equals3.doubleValue())
                                            throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [" + equals1.doubleValue() + " ] [ " + equals2.doubleValue() + " ]  [" + equals3.doubleValue() + " ]\nValor encontrado no JSON:  " + jsonObjectValidation);
                                    }
                                }
                            } else {
                                if (jsonObjectValidation.has(value))
                                    AssertJsonThree(value, equals, equalsTo, equalsTos);
                            }
                        }

                    } else if (jsonObjectValidation.get(array) instanceof JSONArray) {
                        jsonArray = jsonObjectValidation.getJSONArray(array);

                        for (Object l : jsonArray) {
                            jsonObjectValidation = new JSONObject(l.toString());

                            StringGlobal = jsonObjectValidation;
                            if (jsonObjectValidation.has(value) && jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("String")) {
                                if (jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                        jsonObjectValidation.get(value).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                    BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(value).toString());
                                    BigDecimal equals1 = new BigDecimal(equals.toString());
                                    BigDecimal equals2 = new BigDecimal(equalsTo.toString());
                                    BigDecimal equals3 = new BigDecimal(equalsTos.toString());
                                    if (bigDecimal.doubleValue() != equals1.doubleValue()) {
                                        if (bigDecimal.doubleValue() != equals2.doubleValue()) {
                                            if (bigDecimal.doubleValue() != equals3.doubleValue())
                                                throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [" + equals1.doubleValue() + " ] [ " + equals2.doubleValue() + " ]  [" + equals3.doubleValue() + " ]\nValor encontrado no JSON:  " + jsonObjectValidation);
                                        }
                                    }
                                } else {
                                    if (jsonObjectValidation.has(value))
                                        AssertJsonThree(value, equals, equalsTo, equalsTos);

                                }
                            }
                        }
                    } else {
                        AssertJsonThree(value, equals, equalsTo, equalsTos);
                    }
                } else {
                    throw new BradescoAssertionException("\n\nO path  [ " + array + " ]   não existe no seu JSON, ou o caminho está errado.\n\n" + jsonObjectValidation);
                }
            }
        } else {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        mapping = true;


        if (!arrayValue.isEmpty())
            StringGlobal = arrayValue;

        return this;
    }


    public validationResponse report(ReportStatus status, String text) {
        if (bodyStart) {
            BradescoReporter.report(status, text);
        } else {
            throw new BradescoRuntimeException("O report só pode ser chamado após o Body().report()");
        }
        return this;
    }

    boolean validation = false;

    <T extends Object> void AssertJsonNO(String path, T equals, T equalsTo) throws BradescoAssertionException {
        if (jsonObjectValidation.get(path).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                jsonObjectValidation.get(path).getClass().getSimpleName().equalsIgnoreCase("Float")) {
            BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(path).toString());
            BigDecimal bigEquals = new BigDecimal(equals.toString());
            BigDecimal bigEqualsTo = new BigDecimal(equalsTo.toString());
            if (bigDecimal.doubleValue() != bigEquals.doubleValue()) {
                if (bigDecimal.doubleValue() != bigEqualsTo.doubleValue()) {
                    throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [ " + bigEquals.doubleValue() + " ] [ " + bigEqualsTo.doubleValue() + " ]\nValor encontrado no JSON:  " + jsonObjectValidation);

                } else {
                    validation = true;
                }
//                    throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [ " + bigEquals.doubleValue() + " ] [ " + bigEqualsTo.doubleValue() + " ]\nValor encontrado no JSON:  " + jsonObjectValidation);
            }
        } else {
            if (jsonObjectValidation.has(path))
                if (!jsonObjectValidation.get(path).equals(equals)) {
                    if (!jsonObjectValidation.get(path).equals(equalsTo)) {
                        throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [ " + equals + " ] [ " + equalsTo + " ]\nValor encontrado no JSON:  " + jsonObjectValidation);
                    } else {
                        validation = true;
                    }
                }
        }

    }

    <T extends Object> void AssertJsonThree(String array, T equals, T equalsTo, T equalsTos) throws BradescoAssertionException {
        boolean value = false;
        if (jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("Float")) {
            BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(array).toString());
            BigDecimal equals1 = new BigDecimal(equals.toString());
            BigDecimal equals2 = new BigDecimal(equalsTo.toString());
            BigDecimal equals3 = new BigDecimal(equalsTos.toString());
            if (bigDecimal.doubleValue() != equals1.doubleValue()) {
                if (bigDecimal.doubleValue() != equals2.doubleValue()) {
                    if (bigDecimal.doubleValue() != equals3.doubleValue())
                        throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [" + equals1.doubleValue() + " ] [ " + equals2.doubleValue() + " ]  [" + equals3.doubleValue() + " ]\nValor encontrado no JSON:  " + jsonObjectValidation);

                }
            }
        } else {
            if (jsonObjectValidation.has(array))
                if (!jsonObjectValidation.get(array).equals(equals)) {
                    if (!jsonObjectValidation.get(array).equals(equalsTo)) {
                        if (!jsonObjectValidation.get(array).equals(equalsTos))
                            throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [ " + equals + " ] [ " + equalsTos + " ] [ " + equalsTo + " ]\nValor encontrado no JSON:  " + jsonObjectValidation.get(array) + "\n\nJSON error: " +
                                    "" + jsonObjectValidation);
                    }
                }
        }

    }

    <T extends Object> void AssertJsonFour(String path, T equals, T equalsTos, T equalsTo, T equalsToS) throws BradescoAssertionException {
        if (jsonObjectValidation.get(path).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                jsonObjectValidation.get(path).getClass().getSimpleName().equalsIgnoreCase("Float")) {
            BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(path).toString());
            BigDecimal equals1 = new BigDecimal(equals.toString());
            BigDecimal equals2 = new BigDecimal(equalsTos.toString());
            BigDecimal equals3 = new BigDecimal(equalsTo.toString());
            BigDecimal equals4 = new BigDecimal(equalsToS.toString());
            if (bigDecimal.doubleValue() != equals1.doubleValue() &&
                    bigDecimal.doubleValue() != equals2.doubleValue() &&
                    bigDecimal.doubleValue() != equals3.doubleValue() &&
                    bigDecimal.doubleValue() != equals4.doubleValue()) {
                throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [ " + equals + " ] [ " + equalsTos + " ] [ " + equalsTo + " ] [ " + equalsToS + " ]" +
                        "\nValor encontrado no JSON:  " + jsonObjectValidation.get(path) + "\n\nJSON error: " +
                        "" + jsonObjectValidation);
            }
        } else {
            if (!jsonObjectValidation.has(path))
                if (!jsonObjectValidation.get(path).equals(equals)) {
                    if (!jsonObjectValidation.get(path).equals(equalsTo)) {
                        if (!jsonObjectValidation.get(path).equals(equalsTos)) {
                            if (!jsonObjectValidation.get(path).equals(equalsToS))
                                throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [ " + equals + " ] [ " + equalsTos + " ] [ " + equalsTo + " ] [ " + equalsToS + " ]" +
                                        "\nValor encontrado no JSON:  " + jsonObjectValidation.get(path) + "\n\nJSON error: " +
                                        "" + jsonObjectValidation);
                        }
                    }
                }
        }
    }

    validationResponse forValidation(String key) throws BradescoAssertionException {

        Boolean verify = false;
        for (String s : ReplaceJson()) {
            if (s.trim().equalsIgnoreCase(key)) {
                verify = true;
                break;
            }
        }
        if (!verify) {
            throw new BradescoAssertionException("\n\nO valor 《《 " + key + " 》》 não foi encontrado no seu JSON");
        }
        return this;
    }


}