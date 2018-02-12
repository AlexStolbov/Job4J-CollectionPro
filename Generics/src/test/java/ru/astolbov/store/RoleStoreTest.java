package ru.astolbov.store;

import org.junit.Test;
import ru.astolbov.model.Role;
import ru.astolbov.model.User;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddElementThenCanFindIt() {
        Role role1 = new Role("role1");
        Role role2 = new Role("role2");
        RoleStore roleStore = new RoleStore();
        roleStore.add(role1);
        roleStore.add(role2);
        assertThat(roleStore.findById(role1.getId()), is(role1));
        assertThat(roleStore.findById(role2.getId()), is(role2));
    }

    @Test
    public void whenWrongGenericTypeThenCompileError() {
        //RoleStore<User> roleStore = new RoleStore<>();
    }

    @Test
    public void whenReplaceElementThenCanNotFindOldAndCanFindNew() {
        Role roleOld = new Role("old");
        Role roleNew = new Role("new");
        RoleStore roleStore = new RoleStore();
        roleStore.add(roleOld);
        boolean res = roleStore.replace(roleOld.getId(), roleNew);
        assertThat(res, is(true));
        assertNull(roleStore.findById(roleOld.getId()));
        assertThat(roleStore.findById(roleNew.getId()), is(roleNew));
    }

    @Test
    public void whenReplaceNotExistingElementThenGetFalse() {
        RoleStore roleStore = new RoleStore();
        boolean res = roleStore.replace("foo", new Role("any"));
        assertThat(res, is(false));
    }

    @Test
    public void whenDeleteElementThenCanNotFindIt() {
        Role role = new Role("one");
        RoleStore roleStore = new RoleStore();
        roleStore.add(role);
        boolean res = roleStore.delete(role.getId());
        Role find = roleStore.findById(role.getId());
        assertThat(res, is(true));
        assertNull(find);
    }

    @Test
    public void whenDeleteNotExistingElementThenGetFalse() {
        RoleStore roleStore = new RoleStore();
        boolean res = roleStore.delete("foo");
        assertThat(res, is(false));
    }

}