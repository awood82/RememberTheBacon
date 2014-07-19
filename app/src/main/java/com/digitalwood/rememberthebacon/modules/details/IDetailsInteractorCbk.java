package com.digitalwood.rememberthebacon.modules.details;

import com.digitalwood.rememberthebacon.common.model.Consumable;

/**
 * Created by Andrew on 7/18/2014.
 * Copyright 2014
 */
public interface IDetailsInteractorCbk {

    /**
     *
     * @param consumable Will be null if index is invalid
     */
    public void onFinishedLoading(Consumable consumable);
}
