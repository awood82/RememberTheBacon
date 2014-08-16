package com.digitalwood.rememberthebacon.modules.list.applogic;

import android.content.Context;

import com.digitalwood.rememberthebacon.common.datastore.IListStore;
import com.digitalwood.rememberthebacon.common.datastore.ListStoreJsonSerializer;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreIterCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreSetCbk;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.list.IGroceryListInteractorCbk;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public class GroceryListInteractor implements IGroceryListInteractor {
    public static final String DEFAULT_LIST_STORE_FILENAME = "list.json";
    Context mContext;
    private IListStore mListStore;
    private String mListFilename;

    public GroceryListInteractor(Context appContext, IListStore listStore, String listFilename) {
        mContext = appContext;
        // Load the list from disk the first time
        mListFilename = listFilename;
        mListStore = listStore;
        mListStore.deserialize(new ListStoreJsonSerializer(mContext, mListFilename));
    }

    public GroceryListInteractor(Context appContext, IListStore listStore) {
        this(appContext, listStore, DEFAULT_LIST_STORE_FILENAME);
    }

    @Override
    public void loadConsumables(final IGroceryListInteractorCbk callback) {
        final ArrayList<Consumable> consumableList = new ArrayList<Consumable>();

        mListStore.listIterator(new IListStoreIterCbk() {
            @Override
            public void onListIteratorFinished(ListIterator<Consumable> consumableIter) {
                Consumable c;
                while (consumableIter.hasNext()) {
                    c = consumableIter.next();
                    consumableList.add(c);
                }

                if (callback != null) {
                    callback.onFinishedLoading(consumableList);
                }
            }
        });
    }

    @Override
    public void saveConsumables(final IGroceryListInteractorCbk callback) {
        mListStore.serialize(
                new ListStoreJsonSerializer(mContext, mListFilename));
        callback.onFinishedSaving();
    }

    @Override
    public void toggleConsumableBought(final Consumable consumable, final IGroceryListInteractorCbk callback) {
        consumable.setBought(!consumable.isBought()); // Toggle
        mListStore.set(consumable.getId(), consumable, new IListStoreSetCbk() {
            @Override
            public void onSetFinished(boolean result) {
                callback.onFinishedTogglingBought(consumable);
            }
        });
    }
}