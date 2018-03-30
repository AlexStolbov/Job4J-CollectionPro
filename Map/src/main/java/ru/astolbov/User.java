package ru.astolbov;

import java.util.Calendar;
import java.util.Objects;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    @Override
    public int hashCode() {
        //return Objects.hash(name, children, birthday);
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        User that = (User) obj;
        return (this.name.equals(that.name) && this.children == that.children && this.birthday.equals(that.birthday));
    }
}
