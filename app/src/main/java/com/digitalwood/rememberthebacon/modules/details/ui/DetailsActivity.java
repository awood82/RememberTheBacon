package com.digitalwood.rememberthebacon.modules.details.ui;

import android.support.v4.app.Fragment;

import com.digitalwood.rememberthebacon.common.view.SingleFragmentActivity;

import java.util.UUID;

/**
 * Created by Andrew on 7/9/2014.
 * Copyright 2014
 */
public class DetailsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        int consumableIndex = getIntent()
                .getIntExtra(DetailsFragment.EXTRA_CONSUMABLE_INDEX, -1);
        return DetailsFragment.newInstance(consumableIndex);
    }
}
