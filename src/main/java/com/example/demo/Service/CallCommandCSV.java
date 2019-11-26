package com.example.demo.Service;

import com.example.demo.Model.Contact;
import com.example.demo.Model.ContactMerge;
import com.example.demo.configuration.JdbCSV.BatchConfigContact;
import com.example.demo.configuration.JdbCSV.BatchConfigContactMerge;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.stereotype.Service;

@Service
public class CallCommandCSV {

    public void csvBatch(){
        JdbcCursorItemReader<ContactMerge> batchConfigContactMerge=new BatchConfigContactMerge().reader();
        JdbcCursorItemReader<Contact> batchConfigContact=new BatchConfigContact().reader();

    }
}
