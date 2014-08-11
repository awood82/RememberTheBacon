package com.digitalwood.rememberthebacon.unit.list.applogic;

import android.test.AndroidTestCase;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreAddCbk;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.list.IGroceryListInteractorCbk;
import com.digitalwood.rememberthebacon.modules.list.applogic.GroceryListInteractor;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public class GroceryListInteractorUnitTest extends AndroidTestCase {

    private static final String TEST_LIST_STORE_FILENAME = "mytest.json";
    private static final String FIRST_ITEM_NAME = "Bacon";
    private boolean mCallbackFired;

    // NOTE: Only needed while ListStore is a Singleton
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        ListStore.getInstance(getContext()).deleteAll();
    }

    public void testLoadConsumables_WhenNoneSaved_ReturnsEmptyList() {
        GroceryListInteractor interactor = getNewInteractor();
        InteractorCbk cbk = getNewInteractorCbk();

        interactor.loadConsumables(cbk);

        assertEquals(0, cbk.getConsumables().size());
    }

    public void testLoadConsumables_WhenOneSaved_ReturnsOneItem() {
        GroceryListInteractor interactor = getNewInteractorWithOneItem();
        InteractorCbk cbk = getNewInteractorCbk();

        interactor.loadConsumables(cbk);

        assertEquals(1, cbk.getConsumables().size());
    }

    public void testGetConsumableAt_First_ReturnsFirst() {
        GroceryListInteractor interactor = getNewInteractorWithOneItem();
        interactor.loadConsumables(null);

        Consumable c = interactor.getConsumableAt(0);

        assertEquals(FIRST_ITEM_NAME, c.getName());
    }
/*
    public void testGetConsumableAt_NegativeIndex_ReturnsNull() {
        GroceryListInteractor interactor = getNewInteractorWithOneItem();
        interactor.loadConsumables(null);

        Consumable c = interactor.getConsumableAt(0);

        assertNull(c);
    }

    public void testGetConsumableAt_IndexGreaterThanSize_ReturnsNull() {
        GroceryListInteractor interactor = getNewInteractorWithOneItem();
        interactor.loadConsumables(null);

        Consumable c = interactor.getConsumableAt(2);

        assertNull(c);
    }
*/

    public void testLoadConsumables_AfterSavingOne_ReturnsOneWithCorrectName() {
        GroceryListInteractor interactor = getNewInteractorWithOneItem();
        InteractorCbk cbk = getNewInteractorCbk();

        interactor.loadConsumables(cbk);

        assertEquals(FIRST_ITEM_NAME, cbk.getConsumables().get(0).getName());
    }


    private GroceryListInteractor getNewInteractor() {
        return new GroceryListInteractor(getContext(), TEST_LIST_STORE_FILENAME);
    }

    private GroceryListInteractor getNewInteractorWithOneItem() {
        GroceryListInteractor interactor = getNewInteractor();
        Consumable c = new Consumable(FIRST_ITEM_NAME);
        ListStore.getInstance(getContext()).add(
                c,
                new IListStoreAddCbk() {
                    @Override
                    public void onAddFinished(boolean result) {
                        mCallbackFired = true;
                    }
                });
        waitForCallback();

        return interactor;
    }

    private InteractorCbk getNewInteractorCbk() {
        return new InteractorCbk();
    }

    private class InteractorCbk implements IGroceryListInteractorCbk {
        private ArrayList<Consumable> mConsumables;

        @Override
        public void onFinishedLoading(ArrayList<Consumable> consumables) {
            mConsumables = consumables;
        }

        @Override
        public void onFinishedSaving() {
        }

        public ArrayList<Consumable> getConsumables() {
            return mConsumables;
        }
    }

    private void waitForCallback() {
        while (mCallbackFired == false);
        mCallbackFired = false; // reset
    }
}