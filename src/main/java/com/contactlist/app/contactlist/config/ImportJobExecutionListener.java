package com.contactlist.app.contactlist.config;

import com.contactlist.app.contactlist.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Listener component which listens to the batch import completion.
 */
@Component
@Slf4j
public class ImportJobExecutionListener extends JobExecutionListenerSupport {

    private final JdbcTemplate jdbcTemplate;

    @Autowired public ImportJobExecutionListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Import job completed!! Time to verify the results");

            List<Contact> contactList = jdbcTemplate.query("SELECT name, imgurl FROM contact",
                    (rs, row) -> new Contact(
                            rs.getString(1),
                            rs.getString(2))
            ).stream().collect(Collectors.toList());
            log.info("Number of records imported : {}", contactList.size());
        }
    }
}
