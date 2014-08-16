package com.digitalwood.rememberthebacon.common.adapter;

import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.parse.ParseObject;

import java.util.UUID;

/**
 * Created by Andrew on 8/4/2014.
 * Copyright 2014
 */
public class ConsumableToParseObjectAdapter {
    public static final String LIST_TABLE_NAME = "GroceryList"; //TODO
    public static final String ITEM_TABLE_NAME = "Consumable";  //TODO

    public static final String JSON_ID = "itemid";
    public static final String JSON_LIST_ID = "listId";
    public static final String JSON_NAME = "name";
    //public static final String JSON_ORDER = "order";
    public static final String JSON_BOUGHT = "bought";

    public static ParseObject toParseObject(Consumable c) {
        ParseObject parseObject = new ParseObject(ITEM_TABLE_NAME);
        parseObject.put(JSON_ID, c.getId().toString());
        parseObject.put(JSON_LIST_ID, c.getListId().toString());
        parseObject.put(JSON_NAME, c.getName());
        //parseObject.put(JSON_ORDER, c.getOrder());
        parseObject.put(JSON_BOUGHT, c.isBought());
        return parseObject;
    }

    public static Consumable fromParseObject(ParseObject parseObject) {
        Consumable c = new Consumable();
        c.setId(parseObject.getString(JSON_ID));
        c.setListId(parseObject.getString(JSON_LIST_ID));
        c.setName(parseObject.getString(JSON_NAME));
        //c.setOrder(parseObject.getInt(JSON_ORDER));
        c.setBought(parseObject.getBoolean(JSON_BOUGHT));
        return c;
    }

    public static void updateParseObject(ParseObject parseObject, Consumable newValues) {
        //parseObject.put(JSON_ID, newValues.getId().toString());
        //parseObject.put(JSON_LIST_ID, newValues.getListId().toString());
        parseObject.put(JSON_NAME, newValues.getName());
        //parseObject.put(JSON_ORDER, newValues.getOrder());
        parseObject.put(JSON_BOUGHT, newValues.isBought());
    }
}
