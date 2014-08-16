package com.digitalwood.rememberthebacon.modules.list;

import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public interface IGroceryListInteractorCbk {
    void onFinishedLoading(ArrayList<Consumable> consumables);
    void onFinishedSaving();
    void onFinishedTogglingBought(Consumable consumable);
}
