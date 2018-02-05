package com.online.shelter.pet.servlet.model;

import java.time.LocalDate;

public class Pet extends AbstractBaseEntity{
    private final LocalDate createdDate;
    private final String typePet;
    private final String namePet;
    private final String breed;
    private final String sex;
    private final String color;
    private final double age;
    private final int growth;
    private final double weight;

    private final String namePerson;
    private final String phone;
    private final String email;

    public Pet(LocalDate createdDate, String typePet, String namePet, String breed, String sex, String color, double age, int growth, double weight, String namePerson, String phone, String email) {
      this(null, createdDate, typePet, namePet, breed, sex, color, age, growth, weight, namePerson, phone, email);
    }

    public Pet(Integer id, LocalDate createdDate, String typePet, String namePet, String breed, String sex, String color, double age, int growth, double weight, String namePerson, String phone, String email) {
        super(id);
        this.createdDate = createdDate;
        this.typePet = typePet;
        this.namePet = namePet;
        this.breed = breed;
        this.sex = sex;
        this.color = color;
        this.age = age;
        this.growth = growth;
        this.weight = weight;
        this.namePerson = namePerson;
        this.phone = phone;
        this.email = email;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public String getTypePet() {
        return typePet;
    }

    public String getNamePet() {
        return namePet;
    }

    public String getBreed() {
        return breed;
    }

    public String getSex() {
        return sex;
    }

    public String getColor() {
        return color;
    }

    public double getAge() {
        return age;
    }

    public int getGrowth() {
        return growth;
    }

    public double getWeight() {
        return weight;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isNew(){
        return this.id == null;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", typePet='" + typePet + '\'' +
                ", namePet='" + namePet + '\'' +
                ", breed='" + breed + '\'' +
                ", sex=" + sex +
                ", color='" + color + '\'' +
                ", age=" + age +
                ", growth=" + growth +
                ", weight=" + weight +
                ", namePerson='" + namePerson + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}