package com.digitalwood.rememberthebacon.common.datastore;

import android.content.Context;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import org.json.JSONArray;

/**
 * Created by Andrew on 7/20/2014.
 * Copyright 2014
 */
public class ListStoreParseSerializer extends BaseListStoreSerializer {

    private Context mContext;
    private String mObjectName;

    public ListStoreParseSerializer(Context context, String objectName) {
        mContext = context;
        mObjectName = objectName;
    }

    @Override
    protected int saveJsonArray(JSONArray jsonArray) {
        ParseObject parseObject = new ParseObject(mObjectName);
        parseObject.put("consumables", jsonArray);
        parseObject.pinInBackground(mObjectName, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                // TODO: Pass in Callback from Interactor?
            }
        });
        return jsonArray.length();
    }

    @Override
    protected JSONArray loadJsonArray() {
        JSONArray jsonArray = new JSONArray();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(mObjectName);
        query.fromLocalDatastore();
        try {
            jsonArray = query.get("consumables").getJSONArray("consumables");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
}