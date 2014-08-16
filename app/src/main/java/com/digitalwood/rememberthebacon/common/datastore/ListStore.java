package com.digitalwood.rememberthebacon.common.datastore;

import android.content.Context;

import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreAddCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreDeleteAllCbk;
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
    public void add(Consumable consumable, final IListStoreAddCbk cbk) {
        boolean result = mConsumables.add(consumable);
        cbk.onAddFinished(result);
    }

    @Override
    public void set(String id, Consumable newConsumable, final IListStoreSetCbk cbk) {
        boolean found = false;
        int foundIndex;
        // Find index of old consumable,...
        for (foundIndex = 0; foundIndex < mConsumables.size(); foundIndex++) {
            if (mConsumables.get(foundIndex).getId().equals(id)) {
                found = true;
                break;
            }
        }

        if (found) {
            // and replace it with the new consumable
            newConsumable.setId(id);
            Consumable result = mConsumables.set(foundIndex, newConsumable);
            cbk.onSetFinished(result != null);
        } else {
            cbk.onSetFinished(false);
        }
    }

    @Override
    public void get(String id, final IListStoreGetCbk cbk) {
        for (int i = 0; i < mConsumables.size(); i++) {
            if (mConsumables.get(i).getId().equals(id)) {
                cbk.onGetFinished(mConsumables.get(i));
                return;
            }
        }

        cbk.onGetFinished(null);
    }

    @Override
    public void deleteAll(final IListStoreDeleteAllCbk cbk) {
        mConsumables.clear();
        cbk.onDeleteAllFinished(true);
    }

    @Override
    public void listIterator(final IListStoreIterCbk cbk) {
        cbk.onListIteratorFinished(mConsumables.listIterator());
    }

    @Override
    public void size(final IListStoreSizeCbk cbk) {
        cbk.onSizeFinished(mConsumables.size());
    }

    @Override
    public void serialize(final IListStoreSerializer serializer) {
        serializer.saveList(mConsumables);
    }

    @Override
    public void deserialize(final IListStoreSerializer serializer) {
        mConsumables = serializer.loadList();
    }
}
