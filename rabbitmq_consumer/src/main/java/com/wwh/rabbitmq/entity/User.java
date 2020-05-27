package com.wwh.rabbitmq.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author wangwenhao
 * @description User
 * @date 2020-05-27 14:21
 */
public class User implements Serializable {

    private static final long serialVersionUID = 8112280000701738038L;

    private Integer age;

    private String name;

    private String address;

    public User() {
    }

    public User(Integer age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(age, user.age) &&
                Objects.equals(name, user.name) &&
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, address);
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
