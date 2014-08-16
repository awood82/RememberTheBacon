package com.digitalwood.rememberthebacon.modules.list.presenter;

import com.digitalwood.rememberthebacon.common.model.Consumable;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public interface IGroceryListPresenter {

    public void onAddPressed();

    public void onItemClicked(Consumable consumable);

    public void onItemLongClicked(Consumable consumable);

    void onResume();

    void onPause();
}
