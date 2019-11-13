package com.example.demo.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
public class ContactMerge {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
   @Column(nullable = false,unique = true)
    private String phoneNummber;
    @NotNull
    private String city;
    @NotNull
    private String adders;

    @ManyToOne
    @JoinColumn
    private Contact contact;

    public ContactMerge() {
        city="Damas";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAdders() {
        return adders;
    }

    public void setAdders(String adders) {
        this.adders = adders;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
