package jsonvalidation;


import com.bradesco.core.exception.BradescoAssertionException;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import static com.cit.framework.CITRestAssured.readJson;

public class ReadCompleteJSONForValidation<T, R> {
    public static String JSON_;
    public static String pathRoot_;


    public ReadCompleteJSONForValidation Body() {
        return this;
    }

    public ReadCompleteJSONForValidation<T, R> contains(String key) throws BradescoAssertionException {
        Boolean verify = false;
        JSON_ = readJson.extract().response().asString();
        String retorno = new Gson().toJson(JSON_);
        String rep = retorno.replaceAll(":", "");
        String repla = rep.replaceAll("[\\\\(\\\\)\\\\[\\\\]\\\\{\\\\}]", "");
        String replac = repla.replaceAll(",", "");
        String asp = replac.replaceAll("\"", " ");
        String[] arrayValores = asp.split(" ");
        for (String s : arrayValores) {
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

    public ReadCompleteJSONForValidation<T, R> root(String root) {
        pathRoot_ = root;
        return this;
    }

    public ReadCompleteJSONForValidation<T, R> object(String... path) throws BradescoAssertionException {
        List<T> list = readJson.extract().jsonPath().getList(pathRoot_);
        JSONObject json;
        Boolean value;
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

        return this;
    }

    public ReadCompleteJSONForValidation<T, R> and(String key) throws BradescoAssertionException {
        Boolean verify = false;
        String retorno = new Gson().toJson(JSON_);
        String rep = retorno.replaceAll(":", "");
        String repla = rep.replaceAll("[\\\\(\\\\)\\\\[\\\\]\\\\{\\\\}]", "");
        String replac = repla.replaceAll(",", "");
        String asp = replac.replaceAll("\"", " ");
        String[] arrayValores = asp.split(" ");
        for (String s : arrayValores) {
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