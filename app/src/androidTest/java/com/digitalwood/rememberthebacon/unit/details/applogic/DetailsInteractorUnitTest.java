package com.digitalwood.rememberthebacon.unit.details.applogic;

import android.test.AndroidTestCase;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreDeleteAllCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreGetCbk;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.applogic.DetailsInteractor;
import com.digitalwood.rememberthebacon.modules.details.handlers.IDetailsInteractorSaveCbk;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsFragment;

/**
 * Created by Andrew on 7/18/2014.
 * Copyright 2014
 */
public class DetailsInteractorUnitTest extends AndroidTestCase {

    private volatile boolean mCallbackFired;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mCallbackFired = false;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        ListStore.getInstance(getContext()).deleteAll(new IListStoreDeleteAllCbk() {
            @Override
            public void onDeleteAllFinished(boolean success) {
                mCallbackFired= true;
            }
        });
        waitForCallback();
    }

    public void testSaveConsumable_AddNewWithEmptyName_Fails() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c = new Consumable("");

        TestSaveCbk saveCbk = getNewTestSaveCbk();
        interactor.saveConsumable(
                DetailsFragment.EXTRA_CONSUMABLE_TO_EDIT_NOT_SET,
                c,
                saveCbk);
        waitForCallback();

        assertEquals(false, saveCbk.mWasSuccess);
    }

    public void testSaveConsumable_AddNewWithValidName_Succeeds() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c = new Consumable("Bacon");

        TestSaveCbk saveCbk = getNewTestSaveCbk();
        interactor.saveConsumable(
                DetailsFragment.EXTRA_CONSUMABLE_TO_EDIT_NOT_SET,
                c,
                saveCbk);
        waitForCallback();

        assertEquals(true, saveCbk.mWasSuccess);
        assertEquals(true, saveCbk.mWasAdded);
    }

    public void testSaveConsumable_EditDetailsWithValidName_Succeeds() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c1 = addItem(interactor, "Vegetables");
        Consumable c2 = new Consumable("Bacon");

        TestSaveCbk saveCbk = getNewTestSaveCbk();
        interactor.saveConsumable(c1.getId(), c2, saveCbk);
        waitForCallback();

        assertEquals(true, saveCbk.mWasSuccess);
        assertEquals(false, saveCbk.mWasAdded);
    }

    public void testSaveConsumable_EditDetailsWithEmptyName_Fails() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c1 = new Consumable("bacon");
        Consumable c2 = new Consumable("");

        TestSaveCbk saveCbk = getNewTestSaveCbk();
        interactor.saveConsumable(c1.getId(), c2, saveCbk);
        waitForCallback();

        assertEquals(false, saveCbk.mWasSuccess);
    }

    public void testSaveConsumable_EditDetailsWithNameUnchanged_Fails() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c1 = new Consumable("bacon");
        Consumable c2 = new Consumable("bacon");

        TestSaveCbk saveCbk = getNewTestSaveCbk();
        interactor.saveConsumable(c1.getId(), c2, saveCbk);
        waitForCallback();

        assertEquals(false, saveCbk.mWasSuccess);
    }

    public void testSaveConsumable_EditDetailsWithChangedName_ChangesTheItem() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c1 = addItem(interactor, "Vegetables");

        Consumable edited = editItem(interactor, c1, "Bacon");

        assertEquals("Bacon", getItem(c1.getId()).getName());
    }

    public void testSaveConsumable_EditDetailsWithMultipleItemsAdded_ChangesTheItem() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c1 = addItem(interactor, "Bacon 1");
        Consumable c2 = addItem(interactor, "Bacon 2");
        Consumable c3 = addItem(interactor, "Bacon 3");

        Consumable edited2 = editItem(interactor, c2, "Eggs");

        assertEquals("Bacon 1", getItem(c1.getId()).getName());
        assertEquals("Eggs", getItem(c2.getId()).getName());
        assertEquals("Bacon 3", getItem(c3.getId()).getName());
    }




    private DetailsInteractor getNewInteractor() {
        return new DetailsInteractor(ListStore.getInstance(getContext()));
    }

    private TestSaveCbk getNewTestSaveCbk() {
        return new TestSaveCbk();
    }

    private class TestSaveCbk implements IDetailsInteractorSaveCbk {
        boolean mWasSuccess;
        boolean mWasAdded;

        @Override
        public void onFinishedSaving (boolean wasSuccess, boolean wasAdded) {
            mWasSuccess = wasSuccess;
            mWasAdded = wasAdded;
            mCallbackFired = true;
        }
    }

    private Consumable addItem(DetailsInteractor interactor, String name) {
        Consumable consumable = new Consumable(name);
        interactor.saveConsumable(
                DetailsFragment.EXTRA_CONSUMABLE_TO_EDIT_NOT_SET,
                consumable,
                new IDetailsInteractorSaveCbk() {
                    @Override
                    public void onFinishedSaving(boolean wasSuccess, boolean wasAdded) {
                        mCallbackFired = true;
                    }
                });

        waitForCallback();

        return consumable;
    }

    private Consumable editItem(DetailsInteractor interactor, Consumable old, String name) {
        Consumable newConsumable = new Consumable(old);
        newConsumable.setName(name);
        interactor.saveConsumable(
                old.getId(),
                newConsumable,
                new IDetailsInteractorSaveCbk() {
            @Override
            public void onFinishedSaving(boolean wasSuccess, boolean wasAdded) {
                mCallbackFired = true;
            }
        });

        waitForCallback();

        return newConsumable;
    }

    private Consumable getItem(String id) {
        final Consumable[] c = new Consumable[1];
        ListStore.getInstance(mContext).get(id, new IListStoreGetCbk() {
            @Override
            public void onGetFinished(Consumable consumable) {
                c[0] = consumable;
                mCallbackFired = true;
            }
        });
        waitForCallback();

        return c[0];
    }

    private void waitForCallback() {
        while (mCallbackFired == false);
        mCallbackFired = false; // reset
    }
}