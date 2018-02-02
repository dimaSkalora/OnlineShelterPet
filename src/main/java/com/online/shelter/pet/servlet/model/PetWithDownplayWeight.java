package com.online.shelter.pet.servlet.model;

import java.time.LocalDate;

public class PetWithDownplayWeight {
    private final LocalDate createdDate;
    private final String typePet;
    private final String namePet;
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

    public PetWithDownplayWeight(LocalDate createdDate, String typePet, String namePet, String breed, char sex, String color, double age, int growth, double weight, String namePerson, String phone, String email, boolean downplayWeight) {
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
        this.downplayWeight = downplayWeight;
    }

    @Override
    public String toString() {
        return "PetWithDownplayWeight{" +
                "createdDate=" + createdDate +
                ", typePet='" + typePet + '\'' +
                ", namePet='" + namePet + '\'' +
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
