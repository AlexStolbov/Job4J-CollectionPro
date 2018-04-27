package dom;


import java.util.*;
import java.util.function.Predicate;

/**
 * Стакан для одного эмитента
 */
public class DepthOfMarket {
    /**
     * Идентификатор эмитента
     */
    private final String book;

    /**
     * Заявки на покупку.
     */
    private final Set<Order> bids = new TreeSet<>();

    /**
     * Заявки на продажу.
     */
    private final Set<Order> asks = new TreeSet<>();

    public DepthOfMarket(String book) {
        this.book = book;
    }

    public String getBook() {
        return book;
    }

    /**
     * Обработать поступившую заявку.
     * Для заявки на добавление все существующие заявки считаем чужими, т.е. можем совершать сделки.
     * Для заявки на удаление все существующие заявки считаем своими, т.е. удаляем.
     * @param order поступившая заявка
     * @return true если заявка обработана успешно
     */
    public boolean processingOrder(Order order) {
        boolean res = false;
        if (order.getType().equals(OrderType.ADD_ORDER)) {
            if (order.getAction().equals(OrderActions.ASK)) {
                askToBid(order);
            } else {
                bidToAsk(order);
            }
            res = addOrder(order);
        } else {
            if (order.getAction().equals(OrderActions.ASK)) {
                deleteOrder(order, asks);
            } else {
                deleteOrder(order, bids);
            }
        }
        removeZero(asks);
        removeZero(bids);
        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Продажа  Цена  Покупка\n");
        String f = "%7s%6s%7s%n";
        for (Map.Entry<Integer, Integer> pair : rollUp(this.asks).entrySet()) {
            sb.append(String.format(f, pair.getValue(), pair.getKey(), ""));
        }
        for (Map.Entry<Integer, Integer> pair : rollUp(this.bids).entrySet()) {
            sb.append(String.format(f, "", pair.getKey(), pair.getValue()));
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        boolean res  = true;
        if (this != o) {
            if (o == null || getClass() != o.getClass()) {
                res = false;
            } else {
                DepthOfMarket that = (DepthOfMarket) o;
                res = Objects.equals(book, that.getBook());
            }
        }
        return res;
    }

    @Override
    public int hashCode() {
        return book.hashCode();
    }

    public int getCountBidsOrder() {
        return bids.size();
    }

    public int getCountAsksOrder() {
        return asks.size();
    }

    /**
     * Общее количество акций во всех заявках на покупку
     */
    public Integer getBidsVolume() {
        Integer res = 0;
        for (Order current : bids) {
            res += current.getVolume();
        }
        return res;
    }

    /**
     * Общее количество акций во всех заявках на продажу
     */
    public Integer getAsksVolume() {
        Integer res = 0;
        for (Order current : asks) {
            res += current.getVolume();
        }
        return res;
    }


    /**
     * Совершить продажу для заявки
     * @param order заявка на продажу
     */
    private void askToBid(Order order) {
        for (Order current : this.bids) {
            if (order.getPrice() <= current.getPrice()) {
                int to = Integer.min(order.getVolume(), current.getVolume());
                order.decreaseVolume(to);
                current.decreaseVolume(to);
                if (order.getVolume() == 0) {
                    break;
                }
            }
        }
    }

    /**
     * Совершить покупку для заявки
     * @param order заявка на покупку
     */
    private void bidToAsk(Order order) {
        for (Order current : this.asks) {
            if (order.getPrice() >= current.getPrice()) {
                int to = Integer.min(order.getVolume(), current.getVolume());
                order.decreaseVolume(to);
                current.decreaseVolume(to);
                if (order.getVolume() == 0) {
                    break;
                }
            }
        }
    }

    /**
     * Добавить заявку в стакан
     * @param order поданная заявка
     * @return true, если добавлена успешно
     */
    private boolean addOrder(Order order) {
        boolean res = false;
        if (order.getVolume() > 0) {
            if (order.getAction().equals(OrderActions.ASK)) {
                this.asks.add(order);
            } else {
                this.bids.add(order);
            }
            res = true;
        }
        return res;
    }

    /**
     * Формирует карту "свернутую" по цене.
     * @param orders коллекция с уникальными заявками
     * @return  карта: ключ - цена, значение - количество заявок
     */
    private Map<Integer, Integer> rollUp(Set<Order> orders) {
        Map<Integer, Integer> sorted = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });
        Integer exist;
        Integer currentPrice;
        for (Order current : orders) {
            currentPrice = current.getPrice();
            exist = sorted.put(currentPrice, current.getVolume());
            if (exist != null) {
                sorted.put(currentPrice, exist + current.getVolume());
            }
        }
        return sorted;
    }

    /**
     * Удалить заявки с нулевым количеством акций.
     * @param part коллекция с заявками
     */
    private void removeZero(Set<Order> part) {
        part.removeIf(new Predicate<Order>() {
            @Override
            public boolean test(Order order) {
                return order.getVolume() == 0;
            }
        });
    }

    /**
     * Удалить завки согласно цене и количеству в переданной заявке.
     * @param delete заявка, на основании которой производиться удаление
     * @param part коллекция с заявками
     */
    private void deleteOrder(Order delete, Set<Order> part) {
        for (Order current :  part) {
            if (delete.getVolume() == 0) {
                break;
            }
            if (current.getPrice().equals(delete.getPrice())) {
                Integer min = Integer.min(current.getVolume(), delete.getVolume());
                current.decreaseVolume(min);
                delete.decreaseVolume(min);
            }
        }
        removeZero(part);
    }

}
