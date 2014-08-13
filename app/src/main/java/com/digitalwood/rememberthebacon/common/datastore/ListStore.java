package com.digitalwood.rememberthebacon.common.datastore;

import android.content.Context;

import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreAddCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreGetCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreIterCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreSetCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreSizeCbk;
import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/14/2014.
 * Copyright 2014
 */
public class ListStore implements IListStore {

    private static IListStore sListStore;
    private Context mContext;
    private ArrayList<Consumable> mConsumables;

    private ListStore(Context context) {
        mContext = context;
        mConsumables = new ArrayList<Consumable>();
    }

    // NOTE: Singletons are difficult to test. I'll swap this out w/ a database later.
    public static synchronized IListStore getInstance(Context context) {
        if (sListStore == null) {
            sListStore = new ListStore(context);
        }

        return sListStore;
    }

    @Override
    public void add(Consumable consumable, IListStoreAddCbk cbk) {
        boolean result = mConsumables.add(consumable);
        cbk.onAddFinished(result);
    }

    @Override
    public void set(int index, Consumable consumable, IListStoreSetCbk cbk) {
        if (index < 0 || index >= mConsumables.size()) {
            cbk.onSetFinished(false);
        } else {
            Consumable result = mConsumables.set(index, consumable);
            cbk.onSetFinished(result != null);
        }
    }

    @Override
    public void get(int index, IListStoreGetCbk cbk) {
        cbk.onGetFinished(mConsumables.get(index));
    }

    @Override
    public void deleteAll() {
        mConsumables.clear();
    }

    @Override
    public void listIterator(IListStoreIterCbk cbk) {
        cbk.onListIteratorFinished(mConsumables.listIterator());
    }

    @Override
    public void size(IListStoreSizeCbk cbk) {
        cbk.onSizeFinished(mConsumables.size());
    }

    @Override
    public void serialize(IListStoreSerializer serializer) {
        serializer.saveList(mConsumables);
    }

    @Override
    public void deserialize(IListStoreSerializer serializer) {
        mConsumables = serializer.loadList();
    }
}
