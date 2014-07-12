package com.digitalwood.rememberthebacon.unit.list.applogic;

import android.test.AndroidTestCase;

import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.list.IGroceryListInteractorCbk;
import com.digitalwood.rememberthebacon.modules.list.applogic.GroceryListInteractor;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public class GroceryListInteractorUnitTest extends AndroidTestCase {

    public void testLoadConsumables_WhenNoneFound_ReturnsEmptyList() {
        GroceryListInteractor interactor = getNewInteractor();
        InteractorCbk cbk = getNewInteractorCbk();

        interactor.loadConsumables(0, cbk);

        assertEquals(0, cbk.getConsumables().size());
    }

    public void testLoadConsumables_WhenOneFound_ReturnsOneItem() {
        GroceryListInteractor interactor = getNewInteractor();
        InteractorCbk cbk = getNewInteractorCbk();

        interactor.loadConsumables(1, cbk);

        assertEquals(1, cbk.getConsumables().size());
    }



    private GroceryListInteractor getNewInteractor() {
        return new GroceryListInteractor();
    }

    private InteractorCbk getNewInteractorCbk() {
        return new InteractorCbk();
    }

    private class InteractorCbk implements IGroceryListInteractorCbk {
        private ArrayList<Consumable> mConsumables;

        @Override
        public void onFinishedLoading (ArrayList<Consumable> consumables) {
            mConsumables = consumables;
        }

        public ArrayList<Consumable> getConsumables() {
            return mConsumables;
        }
    }

}

