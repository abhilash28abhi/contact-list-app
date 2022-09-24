package com.contactlist.app.contactlist.utils;

import com.contactlist.app.contactlist.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static List<Contact> getMockContactList () {
        List<Contact> contactList = new ArrayList<>();
        Contact contact1 = new Contact("James David", "Url1");
        Contact contact2 = new Contact("Adam Scott", "Url2");
        Contact contact3 = new Contact("Rajesh Kumar", "Url3");
        contactList.add(contact1);
        contactList.add(contact2);
        contactList.add(contact3);
        return contactList;
    }
}
