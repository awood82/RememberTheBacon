package com.digitalwood.rememberthebacon.common;

import android.content.Context;

import com.digitalwood.rememberthebacon.common.datastore.ListStoreJsonSerializer;
import com.digitalwood.rememberthebacon.common.datastore.ListStoreParseSerializer;
import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/20/2014.
 * Copyright 2014
 */
public class ListStoreParseSerializerTestCommon {

    public static ListStoreParseSerializer getNewParseSerializer(Context context) {
        return new ListStoreParseSerializer(context, getDefaultLocation());
    }

    /*public static ListStoreParseSerializer getNewParseSerializer(Context context, String location) {
        return new ListStoreParseSerializer(context, location);
    }*/

    public static ArrayList<Consumable> createList(int numItems) {
        ArrayList<Consumable> list = new ArrayList<Consumable>();
        for (int i = 0; i < numItems; i++) {
            list.add(new Consumable("Bacon #" + i));
        }

        return list;
    }

    public static String getDefaultLocation() {
        return "parsetest.list";
    }
}
