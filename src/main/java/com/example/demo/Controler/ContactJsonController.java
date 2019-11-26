package com.example.demo.Controler;

import com.example.demo.Model.Contact;
import com.example.demo.Service.impl.ContactsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/contacts")
public class ContactJsonController {
    @Autowired
    private ContactsServiceImpl contactServ;

    @RequestMapping(value = "/my", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Contact> GetContactsJson(Model model) {

        return contactServ.getContacts();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Contact> ContactByIdJson(@PathVariable Long id, Model model) {
        Contact contact = contactServ.findByID(id);
        return contactServ.getContacts();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public void deleteContactJson(@PathVariable Long id) {
        Contact contact = contactServ.findByID(id);
        contactServ.deleteContact(id);
        ;
    }

    @RequestMapping(value = "/add/user", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Contact createContactJson(@RequestBody Contact contact) {
        return contactServ.addContact(contact);

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public Contact updateContactJson(@PathVariable Long id, Contact contact) {
        return contactServ.updateContact(id, contact);
    }

    @RequestMapping(value = "/ed/user", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Contact updateContactJson(Contact contact) {
        System.out.println();
        return contactServ.updateContact(contact.getId(), contact);
    }
}
