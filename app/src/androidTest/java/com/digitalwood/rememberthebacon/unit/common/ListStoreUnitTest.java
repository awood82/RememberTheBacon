package com.digitalwood.rememberthebacon.unit.common;

import android.test.AndroidTestCase;

import com.digitalwood.rememberthebacon.common.datastore.IListStore;
import com.digitalwood.rememberthebacon.common.datastore.IListStoreSerializer;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreDeleteAllCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreGetCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreIterCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreSetCbk;
import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreAddCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreSizeCbk;
import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.util.ArrayList;
import java.util.ListIterator;

import static org.mockito.Mockito.*;

/**
 * Created by Andrew on 7/14/2014.
 * Copyright 2014
 */
public class ListStoreUnitTest extends AndroidTestCase{

    private volatile boolean mCallbackFired;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Ensure that the singleton is reset
        deleteConsumables();

        mCallbackFired = false;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        // Ensure that the singleton is reset
        deleteConsumables();
    }

    public void testDeleteAll_AfterCalling_DoesNotInvalidateTheListStore() {
        IListStore store = getListStoreInstance();

        deleteConsumables();

        TestListIterCbk listIterCbk = getTestListIterCbk();
        store.listIterator(listIterCbk);
        waitForCallback();
        assertNotNull(listIterCbk.mConsumableIter);
    }

    public void testAdd_InitialSize_IsZero() {
        IListStore store = getListStoreInstance();

        // Do nothing

        TestSizeCbk sizeCbk = getTestSizeCbk();
        store.size(sizeCbk);
        waitForCallback();
        assertEquals(0, sizeCbk.mSize);
    }

    public void testAdd_AfterAddingOne_SizeIsOne() {
        IListStore store = getListStoreInstance();

        TestAddCbk cbk = getTestAddCbk();
        store.add(new Consumable(), cbk);
        waitForCallback();

        TestSizeCbk sizeCbk = getTestSizeCbk();
        store.size(sizeCbk);
        waitForCallback();
        assertEquals(1, sizeCbk.mSize);
    }

    public void testAdd_IfSucceds_CallbackReturnsTrue() {
        IListStore store = getListStoreInstance();

        TestAddCbk cbk = getTestAddCbk();
        store.add(new Consumable(), cbk);
        waitForCallback();

        assertEquals(true, cbk.mResult);
    }

    public void testDeleteAll_AfterCalling_SizeIsZero() {
        IListStore store = getListStoreInstance();
        TestAddCbk cbk = getTestAddCbk();
        store.add(new Consumable(), cbk);
        waitForCallback();

        deleteConsumables();

        TestSizeCbk sizeCbk = getTestSizeCbk();
        store.size(sizeCbk);
        waitForCallback();
        assertEquals(0, sizeCbk.mSize);
    }
/*
    public void testSet_NegativeIndex_ReturnsFalse() {
        IListStore store = getListStoreInstance();

        TestSetCbk setCbk = getTestSetCbk();
        store.set(-1, new Consumable("Bacon"), setCbk);
        waitForCallback();

        assertEquals(false, setCbk.mResult);
    }

    public void testSet_InvalidIndex_ReturnsFalse() {
        IListStore store = getListStoreInstance();
        TestAddCbk addCbk = getTestAddCbk();
        store.add(new Consumable("Bacon"), addCbk);
        waitForCallback();

        TestSetCbk setCbk = getTestSetCbk();
        store.set(123, new Consumable("Eggs"), setCbk);
        waitForCallback();

        assertEquals(false, setCbk.mResult);
    }
*/
    public void testSet_ValidIndex_ReturnsTrue() {
        IListStore store = getListStoreInstance();
        TestAddCbk addCbk = getTestAddCbk();
        Consumable consumable = new Consumable("Bacon");
        consumable.setId("1");
        store.add(consumable, addCbk);
        waitForCallback();

        TestSetCbk setCbk = getTestSetCbk();
        store.set("1", new Consumable("Eggs"), setCbk);
        waitForCallback();

        assertEquals(true, setCbk.mResult);
    }

    public void testSet_ValidIndex_ChangesTheValue() {
        IListStore store = getListStoreInstance();
        TestAddCbk addCbk = getTestAddCbk();
        Consumable consumable = new Consumable("Bacon");
        consumable.setId("1");
        store.add(consumable, addCbk);
        waitForCallback();

        TestSetCbk setCbk = getTestSetCbk();
        store.set("1", new Consumable("Eggs"), setCbk);
        waitForCallback();

        TestGetCbk getCbk = getTestGetCbk();
        store.get("1", getCbk);
        waitForCallback();
        assertEquals("Eggs", getCbk.mConsumable.getName());
    }

    public void testSerialize_WhenSavingNoObjects_CallsSaveList() {
        IListStore store = getListStoreInstance();
        IListStoreSerializer mockSerializer = mock(IListStoreSerializer.class);

        store.serialize(mockSerializer);

        verify(mockSerializer, times(1)).saveList(any(ArrayList.class));
    }

    public void testSerialize_WhenSavingOneObject_CallsSaveList() {
        IListStore store = getListStoreInstance();
        TestAddCbk cbk = getTestAddCbk();
        store.add(new Consumable("Bacon"), cbk);
        waitForCallback();
        IListStoreSerializer mockSerializer = mock(IListStoreSerializer.class);

        store.serialize(mockSerializer);

        verify(mockSerializer, times(1)).saveList(any(ArrayList.class));
    }

    public void testDeserialize_WhenLoading_CallsLoadList() {
        IListStore store = getListStoreInstance();
        IListStoreSerializer mockSerializer = mock(IListStoreSerializer.class);

        store.deserialize(mockSerializer);

        verify(mockSerializer, times(1)).loadList();
    }



    private IListStore getListStoreInstance() {
        return ListStore.getInstance(getContext());
    }

    private TestAddCbk getTestAddCbk() {
        return new TestAddCbk();
    }

    private TestSetCbk getTestSetCbk() {
        return new TestSetCbk();
    }

    private TestGetCbk getTestGetCbk() {
        return new TestGetCbk();
    }

    private TestSizeCbk getTestSizeCbk() {
        return new TestSizeCbk();
    }

    private TestListIterCbk getTestListIterCbk() {
        return new TestListIterCbk();
    }

    private class TestAddCbk implements IListStoreAddCbk {
        boolean mResult;

        @Override
        public void onAddFinished(boolean result) {
            mResult = result;
            mCallbackFired = true;
        }
    }

    private class TestSetCbk implements IListStoreSetCbk {
        boolean mResult;

        @Override
        public void onSetFinished(boolean result) {
            mResult = result;
            mCallbackFired = true;
        }
    }

    private class TestGetCbk implements IListStoreGetCbk {
        Consumable mConsumable;

        @Override
        public void onGetFinished(Consumable consumable) {
            mConsumable = consumable;
            mCallbackFired = true;
        }
    }

    private class TestListIterCbk implements IListStoreIterCbk {
        ListIterator<Consumable> mConsumableIter;

        @Override
        public void onListIteratorFinished(ListIterator<Consumable> consumableIter) {
            mConsumableIter = consumableIter;
            mCallbackFired = true;
        }
    }

    private class TestSizeCbk implements IListStoreSizeCbk {
        int mSize;

        @Override
        public void onSizeFinished(int size) {
            mSize = size;
            mCallbackFired = true;
        }
    }

    private void deleteConsumables() {
        IListStore store = getListStoreInstance();
        store.deleteAll(new IListStoreDeleteAllCbk() {
            @Override
            public void onDeleteAllFinished(boolean success) {
                mCallbackFired = true;
            }
        });
        waitForCallback();
    }

    private void waitForCallback() {
        while (mCallbackFired == false);
        mCallbackFired = false; // reset
    }
}
