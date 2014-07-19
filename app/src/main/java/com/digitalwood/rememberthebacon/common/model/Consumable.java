package com.digitalwood.rememberthebacon.common.model;

import java.util.UUID;

/**
 * Created by Andrew on 7/9/2014.
 * Copyright 2014
 *
 * Model class for a consumable grocery store item (food, movie, cleaning supply, etc.)
 */
public class Consumable {

    private UUID mId;
    private String mName;

    public Consumable() {
        mId = UUID.randomUUID();
    }

    public Consumable(String name) {
        this();
        mName = name;
    }

    public String toString() { return mName; }

    public boolean equals(Consumable o) { return mName.equals(o.getName()); }

    public UUID getId() { return mId; }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
