package com.digitalwood.rememberthebacon.modules.list.applogic;

import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.list.IGroceryListInteractorCbk;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public class GroceryListInteractor implements IGroceryListInteractor {
    ArrayList<Consumable> mConsumables;

    @Override
    public void loadConsumables(int numConsumables, IGroceryListInteractorCbk callback) {
        mConsumables = new ArrayList<Consumable>();

        //TODO: Load from Data Store
        for (int i = 0; i < numConsumables; i++) {
            Consumable consumable = new Consumable();
            consumable.setName("Food #" + (i + 1));
            mConsumables.add(consumable);
        }

        if (callback != null) {
            callback.onFinishedLoading(mConsumables);
        }
    }

    @Override
    public Consumable getConsumableAt(int i) { //throws ArrayIndexOutOfBoundsException {
        //if (i < 0 || i >= mConsumables.size()) {

        //}
        return mConsumables.get(i);
    }
}
