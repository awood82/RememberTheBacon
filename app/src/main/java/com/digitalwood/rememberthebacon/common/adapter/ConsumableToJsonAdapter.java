package com.digitalwood.rememberthebacon.common.adapter;

import com.digitalwood.rememberthebacon.common.model.Consumable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by Andrew on 7/21/2014.
 * Copyright 2014
 */
public class ConsumableToJsonAdapter {
    private static final String JSON_ID = "id";
    private static final String JSON_NAME = "name";
    private static final String JSON_BOUGHT = "bought";

    public static JSONObject toJson(Consumable c) throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, c.getId().toString());
        json.put(JSON_NAME, c.getName());
        json.put(JSON_BOUGHT, c.isBought());
        return json;
    }

    public static Consumable fromJson(JSONObject jsonObject) throws JSONException {
        Consumable c = new Consumable();
        c.setId(UUID.fromString(jsonObject.getString(JSON_ID)));
        c.setName(jsonObject.getString(JSON_NAME));
        c.setBought(jsonObject.getBoolean(JSON_BOUGHT));
        return c;
    }
}
