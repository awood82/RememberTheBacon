package com.digitalwood.rememberthebacon.modules.details.handlers;

import com.digitalwood.rememberthebacon.common.model.Consumable;

/**
 * Created by Andrew on 7/18/2014.
 * Copyright 2014
 */
public interface IDetailsInteractorLoadCbk {

    /**
     *
     * @param consumable Will be null if index is invalid
     */
    void onFinishedLoading(Consumable consumable);
}
