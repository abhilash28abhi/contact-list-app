package com.contactlist.app.controller;

import com.contactlist.app.api.ContactListResponse;
import com.contactlist.app.exception.DataViolationException;
import com.contactlist.app.service.IContactListManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all the contacts based on current page and size. If name is provided then the contacts are returned containing the provided name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ContactListResponse.class))}),
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContactDetails(
            @Parameter(description = "name of the contact to be searched")
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
