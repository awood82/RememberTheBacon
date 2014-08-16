package com.digitalwood.rememberthebacon.common.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Andrew on 7/9/2014.
 * Copyright 2014
 *
 * Model class for a consumable grocery store item (food, movie, cleaning supply, etc.)
 */
public class Consumable implements Serializable {

    private String mId;
    private String mListId;
    private String mName;
    private boolean mBought;

    public Consumable() {
        mId = UUID.randomUUID().toString();
        mListId = new UUID(0, 0).toString(); //TODO
    }

    public Consumable(String name) {
        this();
        mName = name;
    }

    public Consumable(Consumable toCopy) {
        this();
        if (toCopy != null) {
            mId = toCopy.getId();
            mListId = toCopy.getListId();
            mName = toCopy.getName();
            mBought = toCopy.isBought();
        }
    }

    public String toString() { return mName; }

    public boolean equals(Consumable o) { return mName.equals(o.getName()); }

    public String getId() { return mId; }

    public void setId(String id) { mId = id; }

    public String getListId() { return mListId; }

    public void setListId(String listId) { mListId = listId; }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean isBought() { return mBought; }

    public void setBought(boolean bought) { mBought = bought; }

}
