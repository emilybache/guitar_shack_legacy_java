package com.guitarshack;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderHistory {
    private Backend backend;

    public OrderHistory(Backend backend) {

        this.backend = backend;
    }

    SalesTotal getSalesTotal(Product product, Date endDate, Date startDate) {
        DateFormat format = new SimpleDateFormat("M/d/yyyy");
        Map<String, Object> params1 = new HashMap<>(){{
            put("productId", product.getId());
            put("startDate", format.format(startDate));
            put("endDate", format.format(endDate));
            put("action", "total");
        }};
        String paramString1 = "?";

        for (String key : params1.keySet()) {
            paramString1 += key + "=" + params1.get(key).toString() + "&";
        }
        String url = "https://gjtvhjg8e9.execute-api.us-east-2.amazonaws.com/default/sales" + paramString1;
        String result1 = backend.queryBackend(url);
        return new Gson().fromJson(result1, SalesTotal.class);
    }
}
