package com.contactlist.app.contactlist.service;

import com.contactlist.app.contactlist.api.ContactListResponse;
import com.contactlist.app.contactlist.model.Contact;
import com.contactlist.app.contactlist.repository.ContactListRepository;
import com.contactlist.app.contactlist.service.impl.ContactListManager;
import com.contactlist.app.contactlist.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactListManagerTest {

    @InjectMocks
    private ContactListManager contactListManager;

    @Mock
    private ContactListRepository contactListRepository;

    @Test
    public void getContactDetails_success () {
        Page<Contact> pageResults = new PageImpl<>(TestUtils.getMockContactList());
        when(contactListRepository.findAll(isA(Pageable.class))).thenReturn(pageResults);
        ContactListResponse resp = contactListManager.getContactDetails(PageRequest.of(0,5));
        assertNotNull(resp);
        assertEquals("James David", resp.getContacts().get(0).getName());
        assertEquals(3, resp.getContacts().size());
    }

    @Test
    public void getContactDetailsByName_success () {
        List<Contact> mockList = TestUtils.getMockContactList();
        mockList.remove(0);
        mockList.remove(1);
        Page<Contact> pageResults = new PageImpl<>(mockList);
        when(contactListRepository.findByNameContaining(anyString(), any())).thenReturn(pageResults);
        ContactListResponse resp = contactListManager.getContactDetailsByName("Adam",PageRequest.of(0,5));
        assertNotNull(resp);
        assertEquals("Adam Scott", resp.getContacts().get(0).getName());
        assertEquals(1, resp.getContacts().size());
    }
}
