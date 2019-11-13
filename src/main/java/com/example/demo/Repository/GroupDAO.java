package com.example.demo.Repository;

import com.example.demo.Model.ContactsGroup;
import org.springframework.data.repository.CrudRepository;

public interface GroupDAO extends CrudRepository<ContactsGroup, Long> {
}
