package com.digitalwood.rememberthebacon.modules.list.presenter;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public interface IGroceryListPresenter {

    public void onAddPressed();

    public void onItemClicked(int position);

    void onResume();
}
