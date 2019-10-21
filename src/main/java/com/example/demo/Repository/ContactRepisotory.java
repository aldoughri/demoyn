package com.example.demo.Repository;

import com.example.demo.Model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepisotory extends CrudRepository<Contact, Long> {
    List<Contact> findBylastName(String lastName);

    List<Contact> findByCity(String city);

    List<Contact> findByselectCheck(boolean selectCheck);

    List<Contact> findByplacebo(String placebo);

     List<Contact> findByCompany(String Company);
}
