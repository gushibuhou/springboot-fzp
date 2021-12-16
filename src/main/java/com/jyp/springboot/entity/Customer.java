package com.jyp.springboot.entity;

import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2019/10/19
 * Time: 16:56
 */
public class Customer {
    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}
