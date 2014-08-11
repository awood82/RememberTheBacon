package com.digitalwood.rememberthebacon.common.datastore;

import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreAddCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreSetCbk;
import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.util.ListIterator;

/**
 * Created by Andrew on 8/10/2014.
 * Copyright 2014
 */
public interface IListStore {
    void add(Consumable consumable, IListStoreAddCbk cbk);

    void set(int index, Consumable consumable, IListStoreSetCbk cbk);

    Consumable get(int index);

    void deleteAll();

    ListIterator<Consumable> listIterator();

    int size();

    void serialize(IListStoreSerializer serializer);

    void deserialize(IListStoreSerializer serializer);
}
