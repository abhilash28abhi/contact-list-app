package com.contactlist.app.contactlist.controller;

import com.contactlist.app.contactlist.model.Contact;
import com.contactlist.app.contactlist.service.IContactListManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/contacts")
public class ContactListController {

    @Autowired private IContactListManager contactListManager;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<?> getContactDetails(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int pageSize) {
        List<Contact > contactList = contactListManager.getContactDetails(page, pageSize);
        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }
}
