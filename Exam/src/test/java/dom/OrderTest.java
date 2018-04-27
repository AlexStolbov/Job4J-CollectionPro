package dom;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class OrderTest {

    private Order newOrder() {
        return new OrderBuilder().setBook("gas").setType(OrderType.ADD_ORDER).setAction(OrderActions.ASK).setPrice(10).setVolume(1).getOrder();
    }

    @Test
    public void orderCompareToMustBeConsistentWithEquals() {
        Order o1 = newOrder();
        assertThat(o1.equals(o1), is(o1.compareTo(o1) == 0));
        Order o2 = newOrder();
        assertThat(o1.equals(o2), is(o1.compareTo(o2) == 0));
    }

}