package com.digitalwood.rememberthebacon.functional.details;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreAddCbk;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.R;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsFragment;
import com.digitalwood.rememberthebacon.common.view.TestFragmentActivity;

/**
 * Created by Andrew on 7/10/2014.
 * Copyright 2014
 */
public class DetailsFuncTest extends ActivityInstrumentationTestCase2<TestFragmentActivity> {

    private boolean mCallbackFired;

    public DetailsFuncTest() {
        super(TestFragmentActivity.class);
    }

    public void testUiAddNew_InitialState_WidgetsAreInitialized() {
        TestFragmentActivity activity = setUpActivity();

        EditText edit = (EditText) activity.findViewById(R.id.details_name_editText);

        assertEquals("Add New", activity.getTitle());
        assertEquals("", edit.getText().toString());
    }

    public void testUiEdit_InitialState_WidgetsArePrepopulated() {
        Intent intent = new Intent();
        intent.putExtra(DetailsFragment.EXTRA_CONSUMABLE_INDEX, 0);
        setActivityIntent(intent);
        addItemToListStore("Bacon");
        TestFragmentActivity activity = setUpActivity();

        EditText edit = (EditText) activity.findViewById(R.id.details_name_editText);
        cleanupListStore();

        assertEquals("Edit Details", activity.getTitle());
        assertEquals("Bacon", edit.getText().toString());
    }

    /* TODO
    public void testUi_InitialState_EditBoxIsFocused() {
    }*/


    private void addItemToListStore(String name) {
        ListStore.getInstance(null).add(
                new Consumable(name),
                new IListStoreAddCbk() {
                    @Override
                    public void onAddFinished(boolean result) {
                        mCallbackFired = true;
                    }
                });
        waitForCallback();
    }

    private void cleanupListStore() {
        ListStore.getInstance(getActivity()).deleteAll();
    }

    private TestFragmentActivity setUpActivity() {
        TestFragmentActivity activity = getActivity();
        activity.createFragmentUnderTest(R.layout.fragment_details);
        getInstrumentation().waitForIdleSync();

        return activity;
    }

    private void waitForCallback() {
        while (mCallbackFired == false);
        mCallbackFired = false; // reset
    }
}
