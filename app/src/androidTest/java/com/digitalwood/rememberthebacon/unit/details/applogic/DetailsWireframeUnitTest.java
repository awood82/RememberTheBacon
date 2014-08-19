package com.digitalwood.rememberthebacon.unit.details.applogic;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.Button;

import com.digitalwood.rememberthebacon.R;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsActivity;
import com.digitalwood.rememberthebacon.common.view.TestFragmentActivity;


/**
 * Created by awood on 7/15/14.
 */
public class DetailsWireframeUnitTest extends ActivityUnitTestCase<TestFragmentActivity> {
    private Intent mIntent;

    public DetailsWireframeUnitTest() {
        super(TestFragmentActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        //mIntent = new Intent(this.getInstrumentation().getContext(), TestFragmentActivity.class);
        mIntent = new Intent();
        mIntent.setClassName(
                "com.digitalwood.rememberthebacon.common.view",
                "com.digitalwood.rememberthebacon.common.view.TestFragmentActivity");
        // Don't call startActivity from setUp()!
        // http://developer.android.com/reference/android/test/ActivityUnitTestCase.html#startActivity(android.content.Intent, android.os.Bundle, java.lang.Object)
        //startActivity(mIntent, null, null);
    }

    public void testWireframe_Preconditions() {
        startActivity(mIntent, null, null);
        TestFragmentActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testWireframe_WhenBackButtonIsPressed_ActivityIsFinished() {
        startActivity(mIntent, null, null);
        TestFragmentActivity activity = getActivity();

        activity.onBackPressed();

        assertEquals(true, isFinishCalled());
    }

    public void testWireframe_WhenOkButtonIsPressed_ActivityIsFinished() throws InterruptedException{
        startActivity(mIntent, null, null);
        TestFragmentActivity activity = getActivity();
        View v = activity.getFragmentUnderTest().getView();
        Button ok = (Button) v.findViewById(R.id.details_ok_button);

        ok.performClick();

        assertEquals(true, isFinishCalled());
    }

    public void testWireframe_WhenCancelButtonIsPressed_ActivityIsFinished() {
        startActivity(mIntent, null, null);
        TestFragmentActivity activity = getActivity();
        View v = activity.getFragmentUnderTest().getView();
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
