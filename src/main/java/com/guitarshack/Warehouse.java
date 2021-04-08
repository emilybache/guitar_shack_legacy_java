package com.guitarshack;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private final Backend backend;

    public Warehouse(Backend backend) {
        this.backend = backend;
    }

    Product getProduct(int productId) {
        String baseURL = "https://6hr1390c1j.execute-api.us-east-2.amazonaws.com/default/product";
        Map<String, Object> params = new HashMap<>() {{
            put("id", productId);
        }};
        String paramString = "?";

        for (String key : params.keySet()) {
            paramString += key + "=" + params.get(key).toString() + "&";
        }
        String url = baseURL + paramString;
        String result = backend.queryBackend(url);
        return new Gson().fromJson(result, Product.class);
    }
}
