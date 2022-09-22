package com.contactlist.app.contactlist.service;

import com.contactlist.app.contactlist.model.Contact;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IContactListManager {

    Page<Contact> getContactDetails(int page, int size);
}
