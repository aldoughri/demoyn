package com.example.demo.Controler;


import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@RestController
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    UserService userService1;

    @GetMapping(value = "/register")
    public ModelAndView createUser() {
        return new ModelAndView("registry",
                "appUser", new User());
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createUser(@Autowired User appUser) {

        if (userService1.findUsername(appUser.getUsername()) != null) {
            return new ModelAndView("redirect:/register","massage",
                    "user is already registered");
        }
        userService1.RegisterUser(appUser);
        return new ModelAndView("redirect/");
    }

    @GetMapping(value = "/login")
    public ModelAndView logUser(Model model) {
        return new ModelAndView("loginForm", "appUser", new User());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginUser(User appUser, Model model) {
        if (userService1.findUsername(appUser.getUsername()) == null) {
            return new ModelAndView("redirect:/login","massage","Account dos'nt exist ");

        }

        return new ModelAndView("redirect/");
    }
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Principal user(Principal principal) {
        return principal;
    }


}
