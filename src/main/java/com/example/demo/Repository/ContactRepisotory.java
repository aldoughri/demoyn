package com.example.demo.Repository;

import com.example.demo.Model.Contact;
import com.example.demo.Model.Department;
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

    List<Contact> findByDepartment(Department department);

    @Transactional
    @Modifying
    @Query("Update Contact c Set c=:uContact Where c.id=:id")
     void update (@Param("uContact") Contact contact,@Param("id") Long id  );
    @Transactional
    @Modifying
    @Query("Update Contact c Set c.CheckRole=:CheckRole Where c.id=:id")
    void add (@Param("CheckRole")boolean CheckRole,@Param("id") Long id  );
}
