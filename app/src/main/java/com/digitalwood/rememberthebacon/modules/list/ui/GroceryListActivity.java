package com.digitalwood.rememberthebacon.modules.list.ui;

import android.support.v4.app.Fragment;

import com.digitalwood.rememberthebacon.common.view.SingleFragmentActivity;

public class GroceryListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new GroceryListFragment();
    }

}
