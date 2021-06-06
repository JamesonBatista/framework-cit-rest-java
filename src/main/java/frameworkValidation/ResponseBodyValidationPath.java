package frameworkValidation;

import com.bradesco.core.exception.BradescoAssertionException;
import com.google.gson.Gson;

public class ResponseBodyValidationPath<T> {

    public ResponseBodyValidationPath and(String key) throws BradescoAssertionException {
        Boolean verify = false;
        String retorno = new Gson().toJson(ReadCompleteJSONForValidation.JSON_);
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
