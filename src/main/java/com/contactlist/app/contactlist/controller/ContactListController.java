package com.contactlist.app.contactlist.controller;

import com.contactlist.app.contactlist.api.ContactListResponse;
import com.contactlist.app.contactlist.model.Contact;
import com.contactlist.app.contactlist.service.IContactListManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/contacts")
@Slf4j
public class ContactListController {

    @Autowired private IContactListManager contactListManager;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContactDetails(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int pageSize) throws Exception {
        log.debug("Invoked getContactDetails method with page : {} and pageSize : {}", page, pageSize);
        Page<Contact> pageResults = contactListManager.getContactDetails(page, pageSize);
        if (pageResults.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ContactListResponse contactListResponse = new ContactListResponse();
        contactListResponse.setContacts(pageResults.getContent());
        contactListResponse.setCurrentPage(pageResults.getNumber());
        contactListResponse.setTotalElements(pageResults.getTotalElements());
        contactListResponse.setTotalPages(pageResults.getTotalPages());
        return new ResponseEntity<>(contactListResponse, HttpStatus.OK);
    }
}
