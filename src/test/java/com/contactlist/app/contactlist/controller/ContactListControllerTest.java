package com.contactlist.app.contactlist.controller;

import com.contactlist.app.contactlist.api.ContactListResponse;
import com.contactlist.app.contactlist.exception.DataViolationException;
import com.contactlist.app.contactlist.model.Contact;
import com.contactlist.app.contactlist.service.impl.ContactListManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ContactListControllerTest {

    @Mock
    private ContactListManager manager;

    @InjectMocks
    private ContactListController contactListController;

    @Test
    public void getContactDetails_success () throws Exception {
        List<Contact> contactList = new ArrayList<>();
        Contact contact = new Contact("James David", "Url1");
        contactList.add(contact);
        ContactListResponse response = new ContactListResponse();
        response.setContacts(contactList);

        when(manager.getContactDetails(any())).thenReturn(response);
        ResponseEntity<?> resp = contactListController.getContactDetails("",0, 2);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        ContactListResponse respBody = (ContactListResponse) resp.getBody();
        assertEquals("Url1", respBody.getContacts().get(0).getUrl());
    }

    @Test
    public void getContactDetails_empty_response () throws Exception {
        List<Contact> contactList = new ArrayList<>();
        ContactListResponse response = new ContactListResponse();
        response.setContacts(contactList);

        lenient().when(manager.getContactDetails(any())).thenReturn(response);
        ResponseEntity<?> resp = contactListController.getContactDetails("David",0, 5);
        assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());
    }

    @Test
    public void getContactDetailsByName_success () throws Exception {
        List<Contact> contactList = new ArrayList<>();
        Contact contact = new Contact("James David", "Url1");
        contactList.add(contact);
        ContactListResponse response = new ContactListResponse();
        response.setContacts(contactList);

        when(manager.getContactDetailsByName(anyString(), any())).thenReturn(response);
        ResponseEntity<?> resp = contactListController.getContactDetails("David",0, 5);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        ContactListResponse respBody = (ContactListResponse) resp.getBody();
        assertEquals("James David", respBody.getContacts().get(0).getName());
    }

    @Test
    public void getContactDetailsByName_invalid_page () throws Exception {
        lenient().doThrow(new DataViolationException()).when(manager).getContactDetailsByName(anyString(), any());
        try {
            contactListController.getContactDetails("David",-2, 5);
        } catch (Exception e) {
            assertTrue(e instanceof DataViolationException);
        }
    }
}
