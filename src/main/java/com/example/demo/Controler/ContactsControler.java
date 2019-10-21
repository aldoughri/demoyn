package com.example.demo.Controler;

import com.example.demo.Model.Contact;
import com.example.demo.Service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/contacts")
public class ContactsControler {
    @Autowired
    private ContactsService contactServ;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView GetContacts(Model model) {
       model.addAttribute("contacts",contactServ.getContacts()) ;
    return new ModelAndView("HomeContact");
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView ContactById(@PathVariable Long id,Model model) {
        Contact contact = contactServ.findByID(id);
        if (contact == null) {

        }  model.addAttribute("contacts",contactServ.getContacts()) ;
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
