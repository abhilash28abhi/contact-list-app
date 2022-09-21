package com.contactlist.app.contactlist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class Contact {
//    @Id()
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
    private String name;
    private String url;

    public Contact (String name, String url) {
        this.name = name;
        this.url = url;
    }
}
