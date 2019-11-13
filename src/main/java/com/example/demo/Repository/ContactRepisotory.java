package com.example.demo.Repository;

import com.example.demo.Model.Contact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContactRepisotory extends CrudRepository<Contact, Long> {
    List<Contact> findBylastName(String lastName);


    List<Contact> findByselectCheck(boolean selectCheck);

    @Query("Select c,m From Contact c,ContactMerge m")
    List<Contact> GetContactsAdmin();

    @Query("Select c,m  From User u,Contact c,ContactMerge m, Department d Where u.department =d.id AND c.CheckVal=true")
    List<Contact> GetContactsEmployes();

    /******/
    @Query("Select c  From User,Contact c,ContactMerge m Where c.CheckVal=false ")
    List<Contact> GetContactsValAdmin();


    @Transactional
    @Modifying
    @Query("Update Contact c Set c=:uContact Where c.id=:id")
    void update(@Param("uContact") Contact contact, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("Update Contact c Set c.CheckVal=:CheckVal Where c.id=:id")
    void add(@Param("CheckVal") boolean CheckRole, @Param("id") Long id);
}
