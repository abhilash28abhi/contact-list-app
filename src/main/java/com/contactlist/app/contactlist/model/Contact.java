package com.contactlist.app.contactlist.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Bean which acts as POJO for the table fields
 */
@Getter
@Setter
public class Contact {

    private String name;
    private String url;

    public Contact (String name, String url) {
        this.name = name;
        this.url = url;
    }
}
