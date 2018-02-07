package ru.astolbov.store;

import ru.astolbov.SimpleArray;
import ru.astolbov.model.Base;

public abstract class AbstractStore<T extends Base> implements Store<T> {

    private final SimpleArray<T> store = new SimpleArray<>();

    @Override
    public void add(T  model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean res = false;
        int exist = findIndexById(id);
        if (exist > -1) {
            store.set(exist, model);
            res = true;
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        boolean res = false;
        int exist = findIndexById(id);
        if (exist > -1) {
            store.delete(exist);
            res = true;
        }
        return res;
    }

    @Override
    public T findById(String id) {
        T res = null;
        int exist = findIndexById(id);
        if (exist > -1) {
            res = store.get(exist);
        }
        return res;
    }

    private int findIndexById(String id) {
        int res = -1;
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getId().equals(id)) {
                res = i;
                break;
            }
        }
        return res;
    }
}
