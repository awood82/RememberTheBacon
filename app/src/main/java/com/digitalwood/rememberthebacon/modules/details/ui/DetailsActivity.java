package com.digitalwood.rememberthebacon.modules.details.ui;

import android.support.v4.app.Fragment;

import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.common.view.SingleFragmentActivity;

/**
 * Created by Andrew on 7/9/2014.
 * Copyright 2014
 */
public class DetailsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        String id = getIntent()
                .getStringExtra(DetailsFragment.EXTRA_ID_OF_CONSUMABLE_TO_EDIT);
        return DetailsFragment.newInstance(id);
    }
}
