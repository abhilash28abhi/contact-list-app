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

    @Override public Page<Contact> getContactDetails(int page, int size) {
        log.debug("Entered getContactDetails method with page : {} and size : {}", page, size);
        Pageable pageRequest = PageRequest.of(page, size);
        return contactListRepository.findAll(pageRequest);
    }
}
