package com.digitalwood.rememberthebacon.functional.list;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.datastore.ListStoreJsonSerializer;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreAddCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreDeleteAllCbk;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.common.view.TestFragmentActivity;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsFragment;
import com.digitalwood.rememberthebacon.R;
import com.digitalwood.rememberthebacon.modules.list.ui.GroceryListFragment;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/9/2014.
 * Copyright 2014
 */
public class GroceryListFuncTest extends ActivityInstrumentationTestCase2<TestFragmentActivity> {

    private static final String TEST_LIST_STORE_FILENAME = "test.json";
    private static final long MONITOR_TIMEOUT_MS = 3000;
    private volatile boolean mCallbackFired;

    public GroceryListFuncTest() {
        super(TestFragmentActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setUpIntent(); // Call first
    }

    public void testUi_InitialState_WidgetsAreInExpectedStates() {
        TestFragmentActivity activity = setUpActivity();

        assertEquals("Grocery List", activity.getTitle());
    }

    public void testUiAddNew_WhenPressed_StartsDetailsActivity() throws InterruptedException {
        TestFragmentActivity activity = setUpActivity();

        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
        getInstrumentation().invokeMenuActionSync(activity, R.id.menu_item_new_consumable, 0);
        getInstrumentation().waitForIdleSync();

        findDetailsFragment();
    }

    public void testUi_LongClickingAListItem_StartsDetailActivity() {
        startDetailsFragmentWithOneCheckedItem();

        deleteConsumables();
        Fragment currentFragment = findDetailsFragment();
        assertTrue(currentFragment instanceof DetailsFragment);
    }

    public void testUi_ClickingAListItem_MarksItAsBought() {
        insertConsumable("Bacon", false);
        TestFragmentActivity activity = setUpActivity();

        // By using key presses instead of ListView's listItemClick, I can catch
        // GOTCHA errors such as listView items being focusable
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN);
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);
        getInstrumentation().waitForIdleSync();

        GroceryListFragment fragment = getFragment();
        ListView lv = fragment.getListView();
        CheckBox check = (CheckBox)lv.getChildAt(0).findViewById(R.id.bought_checkBox);
        deleteConsumables();
        assertTrue(check.isChecked());
    }

    public void testUi_ItemClick_TogglesCheckBox() {
        insertConsumable("Bacon", false);
        TestFragmentActivity activity = setUpActivity();
        GroceryListFragment fragment = getFragment();
        final ListView lv = fragment.getListView();
        final View view = lv.getChildAt(0);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lv.performItemClick(view, 0, 0);
            }
        });
        getInstrumentation().waitForIdleSync();

        deleteConsumables();
        CheckBox check = (CheckBox)view.findViewById(R.id.bought_checkBox);
        assertTrue(check.isChecked());
    }

    public void testIntegration_WhenPressingCancelToReturnToGroceryListScreen_PreviouslyToggledCheckboxesAreStillToggled() {
        startDetailsFragmentWithOneCheckedItem();
        DetailsFragment currentFragment = findDetailsFragment();

        final Button cancelButton = (Button) currentFragment.getView().findViewById(R.id.details_cancel_button);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cancelButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        final View newItemView = getFragment().getListView().getChildAt(0);
        deleteConsumables();
        CheckBox check = (CheckBox)newItemView.findViewById(R.id.bought_checkBox);
        assertTrue(check.isChecked());
    }

    public void testIntegration_WhenPressingOkToReturnToGroceryListScreen_PreviouslyToggledCheckboxesAreStillToggled() {
        startDetailsFragmentWithOneCheckedItem();
        DetailsFragment currentFragment = findDetailsFragment();

        final Button okButton = (Button) currentFragment.getView().findViewById(R.id.details_ok_button);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                okButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        final View newItemView = getFragment().getListView().getChildAt(0);
        deleteConsumables();
        CheckBox check = (CheckBox)newItemView.findViewById(R.id.bought_checkBox);
        assertTrue(check.isChecked());
    }




    private void setUpIntent() {
        Intent intent = new Intent();
        intent.putExtra(GroceryListFragment.EXTRA_LIST_STORE_FILENAME, TEST_LIST_STORE_FILENAME);
        setActivityIntent(intent);
    }

    private TestFragmentActivity setUpActivity() {
        TestFragmentActivity activity = getActivity();
        activity.createFragmentUnderTest(R.layout.fragment_mylist);
        getInstrumentation().waitForIdleSync();

        return activity;
    }

    private GroceryListFragment getFragment() {
        GroceryListFragment fragment = (GroceryListFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainer);
        return fragment;
    }

    private DetailsFragment findDetailsFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.fragmentContainer);

        return (DetailsFragment)currentFragment;
    }

    public void startDetailsFragmentWithOneCheckedItem() {
        insertConsumable("Bacon", true);
        TestFragmentActivity activity = setUpActivity();
        final View itemView = getFragment().getListView().getChildAt(0);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                itemView.performLongClick();
            }
        });
        getInstrumentation().waitForIdleSync();
    }


    private void insertConsumable(String name, boolean bought) {
        ArrayList<Consumable> list = new ArrayList<Consumable>();
        Consumable c = new Consumable(name);
        c.setBought(bought);
        ListStore.getInstance(getActivity()).add(
                c,
                new IListStoreAddCbk() {
                    @Override
                    public void onAddFinished(boolean result) {
                        mCallbackFired = true;
                    }
                });
        waitForCallback();
        list.add(c);
        ListStoreJsonSerializer serializer = getListStoreJsonSerializer();
        serializer.saveList(list);
    }

    private void deleteConsumables() {
        ListStore.getInstance(getActivity()).deleteAll(new IListStoreDeleteAllCbk() {
            @Override
            public void onDeleteAllFinished() {
                mCallbackFired = true;
            }
        });
        waitForCallback();

        ArrayList<Consumable> list = new ArrayList<Consumable>();
        ListStoreJsonSerializer serializer = getListStoreJsonSerializer();
        serializer.saveList(list);
    }

    private ListStoreJsonSerializer getListStoreJsonSerializer() {
        return new ListStoreJsonSerializer(
                getActivity(),
                TEST_LIST_STORE_FILENAME);
    }

    private void waitForCallback() {
        while (mCallbackFired == false);
        mCallbackFired = false; // reset
    }
}
