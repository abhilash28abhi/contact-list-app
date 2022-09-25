package com.contactlist.app.repository;

import com.contactlist.app.model.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactListRepositoryTest {

    @Autowired
    private ContactListRepository contactListRepository;

    @Test
    public void findNoContactsIfRepositoryIsEmpty () {
        List<Contact> contacts = contactListRepository.findAll();
        assertEquals(0, contacts.size());
    }

    @Test
    public void findContactsByName_success() {
        Contact contact1 = new Contact("Contact A#1", "Url#1");
        Contact contact2 = new Contact("Contact B#2", "Url#2");
        Contact contact3 = new Contact("Contact ABC#3", "Url#3");
        contactListRepository.save(contact1);
        contactListRepository.save(contact2);
        contactListRepository.save(contact3);
        Pageable page = PageRequest.of(0,5);
        Page<Contact> contacts = contactListRepository.findByNameContaining("B",page);
        assertEquals(2, contacts.getContent().size());
    }

    @Test
    public void findAllContacts_success() {
        Contact contact1 = new Contact("Contact A#1", "Url#1");
        Contact contact2 = new Contact("Contact B#2", "Url#2");
        Contact contact3 = new Contact("Contact ABC#3", "Url#3");
        contactListRepository.save(contact1);
        contactListRepository.save(contact2);
        contactListRepository.save(contact3);
        List<Contact> contacts = contactListRepository.findAll();
        assertEquals(3, contacts.size());
    }
}
