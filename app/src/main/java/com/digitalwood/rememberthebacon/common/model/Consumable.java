package com.digitalwood.rememberthebacon.common.model;

/**
 * Created by Andrew on 7/9/2014.
 * Copyright 2014
 *
 * Model class for a consumable grocery store item (food, movie, cleaning supply, etc.)
 */
public class Consumable {

    private String mName;

    public Consumable(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
