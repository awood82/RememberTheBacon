package com.digitalwood.rememberthebacon.functional.details;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsActivity;
import com.digitalwood.rememberthebacon.R;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsFragment;

import java.util.List;

/**
 * Created by Andrew on 7/10/2014.
 * Copyright 2014
 */
public class DetailsFuncTest extends ActivityInstrumentationTestCase2<DetailsActivity> {
    public DetailsFuncTest() {
        super(DetailsActivity.class);
    }

    public void testUiAddNew_InitialState_WidgetsAreInitialized() {
        DetailsActivity activity = getActivity();
        EditText edit = (EditText) activity.findViewById(R.id.details_name_editText);

        assertEquals("Add New", activity.getTitle());
        assertEquals("", edit.getText().toString());
    }

    public void testUiEdit_InitialState_WidgetsArePrepopulated() {
        Intent intent = new Intent();
        intent.putExtra(DetailsFragment.EXTRA_CONSUMABLE_INDEX, 0);
        setActivityIntent(intent);
        addItemToListStore("Bacon");
        DetailsActivity activity = getActivity();
        EditText edit = (EditText) activity.findViewById(R.id.details_name_editText);
        cleanupListStore();

        assertEquals("Edit Details", activity.getTitle());
        assertEquals("Bacon", edit.getText().toString());
    }


    private void addItemToListStore(String name) {
        ListStore.getInstance(null).add(new Consumable(name));
    }

    private void cleanupListStore() {
        ListStore.getInstance(getActivity()).deleteAll();
    }
}
