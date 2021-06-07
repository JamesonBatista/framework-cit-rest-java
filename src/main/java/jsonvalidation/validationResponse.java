package jsonvalidation;


import com.bradesco.core.exception.BradescoAssertionException;
import com.google.gson.Gson;
import org.apache.poi.ss.formula.functions.T;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import static com.cit.framework.CITRestAssured.readJson;

public class validationResponse {
    public static String JSON_;
    public static String pathRoot_;
    List<T> list;
    Boolean bodyStart = false;
    Boolean andStart = false;
    Boolean rootStart = false;

    public validationResponse Body() {
        bodyStart = true;
        return this;
    }

    static String[] ReplaceJson() {
        JSON_ = readJson.extract().response().asString();
        String retorno = new Gson().toJson(JSON_);
        String[] stringFormat;
        String rep = retorno.replaceAll(":", "\n");
        String repla = rep.replaceAll("[\\\\(\\\\)\\\\[\\\\]\\\\{\\\\}]", "");
        String asp = repla.replaceAll("\"", "  ");
        String replac = asp.replaceAll(",", "  ");


        stringFormat = replac.split("  ");
        return stringFormat;
    }

    public validationResponse contains(String key) throws BradescoAssertionException {
        if (!bodyStart) {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        } else {
            andStart = true;
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
        }
        return this;
    }

    public validationResponse root(String... root) throws BradescoAssertionException {
        if (!bodyStart) {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...\n" +
                    "Em caso de dúvidas, olhe o DOC 《《 src/test/resources/FrameworkCIT.md 》》");
        } else {
            if (root.length > 0) {
                System.out.println("ROOT MAIOR");
                for (String rootpath : root) {
                    rootStart = true;
                    pathRoot_ = rootpath;
                    list = readJson.extract().jsonPath().getList(pathRoot_ == "" ? "$" : pathRoot_);
                }
            } else {
                rootStart = true;
                pathRoot_ = "$";
                list = readJson.extract().jsonPath().getList(pathRoot_);
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
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body().root('data') seguido do root().object()...\n" +
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

    public validationResponse and(String key) throws BradescoAssertionException {
        if (!bodyStart) {
            throw new BradescoAssertionException("\n\nErro ao iniciar validação JSON, por favor inicie usando o método Body()...");
        } else if (!andStart) {
            throw new BradescoAssertionException("\n\nAo usar o and, inicie o Body().contains('id').and('name');");
        } else {
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
        }
        return this;
    }
}