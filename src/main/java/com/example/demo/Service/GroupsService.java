package com.example.demo.Service;

import com.example.demo.Model.ContactsGroup;
import com.example.demo.Repository.GroupDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupsService {
    @Autowired
    GroupDAO groupDAO;

    public void remove(Long id) {
        groupDAO.deleteById(id);
    }

    public void add(ContactsGroup contactsGroup) {
        groupDAO.save(contactsGroup);
    }

    public ContactsGroup getId(Long id) {
        return groupDAO.findById(id).get();
    }

    public List<ContactsGroup> getGroups() {
        List<ContactsGroup> groups = new ArrayList<>();
        groupDAO.findAll().forEach(groups::add);
        return groups;
    }
}
