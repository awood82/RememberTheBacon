package com.digitalwood.rememberthebacon.unit.details.applogic;

import android.test.AndroidTestCase;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.IDetailsInteractorCbk;
import com.digitalwood.rememberthebacon.modules.details.applogic.DetailsInteractor;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsFragment;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/18/2014.
 * Copyright 2014
 */
public class DetailsInteractorUnitTest extends AndroidTestCase {

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        ListStore.getInstance(getContext()).deleteAll();
    }

    public void testSaveConsumable_AddNewWithEmptyName_Fails() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c = new Consumable("");

        boolean result = interactor.saveConsumable(DetailsFragment.EXTRA_CONSUMABLE_INDEX_NOT_SET, c);

        assertEquals(false, result);
    }

    public void testSaveConsumable_AddNewWithValidName_Succeeds() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c = new Consumable("Bacon");

        boolean result = interactor.saveConsumable(DetailsFragment.EXTRA_CONSUMABLE_INDEX_NOT_SET, c);

        assertEquals(true, result);
    }

    public void testSaveConsumable_EditDetailsWithValidName_Succeeds() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c = new Consumable("Bacon");

        boolean result = interactor.saveConsumable(0, c);

        assertEquals(true, result);
    }

    public void testSaveConsumable_EditDetailsWithEmptyName_Fails() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c = new Consumable("");

        boolean result = interactor.saveConsumable(0, c);

        assertEquals(false, result);
    }

    public void testSaveConsumable_EditDetailsWithChangedName_ChangesTheItem() {
        DetailsInteractor interactor = getNewInteractor();
        addItem(interactor, "Bacon");

        editItem(interactor, 0, "Eggs");

        InteractorCbk cbk = getNewInteractorCbk();
        interactor.loadConsumable(0, cbk);
        assertEquals("Eggs", cbk.getConsumable().getName());
    }

    public void testSaveConsumable_EditDetailsWithMultipleItemsAdded_ChangesTheItem() {
        DetailsInteractor interactor = getNewInteractor();
        addItem(interactor, "Bacon");
        addItem(interactor, "Bacon 2");
        addItem(interactor, "Bacon 3");

        editItem(interactor, 1, "Eggs");

        InteractorCbk cbk = getNewInteractorCbk();
        interactor.loadConsumable(0, cbk);
        assertEquals("Bacon", cbk.getConsumable().getName());
        interactor.loadConsumable(1, cbk);
        assertEquals("Eggs", cbk.getConsumable().getName());
        interactor.loadConsumable(2, cbk);
        assertEquals("Bacon 3", cbk.getConsumable().getName());
    }


    private DetailsInteractor getNewInteractor() {
        return new DetailsInteractor(getContext());
    }

    private InteractorCbk getNewInteractorCbk() {
        return new InteractorCbk();
    }

    private class InteractorCbk implements IDetailsInteractorCbk {
        private Consumable mConsumable;

        @Override
        public void onFinishedLoading (Consumable consumable) {
            mConsumable = consumable;
        }

        public Consumable getConsumable() {
            return mConsumable;
        }
    }

    private void addItem(DetailsInteractor interactor, String name) {
        interactor.saveConsumable(
                DetailsFragment.EXTRA_CONSUMABLE_INDEX_NOT_SET,
                new Consumable(name));
    }

    private void editItem(DetailsInteractor interactor, int index, String name) {
        interactor.saveConsumable(index, new Consumable(name));
    }


}
