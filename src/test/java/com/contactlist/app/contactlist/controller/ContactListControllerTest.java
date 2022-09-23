package com.contactlist.app.contactlist.controller;

import com.contactlist.app.contactlist.api.ContactListResponse;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactListControllerTest {

    @Mock
    private ContactListManager manager;

    @InjectMocks
    private ContactListController contactListController;

    @Test
    public void getContactDetails_success () throws Exception {
        List<Contact> contactList = new ArrayList<>();
        Contact contact = new Contact("Spring Boot @WebMvcTest", "Description");
        contactList.add(contact);
        ContactListResponse response = new ContactListResponse();
        response.setContacts(contactList);

        doReturn(response).when(manager).getContactDetails(any());
        when(manager.getContactDetails(PageRequest.of(0,5))).thenReturn(response);
        ResponseEntity<?> resp = contactListController.getContactDetails("",0, 2);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }
}
