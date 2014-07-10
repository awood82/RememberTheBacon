package com.digitalwood.rememberthebacon.functional;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;

import com.digitalwood.rememberthebacon.GroceryListActivity;

/**
 * Created by Andrew on 7/9/2014.
 * Copyright 2014
 */
public class GroceryListTest extends ActivityInstrumentationTestCase2<GroceryListActivity> {

    public GroceryListTest() {
        super(GroceryListActivity.class);
    }

    // Dumb little test to verify that tests are set up and running properly
    public void testOnCreate_AfterCalled_TitleIsSet() {
        Activity activity = getActivity();

        assertEquals("Grocery List", activity.getTitle());
    }
}
