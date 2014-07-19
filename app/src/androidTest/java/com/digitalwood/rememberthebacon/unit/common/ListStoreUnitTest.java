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
        ListStore store = getListStoreInstance();

        store.deleteAll();

        assertNotNull(store.listIterator());
    }

    void testAdd_InitialSize_IsZero() {
        ListStore store = getListStoreInstance();

        // Do nothing

        assertEquals(0, store.size());
    }

    void testAdd_AfterAddingOne_SizeIsOne() {
        ListStore store = getListStoreInstance();

        store.add(new Consumable());

        assertEquals(1, store.size());
    }

    void testDeleteAll_AfterCalling_SizeIsZero() {
        ListStore store = getListStoreInstance();
        store.add(new Consumable());

        store.deleteAll();

        assertEquals(0, store.size());
    }

    void testSet_NegativeIndex_ReturnsFalse() {
        ListStore store = getListStoreInstance();

        boolean result = store.set(-1, new Consumable("Bacon"));

        assertEquals(false, result);
    }

    void testSet_InvalidIndex_ReturnsFalse() {
        ListStore store = getListStoreInstance();
        store.add(new Consumable("Bacon"));

        boolean result = store.set(3, new Consumable("Eggs"));

        assertEquals(false, result);
    }

    void testSet_ValidIndex_ReturnsTrue() {
        ListStore store = getListStoreInstance();
        store.add(new Consumable("Bacon"));

        boolean result = store.set(0, new Consumable("Eggs"));

        assertEquals(true, result);
    }

    void testSet_ValidIndex_ChangesTheValue() {
        ListStore store = getListStoreInstance();
        store.add(new Consumable("Bacon"));

        boolean result = store.set(0, new Consumable("Eggs"));

        assertEquals("Eggs", store.get(0).getName());
    }


    private ListStore getListStoreInstance() {
        return ListStore.getInstance(getContext());
    }
}
