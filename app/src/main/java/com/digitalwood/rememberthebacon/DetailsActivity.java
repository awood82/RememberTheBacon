package com.digitalwood.rememberthebacon;

import android.support.v4.app.Fragment;

/**
 * Created by Andrew on 7/9/2014.
 * Copyright 2014
 */
public class DetailsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new DetailsFragment();
    }
}
