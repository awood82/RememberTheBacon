package com.digitalwood.rememberthebacon.modules.list.applogic;

import android.content.Context;

import com.digitalwood.rememberthebacon.common.datastore.IListStore;
import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.datastore.ListStoreJsonSerializer;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreGetCbk;
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
    private String mListStoreFilename;

    public GroceryListInteractor(Context appContext, String listStoreFilename) {
        mContext = appContext;
        // Load the list from disk the first time
        mListStoreFilename = listStoreFilename;
        ListStore.getInstance(mContext)
                .deserialize(new ListStoreJsonSerializer(mContext, mListStoreFilename));
    }

    public GroceryListInteractor(Context appContext) {
        this(appContext, DEFAULT_LIST_STORE_FILENAME);
    }

    @Override
    public void loadConsumables(final IGroceryListInteractorCbk callback) {
        final ArrayList<Consumable> mConsumables = new ArrayList<Consumable>();

        ListStore.getInstance(mContext).listIterator(new IListStoreIterCbk() {
            @Override
            public void onListIteratorFinished(ListIterator<Consumable> consumableIter) {
                Consumable c;
                while (consumableIter.hasNext()) {
                    c = consumableIter.next();
                    mConsumables.add(c);
                }

                if (callback != null) {
                    callback.onFinishedLoading(mConsumables);
                }
            }
        });
    }

    @Override
    public void saveConsumables(IGroceryListInteractorCbk callback) {
        ListStore.getInstance(mContext).serialize(
                new ListStoreJsonSerializer(mContext, mListStoreFilename));
        callback.onFinishedSaving();
    }

    @Override
    public void toggleConsumableBought(final int position) {
        final IListStore ls = ListStore.getInstance(mContext);
        ls.get(position, new IListStoreGetCbk() {
            @Override
            public void onGetFinished(Consumable consumable) {
                consumable.setBought(!consumable.isBought()); // Toggle
                ls.set(position, consumable, new IListStoreSetCbk() {
                    @Override
                    public void onSetFinished(boolean result) {
                        //TODO: No need to do anything?
                    }
                });
            }
        });
    }

}
