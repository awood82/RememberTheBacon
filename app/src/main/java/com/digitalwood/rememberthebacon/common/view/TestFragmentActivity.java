package com.digitalwood.rememberthebacon.common.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.digitalwood.rememberthebacon.R;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsFragment;
import com.digitalwood.rememberthebacon.modules.list.ui.GroceryListFragment;

/**
 * Created by Andrew on 7/27/2014.
 * Copyright 2014
 */
public class TestFragmentActivity extends MasterActivity {

    public static final String EXTRA_FRAGMENT_UNDER_TEST =
            "com.digitalwood.rememberthebacon.unit.common.extra_fragment_under_test";

    private Fragment mFragmentUnderTest;

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        return;
    }

    public void createFragmentUnderTest(int fragmentLayoutId) {
        // Figure out which Fragment is under test,
        // Handle any Intent Extras that get passed into any of our Fragments under test,
        // and return a new instance of the Fragment under test
        //int fragmentUnderTest = getIntent().getIntExtra("EXTRA_FRAGMENT_UNDER_TEST", -1);
        switch (fragmentLayoutId) {
            case 0: // Test just starting
                return;
            case R.layout.fragment_mylist:
                String testFile = getIntent()
                        .getStringExtra(GroceryListFragment.EXTRA_LIST_STORE_FILENAME);
                mFragmentUnderTest = GroceryListFragment.newInstance(testFile);
                break;
            case R.layout.fragment_details:
                String id = getIntent()
                        .getStringExtra(DetailsFragment.EXTRA_ID_OF_CONSUMABLE_TO_EDIT);
                if (id == null) {
                    id = DetailsFragment.EXTRA_CONSUMABLE_TO_EDIT_NOT_SET;
                }
                mFragmentUnderTest = DetailsFragment.newInstance(id);
                break;
            default:
                assert(false);
        }

        // update the main content by replacing fragments
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, mFragmentUnderTest)
                .commitAllowingStateLoss();
    }

    public Fragment getFragmentUnderTest() {
        return mFragmentUnderTest;
    }
}
