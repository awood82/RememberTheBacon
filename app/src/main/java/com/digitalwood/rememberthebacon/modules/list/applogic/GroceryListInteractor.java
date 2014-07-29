package com.digitalwood.rememberthebacon.modules.list.applogic;

import android.content.Context;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.datastore.ListStoreJsonSerializer;
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
    public void loadConsumables(IGroceryListInteractorCbk callback) {
        ArrayList<Consumable> mConsumables = new ArrayList<Consumable>();

        ListIterator<Consumable> iter = ListStore.getInstance(mContext).listIterator();
        Consumable c;
        while (iter.hasNext()) {
            c = iter.next();
            mConsumables.add(c);
        }

        if (callback != null) {
            callback.onFinishedLoading(mConsumables);
        }
    }

    @Override
    public void saveConsumables(IGroceryListInteractorCbk callback) {
        ListStore.getInstance(mContext).serialize(
                new ListStoreJsonSerializer(mContext, mListStoreFilename));
        callback.onFinishedSaving();
    }

    @Override
    public Consumable getConsumableAt(int position) { //throws ArrayIndexOutOfBoundsException {
        /*if (position < 0 || position >= mConsumables.size()) {
            return null;
        }*/
        return ListStore.getInstance(mContext).get(position);
    }

    @Override
    public void toggleConsumableBought(int position) {
        ListStore ls = ListStore.getInstance(mContext);
        Consumable c = ls.get(position);
        c.setBought(!c.isBought()); // Toggle
        ls.set(position, c);
    }

}
