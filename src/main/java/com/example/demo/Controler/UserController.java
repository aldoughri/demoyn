package com.example.demo.Controler;


import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> users() {

   /*     return new ModelAndView("UserPage", "users", userService.users())
                .addObject("massage", "get all users");
   */
        return userService.users();
    }


    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ModelAndView userById(@PathVariable Long id) {
        User appUser = null;//findone
        if (appUser == null) {
            return new ModelAndView("NoConnect", "NO_CONTENT", new ResponseEntity<User>(HttpStatus.NO_CONTENT));
        } else {
            return new ModelAndView("UserPage", "users", userService.finduser(id))
                    .addObject("massage", "get user " + id);
        }
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable Long id) {
        User appUser = userService.finduser(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        if (appUser == null) {
            return new ModelAndView("NoConnect", "NO_CONTENT", new ResponseEntity<User>(HttpStatus.NO_CONTENT));
        } else if (appUser.getUsername().equalsIgnoreCase(loggedUsername)) {
            throw new RuntimeException("You cannot delete your account");
        } else {
            userService.deleteUser(id);
            return new ModelAndView("UserPage", "users", userService.users())
                    .addObject("massage", "deleted user " + appUser);

        }

    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String createUser(@RequestBody User user) {
        if (userService.findUsername(user.getUsername()) != null) {
            return "Username already exist";
        }
        userService.addUser(user);
        return "redirect:/ll";
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User appUser) {
        if (userService.findUsername(appUser.getUsername()) != null
                && userService.findUsername(appUser.getUsername()).getId() != appUser.getId()) {
            throw new RuntimeException("Username already exist");
        }
        userService.addUser(appUser);
        return userService.finduser(appUser.getId());
    }

}