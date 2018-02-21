package com.online.shelter.pet.spring_mvc.to;

import java.time.LocalDateTime;
import java.util.Objects;

public class PetWithDownplayWeight extends BaseTo {
    private final LocalDateTime createdDate;
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

    private final boolean downplayWeight;

    public PetWithDownplayWeight(Integer id, LocalDateTime createdDate, String typePet, String namePet, String breed, String sex, String color, double age, int growth, double weight, String namePerson, String phone, String email, boolean downplayWeight) {
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
        this.downplayWeight = downplayWeight;
    }

    public LocalDateTime getCreatedDate() {
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

    public boolean isDownplayWeight() {
        return downplayWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PetWithDownplayWeight that = (PetWithDownplayWeight) o;
        return weight == that.weight &&
                downplayWeight == that.downplayWeight &&
                Objects.equals(id, that.id) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(typePet, that.typePet) &&
                Objects.equals(namePet, that.namePet) &&
                Objects.equals(breed, that.breed) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(color, that.color) &&
                Objects.equals(age, that.age) &&
                Objects.equals(growth, that.growth) &&
                Objects.equals(namePerson, that.namePerson) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdDate, typePet, namePet, breed,sex,color,age,
                growth,weight,namePerson,phone,email);
    }

    @Override
    public String toString() {
        return "PetWithDownplayWeight{" +
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
                ", downplayWeight=" + downplayWeight +
                '}';
    }
}