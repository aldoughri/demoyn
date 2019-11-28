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
import org.springframework.context.annotation.Bean;
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

    @Bean("b")
    public JdbcCursorItemReader<Contact> reader(){
        JdbcCursorItemReader<Contact> cursorItemReader = new JdbcCursorItemReader<>();
        cursorItemReader.setDataSource(dataSource);
        cursorItemReader.setSql("SELECT id, check_val, full_name, company, email, first_name, last_name, select_check, contacts_group_id, owner_id FROM `contact` WHERE 1");
        cursorItemReader.setRowMapper(new ContactRowMapper());
        return cursorItemReader;
    }

    @Bean( "b")
    public ContactItemProcessor processor(){
        return new ContactItemProcessor();
    }

    @Bean( "b")
    public FlatFileItemWriter<Contact> writer(){
        FlatFileItemWriter<Contact> writer = new FlatFileItemWriter<>();
        writer.setResource(new ClassPathResource("ContactFile.csv"));

        DelimitedLineAggregator<Contact> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<Contact> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"personId","firstName","lastName","email","age"});
        lineAggregator.setFieldExtractor(fieldExtractor);

        writer.setLineAggregator(lineAggregator);
        return writer;
    }

    @Bean( "b")
    public Step step1(){
        return stepBuilderFactory.get("step1").<Contact,Contact>chunk(100).reader(reader()).processor(processor()).writer(writer()).build();
    }

    @Bean( "b")
    public Job exportPerosnJob(){
        return jobBuilderFactory.get("exportPeronJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
    }
}
