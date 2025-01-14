import com.guitarshack.Backend;
import com.guitarshack.StockMonitor;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

public class StockMonitorTest {

    @Test
    public void alertTriggeredIfStockLow() {
        int productId = 811;
        int quantity = 811;


        AtomicReference<String> message = new AtomicReference<>();

        StockMonitor monitor = new StockMonitor(product -> {
            message.set("You need to reorder product " + product.getId() +
                    ". Only " + product.getStock() + " remaining in stock");
        }, url -> {
            if (url.contains("sales") ) {
                return "{ \"total\": 53 }";
            } else if (url.contains("product")) {
                return "{ \"stock\": 53,  \"id\": 811, \"leadTime\": 53}";
            }
            return null;
        });

        monitor.productSold(productId, quantity);

        assertEquals("You need to reorder product 811. Only 53 remaining in stock", message.get());

    }

    @Test
    public void alertNotTriggered()
    {
        int productId = 811;
        int quantity = 4;


        AtomicReference<String> message = new AtomicReference<>();

        StockMonitor monitor = new StockMonitor(product -> {
            message.set("You need to reorder product " + product.getId() +
                    ". Only " + product.getStock() + " remaining in stock");
        }, url -> {
            if (url.contains("sales") ) {
                return "{ \"total\": 53 }";
            } else if (url.contains("product")) {
                return "{ \"stock\": 53,  \"id\": 811, \"leadTime\": 3}";
            }
            return null;
        });

        monitor.productSold(productId, quantity);

        assertEquals(null, message.get());

    }
}
