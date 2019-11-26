package com.example.demo.Model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String FullName;
    @NotNull
    private String FirstName;
    @NotNull
    private String LastName;

    private String username;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    @Column(nullable = false)
    private String password;

    private int active;

    private String roles = "";

    private String permissions = "";
    @ManyToOne
    @JoinColumn
    private Department department;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Contact> contacts;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ContactsGroup> contactsGroups;

    public User(@NotNull String fullName, @NotNull String firstName, @NotNull String lastName, @NotNull String email, @NotNull String password, String roles, String permissions) {
        FirstName = firstName;
        LastName = lastName;

        this.email = email;

        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = 1;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList() {
        if (this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<ContactsGroup> getContactsGroups() {
        return contactsGroups;
    }

    public void setContactsGroups(List<ContactsGroup> contactsGroups) {
        this.contactsGroups = contactsGroups;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", FullName='" + FullName + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles='" + roles + '\'' +
                ", permissions='" + permissions + '\'' +
                ", department=" + department +
                ", contacts=" + contacts +
                ", contactsGroups=" + contactsGroups +
                '}';
    }
}