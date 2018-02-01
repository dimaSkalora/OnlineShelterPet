package com.online.shelter.pet.servlet.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Animal {
    private final LocalDateTime dateTime;
    private final String typeAnimal;
    private final String nameAnimal;
    private final String breed;
    private final char sex;
    private final String color;
    private final double age;
    private final int growth;
    private final double weight;

    private final String namePerson;
    private final String phone;
    private final String email;

    public Animal(LocalDateTime dateTime, String typeAnimal, String nameAnimal, String breed, char sex, String color, double age, int growth, double weight, String namePerson, String phone, String email) {
        this.dateTime = dateTime;
        this.typeAnimal = typeAnimal;
        this.nameAnimal = nameAnimal;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getTypeAnimal() {
        return typeAnimal;
    }

    public String getNameAnimal() {
        return nameAnimal;
    }

    public String getBreed() {
        return breed;
    }

    public char getSex() {
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

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    @Override
    public String toString() {
        return "Animal{" +
                "dateTime=" + dateTime +
                ", typeAnimal='" + typeAnimal + '\'' +
                ", nameAnimal='" + nameAnimal + '\'' +
                ", breed='" + breed + '\'' +
                ", sex=" + sex +
                ", color='" + color + '\'' +
                ", age=" + age +
                ", growth=" + growth +
                ", weight=" + weight +
                ", namePerson='" + namePerson + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}
