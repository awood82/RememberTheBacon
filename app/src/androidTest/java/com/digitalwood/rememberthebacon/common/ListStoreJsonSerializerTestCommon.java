package com.digitalwood.rememberthebacon.common;

import android.content.Context;

import com.digitalwood.rememberthebacon.common.datastore.ListStoreJsonSerializer;
import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/20/2014.
 * Copyright 2014
 */
public class ListStoreJsonSerializerTestCommon {

    public static ListStoreJsonSerializer getNewJsonSerializer(Context context) {
        return new ListStoreJsonSerializer(context, getDefaultLocation());
    }

    /*public static ListStoreJsonSerializer getNewJsonSerializer(Context context, String location) {
        return new ListStoreJsonSerializer(context, location);
    }*/

    public static ArrayList<Consumable> createList(int numItems) {
        ArrayList<Consumable> list = new ArrayList<Consumable>();
        for (int i = 0; i < numItems; i++) {
            list.add(new Consumable("Bacon #" + i));
        }

        return list;
    }

    public static String getDefaultLocation() {
        return "test.list";
    }
}
