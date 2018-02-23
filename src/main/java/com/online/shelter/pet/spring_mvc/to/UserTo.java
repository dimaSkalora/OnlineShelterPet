package com.online.shelter.pet.spring_mvc.to;

import com.online.shelter.pet.spring_mvc.util.UserUtil;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Size(min = 5, max = 32, message = "length must between 5 and 32 characters")
    private String password;

    @NotNull
    private Double downplayWeight = UserUtil.DEFAULT_NOLMAL_WEIGHT;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password, Double downplayWeight) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.downplayWeight = downplayWeight;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getDownplayWeight() {
        return downplayWeight;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", downplayWeight=" + downplayWeight +
                ", id=" + id +
                '}';
    }
}