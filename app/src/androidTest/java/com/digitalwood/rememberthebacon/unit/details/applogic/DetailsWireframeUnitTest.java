package com.digitalwood.rememberthebacon.unit.details.applogic;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.Button;

import com.digitalwood.rememberthebacon.R;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsActivity;


/**
 * Created by awood on 7/15/14.
 */
public class DetailsWireframeUnitTest extends ActivityUnitTestCase<DetailsActivity> {

    public DetailsWireframeUnitTest() {
        super(DetailsActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent i = new Intent();
        i.setClassName("com.digitalwood.rememberthebacon.modules.details.ui", "com.digitalwood.rememberthebacon.modules.details.ui.DetailsActivity");
        startActivity(i, null, null);
    }

    public void testWireframe_Preconditions() {
        DetailsActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testWireframe_WhenBackButtonIsPressed_ActivityIsFinished() {
        DetailsActivity activity = getActivity();

        activity.onBackPressed();

        assertEquals(true, isFinishCalled());
    }

    public void testWireframe_WhenOkButtonIsPressed_ActivityIsFinished() throws InterruptedException{
        DetailsActivity activity = getActivity();
        View v = getFragmentView(activity);
        Button ok = (Button) v.findViewById(R.id.details_ok_button);

        ok.performClick();

        assertEquals(true, isFinishCalled());
    }

    public void testWireframe_WhenCancelButtonIsPressed_ActivityIsFinished() {
        DetailsActivity activity = getActivity();
        View v = getFragmentView(activity);
        Button cancel = (Button) v.findViewById(R.id.details_cancel_button);

        cancel.performClick();

        assertEquals(true, isFinishCalled());
    }


    private View getFragmentView(DetailsActivity activity) {
        getInstrumentation().callActivityOnResume(activity);
        getInstrumentation().callActivityOnStart(activity);
        Fragment fragment = activity.getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        return fragment.getView();
    }
}
