package ru.astolbov;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void whenNotOverrideEqualsAndHashThenMapContainsTwoElement() {
        Map<User, String> map = new HashMap<>();
        User user1 = new User("Bob", 1, new GregorianCalendar(1900, 1, 1));
        User user2 = new User("Bob", 1, new GregorianCalendar(1900, 1, 1));
        map.put(user1, "one");
        map.put(user2, "two");
        System.out.println(map);

    }

}