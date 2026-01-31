package com.juandayvc.clients.domain.model;


import com.juandayvc.clients.domain.model.enums.GenderType;

public abstract class Person {

    protected final String fullName;
    protected final GenderType gender;
    protected final Integer age;
    protected final String identification;
    protected final String address;
    protected final String phoneNumber;

    protected Person(
            String fullName,
            GenderType gender,
            Integer age,
            String identification,
            String address,
            String phoneNumber
    ) {
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.identification = identification;
        this.address = address;
        this.phoneNumber = phoneNumber;

        //if (age < 0) throw new IllegalArgumentException("Age invalid");
    }


    public String getFullName() {
        return fullName;
    }

    public GenderType getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getIdentification() {
        return identification;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


}
