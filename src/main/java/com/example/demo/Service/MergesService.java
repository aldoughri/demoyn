package com.example.demo.Service;

import com.example.demo.Model.ContactMerge;
import com.example.demo.Model.ContactsGroup;
import com.example.demo.Repository.MergeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MergesService {
    @Autowired
    MergeDAO mergeDAO;
    public List<ContactMerge> getMerges() {
        List<ContactMerge> merges=new ArrayList<>();
        mergeDAO.findAll().forEach(merges::add);
        return merges;
    }

    public ContactMerge getId(Long id) {
    return mergeDAO.findById(id).get();
    }

    public void add(ContactMerge contactMerge) {
    mergeDAO.save(contactMerge);
    }

    public void remove(Long id) {
    mergeDAO.deleteById(id);
    }
}
