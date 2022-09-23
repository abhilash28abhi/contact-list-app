package com.contactlist.app.contactlist.controller;

import com.contactlist.app.contactlist.api.ContactListResponse;
import com.contactlist.app.contactlist.exception.DataViolationException;
import com.contactlist.app.contactlist.model.Contact;
import com.contactlist.app.contactlist.service.IContactListManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/contacts")
@Slf4j
public class ContactListController {

    @Autowired private IContactListManager contactListManager;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContactDetails(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int pageSize) throws Exception {
        log.debug("Invoked getContactDetails method with page : {} and pageSize : {}", page, pageSize);
        validateRequestParams (page, pageSize);
        Pageable paging = PageRequest.of(page, pageSize);
        Page<Contact> pageResults = null;

        if (name == null) {
            pageResults = contactListManager.getContactDetails(paging);
        } else {
            pageResults = contactListManager.getContactDetailsByName(name, paging);
        }

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

    private void validateRequestParams(int page, int pageSize) {
        if (page < 0) {
            throw new DataViolationException("Page can't be less than 0.");
        }
        if (pageSize > Integer.MAX_VALUE || page > Integer.MAX_VALUE) {
            throw new DataViolationException("Page size is out of range.");
        }
    }
}
