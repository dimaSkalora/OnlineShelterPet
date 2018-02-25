package com.online.shelter.pet.spring_mvc.model;

import com.online.shelter.pet.spring_mvc.View;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity{
    @NotBlank
    @Size(min = 2, max =100)
    @Column(name = "name", nullable = false)
    @SafeHtml(groups = {View.Web.class})
    protected String name;

    public AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, name);
    }
}
