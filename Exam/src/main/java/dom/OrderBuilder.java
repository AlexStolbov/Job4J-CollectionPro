package dom;

public class OrderBuilder {

    private String book;
    private OrderType type;
    private OrderActions action;
    private int price;
    private int volume;

    public OrderBuilder setBook(String book) {
        this.book = book;
        return this;
    }

    public OrderBuilder setType(OrderType type) {
        this.type = type;
        return this;
    }

    public OrderBuilder setAction(OrderActions action) {
        this.action = action;
        return this;
    }

    public OrderBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public OrderBuilder setVolume(int volume) {
        this.volume = volume;
        return this;
    }

    public Order getOrder() {
        return new Order(this.book, this.type, this.action, this.price, this.volume);
    }

}
