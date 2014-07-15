package com.digitalwood.rememberthebacon.modules.list.applogic;

import android.content.Context;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.list.IGroceryListInteractorCbk;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public class GroceryListInteractor implements IGroceryListInteractor {
    Context mContext;

    public GroceryListInteractor(Context appContext) {
        mContext = appContext;
    }

    @Override
    public boolean saveConsumable(Consumable c) {
        if (c.getName().isEmpty()) {
            return false;
        }

        return ListStore.getInstance(mContext).add(c);
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
    public Consumable getConsumableAt(int i) { //throws ArrayIndexOutOfBoundsException {
        /*if (i < 0 || i >= mConsumables.size()) {
            return null;
        }*/
        return ListStore.getInstance(mContext).get(i);
    }

}