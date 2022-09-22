package com.contactlist.app.contactlist.service;

import com.contactlist.app.contactlist.model.Contact;

import java.util.List;

public interface IContactListManager {

    List<Contact> getContactDetails(int page, int size);
}
