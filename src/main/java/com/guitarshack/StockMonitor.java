package com.guitarshack;

import java.util.Calendar;
import java.util.Date;

public class StockMonitor {
    private final Alert alert;
    private final OrderHistory orderHistory;
    private Backend backend;
    private Warehouse warehouse;


    public StockMonitor(Alert alert, Backend backend) {
        this.alert = alert;
        this.backend = backend;
        this.orderHistory = new OrderHistory(backend);
        warehouse = new Warehouse(backend);

    }

    public void productSold(int productId, int quantity) {
        Product product = warehouse.getProduct(productId);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        Date endDate = calendar.getTime();
        calendar.add(Calendar.DATE, -30);
        Date startDate = calendar.getTime();
        SalesTotal total = orderHistory.getSalesTotal(product, endDate, startDate);
        if(product.getStock() - quantity <= (int) ((double) (total.getTotal() / 30) * product.getLeadTime()))
            alert.send(product);
    }

}
