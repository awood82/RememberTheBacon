package com.digitalwood.rememberthebacon.modules.details.ui;

/**
 * Created by Andrew on 7/14/2014.
 * Copyright 2014
 */
public interface IDetailsView {

    public void setTitle(String title);

    public String getItemName();

    public void setItemName(String name);

    public void showKeyboard();

    public void hideKeyboard();
}
