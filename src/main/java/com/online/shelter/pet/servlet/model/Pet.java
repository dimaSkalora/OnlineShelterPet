package com.online.shelter.pet.servlet.model;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Pet extends AbstractBaseEntity{
    private LocalDateTime createdDate;
    private String typePet;
    private String namePet;
    private String breed;
    private String sex;
    private String color;
    private double age;
    private int growth;
    private double weight;

    private String namePerson;
    private String phone;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Pet() {
    }

    public Pet(LocalDateTime createdDate, String typePet, String namePet, String breed, String sex, String color, double age, int growth, double weight, String namePerson, String phone, String email) {
      this(null, createdDate, typePet, namePet, breed, sex, color, age, growth, weight, namePerson, phone, email);
    }

    public Pet(Integer id, LocalDateTime createdDate, String typePet, String namePet, String breed, String sex, String color, double age, int growth, double weight, String namePerson, String phone, String email) {
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDate getDate() {
        return createdDate.toLocalDate();
    }

    public LocalTime getTime() {
        return createdDate.toLocalTime();
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

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setTypePet(String typePet) {
        this.typePet = typePet;
    }

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public void setGrowth(int growth) {
        this.growth = growth;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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