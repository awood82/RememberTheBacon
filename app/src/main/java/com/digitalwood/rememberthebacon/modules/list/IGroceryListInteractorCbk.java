package com.digitalwood.rememberthebacon.modules.list;

import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public interface IGroceryListInteractorCbk {
    public void onFinishedLoading(ArrayList<Consumable> consumables);
}
