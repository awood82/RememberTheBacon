package com.digitalwood.rememberthebacon.modules.list.applogic;

import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.list.IGroceryListInteractorCbk;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public class GroceryListInteractor implements IGroceryListInteractor {
    @Override
    public void loadConsumables(int numConsumables, IGroceryListInteractorCbk callback) {
        ArrayList<Consumable> consumables = new ArrayList<Consumable>();

        //TODO: Load from Data Store
        for (int i = 0; i < numConsumables; i++)
        {
            consumables.add(new Consumable("Food #" + (i + 1)));
        }

        callback.onFinishedLoading(consumables);
    }
}
