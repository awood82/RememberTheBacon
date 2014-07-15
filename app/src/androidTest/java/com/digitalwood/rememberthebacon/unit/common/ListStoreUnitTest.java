package com.digitalwood.rememberthebacon.unit.common;

import android.test.AndroidTestCase;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.util.List;

/**
 * Created by Andrew on 7/14/2014.
 * Copyright 2014
 */
public class ListStoreUnitTest extends AndroidTestCase{
    void testDeleteAll_AfterCalling_DoesNotInvalidateTheListStore() {
        ListStore store = ListStore.getInstance(getContext());

        store.deleteAll();

        assertNotNull(store.listIterator());
    }

    void testAdd_InitialSize_IsZero() {
        ListStore store = ListStore.getInstance(getContext());

        // Do nothing

        assertEquals(0, store.size());
    }

    void testAdd_AfterAddingOne_SizeIsOne() {
        ListStore store = ListStore.getInstance(getContext());

        store.add(new Consumable());

        assertEquals(1, store.size());
    }

    void testDeleteAll_AfterCalling_SizeIsZero() {
        ListStore store = ListStore.getInstance(getContext());
        store.add(new Consumable());

        store.deleteAll();

        assertEquals(0, store.size());
    }
}
