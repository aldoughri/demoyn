package com.example.demo.configuration.JdbCSV.Process;

import com.example.demo.Model.Contact;
import org.springframework.batch.item.ItemProcessor;

public class ContactItemProcessor implements ItemProcessor<Contact,Contact> {

    @Override
    public Contact process(Contact contact) throws Exception {
        return contact;
    }


}
