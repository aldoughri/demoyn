package com.example.demo.Service;

import com.example.demo.Model.Contact;
import com.example.demo.Model.User;
import com.example.demo.configuration.Securty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRep;

    @Secured({"ROLE_ADMIN"})
    public List<User> users() {
        List<User> users = new ArrayList<>();

        userRep.findAll().forEach(user -> users.add(user));
        return users;
    }

    public User findUsername(String username) {
        return userRep.findByUsername(username).get();
    }

    @Secured({"ROLE_ADMIN"})
    public void addUser(User user) {

        userRep.save(user);
    }

    public void RegisterUser(User user) {
        user.setRoles("USER");

        userRep.save(user);
    }

    /* public void updatePassword(String email, User user) {
         userRep.save(user);
     }
 */
    public User finduser(Long id) {
        return userRep.findById(id).get();
    }

    public void deleteUser(Long id) {
        userRep.deleteById(id);
    }
    @Secured({"ROLE_ADMIN"})
    public List<Contact> GetContactsAdmin() {
        List<Contact> list=new ArrayList<>();
         userRep.findAll().forEach(u->list.addAll(u.getContacts()));
         return list;
    }

}
