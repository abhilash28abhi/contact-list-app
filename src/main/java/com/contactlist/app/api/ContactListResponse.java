package com.contactlist.app.api;

import com.contactlist.app.model.Contact;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactListResponse {

    private List<Contact> contacts;
    private int currentPage;
    private long totalElements;
    private int totalPages;
}
