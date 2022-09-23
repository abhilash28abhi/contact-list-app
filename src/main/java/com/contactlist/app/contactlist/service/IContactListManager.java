package com.contactlist.app.contactlist.service;

import com.contactlist.app.contactlist.api.ContactListResponse;
import org.springframework.data.domain.Pageable;

public interface IContactListManager {

    ContactListResponse getContactDetails(Pageable paging);

    ContactListResponse getContactDetailsByName(String name, Pageable paging);
}
