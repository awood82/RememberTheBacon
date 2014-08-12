package com.digitalwood.rememberthebacon.common.datastore.callbacks;

import com.digitalwood.rememberthebacon.common.model.Consumable;

/**
 * Created by Andrew on 8/11/2014.
 * Copyright 2014
 */
public interface IListStoreGetCbk {
    void onGetFinished(Consumable consumable);
}
