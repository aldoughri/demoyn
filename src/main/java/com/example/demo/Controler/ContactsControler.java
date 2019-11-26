package com.example.demo.Controler;

import com.example.demo.Model.Contact;
import com.example.demo.Service.impl.ContactsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController

@RequestMapping("user/contacts")

public class ContactsControler {
    @Autowired
    private ContactsServiceImpl contactServ;
    Logger logger = LoggerFactory.getLogger(ContactsControler.class);
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/val", method = RequestMethod.GET)
    public ModelAndView ContactsVal(Model model) {
        model.addAttribute("contacts", contactServ.GetContactsValAdmin());
        return new ModelAndView("HomeContact", "massage", "Validation ");

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView AllContacts(Model model) {
        model.addAttribute("contacts", contactServ.GetContactsAdmin());
        return new ModelAndView("HomeContact", "massage", "All ");
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView Contacts(Model model) {
        model.addAttribute("contacts", contactServ.GetContactsEmployes());
        return new ModelAndView("HomeContact");
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public ModelAndView GetContacts(Model model) {
        model.addAttribute("contacts", contactServ.getContacts());
        return new ModelAndView("ContactHome", "massage", "My ");
    }


    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView ContactById(@PathVariable Long id, Model model) {
        Contact contact = contactServ.findByID(id);
        if (contact == null) {
            return new ModelAndView("HomeContact", "massage",
                    "can't found");
        }

        model.addAttribute("contactId", contactServ.getContacts());
        return new ModelAndView("HomeContact").addObject("massage", "get id: " + id);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteContact(@PathVariable Long id) {
        Contact contact = contactServ.findByID(id);
        if (contact == null) {
            //return "redirect:/adress";
        } else {
            System.out.println("Deleted: "+contactServ.findByID(id).toString());
            contactServ.deleteContact(id);
            return "redirect:/contacts/home";
        }
        return "";
    }

    @GetMapping("/add")
    public ModelAndView ContactPageAdd(Model model) {
        model.addAttribute("contact", new Contact().getOwner().getRoles());
        return new ModelAndView("addContact");
    }


    /*   @PreAuthorize("hasRole('USER')")*/
    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    public String createContact(@RequestBody Contact contact) {
        contactServ.addContact(contact);
        System.out.println("Added: "+ contactServ.findByID(contact.getId()).toString());
        return "redirect:/contacts/home";
    }
    /* @PreAuthorize("hasRole('USER')")
     */


    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateContactForm(@PathVariable Long id) {
        return new ModelAndView("edContact").addObject("contact", contactServ.findByID(id));
    }


    /*@PreAuthorize("hasRole('USER')")*/
    @RequestMapping(value = "/ed/user", method = RequestMethod.POST)
    public String updateContact(Contact contact) {
        contactServ.updateContact(contact.getId(), contact);
        System.out.println("updated: "+ contactServ.findByID(contact.getId()).toString());
        return "redirect:/contacts/home";
    }


    @RequestMapping(value = "/merge", method = RequestMethod.POST)
    public String updateContactMerg(Contact contact) {
        Contact contact1 = new Contact();
        contactServ.addContact(contact);
        contactServ.updateContact(contact1.getId(), contact);
        return "redirect:/contacts/home";
    }
}
