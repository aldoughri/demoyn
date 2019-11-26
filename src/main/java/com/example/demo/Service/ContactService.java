package com.example.demo.Service;

import com.example.demo.Model.Contact;

import java.util.List;

public interface ContactService {
    void merge(List<Contact> contacts, Contact contact);
}
