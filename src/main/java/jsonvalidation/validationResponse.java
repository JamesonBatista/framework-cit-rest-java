package jsonvalidation;


import com.bradesco.core.exception.BradescoAssertionException;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static com.cit.framework.CITRestAssured.*;

public class validationResponse {
    static String bodyValidation;
    static Boolean containsGet = false;
    List<T> list;
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

    public validationResponse root(String... root) throws BradescoAssertionException {
        if (!bodyStart) {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        } else {
            rootStart = true;
            if (root.length == 1) {
                for (String rootPath : root) {
                    list = ExternalContainsJSON.extract().jsonPath().getList(rootPath);
                }
            } else if (root.length > 1) {
                throw new BradescoAssertionException("\n\nSeu root() só pode conter apenas UM valor. Ex: root('data').\n" +
                        "Porque o root é seu rootPath seu Array.");
            } else {
                list = ExternalContainsJSON.extract().jsonPath().getList("$");
            }
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

    public validationResponse get(String key) throws BradescoAssertionException {
        Boolean verify = false;
        if (containsGet) {
            for (int i = 0; i < ReplaceJson().length; i++) {

                if (ReplaceJson()[i].trim().equalsIgnoreCase(key.trim())) {
                    verify = true;
                    innerLoop:
                    for (int x = i; x < ReplaceJson().length; x++) {
                        String delete = StringUtils.deleteWhitespace(ReplaceJson()[x]);
                        if (!delete.trim().equalsIgnoreCase(key) && !delete.trim().isEmpty()) {
                            StringGlobal = ReplaceJson()[x];
                            if (ReplaceJson()[x].trim().equalsIgnoreCase("[") || ReplaceJson()[x].trim().equalsIgnoreCase("{")) {
                                throw new BradescoAssertionException("\n\nO método get() não pode ser usado para extrair valor de um Array\n" +
                                        "ex: data:[]\n" +
                                        "ou Objeto,\n" +
                                        "ex: data:{}\n" +
                                        "apenas de campos.\n" +
                                        "ex: name: CI&T ou id: 7 ou valor: 10000, etc." +
                                        "\n\n");

                            }
                            System.out.println("\n\nValor do campo " + key.toUpperCase() + " é: " + StringGlobal);
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
        return this;
    }

    public validationResponse root(String KeyObject, String path, Object equals) throws BradescoAssertionException {
        if (!bodyStart) {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        } else {
            JSONObject json = new JSONObject(response.asString());
            json = json.getJSONObject(KeyObject);
            Assert.assertThat(json.get(path), Matchers.is(equals));
            StringGlobal = json.get(path).toString();
            andContinueThree = true;
            return this;
        }
    }

    public validationResponse root(String KeyObject, String path) throws BradescoAssertionException {
        Object obj;
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

    public validationResponse and(String KeyObject, String path, Object equals) throws BradescoAssertionException {
        if (andContinueThree) {
            JSONObject json = new JSONObject(response.asString());
            json = json.getJSONObject(KeyObject);
            Assert.assertThat(json.get(path), Matchers.is(equals));
        } else {
            throw new BradescoAssertionException("\n\n O *** and ***  só pode ser usado após o root(String KeyObject, String path, String equals), que é o root com 3 parâmetros \n" +
                    "olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        }
        return this;
    }
}