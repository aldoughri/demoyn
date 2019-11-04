package com.example.demo.Service;
//// find by department
//// find admin accept

import com.example.demo.Model.Contact;
import com.example.demo.Model.Department;
import com.example.demo.Model.User;
import com.example.demo.Repository.ContactRepisotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactsService {
    @Autowired
    ContactRepisotory contactRepl;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNummber;
    private String city;
    private String company;
    private String job;
    private String organization;
    private String adders;
    private String placebo;
    @Secured({"ROLE_ADMIN"})
    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();

        contactRepl.findAll().forEach(contact -> contacts.add(contact));
        return contacts;
    }
    public void addContact(Contact contact) {
        contactRepl.save(contact);
    }
    @Secured({"ROLE_ADMIN"})
    public void AdminaddContact(boolean added,Long id){
        contactRepl.add(added,id);
    }
    public void updateContact(Long id, Contact contact) {
        contactRepl.update(contact,id);
    }
    @Transactional
    public void deleteContact(Long id) {
        contactRepl.deleteById(id);
    }


    public Contact findByID(Long id) {
        return contactRepl.findById(id).get();
    }


    public List<Contact> findBySelected(boolean selectCheck) {
        return contactRepl.findByselectCheck(selectCheck);
    }

    public List<Contact> findByLastName(String lastName) {
        return contactRepl.findBylastName(lastName);
    }

    public List<Contact> findByDepartment(Department department) {
        return contactRepl.findByDepartment(department);
    }



    public void marge(String lastName){

    List<Contact> contacts1= contactRepl.findByselectCheck(true);
    String firstName = "";

    String email="";
    String phoneNummber = "";
    String city="";
    String company="";
    String job="";
    String organization="";
    String adders="";
    String placebo="";
    User user = null;


    for (Contact contacct:contacts1){
         email+=contacct.getEmail()+"\n";
         phoneNummber+=contacct.getPhoneNummber()+"\n";
         city+=contacct.getCity()+"\n";
         company+=contacct.getCompany()+"\n";
         adders+=contacct.getAdders()+"\n";
         user=contacct.getOwner();
    }
   // Contact contact=new Contact("",lastName,email,phoneNummber,city,company,job,organization,adders,placebo,false,user);
   Contact contact =new Contact();/**********/
    contactRepl.save(contact);
    contactRepl.deleteAll(contacts1);
    }
}
