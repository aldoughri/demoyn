package com.example.demo.configuration.JdbCSV;

import com.example.demo.Model.ContactMerge;
import com.example.demo.configuration.JdbCSV.Process.ContactMergeItemProcessor;
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
public class BatchConfigContactMerge {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    public BatchConfigContactMerge() {
    }

    public JdbcCursorItemReader<ContactMerge> reader(){
        JdbcCursorItemReader<ContactMerge> cursorItemReader = new JdbcCursorItemReader<>();
        cursorItemReader.setDataSource(dataSource);
        cursorItemReader.setSql("SELECT person_id,first_name,last_name,email,age FROM ContactMerge");
        cursorItemReader.setRowMapper(new ContactMergeRowMapper());
        return cursorItemReader;
    }


    public ContactMergeItemProcessor processor(){
        return new ContactMergeItemProcessor();
    }

    public FlatFileItemWriter<ContactMerge> writer(){
        FlatFileItemWriter<ContactMerge> writer = new FlatFileItemWriter<>();
        writer.setResource(new ClassPathResource("ContactMerge.csv"));

        DelimitedLineAggregator<ContactMerge> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<ContactMerge> fieldExtractor = new BeanWrapperFieldExtractor<>();
        lineAggregator.setFieldExtractor(fieldExtractor);

        writer.setLineAggregator(lineAggregator);
        return writer;
    }

    public Step step2(){
        return stepBuilderFactory.get("step2").<ContactMerge,ContactMerge>chunk(100).reader(reader()).processor(processor()).writer(writer()).build();
    }

    public Job exportPerosnJob(){
        return jobBuilderFactory.get("exportPeronJob").incrementer(new RunIdIncrementer()).flow(step2()).end().build();
    }
}
