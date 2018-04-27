package dom;

import java.util.HashSet;
import java.util.Set;

/**
 * Эмитенты.
 */
class Issuers {
    private final Set<DepthOfMarket> books = new HashSet<>();

    /**
     * Добавить нового эмитента
     * @param book идентификатор эмитента
     * @return true если эмитент добавлен успешно
     */
    public boolean addBook(String book) {
        boolean res = false;
        DepthOfMarket n = new DepthOfMarket(book);
        if (!books.contains(n)) {
            books.add(n);
            res = true;
        }
        return res;
    }

    /**
     * Добавить заявку для эмитента
     * @param book идентификатор эмитента
     * @param type тип заявки
     * @param action действие заявки
     * @param volume количество акций
     * @param price цена покупки/продажи
     * @return true если заявка добавлена успешно
     */
    public boolean addOrder(String book, OrderType type, OrderActions action, int volume, int price) {
        boolean res = false;
        DepthOfMarket find = getBook(book);
        if (find != null) {
            Order newOrder = new OrderBuilder().setBook(book).setType(type).setAction(action).setVolume(volume).setPrice(price).getOrder();
            res = find.processingOrder(newOrder);
        }
        return res;
    }

    /**
     * Получить стакан для эмитента
     * @param book идентификатор эмитента
     * @return стакан
     */
    public DepthOfMarket getBook(String book) {
        DepthOfMarket res = null;
        for (DepthOfMarket d : this.books) {
            if (d.getBook().equals(book)) {
                res = d;
            }
        }
        return res;
    }
}
