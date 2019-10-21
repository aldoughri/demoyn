package com.example.demo.Model;


import javax.persistence.*;


@Entity

public class Contact {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String phoneNummber;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String company;
    @Column(nullable = false)
    private String job;
    @Column(nullable = false)
    private String organization;
    private String adders;
    private String placebo;
    private boolean selectCheck = false;

    @ManyToOne
    @JoinColumn
    private User owner;

    public Contact() {
    }
    public Contact(String firstName, String lastName, String email, String phoneNummber,
                   String city, String company, String job, String organization, String adders,
                   String placebo, boolean selectCheck, User owner) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNummber = phoneNummber;
        this.city = city;
        this.company = company;
        this.job = job;
        this.organization = organization;
        this.adders = adders;
        this.placebo = placebo;
        this.selectCheck = selectCheck;
        this.owner = owner;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAdders() {
        return adders;
    }

    public void setAdders(String adders) {
        this.adders = adders;
    }

    public String getPlacebo() {
        return placebo;
    }

    public void setPlacebo(String placebo) {
        this.placebo = placebo;
    }

    public boolean isSelectCheck() {
        return selectCheck;
    }

    public void setSelectCheck(boolean selectCheck) {
        this.selectCheck = selectCheck;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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
                ", job='" + job + '\'' +
                ", organization='" + organization + '\'' +
                ", adders='" + adders + '\'' +
                ", placebo='" + placebo + '\'' +
                ", selectCheck=" + selectCheck +
                ", owner=" + owner +
                '}';
    }
}
