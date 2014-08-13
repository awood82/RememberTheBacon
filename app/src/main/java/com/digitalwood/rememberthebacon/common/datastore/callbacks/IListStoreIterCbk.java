package com.digitalwood.rememberthebacon.common.datastore.callbacks;

import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.util.ListIterator;

/**
 * Created by Andrew on 8/12/2014.
 * Copyright 2014
 */
public interface IListStoreIterCbk {
    void onListIteratorFinished(ListIterator<Consumable> consumableIter);
}
