package com.contactlist.app.contactlist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Bean which acts as POJO for the table fields
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;

    public Contact (String name, String url) {
        this.name = name;
        this.url = url;
    }
}
