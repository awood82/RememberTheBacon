package com.digitalwood.rememberthebacon.common.datastore;

import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreAddCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreDeleteAllCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreGetCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreIterCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreSetCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreSizeCbk;
import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.util.ListIterator;

/**
 * Created by Andrew on 8/10/2014.
 * Copyright 2014
 */
public interface IListStore {
    void add(Consumable consumable, final IListStoreAddCbk cbk);

    void set(int index, Consumable consumable, final IListStoreSetCbk cbk);

    void get(int index, final IListStoreGetCbk cbk);

    void deleteAll(final IListStoreDeleteAllCbk cbk);

    void listIterator(final IListStoreIterCbk cbk);

    void size(final IListStoreSizeCbk cbk);

    void serialize(final IListStoreSerializer serializer);

    void deserialize(final IListStoreSerializer serializer);
}
