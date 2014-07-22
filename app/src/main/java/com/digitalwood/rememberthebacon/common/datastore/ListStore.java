package com.digitalwood.rememberthebacon.common.datastore;

import android.content.Context;

import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Andrew on 7/14/2014.
 * Copyright 2014
 */
public class ListStore {

    private static ListStore sListStore;
    private Context mContext;
    private ArrayList<Consumable> mConsumables;

    private ListStore(Context context) {
        mContext = context;
        mConsumables = new ArrayList<Consumable>();
    }

    // NOTE: Singletons are difficult to test. I'll swap this out w/ a database later.
    public static synchronized ListStore getInstance(Context context) {
        if (sListStore == null) {
            sListStore = new ListStore(context);
        }

        return sListStore;
    }

    public boolean add(Consumable consumable) {
        return mConsumables.add(consumable);
    }

    public boolean set(int index, Consumable consumable) {
        if (index < 0 || index >= mConsumables.size()) {
            return false;
        }
        mConsumables.set(index, consumable);
        return true;
    }

    public Consumable get(int index) {
        return mConsumables.get(index);
    }

    public void deleteAll() {
        mConsumables.clear();
    }

    public ListIterator<Consumable> listIterator() {
        return mConsumables.listIterator();
    }

    public int size() {
        return mConsumables.size();
    }

    public void serialize(IListStoreSerializer serializer) {
        serializer.saveList(mConsumables);
    }

    public void deserialize(IListStoreSerializer serializer) {
        mConsumables = serializer.loadList();
    }
}
