package com.guitarshack;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StockMonitor {
    private final Alert alert;
    private Backend backend;

    public StockMonitor(Alert alert, Backend backend) {
        this.alert = alert;
        this.backend = backend;
    }

    public void productSold(int productId, int quantity) {
        Product product = getProduct(productId);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        Date endDate = calendar.getTime();
        calendar.add(Calendar.DATE, -30);
        Date startDate = calendar.getTime();
        SalesTotal total = getSalesTotal(product, endDate, startDate);
        if(product.getStock() - quantity <= (int) ((double) (total.getTotal() / 30) * product.getLeadTime()))
            alert.send(product);
    }

    private SalesTotal getSalesTotal(Product product, Date endDate, Date startDate) {
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

    private Product getProduct(int productId) {
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
