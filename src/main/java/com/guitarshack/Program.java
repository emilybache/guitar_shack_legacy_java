package com.guitarshack;

public class Program {

    public static StockMonitor monitor = new StockMonitor(product -> {
        // We are faking this for now
        System.out.println(
                "You need to reorder product " + product.getId() +
                        ". Only " + product.getStock() + " remaining in stock");
    }, new WebService());

    public static void main(String[] args) {
        int productId = Integer.parseInt(args[0]);
        int quantity = Integer.parseInt(args[1]);

        monitor.productSold(productId, quantity);
        System.out.println("program finished");
    }
}
