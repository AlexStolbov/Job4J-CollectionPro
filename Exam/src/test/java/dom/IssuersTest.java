package dom;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class IssuersTest {

    @Test
    public void whenAddTwoOrdersWithDifferentPriceThenCountOrderIsTwo() {
        String gas = "gas";
        String oil = "oil";
        Issuers issuer = new Issuers();
        boolean res;
        res = issuer.addBook(gas);
        assertTrue(res);
        res = issuer.addBook(oil);
        assertTrue(res);
        res = issuer.addBook(oil);
        assertFalse(res);

        res = issuer.addOrder(gas, OrderType.ADD_ORDER, OrderActions.ASK, 5, 110);
        assertTrue(res);
        issuer.addOrder(gas, OrderType.ADD_ORDER, OrderActions.BID, 3, 90);
        res = issuer.addOrder("oops", OrderType.ADD_ORDER, OrderActions.BID, 3, 90);
        assertFalse(res);

        assertThat(issuer.getBook(gas).getCountBidsOrder() + issuer.getBook(gas).getCountAsksOrder(), is(2));
    }

    @Test
    public void whenAddTwoOrdersWithEqualPriceAndEqualVolumeThenCountOrderIsZero() {
        String gas = "gas";
        Issuers issuer = new Issuers();
        issuer.addBook(gas);
        int volume = 3;
        issuer.addOrder(gas, OrderType.ADD_ORDER, OrderActions.ASK, volume, 110);
        issuer.addOrder(gas, OrderType.ADD_ORDER, OrderActions.BID, volume, 110);

        assertThat(issuer.getBook(gas).getCountBidsOrder() + issuer.getBook(gas).getCountAsksOrder(), is(0));
    }

    @Test
    public void whenAddTwoOrdersWithEqualPriceAndDifferentVolumeThenCountOrderIsNotZero() {
        String gas = "gas";
        Issuers issuer = new Issuers();
        issuer.addBook(gas);
        int asks = 4;
        int bids = 1;
        int da = (asks > bids) ? asks - bids : 0;
        int db = asks > bids ? 0 : bids - asks;

        issuer.addOrder(gas, OrderType.ADD_ORDER, OrderActions.ASK, asks, 110);
        issuer.addOrder(gas, OrderType.ADD_ORDER, OrderActions.BID, bids, 110);

        assertThat(issuer.getBook(gas).getBidsVolume(), is(db));
        assertThat(issuer.getBook(gas).getAsksVolume(), is(da));
    }

    @Test
    public void addIssuer() {
        String gas = "gas";
        Issuers issuer = new Issuers();
        issuer.addBook(gas);
        issuer.addBook("all");
        issuer.addBook("gold");

        issuer.addOrder(gas, OrderType.ADD_ORDER, OrderActions.ASK, 3, 110);
        issuer.addOrder(gas, OrderType.ADD_ORDER, OrderActions.ASK, 3, 111);
        issuer.addOrder(gas, OrderType.ADD_ORDER, OrderActions.BID, 5, 90);
        issuer.addOrder(gas, OrderType.ADD_ORDER, OrderActions.BID, 1, 100);
        issuer.addOrder(gas, OrderType.ADD_ORDER, OrderActions.ASK, 7, 90);
        issuer.addOrder(gas, OrderType.DELETE_ORDER, OrderActions.ASK, 1, 110);
        issuer.addOrder(gas, OrderType.DELETE_ORDER, OrderActions.ASK, 3, 111);
        issuer.addOrder(gas, OrderType.ADD_ORDER, OrderActions.BID, 2, 70);
        issuer.addOrder(gas, OrderType.DELETE_ORDER, OrderActions.BID, 1, 70);

        System.out.println(issuer.getBook(gas));

    }

}