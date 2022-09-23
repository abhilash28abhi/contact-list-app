package com.contactlist.app.contactlist.service.impl;

import com.contactlist.app.contactlist.model.Contact;
import com.contactlist.app.contactlist.repository.ContactListRepository;
import com.contactlist.app.contactlist.service.IContactListManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactListManager implements IContactListManager {

    @Autowired
    private ContactListRepository contactListRepository;

    @Override public Page<Contact> getContactDetails(Pageable paging) {
        log.debug("Entered getContactDetails method with page : {} and size : {}", paging.getPageNumber(), paging.getPageSize());
        return contactListRepository.findAll(paging);
    }

    @Override public Page<Contact> getContactDetailsByName(String name, Pageable paging) {
        log.debug("Entered getContactDetailsByName method with search term : {} , page : {} and size : {}", name, paging.getPageNumber(), paging.getPageSize());
        return contactListRepository.findByNameContaining(name, paging);
    }
}
