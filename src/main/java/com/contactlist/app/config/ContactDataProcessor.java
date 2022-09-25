package com.contactlist.app.config;

import com.contactlist.app.model.Person;
import com.contactlist.app.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

/**
 * Class which does batching processing of the input CSV file data.
 * The process method is called for each line of the CSV file
 */
@Slf4j
public class ContactDataProcessor implements ItemProcessor<Person, Contact> {

    @Override
    public Contact process(Person item) {
        Contact contact = new Contact(item.getName(), item.getUrl());
        return contact;
    }
}
