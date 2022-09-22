package com.contactlist.app.contactlist.repository;

import com.contactlist.app.contactlist.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactListRepository extends JpaRepository<Contact, String> {
}
