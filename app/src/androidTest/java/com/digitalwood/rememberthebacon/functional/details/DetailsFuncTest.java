package com.digitalwood.rememberthebacon.functional.details;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.digitalwood.rememberthebacon.modules.details.ui.DetailsActivity;
import com.digitalwood.rememberthebacon.R;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsFragment;

/**
 * Created by Andrew on 7/10/2014.
 * Copyright 2014
 */
public class DetailsFuncTest extends ActivityInstrumentationTestCase2<DetailsActivity> {
    public DetailsFuncTest() {
        super(DetailsActivity.class);
    }

    public void testUiAddNew_InitialState_WidgetsAreBlank() {
        DetailsActivity activity = getActivity();
        EditText edit = (EditText) activity.findViewById(R.id.details_name_editText);

        assertEquals("Details", activity.getTitle());
        assertEquals("", edit.getText().toString());
    }
/* TODO: Stopping point for tonight. Enable this test, and resume coding!
    public void testUiEdit_InitialState_WidgetsArePrepopulated() {
        Intent intent = new Intent();
        intent.putExtra(DetailsFragment.EXTRA_CONSUMABLE_ID, 1);
        DetailsActivity activity = getActivity();
        EditText edit = (EditText) activity.findViewById(R.id.details_name_editText);

        assertEquals("Details", activity.getTitle());
        assertEquals("Bacon", edit.getText().toString());
    }
*/
}
