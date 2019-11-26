package com.example.demo.configuration.JdbCSV;

import com.example.demo.Model.Contact;


import com.example.demo.configuration.JdbCSV.Process.ContactItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfigContact {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    public BatchConfigContact() {
    }

    
    public JdbcCursorItemReader<Contact> reader(){
        JdbcCursorItemReader<Contact> cursorItemReader = new JdbcCursorItemReader<>();
        cursorItemReader.setDataSource(dataSource);
        cursorItemReader.setSql("SELECT person_id,first_name,last_name,email,age FROM Contact");
        cursorItemReader.setRowMapper(new ContactRowMapper());
        return cursorItemReader;
    }

    
    private ContactItemProcessor processor(){
        return new ContactItemProcessor();
    }

    
    private FlatFileItemWriter<Contact> writer(){
        FlatFileItemWriter<Contact> writer = new FlatFileItemWriter<>();
        writer.setResource(new ClassPathResource("Contact.csv"));

        DelimitedLineAggregator<Contact> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<Contact> fieldExtractor = new BeanWrapperFieldExtractor<>();
        lineAggregator.setFieldExtractor(fieldExtractor);

        writer.setLineAggregator(lineAggregator);
        return writer;
    }

    
    private Step step1(){
        return stepBuilderFactory.get("step1").<Contact,Contact>chunk(100).reader(reader()).processor(processor()).writer(writer()).build();
    }

    
    private Job exportPerosnJob(){
        return jobBuilderFactory.get("exportPeronJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
    }
}
