package com.example.demo.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ContactsGroup {
    @Id
    @GeneratedValue
    Long id;
    @NotNull
    String Name;
    @OneToMany(mappedBy = "contactsGroup", cascade = CascadeType.ALL)
    List<Contact> groupContacts;
    @ManyToOne
    @JoinColumn
    User user;

    public ContactsGroup() {
        Name = "Group " + id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Contact> getGroupContacts() {
        return groupContacts;
    }

    public void setGroupContacts(List<Contact> groupContacts) {
        this.groupContacts = groupContacts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ContactrGroup{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", groupContacts=" + groupContacts +
                ", user=" + user +
                '}';
    }
}
