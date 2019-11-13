package com.example.demo.Model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity

public class Contact {


    @ManyToOne
    @JoinColumn
    ContactsGroup contactsGroup;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String FullName;

    private String firstName;
    @NotNull
    @Column(nullable = false)
    @NotEmpty
    private String lastName;/**/
    @NotNull
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String email;
    /***/
    @NotNull
    @NotEmpty
    private String company;
    /****/

    private boolean selectCheck = false;
    private boolean CheckVal = false;
    @ManyToOne
    @JoinColumn
    private User owner;
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    List<ContactMerge> contactMerges;

    public Contact() {
        company = "my company";

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isSelectCheck() {
        return selectCheck;
    }

    public void setSelectCheck(boolean selectCheck) {
        this.selectCheck = selectCheck;
    }

    public boolean isCheckVal() {
        return CheckVal;
    }

    public void setCheckVal(boolean CheckVal) {
        this.CheckVal = CheckVal;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ContactsGroup getContactsGroup() {
        return contactsGroup;
    }

    public void setContactsGroup(ContactsGroup contactsGroup) {
        this.contactsGroup = contactsGroup;
    }

    public List<ContactMerge> getContactMerges() {
        return contactMerges;
    }

    public void setContactMerges(List<ContactMerge> contactMerges) {
        this.contactMerges = contactMerges;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", selectCheck=" + selectCheck +
                ", CheckVal=" + CheckVal +
                ", owner=" + owner +
                ", contactsGroup=" + contactsGroup +
                ", contactMerges=" + contactMerges +
                '}';
    }
}
