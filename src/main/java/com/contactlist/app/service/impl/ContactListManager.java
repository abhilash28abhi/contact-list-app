package com.contactlist.app.service.impl;

import com.contactlist.app.api.ContactListResponse;
import com.contactlist.app.model.Contact;
import com.contactlist.app.repository.ContactListRepository;
import com.contactlist.app.service.IContactListManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactListManager implements IContactListManager {

    @Autowired
    private ContactListRepository contactListRepository;

    @Override public ContactListResponse getContactDetails(Pageable paging) {
        log.debug("Entered getContactDetails method with page : {} and size : {}", paging.getPageNumber(), paging.getPageSize());
        Page<Contact> pageResults = contactListRepository.findAll(paging);
        ContactListResponse contactListResponse = new ContactListResponse();
        contactListResponse.setContacts(pageResults.getContent());
        contactListResponse.setCurrentPage(pageResults.getNumber());
        contactListResponse.setTotalElements(pageResults.getTotalElements());
        contactListResponse.setTotalPages(pageResults.getTotalPages());
        return contactListResponse;
    }

    @Override public ContactListResponse getContactDetailsByName(String name, Pageable paging) {
        log.debug("Entered getContactDetailsByName method with search term : {} , page : {} and size : {}", name, paging.getPageNumber(), paging.getPageSize());
        Page<Contact> pageResults =  contactListRepository.findByNameContaining(name, paging);
        ContactListResponse contactListResponse = new ContactListResponse();
        contactListResponse.setContacts(pageResults.getContent());
        contactListResponse.setCurrentPage(pageResults.getNumber());
        contactListResponse.setTotalElements(pageResults.getTotalElements());
        contactListResponse.setTotalPages(pageResults.getTotalPages());
        return contactListResponse;
    }
}
