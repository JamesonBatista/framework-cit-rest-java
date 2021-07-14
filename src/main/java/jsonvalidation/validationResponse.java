package jsonvalidation;


import com.bradesco.core.exception.BradescoAssertionException;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.cit.framework.CITRestAssured.*;

public class validationResponse {
    static String bodyValidation;
    static Boolean containsGet = false;
    List<T> list = null;
    Boolean bodyStart = false;
    Boolean rootStart = false;
    Boolean andContinue = false;
    Boolean andContinueThree = false;
    Boolean newObjectInit = false;

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

    Boolean rootInit = false;

    public validationResponse root(String... root) throws BradescoAssertionException {
        rootInit = true;
        if (!bodyStart) {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        } else {
            rootStart = true;
            if (root.length == 1) {
                for (String rootPath : root) {
                    list = ExternalContainsJSON.extract().jsonPath().getList(rootPath);
                }
            } else {
                list = ExternalContainsJSON.extract().jsonPath().getList("$");
            }
        }
        return this;
    }

    public validationResponse root(String KeyObject, String path) throws BradescoAssertionException {

        if (!bodyStart) {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        } else {
            JSONObject json = new JSONObject(response.asString());
            Object value = json.getJSONObject(KeyObject).get(path);
            andContinue = true;
            return this;
        }
    }

    public <T extends Object> validationResponse root(String KeyObject, String path, T equals) throws BradescoAssertionException {

        BigDecimal bigDecimal = null;
        if (!bodyStart) {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        } else {
            JSONObject json = new JSONObject(response.asString());
            json = json.getJSONObject(KeyObject);
            if (json.get(path).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                    json.get(path).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                String valueBig = json.get(path).toString();
                bigDecimal = new BigDecimal(valueBig);
                bigDecimal.doubleValue();
            }

            Assert.assertThat(bigDecimal == null ? json.get(path) : bigDecimal.doubleValue(), Matchers.is(equals));
            StringGlobal = json.get(path);
            andContinueThree = true;
            return this;
        }
    }

    public <T extends Object> validationResponse newArray(String array, String... path) throws BradescoAssertionException {
        if (rootInit) {
            BigDecimal bigDecimal = null;
            JSONObject json;
            for (Object l : list) {
                json = new JSONObject((Map<String, ?>) l);
                JSONArray jsonArray = json.getJSONArray(array);
                for (Object lista : jsonArray) {
                    json = new JSONObject(lista.toString());
                    if (path.length == 1) {
                        for (String paths : path) {
                            if (json.get(paths).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                    json.get(paths).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                String valueBig = json.get(paths).toString();
                                bigDecimal = new BigDecimal(valueBig);
                                bigDecimal.doubleValue();
                            }
                            StringGlobal = bigDecimal == null ? json.get(paths) : bigDecimal.doubleValue();
                        }
                    } else if (path.length > 1) {
                        throw new BradescoAssertionException("\n\nSeu método pode apenas pegar o valor de um path Ex: newArray('data', 'id').\n" +
                                "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
                    }
                }
            }
        } else {
            throw new BradescoAssertionException("\n\ngetString só pode ser usado com o root usando apenas um parâmetro Ex: root('data').\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        return this;
    }

    public validationResponse object(String... path) throws BradescoAssertionException {
        newObjectInit = true;
        JSONObject json;
        Boolean value;
        if (!bodyStart) {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...");
        } else if (!rootStart) {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método object() seguido do com o root...\n" +
                    "Ex: Body().root('data').object('users', 'id', 'name')\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        } else {
            if (path.length > 0) {
                for (Object l : list) {
                    json = new JSONObject((Map<String, ?>) l);
                    for (String listArray : path) {
                        value = json.has(listArray);
                        if (!value) {
                            throw new BradescoAssertionException("\n\nO valor 《《 " + listArray + " 》》 não existe na sua lista de Objetos.\n" +
                                    "Object error: " + json);
                        }
                    }
                }
            } else {
                throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, o método object() precisa conter ao menos um campo do objeto a ser validado.\n" +
                        "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
            }
        }
        return this;
    }

    public validationResponse newObject(String rootPath, String... path) throws BradescoAssertionException {
        JSONObject json;
        Boolean value = null;
        if (newObjectInit) {
            for (Object l : list) {
                json = new JSONObject((Map<String, ?>) l);
                String valuers = rootPath;
                String[] arrayValuers = valuers.split(" ");
                for (String s : arrayValuers) {
                    json = json.getJSONObject(s);
                }
                for (String listObject : path) {
                    value = json.has(listObject);
                    if (!value) {
                        throw new BradescoAssertionException("\n\nO valor 《《 " + listObject + " 》》 não existe na sua lista de Objetos.\n" +
                                "Object error: " + json);
                    }
                }

            }
        } else {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, o método newObject() só pode ser usado após o método object().\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
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

    public <T extends Object> validationResponse get(String key, String equals) throws BradescoAssertionException {
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

    public <T extends Object> validationResponse get(String key, String equals, String equalsTo) throws BradescoAssertionException {
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
            System.out.println("Caso precise fazer um Assert, use da seguinte forma:  Assert.assertThat(StringGlobal, Matchers.is('CI&T'))");
        }
        return this;
    }

    public <T extends Object> validationResponse getString(String path, T equals) throws BradescoAssertionException {
        if (rootInit) {
            JSONObject json = null;
            BigDecimal bigDecimal = null;
            for (Object l : list) {
                json = new JSONObject((Map<String, ?>) l);
                if (json.get(path).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                        json.get(path).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                    String valueBig = json.get(path).toString();
                    bigDecimal = new BigDecimal(valueBig);
                    bigDecimal.doubleValue();
                }
            }
            Assert.assertThat(bigDecimal == null ? json.get(path) : bigDecimal.doubleValue(), Matchers.is(equals));
        } else {
            throw new BradescoAssertionException("\n\ngetString só pode ser usado com o root usando apenas um parâmetro Ex: root('data').\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        return this;
    }

    public validationResponse and(String KeyObject, String path) throws BradescoAssertionException {
        if (andContinue) {
            JSONObject json = new JSONObject(response.asString());
            Object value = json.getJSONObject(KeyObject).get(path);
        } else {
            throw new BradescoAssertionException("\n\n O *** and ***  só pode ser usado após o root(String KeyObject, String path), que é o root com 2 parâmetros \n" +
                    "olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        return this;
    }

    public <T extends Object> validationResponse and(String KeyObject, String path, T equals) throws BradescoAssertionException {
        BigDecimal bigDecimal = null;

        if (andContinueThree) {
            JSONObject json = new JSONObject(response.asString());
            json = json.getJSONObject(KeyObject);
            if (json.get(path).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                    json.get(path).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                String valueBig = json.get(path).toString();
                bigDecimal = new BigDecimal(valueBig);
                bigDecimal.doubleValue();
            }

            Assert.assertThat(bigDecimal == null ? json.get(path) : bigDecimal.doubleValue(), Matchers.is(equals));
        } else {
            throw new BradescoAssertionException("\n\n O *** and ***  só pode ser usado após o root(String KeyObject, String path, String equals), que é o root com 3 parâmetros \n" +
                    "olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        return this;
    }

    static Object jsonObject;
    Boolean BooleanobjectForArray = false;

    public validationResponse objectForArray(String KeyObject, String Array, String... path) throws BradescoAssertionException {
        BooleanobjectForArray = true;
        JSONObject json = new JSONObject(response.asString());
        JSONArray jsonArray = json.getJSONObject(KeyObject).getJSONArray(Array);
        JSONObject object = null;
        for (Object lista : jsonArray) {
            object = new JSONObject(lista.toString());
            jsonObject = object;
        }
        if (path.length == 1) {
            for (String lista : path) {
                if (object.get(lista).getClass().getSimpleName().equalsIgnoreCase("BigDecimal")) {
                    BigDecimal bigDecimal = new BigDecimal(object.get(lista).toString());
                    StringGlobal = bigDecimal.doubleValue();
                }
            }
        } else if (path.length > 1) {
            throw new BradescoAssertionException("\n\nSeu método pode apenas conter 3 campos KeyObject, Array e path.\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        return this;
    }

    Object newObjectForArray;

    public validationResponse newObjectForArray(String KeyObject, String Array, String... path) throws BradescoAssertionException {
        if (BooleanobjectForArray) {
            JSONObject json = new JSONObject(jsonObject.toString());
            JSONArray jsonArray = json.getJSONObject(KeyObject).getJSONArray(Array);

            JSONObject object = null;
            for (Object lista : jsonArray) {
                object = new JSONObject(lista.toString());
                newObjectForArray = object;
            }
            BigDecimal bigDecimal;
            if (path.length == 1) {
                for (String objectPath : path) {

                    if (object.get(objectPath).getClass().getSimpleName().equalsIgnoreCase("BigDecimal")) {
                        String valueBig = StringGlobal.toString();
                        bigDecimal = new BigDecimal(valueBig);
                        StringGlobal = bigDecimal.doubleValue();
                    } else {
                        StringGlobal = object.get(objectPath);
                    }
                }
            } else if (path.length > 1) {
                throw new BradescoAssertionException("\n\nSeu método pode apenas conter 3 campos KeyObject, Array e path.\n" +
                        "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
            }
        } else {
            throw new BradescoAssertionException("\n\nPara usar o newObjectForArray você precisa usar o objectForArray.\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }

        return this;
    }

    public validationResponse getObject(String KeyObject, String path, String... equals) throws BradescoAssertionException {
        JSONObject json = new JSONObject(list == null ? jsonObject.toString() : list.toString());
        json = json.getJSONObject(KeyObject);
        if (equals.length == 1) {
            for (String lista : equals) {
                if (json.get(path).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                        json.get(path).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                    BigDecimal bigDecimal = new BigDecimal(json.get(path).toString());
                    StringGlobal = bigDecimal.doubleValue();
                } else {
                    StringGlobal = json.get(path);
                }
                Assert.assertThat(StringGlobal, Matchers.is(lista));
            }
        } else if (equals.length > 1) {
            throw new BradescoAssertionException("\n\nSeu método pode apenas conter 3 campos KeyObject, path e equals.\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        return this;
    }

    JSONArray jsonArray;
    JSONObject jsonObjectValidation = null;


    public <T extends Object> validationResponse mapping(String key, T... equals) throws BradescoAssertionException {
        if (bodyStart) {
            if (response.asString().startsWith("[")) {
                jsonArray = new JSONArray(response.asString());

                for (Object list : jsonArray) {
                    jsonObjectValidation = new JSONObject(list.toString());
                }
            } else {
                jsonObjectValidation = new JSONObject(response.asString());
            }
            String[] arrayList = key.split(" > ");
            Iterator<String> iterator = Arrays.stream(arrayList).iterator();
            String nextKeys = new String();
            Object next;
            for (String array : arrayList) {
                array = array.trim();
                next = iterator.next();
                boolean exist = jsonObjectValidation.has(array);

                if (exist) {
                    if (jsonObjectValidation.get(array) instanceof JSONObject) {
                        jsonObjectValidation = jsonObjectValidation.getJSONObject(array);

                        for (T lista : equals) {
                            if (jsonObjectValidation.has(array)) {
                                if (jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                        jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                    BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(array).toString());
                                    Assert.assertThat(bigDecimal.doubleValue(), Matchers.is(lista));
                                } else {
                                    if (!jsonObjectValidation.has(array))
                                        Assert.assertThat(jsonObjectValidation.get(array), Matchers.is(lista));
                                }
                            }
                        }

                    } else if (jsonObjectValidation.get(array) instanceof JSONArray) {
                        jsonArray = jsonObjectValidation.getJSONArray(array);

                        for (Object l : jsonArray) {
                            jsonObjectValidation = new JSONObject(l.toString());
                            if (equals.length == 1) {
                                if (jsonObjectValidation.has(array)) {
                                    for (T lista : equals) {
                                        if (jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                                jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                            BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(array).toString());
                                            Assert.assertThat(bigDecimal.doubleValue(), Matchers.is(lista));
                                        } else {
                                            Assert.assertThat(jsonObjectValidation.get(array), Matchers.is(lista));
                                        }
                                    }
                                }

                            } else if (equals.length > 1) {
                                throw new BradescoAssertionException("\n\nO método apenas permite 2 valores, o path e o comparativo EQUALS.");
                            }
                        }
                    } else {
                        if (equals.length == 1) {

                            for (T lista : equals) {
                                if (jsonObjectValidation.has(array))
                                    if (jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                            jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                        BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(array).toString());
                                        Assert.assertThat(bigDecimal.doubleValue(), Matchers.is(lista));
                                    } else {
                                        Assert.assertThat(jsonObjectValidation.get(array), Matchers.is(lista));
                                    }
                                else {
                                    throw new BradescoAssertionException("\n\nO path  [ " + array + " ]  não existe no seu JSON, ou o caminho está errado.");
                                }
                            }
                        } else if (equals.length > 1) {
                            throw new BradescoAssertionException("\n\nO método apenas permite 2 valores, o path e o comparativo EQUALS.");
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
        return this;
    }

    public <T extends Object> validationResponse mapping(String key, String path, T equals) throws BradescoAssertionException {
        if (bodyStart) {
            if (response.asString().startsWith("[")) {
                jsonArray = new JSONArray(response.asString());

                for (Object list : jsonArray) {
                    jsonObjectValidation = new JSONObject(list.toString());
                }
            } else {
                jsonObjectValidation = new JSONObject(response.asString());
            }
            String[] arrayList = key.split(" > ");
            String nextKeys = new String();
            for (String array : arrayList) {
                array = array.trim();
                boolean exist = jsonObjectValidation.has(array);

                if (exist) {
                    if (jsonObjectValidation.get(array) instanceof JSONObject) {
                        jsonObjectValidation = jsonObjectValidation.getJSONObject(array);

                        if (jsonObjectValidation.has(array)) {
                            if (jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                    jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(array).toString());
                                Assert.assertThat(bigDecimal.doubleValue(), Matchers.is(equals));
                            } else {
                                if (!jsonObjectValidation.has(array))
                                    Assert.assertThat(jsonObjectValidation.get(array), Matchers.is(equals));
                            }
                        }

                    } else if (jsonObjectValidation.get(array) instanceof JSONArray) {
                        jsonArray = jsonObjectValidation.getJSONArray(array);

                        for (Object l : jsonArray) {
                            jsonObjectValidation = new JSONObject(l.toString());
                            if (jsonObjectValidation.has(array)) {
                                if (jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                        jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                    BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(array).toString());
                                    Assert.assertThat(bigDecimal.doubleValue(), Matchers.is(equals));
                                } else {
                                    Assert.assertThat(jsonObjectValidation.get(array), Matchers.is(equals));
                                }

                            } else {
                                if (jsonObjectValidation.has(path))
                                    if (jsonObjectValidation.get(path).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                            jsonObjectValidation.get(path).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                        BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(path).toString());
                                        Assert.assertThat(bigDecimal.doubleValue(), Matchers.is(equals));
                                    } else {
                                        Assert.assertThat(jsonObjectValidation.get(path), Matchers.is(equals));
                                    }
                            }
                        }
                    } else {

                        if (jsonObjectValidation.has(array))
                            if (jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                    jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(array).toString());
                                Assert.assertThat(bigDecimal.doubleValue(), Matchers.is(equals));
                            } else {
                                Assert.assertThat(jsonObjectValidation.get(array), Matchers.is(equals));
                            }
                        else {
                            throw new BradescoAssertionException("\n\nO path  " + array + "  não existe no seu JSON, ou o caminho está errado.");
                        }


                    }
                } else {
                    throw new BradescoAssertionException("\n\nO path  " + array + "  não existe no seu JSON, ou o caminho está errado.");
                }
            }

        } else {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        return this;
    }

    public <T extends Object> validationResponse mapping(String key, String path, T equals, T equalsTo) throws BradescoAssertionException {
        if (bodyStart) {
            if (response.asString().startsWith("[")) {
                jsonArray = new JSONArray(response.asString());

                for (Object list : jsonArray) {
                    jsonObjectValidation = new JSONObject(list.toString());
                }
            } else {
                jsonObjectValidation = new JSONObject(response.asString());
            }
            String[] arrayList = key.split(" > ");
            String nextKeys = new String();
            for (String array : arrayList) {
                array = array.trim();
                boolean exist = jsonObjectValidation.has(array);

                if (exist) {
                    if (jsonObjectValidation.get(array) instanceof JSONObject) {
                        jsonObjectValidation = jsonObjectValidation.getJSONObject(array);

                        if (jsonObjectValidation.has(array) && jsonObjectValidation.has(path)) {
                            AssertJson(array, equals, equalsTo);
                        } else if (jsonObjectValidation.has(path)) {
                            AssertJson(path, equals, equalsTo);
                        }

                    } else if (jsonObjectValidation.get(array) instanceof JSONArray) {
                        jsonArray = jsonObjectValidation.getJSONArray(array);

                        for (Object l : jsonArray) {
                            jsonObjectValidation = new JSONObject(l.toString());
                            if (jsonObjectValidation.has(array)) {
                                if (jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                        jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                    BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(array).toString());
                                    BigDecimal bigEquals = new BigDecimal(equals.toString());
                                    BigDecimal bigEqualsTo = new BigDecimal(equalsTo.toString());
                                    if (bigDecimal.doubleValue() != bigEquals.doubleValue()) {
                                        if (bigDecimal.doubleValue() != bigEqualsTo.doubleValue())
                                            throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [ " + bigEquals.doubleValue() + " ] [ " + bigEqualsTo.doubleValue() + " ]\nValor encontrado no JSON:  " + jsonObjectValidation);
                                    }
                                } else {
                                    if (jsonObjectValidation.has(path))
                                        AssertJson(path, equals, equalsTo);
                                }
                            } else {
                                if (jsonObjectValidation.has(path))
                                    AssertJson(path, equals, equalsTo);
                            }
                        }
                    } else {
                        if (jsonObjectValidation.has(array))
                            AssertJson(path, equals, equalsTo);
                        else {
                            throw new BradescoAssertionException("\n\nO path  [ " + array + " ]  não existe no seu JSON, ou o caminho está errado.\n\n" + jsonObjectValidation);
                        }
                    }
                } else {
                    throw new BradescoAssertionException("\n\nO path  [ " + array + " ]  não existe no seu JSON, ou o caminho está errado.\n\n" + jsonObjectValidation);
                }
            }

        } else {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        return this;
    }

    public <T extends Object> validationResponse mapping(String key, String path, T equals, T equalsTo, T equalsTos) throws BradescoAssertionException {
        if (bodyStart) {
            if (response.asString().startsWith("[")) {
                jsonArray = new JSONArray(response.asString());

                for (Object list : jsonArray) {
                    jsonObjectValidation = new JSONObject(list.toString());
                }
            } else {
                jsonObjectValidation = new JSONObject(response.asString());
            }
            String[] arrayList = key.split(" > ");
            String nextKeys = new String();
            for (String array : arrayList) {
                array = array.trim();
                boolean exist = jsonObjectValidation.has(array);

                if (exist) {
                    if (jsonObjectValidation.get(array) instanceof JSONObject) {
                        jsonObjectValidation = jsonObjectValidation.getJSONObject(array);

                        if (jsonObjectValidation.has(array)) {
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
                                if (!jsonObjectValidation.has(array))
                                    if (!jsonObjectValidation.get(path).equals(equals.toString())) {
                                        if (!jsonObjectValidation.get(path).equals(equalsTo)) {
                                            if (!jsonObjectValidation.get(path).equals(equalsTos))
                                                throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [ " + equals + " ] [ " + equalsTos + " ] [ " + equalsTo + " ]\nValor encontrado no JSON:  " + jsonObjectValidation.get(path) + "\n\nJSON error: " +
                                                        "" + jsonObjectValidation);
                                        }
                                    }
                            }
                        }

                    } else if (jsonObjectValidation.get(array) instanceof JSONArray) {
                        jsonArray = jsonObjectValidation.getJSONArray(array);

                        for (Object l : jsonArray) {
                            jsonObjectValidation = new JSONObject(l.toString());
                            if (jsonObjectValidation.has(array)) {
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
                                    if (jsonObjectValidation.has(path))
                                        if (!jsonObjectValidation.get(path).equals(equals.toString())) {
                                            if (!jsonObjectValidation.get(path).equals(equalsTo)) {
                                                if (!jsonObjectValidation.get(path).equals(equalsTos))
                                                    throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [ " + equals + " ] [ " + equalsTos + " ] [ " + equalsTo + " ]\nValor encontrado no JSON:  " + jsonObjectValidation.get(path) + "\n\nJSON error: " +
                                                            "" + jsonObjectValidation);
                                            }
                                        }
                                }

                            } else {
                                if (jsonObjectValidation.has(path))
                                    AssertJsonThree(path, equals, equalsTo, equalsTos);
                            }
                        }
                    } else {
                        if (jsonObjectValidation.has(array))
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
                                if (jsonObjectValidation.has(path))
                                    if (!jsonObjectValidation.get(path).equals(equals.toString())) {
                                        nextKeys = equalsTo.toString();
                                        if (!jsonObjectValidation.get(path).equals(equalsTo.toString())) {
                                            nextKeys = equalsTos.toString();
                                            if (!jsonObjectValidation.get(path).equals(equalsTos.toString()))
                                                throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [ " + equals + " ] [ " + equalsTos + " ] [ " + nextKeys + " ]\nValor encontrado no JSON:  " + jsonObjectValidation.get(path) + "\n\nJSON error: " +
                                                        "" + jsonObjectValidation);
                                        }
                                    }
                            }
                        else {
                            throw new BradescoAssertionException("\n\nO path  [ " + array + " ]  não existe no seu JSON, ou o caminho está errado.");
                        }
                    }
                } else {
                    throw new BradescoAssertionException("\n\nO path  [ " + array + " ]  não existe no seu JSON, ou o caminho está errado.");
                }
            }

        } else {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        return this;
    }

    public <T extends Object> validationResponse mapping(String key, String path, T equals, T equalsTo, T equalsTos, T equalsToS) throws BradescoAssertionException {
        if (bodyStart) {
            if (response.asString().startsWith("[")) {
                jsonArray = new JSONArray(response.asString());

                for (Object list : jsonArray) {
                    jsonObjectValidation = new JSONObject(list.toString());
                }
            } else {
                jsonObjectValidation = new JSONObject(response.asString());
            }
            String[] arrayList = key.split(" > ");
            String nextKeys = new String();
            for (String array : arrayList) {
                array = array.trim();
                boolean exist = jsonObjectValidation.has(array);

                if (exist) {
                    if (jsonObjectValidation.get(array) instanceof JSONObject) {
                        jsonObjectValidation = jsonObjectValidation.getJSONObject(array);

                        if (jsonObjectValidation.has(array)) {
                            if (jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                    jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(array).toString());
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
                                if (!jsonObjectValidation.has(array))
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

                    } else if (jsonObjectValidation.get(array) instanceof JSONArray) {
                        jsonArray = jsonObjectValidation.getJSONArray(array);

                        for (Object l : jsonArray) {
                            jsonObjectValidation = new JSONObject(l.toString());
                            if (jsonObjectValidation.has(array)) {
                                if (jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                        jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                    BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(array).toString());
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
                                    if (!jsonObjectValidation.has(array))
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

                            } else {
                                if (jsonObjectValidation.has(path))
                                    AssertJsonFour(path, equals, equalsTos, equalsTo, equalsToS);
                            }
                        }
                    } else {

                        if (jsonObjectValidation.has(array))
                            if (jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                                    jsonObjectValidation.get(array).getClass().getSimpleName().equalsIgnoreCase("Float")) {
                                BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(array).toString());
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
                                if (!jsonObjectValidation.has(array))
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
                        else {
                            throw new BradescoAssertionException("\n\nO path  [ " + array + " ]  não existe no seu JSON, ou o caminho está errado.");
                        }
                    }
                } else {
                    throw new BradescoAssertionException("\n\nO path  [ " + array + " ]  não existe no seu JSON, ou o caminho está errado.");
                }
            }

        } else {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        return this;
    }

    boolean pathRootInit = false;
    JSONObject jsonPathRoot;
    JSONArray arrayPathRoot;
    Object jsonFinal;


    public <T extends Object> validationResponse pathRoot(String pathRoot) throws BradescoAssertionException {
        pathRootInit = true;
        jsonPathRoot = new JSONObject(response.asString());
        Iterator<?> keys;
        String nextKeys;
        boolean exists = jsonPathRoot.has(pathRoot);

        if (bodyStart) {
            if (!exists) {
                keys = jsonPathRoot.keys();
                while (keys.hasNext()) {
                    nextKeys = (String) keys.next();
                    try {
                        if (jsonPathRoot.get(nextKeys) instanceof JSONObject) {
                            if (!exists) {
                                jsonPathRoot = jsonPathRoot.getJSONObject(nextKeys);
                                pathRoot(pathRoot);
                            }
                        } else if (jsonPathRoot.get(nextKeys) instanceof JSONArray) {
                            arrayPathRoot = jsonPathRoot.getJSONArray(nextKeys);
                            for (int i = 0; i < arrayPathRoot.length(); i++) {
                                String jsonarrayString = arrayPathRoot.get(i).toString();
                                JSONObject innerJson = new JSONObject(jsonarrayString);

                                if (!exists) {
                                    jsonPathRoot = innerJson;
                                    pathRoot(pathRoot);
                                }
                            }
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            } else {
                StringGlobal = jsonPathRoot.get(pathRoot);
            }

        } else {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }

        return this;
    }


    <T extends Object> void AssertJson(String path, T equals, T equalsTo) throws BradescoAssertionException {
        if (jsonObjectValidation.get(path).getClass().getSimpleName().equalsIgnoreCase("BigDecimal") ||
                jsonObjectValidation.get(path).getClass().getSimpleName().equalsIgnoreCase("Float")) {
            BigDecimal bigDecimal = new BigDecimal(jsonObjectValidation.get(path).toString());
            BigDecimal bigEquals = new BigDecimal(equals.toString());
            BigDecimal bigEqualsTo = new BigDecimal(equalsTo.toString());
            if (bigDecimal.doubleValue() != bigEquals.doubleValue()) {
                if (bigDecimal.doubleValue() != bigEqualsTo.doubleValue())
                    throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [ " + bigEquals.doubleValue() + " ] [ " + bigEqualsTo.doubleValue() + " ]\nValor encontrado no JSON:  " + jsonObjectValidation);
            }
        } else {
            if (jsonObjectValidation.has(path))
                if (!jsonObjectValidation.get(path).equals(equals)) {
                    if (!jsonObjectValidation.get(path).equals(equalsTo)) {
                        throw new BradescoAssertionException("\n\nO valor informado não foi encontrado:  [ " + equals + " ] [ " + equalsTo + " ]\nValor encontrado no JSON:  " + jsonObjectValidation);
                    }
                }
        }

    }

    <T extends Object> void AssertJsonThree(String array, T equals, T equalsTo, T equalsTos) throws BradescoAssertionException {
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
            if (!jsonObjectValidation.has(array))
                if (!jsonObjectValidation.get(array).equals(equals.toString())) {
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