package com.example.demo.configuration.JdbCSV;

import com.example.demo.Model.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

    public class ContactRowMapper implements RowMapper<Contact> {

        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
            Contact person = new Contact();
            person.setId(rs.getLong("id"));
            return person;
        }

    }

