package com.contactlist.app.repository;

import com.contactlist.app.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactListRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findByNameContaining(String name, Pageable pageable);
}
