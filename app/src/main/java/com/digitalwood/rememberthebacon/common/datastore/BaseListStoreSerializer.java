package com.digitalwood.rememberthebacon.common.datastore;

import com.digitalwood.rememberthebacon.common.adapter.ConsumableToJsonAdapter;
import com.digitalwood.rememberthebacon.common.model.Consumable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Andrew on 7/29/2014.
 * Copyright 2014
 */
abstract public class BaseListStoreSerializer implements IListStoreSerializer {
    @Override
    public int saveList(ArrayList<Consumable> list) {
        JSONArray jsonArray = convertToJsonArray(list);
        return saveJsonArray(jsonArray);
    }

    @Override
    public ArrayList<Consumable> loadList() {
        JSONArray jsonArray = loadJsonArray();
        return convertToListOfConsumables(jsonArray);
    }

    abstract protected int saveJsonArray(JSONArray jsonArray);

    abstract protected JSONArray loadJsonArray();

    protected JSONArray convertToJsonArray(ArrayList<Consumable> list) {
        JSONArray array = new JSONArray();
        try {
            for (Consumable item : list) {
                array.put(ConsumableToJsonAdapter.toJson(item));
            }
        } catch (JSONException e) {
            // We can continue. Save whatever we can.
        }

        return array;
    }

    protected ArrayList<Consumable> convertToListOfConsumables(JSONArray jsonArray) {
        ArrayList<Consumable> list = new ArrayList<Consumable>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Consumable c = null;
            try {
                c = ConsumableToJsonAdapter.fromJson(jsonArray.getJSONObject(i));
                list.add(c);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
