package com.digitalwood.rememberthebacon.modules.details.presenter;

import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.util.UUID;

/**
 * Created by Andrew on 7/14/2014.
 * Copyright 2014
 */
public interface IDetailsPresenter {

    public void onResume(String id);

    public void onPause();

    public void okButtonPressed();

    public void cancelButtonPressed();
}
