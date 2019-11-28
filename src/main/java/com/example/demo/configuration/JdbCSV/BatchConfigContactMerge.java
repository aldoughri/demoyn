package com.example.demo.configuration.JdbCSV;

import com.example.demo.Listener.Listener;
import com.example.demo.Model.ContactMerge;
import com.example.demo.configuration.JdbCSV.Process.ContactMergeItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
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
public class BatchConfigContactMerge {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    public BatchConfigContactMerge() {
    }
    @Bean( "bb")
    public JdbcCursorItemReader<ContactMerge> reader(){
        JdbcCursorItemReader<ContactMerge> cursorItemReader = new JdbcCursorItemReader<>();
        cursorItemReader.setDataSource(dataSource);
        cursorItemReader.setSql("SELECT id, adders, city, phone_nummber, contact_id FROM contact_merge WHERE 1");
        cursorItemReader.setRowMapper(new ContactMergeRowMapper());
        return cursorItemReader;
    }

    @Bean( "bb")
    public ContactMergeItemProcessor processor(){
        return new ContactMergeItemProcessor();
    }
    @Bean( "bb")
    public FlatFileItemWriter<ContactMerge> writer(){
        FlatFileItemWriter<ContactMerge> writer = new FlatFileItemWriter<>();
        writer.setResource(new ClassPathResource("CSVFiels\\ContactMergeFile.csv"));

        DelimitedLineAggregator<ContactMerge> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<ContactMerge> fieldExtractor = new BeanWrapperFieldExtractor<>();
       lineAggregator.setFieldExtractor(fieldExtractor);

        writer.setLineAggregator(lineAggregator);
        return writer;
    }
    @Bean("bb")
    public Step step2(){
        return stepBuilderFactory.get("step2").<ContactMerge,ContactMerge>chunk(100).reader(reader()).processor(processor()).writer(writer()).build();
    }
    @Bean("bb")
    public Job exportPerosnJob(){
        return jobBuilderFactory.get("exportPeronJob").incrementer(new RunIdIncrementer())/*.listener(listener())*/.flow(step2()).end().build();
    }
    @Bean
    public JobExecutionListener listener() {
        return new Listener();
    }
}
