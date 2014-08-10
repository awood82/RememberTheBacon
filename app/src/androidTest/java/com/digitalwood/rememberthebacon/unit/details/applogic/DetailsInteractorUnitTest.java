package com.digitalwood.rememberthebacon.unit.details.applogic;

import android.test.AndroidTestCase;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.handlers.IDetailsInteractorLoadCbk;
import com.digitalwood.rememberthebacon.modules.details.applogic.DetailsInteractor;
import com.digitalwood.rememberthebacon.modules.details.handlers.IDetailsInteractorSaveCbk;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsFragment;

/**
 * Created by Andrew on 7/18/2014.
 * Copyright 2014
 */
public class DetailsInteractorUnitTest extends AndroidTestCase {

    boolean mCallbackFired;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mCallbackFired = false;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        ListStore.getInstance(getContext()).deleteAll();
    }

    public void testSaveConsumable_AddNewWithEmptyName_Fails() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c = new Consumable("");

        InteractorSaveCbk saveCbk = getNewInteractorSaveCbk();
        interactor.saveConsumable(
                DetailsFragment.EXTRA_CONSUMABLE_INDEX_NOT_SET,
                c,
                saveCbk);

        waitForCallback();
        assertEquals(false, saveCbk.mWasSuccess);
    }

    public void testSaveConsumable_AddNewWithValidName_Succeeds() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c = new Consumable("Bacon");

        InteractorSaveCbk saveCbk = getNewInteractorSaveCbk();
        interactor.saveConsumable(DetailsFragment.EXTRA_CONSUMABLE_INDEX_NOT_SET, c, saveCbk);

        waitForCallback();
        assertEquals(true, saveCbk.mWasSuccess);
        assertEquals(true, saveCbk.mWasAdded);
    }

    public void testSaveConsumable_EditDetailsWithValidName_Succeeds() {
        DetailsInteractor interactor = getNewInteractor();
        addItem(interactor, "Vegetables");
        Consumable c = new Consumable("Bacon");

        InteractorSaveCbk saveCbk = getNewInteractorSaveCbk();
        interactor.saveConsumable(0, c, saveCbk);

        waitForCallback();
        assertEquals(true, saveCbk.mWasSuccess);
        assertEquals(false, saveCbk.mWasAdded);
    }

    public void testSaveConsumable_EditDetailsWithEmptyName_Fails() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c = new Consumable("");

        InteractorSaveCbk saveCbk = getNewInteractorSaveCbk();
        interactor.saveConsumable(0, c, saveCbk);

        waitForCallback();
        assertEquals(false, saveCbk.mWasSuccess);
    }

    public void testSaveConsumable_EditDetailsWithChangedName_ChangesTheItem() {
        DetailsInteractor interactor = getNewInteractor();
        addItem(interactor, "Vegetables");

        editItem(interactor, 0, "Bacon");

        InteractorLoadCbk cbk = getNewInteractorLoadCbk();
        interactor.loadConsumable(0, cbk);
        waitForCallback();
        assertEquals("Bacon", cbk.mConsumable.getName());
    }

    public void testSaveConsumable_EditDetailsWithMultipleItemsAdded_ChangesTheItem() {
        DetailsInteractor interactor = getNewInteractor();
        addItem(interactor, "Bacon");
        addItem(interactor, "Bacon 2");
        addItem(interactor, "Bacon 3");

        editItem(interactor, 1, "Eggs");

        InteractorLoadCbk cbk = getNewInteractorLoadCbk();
        interactor.loadConsumable(0, cbk);
        assertEquals("Bacon", cbk.mConsumable.getName());
        interactor.loadConsumable(1, cbk);
        assertEquals("Eggs", cbk.mConsumable.getName());
        interactor.loadConsumable(2, cbk);
        assertEquals("Bacon 3", cbk.mConsumable.getName());
    }



    private DetailsInteractor getNewInteractor() {
        return new DetailsInteractor(getContext());
    }

    private InteractorLoadCbk getNewInteractorLoadCbk() {
        return new InteractorLoadCbk();
    }

    private InteractorSaveCbk getNewInteractorSaveCbk() {
        return new InteractorSaveCbk();
    }

    private class InteractorLoadCbk implements IDetailsInteractorLoadCbk {
        private Consumable mConsumable;

        @Override
        public void onFinishedLoading (Consumable consumable) {
            mConsumable = consumable;
            mCallbackFired = true;
        }
    }

    private class InteractorSaveCbk implements IDetailsInteractorSaveCbk {
        boolean mWasSuccess;
        boolean mWasAdded;

        @Override
        public void onFinishedSaving (boolean wasSuccess, boolean wasAdded) {
            mWasSuccess = wasSuccess;
            mWasAdded = wasAdded;
            mCallbackFired = true;
        }
    }

    private void addItem(DetailsInteractor interactor, String name) {
        interactor.saveConsumable(
                DetailsFragment.EXTRA_CONSUMABLE_INDEX_NOT_SET,
                new Consumable(name),
                new IDetailsInteractorSaveCbk() {
                    @Override
                    public void onFinishedSaving(boolean wasSuccess, boolean wasAdded) {
                        mCallbackFired = true;
                    }
                });

        waitForCallback();
        mCallbackFired = false; // reset
    }

    private void editItem(DetailsInteractor interactor, int index, String name) {
        interactor.saveConsumable(index, new Consumable(name), new IDetailsInteractorSaveCbk() {
            @Override
            public void onFinishedSaving(boolean wasSuccess, boolean wasAdded) {
                mCallbackFired = true;
            }
        });

        waitForCallback();
        mCallbackFired = false; // reset
    }

    private void waitForCallback() {
        while (mCallbackFired == false);
    }
}