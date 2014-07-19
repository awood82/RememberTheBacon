package com.digitalwood.rememberthebacon.modules.list.applogic;

import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.list.IGroceryListInteractorCbk;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public interface IGroceryListInteractor {

    public void loadConsumables(IGroceryListInteractorCbk callback);

    Consumable getConsumableAt(int i);

}