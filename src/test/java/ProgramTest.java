import com.guitarshack.StockMonitor;
import com.guitarshack.Warehouse;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

public class ProgramTest {

    @Test
    public void testEverything() {
        int productId = 811;
        int quantity = 811;

        AtomicReference<String> message = new AtomicReference<>();

        StockMonitor monitor = new StockMonitor(product -> {
            message.set("You need to reorder product " + product.getId() +
                    ". Only " + product.getStock() + " remaining in stock");
        }, new Warehouse());
        monitor.productSold(productId, quantity);

        assertEquals("You need to reorder product 811. Only 53 remaining in stock", message.get());


    }
}
