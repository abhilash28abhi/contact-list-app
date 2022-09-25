package com.contactlist.app.service;

import com.contactlist.app.api.ContactListResponse;
import org.springframework.data.domain.Pageable;

public interface IContactListManager {

    ContactListResponse getContactDetails(Pageable paging);

    ContactListResponse getContactDetailsByName(String name, Pageable paging);
}
