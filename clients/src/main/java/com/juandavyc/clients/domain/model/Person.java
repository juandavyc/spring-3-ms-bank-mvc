package com.juandavyc.clients.domain.model;


import com.juandavyc.clients.domain.model.enums.GenderType;

public abstract class Person {

    protected String fullName;
    protected GenderType gender;
    protected Integer age;
    protected String identification;
    protected String address;
    protected String phoneNumber;

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


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
