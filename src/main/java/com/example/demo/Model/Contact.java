package com.example.demo.Model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity

public class Contact {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String firstName;
    @NotNull
    @Column(nullable = false)
    private String lastName;
    @NotNull
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
    @Column(nullable = false,unique = true)

    private String phoneNummber;
    @NotNull
    @Column(nullable = false)
    private String city;
    private String company;
    private String adders;
    private boolean selectCheck = false;
    private boolean CheckRole = false;
    @ManyToOne
 /*   @JoinColumn
 */   private User owner;
@ManyToOne
ContactrGroup contactrGroup;
    public Contact() {
        city="Damas";
        company="my company";

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNummber() {
        return phoneNummber;
    }

    public void setPhoneNummber(String phoneNummber) {
        this.phoneNummber = phoneNummber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAdders() {
        return adders;
    }

    public void setAdders(String adders) {
        this.adders = adders;
    }

    public boolean isSelectCheck() {
        return selectCheck;
    }

    public void setSelectCheck(boolean selectCheck) {
        this.selectCheck = selectCheck;
    }

    public boolean isCheckRole() {
        return CheckRole;
    }

    public void setCheckRole(boolean checkRole) {
        CheckRole = checkRole;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ContactrGroup getContactrGroup() {
        return contactrGroup;
    }

    public void setContactrGroup(ContactrGroup contactrGroup) {
        this.contactrGroup = contactrGroup;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNummber='" + phoneNummber + '\'' +
                ", city='" + city + '\'' +
                ", company='" + company + '\'' +
                ", adders='" + adders + '\'' +
                ", selectCheck=" + selectCheck +
                ", CheckRole=" + CheckRole +
                ", owner=" + owner +
                ", contactrGroup=" + contactrGroup +
                '}';
    }
}
