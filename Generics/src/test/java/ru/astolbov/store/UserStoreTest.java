package ru.astolbov.store;

import org.junit.Test;
import ru.astolbov.model.User;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenAddElementThenCanFindIt() {
        User user1 = new User("user1");
        User user2 = new User("user2");
        UserStore<User> userStore = new UserStore<>();
        userStore.add(user1);
        userStore.add(user2);
        assertThat(userStore.findById(user1.getId()), is(user1));
        assertThat(userStore.findById(user2.getId()), is(user2));
    }

    @Test
    public void whenWrongGenericTypeThenCompileError() {
        //UserStore<Role> wrongStore = new UserStore<>();
    }
}