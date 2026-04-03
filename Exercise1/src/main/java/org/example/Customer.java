package org.example;

public class Customer {
    private String name, gender, address;

    public Customer(String name, String gender, String address) {
        this.name = name;
        this.gender = gender;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }
}
