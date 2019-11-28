package com.example.demo.configuration.JdbCSV;

import com.example.demo.Model.Contact;
import com.example.demo.Model.ContactMerge;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMergeRowMapper implements RowMapper<ContactMerge> {
    @Override
    public ContactMerge mapRow(ResultSet rs, int rowNum) throws SQLException {
        ContactMerge person = new ContactMerge();
        person.setId(rs.getLong("id"));
        return person;
    }    }
