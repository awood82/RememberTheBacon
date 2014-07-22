package com.digitalwood.rememberthebacon.unit.common;

import android.test.AndroidTestCase;

import com.digitalwood.rememberthebacon.common.datastore.IListStoreSerializer;
import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.model.Consumable;

import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mock.*;
import static org.mockito.Mockito.*;

/**
 * Created by Andrew on 7/14/2014.
 * Copyright 2014
 */
public class ListStoreUnitTest extends AndroidTestCase{

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        // Ensure that the singleton is reset
        ListStore store = getListStoreInstance();
        store.deleteAll();
    }

    public void testDeleteAll_AfterCalling_DoesNotInvalidateTheListStore() {
        ListStore store = getListStoreInstance();

        store.deleteAll();

        assertNotNull(store.listIterator());
    }

    public void testAdd_InitialSize_IsZero() {
        ListStore store = getListStoreInstance();

        // Do nothing

        assertEquals(0, store.size());
    }

    public void testAdd_AfterAddingOne_SizeIsOne() {
        ListStore store = getListStoreInstance();

        store.add(new Consumable());

        assertEquals(1, store.size());
    }

    public void testDeleteAll_AfterCalling_SizeIsZero() {
        ListStore store = getListStoreInstance();
        store.add(new Consumable());

        store.deleteAll();

        assertEquals(0, store.size());
    }

    public void testSet_NegativeIndex_ReturnsFalse() {
        ListStore store = getListStoreInstance();

        boolean result = store.set(-1, new Consumable("Bacon"));

        assertEquals(false, result);
    }

    public void testSet_InvalidIndex_ReturnsFalse() {
        ListStore store = getListStoreInstance();
        store.add(new Consumable("Bacon"));

        boolean result = store.set(123, new Consumable("Eggs"));

        assertEquals(false, result);
    }

    public void testSet_ValidIndex_ReturnsTrue() {
        ListStore store = getListStoreInstance();
        store.add(new Consumable("Bacon"));

        boolean result = store.set(0, new Consumable("Eggs"));

        assertEquals(true, result);
    }

    public void testSet_ValidIndex_ChangesTheValue() {
        ListStore store = getListStoreInstance();
        store.add(new Consumable("Bacon"));

        boolean result = store.set(0, new Consumable("Eggs"));

        assertEquals("Eggs", store.get(0).getName());
    }

    public void testSerialize_WhenSavingNoObjects_CallsSaveList() {
        ListStore store = getListStoreInstance();
        IListStoreSerializer mockSerializer = mock(IListStoreSerializer.class);

        store.serialize(mockSerializer);

        verify(mockSerializer, times(1)).saveList(any(ArrayList.class));
    }

    public void testSerialize_WhenSavingOneObject_CallsSaveList() {
        ListStore store = getListStoreInstance();
        store.add(new Consumable("Bacon"));
        IListStoreSerializer mockSerializer = mock(IListStoreSerializer.class);

        store.serialize(mockSerializer);

        verify(mockSerializer, times(1)).saveList(any(ArrayList.class));
    }

    public void testDeserialize_WhenLoading_CallsLoadList() {
        ListStore store = getListStoreInstance();
        IListStoreSerializer mockSerializer = mock(IListStoreSerializer.class);

        store.deserialize(mockSerializer);

        verify(mockSerializer, times(1)).loadList();
    }



    private ListStore getListStoreInstance() {
        return ListStore.getInstance(getContext());
    }
}
