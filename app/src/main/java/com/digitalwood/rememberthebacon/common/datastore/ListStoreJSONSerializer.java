package com.digitalwood.rememberthebacon.common.datastore;

import android.content.Context;

import com.digitalwood.rememberthebacon.common.adapter.ConsumableToJsonAdapter;
import com.digitalwood.rememberthebacon.common.model.Consumable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Andrew on 7/20/2014.
 * Copyright 2014
 */
public class ListStoreJsonSerializer implements IListStoreSerializer {

    private Context mContext;
    private String mFilename;

    public ListStoreJsonSerializer(Context context, String filename) {
        mContext = context;
        mFilename = filename;
    }

    @Override
    public int saveList(ArrayList<Consumable> list) {
        int mNumSaved = -1;

        JSONArray array = new JSONArray();
        try {
            for (Consumable item : list) {
                array.put(ConsumableToJsonAdapter.toJson(item));
            }
        } catch (JSONException e) {
            // We can continue. Save whatever we can.
        }

        Writer writer = null;
        try {
            OutputStream os = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(os);
            writer.write(array.toString());
            mNumSaved = array.length();
        } catch (IOException e) {
        }
        finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e2) {
            }
        }

        return mNumSaved;
    }

    @Override
    public ArrayList<Consumable> loadList() {
        ArrayList<Consumable> list = new ArrayList<Consumable>();
        BufferedReader reader = null;
        try {
            InputStream is = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Line breaks are omitted and irrelevant
                jsonString.append(line);
            }

            // Parse the JSON
            JSONArray array = (JSONArray)new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < array.length(); i++) {
                Consumable c = ConsumableToJsonAdapter.fromJson(array.getJSONObject(i));
                list.add(c);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e2) {
        } catch (JSONException e3) {
        }

        return list;
    }


}