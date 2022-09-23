package com.contactlist.app.contactlist.service;

import com.contactlist.app.contactlist.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IContactListManager {

    Page<Contact> getContactDetails(Pageable paging);

    Page<Contact> getContactDetailsByName(String name, Pageable paging);
}
