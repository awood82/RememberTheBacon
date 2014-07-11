package com.digitalwood.rememberthebacon.functional;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.widget.EditText;

import com.digitalwood.rememberthebacon.DetailsActivity;
import com.digitalwood.rememberthebacon.R;

/**
 * Created by Andrew on 7/10/2014.
 * Copyright 2014
 */
public class DetailsFuncTest extends ActivityInstrumentationTestCase2<DetailsActivity> {
    public DetailsFuncTest() {
        super(DetailsActivity.class);
    }

    public void testUi_InitialState_WidgetsAreInExpectedStates() {

        DetailsActivity activity = getActivity();
        EditText edit = (EditText) activity.findViewById(R.id.details_name_editText);

        assertEquals("Details", activity.getTitle());
        assertEquals("", edit.getText().toString());
    }

}
