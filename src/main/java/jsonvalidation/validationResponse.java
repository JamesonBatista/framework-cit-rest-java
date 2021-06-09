package jsonvalidation;


import com.bradesco.core.exception.BradescoAssertionException;
import com.google.gson.Gson;
import org.apache.poi.ss.formula.functions.T;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import static com.cit.framework.CITRestAssured.*;

public class validationResponse {
    static String bodyValidation;
    List<T> list;
    Boolean bodyStart = false;
    Boolean rootStart = false;

    public validationResponse Body() {
        bodyStart = true;
        return this;
    }

    static String[] ReplaceJson() {
        bodyValidation = ExternalContainsJSON.extract().response().asString();
        String retorno = new Gson().toJson(bodyValidation);
        String[] stringFormat;
        String rep = retorno.replaceAll(":", "\n");
        String repla = rep.replaceAll("[\\\\(\\\\)\\\\[\\\\]\\\\{\\\\}]", "");
        String asp = repla.replaceAll("\"", "  ");
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

    validationResponse forValidation(String key) throws BradescoAssertionException {

        Boolean verify = false;
        for (String s : ReplaceJson()) {
            if (s.equalsIgnoreCase(key)) {
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