package com.digitalwood.rememberthebacon.modules.list.applogic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.digitalwood.rememberthebacon.R;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsFragment;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public class GroceryListWireframe implements IGroceryListWireframe {

    FragmentActivity mFragmentActivity;

    public GroceryListWireframe(FragmentActivity fragmentActivity) {
        mFragmentActivity = fragmentActivity;
    }

    @Override
    public void navigateAddPressed() {
        FragmentManager fm = mFragmentActivity.getSupportFragmentManager();
        Fragment fragment = DetailsFragment.newInstance(DetailsFragment.EXTRA_CONSUMABLE_TO_EDIT_NOT_SET);
        fm.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateListItemPressed(Consumable consumable) {
        FragmentManager fm = mFragmentActivity.getSupportFragmentManager();
        Fragment fragment = DetailsFragment.newInstance(consumable.getId());
        fm.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }
}
