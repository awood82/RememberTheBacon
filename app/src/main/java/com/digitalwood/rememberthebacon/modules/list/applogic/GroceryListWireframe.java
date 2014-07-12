package com.digitalwood.rememberthebacon.modules.list.applogic;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.digitalwood.rememberthebacon.DetailsActivity;

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
        Intent intent = new Intent(mFragmentActivity, DetailsActivity.class);
        mFragmentActivity.startActivityForResult(intent, 0);
    }
}
