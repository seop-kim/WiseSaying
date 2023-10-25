package com.techit.wiseapp.json;

import com.techit.wiseapp.model.WiseModel;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonConverter {
    /**
     * Java WiseModel Object -> JSONObject
     * @param wise
     * @return JSONObject
     */
    public JSONObject javaToJson(WiseModel wise) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", wise.getId());
        jsonObject.put("content", wise.getContent());
        jsonObject.put("author", wise.getAuthor());

        return jsonObject;
    }

    /**
     * Java List<WiseModel> -> JSONArray
     * @param wiseArr
     * @return JSONArray
     */
    public JSONArray javaArrToJsonArr(List<WiseModel> wiseArr) {
        JSONArray jsonArr = new JSONArray();

        for (WiseModel model : wiseArr) {
            JSONObject jsonObject = this.javaToJson(model);
            jsonArr.add(jsonObject);
        }

        return jsonArr;
    }
}
