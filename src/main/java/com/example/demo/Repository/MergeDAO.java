package com.example.demo.Repository;

import com.example.demo.Model.ContactMerge;
import org.springframework.data.repository.CrudRepository;

public interface MergeDAO extends CrudRepository<ContactMerge,Long> {
}
