package com.digitalwood.rememberthebacon.modules.details.handlers;

/**
 * Created by Andrew on 8/10/2014.
 * Copyright 2014
 */
public interface IDetailsInteractorSaveCbk {

    /**
     * Callback when DetailsInteractor finishes saving a consumable
     * @param wasSuccess - true if the save was successful
     * @param wasAdded - If wasSuccess is true, then this is
     *                   true if the item was added, false if an item was overwritten.
     *                   The value is undefined is wasSuccess is false.
     */
    void onFinishedSaving(boolean wasSuccess, boolean wasAdded);
}
