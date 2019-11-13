package com.example.demo.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotNull
    String Name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    List<User> users;

    public Department() {
        Name = "CRM";
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", users=" + users +
                '}';
    }
}
