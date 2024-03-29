package com.example.demo.Repository;

import com.example.demo.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContactRepisotory extends JpaRepository<Contact, Long> {
    List<Contact> findBylastName(String lastName);


    List<Contact> findByselectCheck(boolean selectCheck);



    @Transactional
    @Modifying
    @Query("Update Contact c Set c=:uContact Where c.id=:id")
    void update(@Param("uContact") Contact contact, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("Update Contact c Set c.CheckVal=:CheckVal Where c.id=:id")
    void add(@Param("CheckVal") boolean CheckRole, @Param("id") Long id);
}
