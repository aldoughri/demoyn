package com.example.demo.configuration.JdbCSV.Process;

import com.example.demo.Model.ContactMerge;
import org.springframework.batch.item.ItemProcessor;

public class ContactMergeItemProcessor implements ItemProcessor<ContactMerge,ContactMerge> {

    @Override
    public ContactMerge process(ContactMerge contactMerge) throws Exception {
        return contactMerge;
    }
}
