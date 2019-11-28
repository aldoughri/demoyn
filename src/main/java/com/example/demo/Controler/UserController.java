package com.example.demo.Controler;


import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@PreAuthorize("has_Role('ADMIN')")
@RequestMapping(value = "/Admin/users")
public class UserController {
    @Autowired
    UserService userService;



    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView AllContacts(Model model) {
        model.addAttribute("contacts", userService.GetContactsAdmin());
        return new ModelAndView("HomeContact", "massage", "All ");
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView users() {

   /*     return new ModelAndView("UserPage", "users", userService.users())
                .addObject("massage", "get all users");
   */
        userService.users();
        return new ModelAndView("UserHome",
                "users", userService.users());
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView userById(@PathVariable Long id) {
        return new ModelAndView("AdminDashboard", "userId",
                userService.finduser(id))
                .addObject("massage", "geting user: " + id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Long id) {
        User appUser = userService.finduser(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        userService.deleteUser(id);
        return new ModelAndView("redirect:/", "massage",
                "deleted user completed " + id);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView createUser(@RequestBody User user) {
        if (userService.findUsername(user.getUsername()) != null) {
            return new ModelAndView("redirect:/add", "massage",
                    "Username already exist");
        }
        userService.addUser(user);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/edit/user/{id}", method = RequestMethod.GET)
    public ModelAndView updateUser(@RequestBody User appUser) {

        userService.addUser(appUser);
        return new ModelAndView("UserForm", "user", userService.finduser(appUser.getId()));
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView createUser() {
        return new ModelAndView("NewUser", "user", new User());
    }
}