package com.online.shelter.pet.servlet.model;

import java.time.LocalDateTime;

public class AnimalWithDownplayWeight {
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

    private final boolean downplayWeight;

    public AnimalWithDownplayWeight(LocalDateTime dateTime, String typeAnimal, String nameAnimal, String breed, char sex, String color, double age, int growth, double weight, String namePerson, String phone, String email, boolean downplayWeight) {
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
        this.downplayWeight = downplayWeight;
    }

    @Override
    public String toString() {
        return "AnimalWithDownplayWeight{" +
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
                ", downplayWeight=" + downplayWeight +
                '}';
    }
}
