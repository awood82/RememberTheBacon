package com.digitalwood.rememberthebacon.modules.list.ui;

import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public interface IGroceryListView {
    public void setTitle(String title);
    public void setItems(ArrayList<Consumable> items);
    //public void toggleItemBought(int index);
    public void toggleItemBought(Consumable consumable);
    public void toast(int id);
}
