package com.digitalwood.rememberthebacon.modules.details.applogic;

import android.support.v4.app.FragmentActivity;

/**
 * Created by Andrew on 7/14/2014.
 * Copyright 2014
 */
public class DetailsWireframe implements IDetailsWireframe {

    FragmentActivity mFragmentActivity;

    public DetailsWireframe(FragmentActivity fragmentActivity) {
        mFragmentActivity = fragmentActivity;
    }

    @Override
    public void navigateOkPressed() {
        mFragmentActivity.finish();
    }

    @Override
    public void navigateCancelPressed() {
        mFragmentActivity.finish();
    }

    @Override
    public void navigateBackPressed() {
        mFragmentActivity.finish();
    }

}
