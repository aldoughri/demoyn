package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRep;

    @Secured({"ROLE_ADMIN"})
    public List<User> users() {
        List<User> users = new ArrayList<>();

        userRep.findAll().forEach(user -> users.add(user));
        return users;
    }

    public User findUsername(String email) {
        return userRep.findByEmail(email);
    }

    @Secured({"ROLE_ADMIN"})
    public void addUser(User user) {

            userRep.save(user);
        }
    public void RegisterUser(User user) {
        user.setRole("USER");

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userRep.findByEmail(username);
        return appUser;
    }
}
