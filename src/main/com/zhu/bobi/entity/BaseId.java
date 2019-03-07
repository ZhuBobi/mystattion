package com.zhu.bobi.entity;

import javax.persistence.*;
import javax.persistence.GenerationType;

public class BaseId {
    private String id;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
