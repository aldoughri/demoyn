package com.example.demo.Service.impl;
//// find by department
//// find admin accept

import com.example.demo.Model.Contact;
import com.example.demo.Repository.ContactRepisotory;
import com.example.demo.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactsServiceImpl implements ContactService {
    @Autowired
    ContactRepisotory contactRepl;

    @Transactional
    @Override
    public void merge(List<Contact> contacts, Contact contact) {
        //after select all
        contacts.forEach(con -> contact.getContactMerges().addAll(con.getContactMerges()));
        contactRepl.deleteAll(contacts);
        contactRepl.save(contact);
    }

    @Secured({"ROLE_ADMIN"})
    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();

        contactRepl.findAll().forEach(contact -> contacts.add(contact));
        return contacts;
    }

    public void addContact(Contact contact) {
        contact.setCheckVal(false);
        contactRepl.save(contact);
    }

    @Secured({"ROLE_ADMIN"})
    public void AdminAddContact(Contact contact) {
        contact.setCheckVal(true);
        contactRepl.save(contact);
    }

   /* @Secured({"ROLE_ADMIN"})
    public void AdminaddContact(boolean added, Long id) {
        contactRepl.add(added, id);
    }
*/
    public void updateContact(Long id, Contact contact) {
     contact.setCheckVal(false);
        contactRepl.update(contact, id);
    }

    public void updateAdminContact(Long id, Contact contact) {
        contact.setCheckVal(true);
        contactRepl.update(contact, id);
    }

    @Transactional
    public void deleteContact(Long id) {
        contactRepl.deleteById(id);
    }


    public Contact findByID(Long id) {
        return contactRepl.findById(id).get();
    }


    /*public List<Contact> findBySelected(boolean selectCheck) {
        return contactRepl.findByselectCheck(selectCheck);
    }
*/
    public List<Contact> findByLastName(String lastName) {
        return contactRepl.findBylastName(lastName);
    }

    public List<Contact> GetContactsEmployes() {
        return contactRepl.GetContactsEmployes();
    }

    public List<Contact> GetContactsValAdmin() {
        return contactRepl.GetContactsValAdmin();
    }

    public List<Contact> GetContactsAdmin() {
        return contactRepl.GetContactsAdmin();
    }

}
