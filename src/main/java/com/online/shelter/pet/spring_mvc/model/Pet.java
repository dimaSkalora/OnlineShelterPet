package com.online.shelter.pet.spring_mvc.model;

import com.online.shelter.pet.spring_mvc.View;
import com.online.shelter.pet.spring_mvc.util.DateTimeUtil;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Pet.ALL_SORTED, query = "SELECT p FROM Pet p WHERE p.user.id=:userId ORDER BY p.createdDate DESC"),
        @NamedQuery(name = Pet.DELETE, query = "DELETE FROM Pet p WHERE p.id=:id AND p.user.id=:userId"),
        @NamedQuery(name = Pet.GET_BETWEEN, query = "SELECT p FROM Pet p " +
                "WHERE p.user.id=:userId AND p.dateTime BETWEEN :startDate AND :endDate ORDER BY p.dateTime DESC")
})
@Entity
@Table(name = "pets", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","createddate"}, name =
        "pets_unique_user_createddate_idx")})
public class Pet extends AbstractBaseEntity{

    public static final String ALL_SORTED = "Pet.getAll";
    public static final String DELETE = "Pet.delete";
    public static final String GET_BETWEEN = "Pet.getBetween";

    @Column(name = "createdDate",nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime createdDate;

    @Column(name = "typePet", nullable=false)
    @NotBlank
    @Size(min = 3, max = 8)
    private String typePet;

    @Column(name = "namePet", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String namePet;

    @Column(name = "breed", nullable = false)
    @NotBlank
    @Size(min = 5, max =20)
    private String breed;

    @Column(name = "sex", nullable = false)
    @NotBlank
    @Size(min = 1, max = 10)
    private String sex;

    @Column(name = "color", nullable = false)
    @NotBlank
    @Size(min = 4,max = 15)
    private String color;

    @Column(name = "age", nullable = false)
    @NotBlank
    private Double age;

    @Column(name = "growth", nullable = false)
    @NotBlank
    @Range(min = 5, max = 100)
    private Integer growth;

    @Column(name = "weight",nullable = false)
    @NotBlank
    private Double weight;

    @Column(name = "namePerson", nullable = false)
    @NotBlank
    @Size(min = 4, max = 100)
    private String namePerson;

    @Column(name = "phone", nullable = false)
    @NotBlank
    @Size(min = 7, max = 15)
    private String phone;

    @Column(name = "email", nullable = false)
    @NotBlank
    @Size(min = 5, max = 50)
    @SafeHtml(groups = {View.Web.class})
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
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

    public void setAge(Double age) {
        this.age = age;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public void setWeight(Double weight) {
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