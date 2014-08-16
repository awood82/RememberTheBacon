package com.digitalwood.rememberthebacon.modules.list.applogic;

import com.digitalwood.rememberthebacon.common.model.Consumable;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public interface IGroceryListWireframe {

    public void navigateAddPressed();

    public void navigateListItemPressed(Consumable consumable);
}
