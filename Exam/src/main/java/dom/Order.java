package dom;

public class Order implements Comparable<Order> {
    //уникальный ключ заявки.
    private final int id;
    static private int nextId = 1;
    //идентификатор ценной бумаги.
    private final String book;
    //add/delete - выставить заявку на торги или снять
    private final OrderType type;
    //bid/ask - заявка имеет два действия. Заявка на покупка ценной бумаги или на продажу.
    private final OrderActions action;
    //цена, по которой мы ходим сделать действия покупки или продажи.
    private final Integer price;
    //количество акций, которые мы ходим продать или купить.
    private Integer volume;

    public Order(String book, OrderType type, OrderActions action, int price, int volume) {
        this.id = nextId++;
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.volume = volume;
    }

    public OrderType getType() {
        return type;
    }

    public OrderActions getAction() {
        return action;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getVolume() {
        return volume;
    }

    public void decreaseVolume(int volume) {
        this.volume -= volume;
    }

    /**
     * Обеспечивает возрастание по price, если price одинаковая, то по id (уникальное).
     * Сделано так для размещения в дереве уникальных заявок.
     */
    @Override
    public int compareTo(Order o) {
        int res;
        if (o == null) {
            res = -1;
        } else {
            res = this.getPrice().compareTo(o.getPrice());
            if (res == 0) {
                res = Integer.compare(this.id, o.id);
            }
        }
        return res;
    }
}
