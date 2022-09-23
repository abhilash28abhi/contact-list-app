package com.contactlist.app.contactlist.controller;

import com.contactlist.app.contactlist.api.ContactListResponse;
import com.contactlist.app.contactlist.exception.DataViolationException;
import com.contactlist.app.contactlist.service.IContactListManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        ContactListResponse contactListResponse = null;

        if (name == null || "".equals(name)) {
            contactListResponse = contactListManager.getContactDetails(paging);
        } else {
            contactListResponse = contactListManager.getContactDetailsByName(name, paging);
        }

        if (null == contactListResponse || contactListResponse.getContacts().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
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
