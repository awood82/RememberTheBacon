package com.digitalwood.rememberthebacon.unit.details.applogic;

import android.test.AndroidTestCase;

import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.applogic.DetailsInteractor;

/**
 * Created by Andrew on 7/18/2014.
 * Copyright 2014
 */
public class DetailsInteractorUnitTest extends AndroidTestCase {


    public void testSaveConsumable_WithEmptyName_Fails() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c = new Consumable("");

        boolean result = interactor.saveConsumable(c);

        assertEquals(false, result);
    }

    public void testSaveConsumable_WithValidName_Succeeds() {
        DetailsInteractor interactor = getNewInteractor();
        Consumable c = new Consumable("Bacon");

        boolean result = interactor.saveConsumable(c);

        assertEquals(true, result);
    }


    private DetailsInteractor getNewInteractor() {
        return new DetailsInteractor(getContext());
    }


}
