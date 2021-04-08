package com.guitarshack;

import java.util.Calendar;
import java.util.Date;

public class StockMonitor {
    private final Alert alert;
    private final OrderHistory orderHistory;
    private Warehouse warehouse;

    public StockMonitor(Alert alert, Backend backend) {
        this.alert = alert;
        this.orderHistory = new OrderHistory(backend);
        warehouse = new Warehouse(backend);

    }

    public void productSold(int productId, int quantity) {
        Product product = warehouse.getProduct(productId);
        TimeInterval timeInterval = getLastNDays(-30);
        SalesTotal total = orderHistory.getSalesTotal(product, timeInterval);
        if(product.getStock() - quantity <= (int) ((double) (total.getTotal() / 30) * product.getLeadTime()))
            alert.send(product);
    }

    private TimeInterval getLastNDays(int numberOfDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        Date endDate = calendar.getTime();
        calendar.add(Calendar.DATE, numberOfDays);
        Date startDate = calendar.getTime();
        TimeInterval timeInterval = new TimeInterval(endDate, startDate);
        return timeInterval;
    }

}
