package com.example.demo.Controler;

import com.example.demo.Model.Contact;
import com.example.demo.Service.impl.ContactsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("Admin/contacts")
public class AdminContactsControler {
    @Autowired
    ContactsServiceImpl contactServ;
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView GetContacts(Model model) {
       model.addAttribute("contacts",contactServ.getContacts()) ;
    return new ModelAndView("HomeContact");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView Contacts(Model model) {
        model.addAttribute("contacts",contactServ.GetContactsAdmin()) ;
        return new ModelAndView("HomeContact");
    }

    @RequestMapping(value = "/val", method = RequestMethod.GET)
    public ModelAndView ContactsVal(Model model) {
        model.addAttribute("contacts",contactServ.GetContactsValAdmin()) ;
        return new ModelAndView("HomeContact");
    }
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView ContactById(@PathVariable Long id,Model model) {
        Contact contact = contactServ.findByID(id);
        if (contact == null) {
        return new ModelAndView("redirect:/home");
        }  if (!contact.isCheckRole())
        {
            return new ModelAndView("redirect:/home");
        }
        model.addAttribute("contacts",contactServ.getContacts()) ;
        return new ModelAndView("HomeContact");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteContact(@PathVariable Long id) {
        Contact contact = contactServ.findByID(id);
        if (contact == null) {
            //return "redirect:/adress";
        } else {
            contactServ.deleteContact(id);
            return "redirect:/contacts/home"; }
return "";
    }

@GetMapping("/add")
    public ModelAndView ContactPageAdd(Model model) {

        return new ModelAndView("addContact");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createContact(@RequestBody Contact contact) {
        contactServ.addContact(contact);
        return "redirect:/contacts/home";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updateContact(@RequestBody Contact contact) {
        return "redirect:/adress";
    }
}
