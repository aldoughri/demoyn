package com.example.demo.Controler;

import com.example.demo.Model.Contact;
import com.example.demo.Service.impl.ContactsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController

@RequestMapping("user/contacts")

public class UserContactsControler {
    @Autowired
    private ContactsServiceImpl contactServ;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/val", method = RequestMethod.GET)
    public ModelAndView ContactsVal(Model model) {
        model.addAttribute("contacts", contactServ.GetContactsValAdmin());
        return new ModelAndView("HomeContact","massage","Validation ");

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView AllContacts(Model model) {
        model.addAttribute("contacts", contactServ.GetContactsAdmin());
        return new ModelAndView("HomeContact","massage","All ");
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView Contacts(Model model) {
        model.addAttribute("contacts", contactServ.GetContactsEmployes());
        return new ModelAndView("HomeContact");
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public ModelAndView GetContacts(Model model) {
        model.addAttribute("contacts", contactServ.getContacts());
        return new ModelAndView("ContactHome","massage","My ");
    }



    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView ContactById(@PathVariable Long id, Model model) {
        Contact contact = contactServ.findByID(id);
        if (contact == null) {
            return new ModelAndView("HomeContact","massage",
                    "can't found");
        }

        model.addAttribute("contactId", contactServ.getContacts());
        return new ModelAndView("HomeContact").addObject("massage","get id: "+id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteContact(@PathVariable Long id) {
        Contact contact = contactServ.findByID(id);
        if (contact == null) {
            //return "redirect:/adress";
        } else {
            contactServ.deleteContact(id);
            return "redirect:/contacts/home";
        }
        return "";
    }

    @GetMapping("/add")
    public ModelAndView ContactPageAdd(Model model) {
        model.addAttribute("contact",new Contact().getOwner().getRole());
        return new ModelAndView("addContact");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/add/admin", method = RequestMethod.POST)
    public String createAdminContact(@RequestBody Contact contact) {
contactServ.AdminAddContact(contact);
        return "redirect:/contacts/home";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    public String createContact(@RequestBody Contact contact) {
        contactServ.addContact(contact);
        return "redirect:/contacts/home";
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateContactForm(@PathVariable Long id) {
        return new ModelAndView("edContact").addObject("contact",contactServ.findByID(id));
    }
    @PreAuthorize("HarRole('USER')")
    @RequestMapping(value = "/ed/user", method = RequestMethod.POST)
    public String updateContact(Contact contact) {
    contactServ.updateContact(contact.getId(),contact);
    return"redirect:/contacts/home" ; }
    @PreAuthorize("HarRole('ADMIN')")
    @RequestMapping(value = "/ed/admin", method = RequestMethod.POST)
    public String updateAdminContact(Contact contact) {
        contactServ.updateContact(contact.getId(),contact);
        return"redirect:/contacts/home" ; }
}
