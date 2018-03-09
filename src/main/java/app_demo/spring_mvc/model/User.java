package app_demo.spring_mvc.model;

import org.springframework.util.CollectionUtils;

import java.util.*;

import static app_demo.spring_mvc.util.UserUtil.DEFAULT_NOLMAL_WEIGHT;


public class User extends AbstractNamedEntity {

    private String email;

    private String password;

    private Date registered = new Date();

    private Set<Role> roles;

    private boolean enabled = true;

    private double normalWeight = DEFAULT_NOLMAL_WEIGHT;

    protected List<Pet> pets;

    public User(){}

    //Конструктор копирование
    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getNormalWeight(), u.isEnabled(), u.getRegistered(), u.getRoles());
    }

    public User(Integer id, String name, String email, String password, double normalWeight, Role role, Role... roles){
        this(id,name,email,password, normalWeight,true, new Date(), EnumSet.of(role,roles));
    }

    public User(Integer id, String name, String email, String password,double normalWeight, boolean enabled,Date registered, Collection<Role> roles ) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.normalWeight = normalWeight;
        this.registered = registered;
        setRoles(roles);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public double getNormalWeight() {
        return normalWeight;
    }

    public void setNormalWeight(double normalWeight) {
        this.normalWeight = normalWeight;
    }

    public List<Pet> getPets() {
        return pets;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", normalWeight=" + normalWeight +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
