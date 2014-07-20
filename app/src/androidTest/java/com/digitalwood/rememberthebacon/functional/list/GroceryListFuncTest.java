package com.digitalwood.rememberthebacon.functional.list;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsActivity;
import com.digitalwood.rememberthebacon.modules.list.ui.GroceryListActivity;
import com.digitalwood.rememberthebacon.R;
import com.digitalwood.rememberthebacon.modules.list.ui.GroceryListFragment;

/**
 * Created by Andrew on 7/9/2014.
 * Copyright 2014
 */
public class GroceryListFuncTest extends ActivityInstrumentationTestCase2<GroceryListActivity> {

    private static final int MONITOR_TIMEOUT_MS = 1000;

    public GroceryListFuncTest() {
        super(GroceryListActivity.class);
    }

    public void testUi_InitialState_WidgetsAreInExpectedStates() {
        GroceryListActivity activity = getActivity();

        assertEquals("Grocery List", activity.getTitle());
    }

    public void testUiAddNew_WhenPressed_StartsDetailsActivity() throws InterruptedException {
        Instrumentation.ActivityMonitor am = getInstrumentation().addMonitor(
                DetailsActivity.class.getName(), null, false);
        GroceryListActivity activity = getActivity();

        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
        getInstrumentation().invokeMenuActionSync(activity, R.id.menu_item_new_consumable, 0);
        getInstrumentation().waitForIdleSync();

        Activity newActivity = getInstrumentation().waitForMonitorWithTimeout(am, MONITOR_TIMEOUT_MS);
        if (newActivity != null) {
            newActivity.finish();
        }
        assertTrue(getInstrumentation().checkMonitorHit(am, 1));
    }

    /* TODO: Figure out how to simulate a long press and hold
    public void testUi_LongClickingAListItem_StartsDetailActivity() {
        Instrumentation.ActivityMonitor am = getInstrumentation().addMonitor(
                DetailsActivity.class.getName(), null, false);
        insertConsumable("Bacon");
        GroceryListActivity activity = getActivity();

        // By using key presses instead of ListView's listItemClick, I can catch
        // GOTCHA errors such as listView items being focusable
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_PAGE_DOWN);
        KeyEvent longPress = new KeyEvent(SystemClock.uptimeMillis()+1000, SystemClock.uptimeMillis() + 3000, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_CENTER, 1);
        getInstrumentation().sendKeySync(longPress);
        getInstrumentation().waitForIdleSync();

        Activity newActivity = getInstrumentation().waitForMonitorWithTimeout(am, MONITOR_TIMEOUT_MS);
        if (newActivity != null) {
            newActivity.finish();
        }
        deleteConsumables();
        assertTrue(getInstrumentation().checkMonitorHit(am, 1));
    }*/

    public void testUi_ClickingAListItem_MarksItAsBought() {
        insertConsumable("Bacon");
        GroceryListActivity activity = getActivity();

        // By using key presses instead of ListView's listItemClick, I can catch
        // GOTCHA errors such as listView items being focusable
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_PAGE_DOWN);
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);
        getInstrumentation().waitForIdleSync();

        deleteConsumables();
        GroceryListFragment fragment = (GroceryListFragment) activity.getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainer);
        ListView lv = fragment.getListView();
        CheckBox check = (CheckBox)lv.getChildAt(0).findViewById(R.id.bought_checkBox);
        assertTrue(check.isChecked());
    }
/*
    @UiThreadTest
    public void testUi_ItemClick_TogglesCheckBox() {
        insertConsumable("Bacon");
        GroceryListActivity activity = getActivity();
        GroceryListFragment fragment = (GroceryListFragment) activity.getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainer);
        ListView lv = fragment.getListView();
        View view = lv.getChildAt(0);

        lv.performItemClick(view, 0, 0);

        deleteConsumables();
        CheckBox check = (CheckBox)view.findViewById(R.id.bought_checkBox);
        assertTrue(check.isChecked());
    }
*/

    private void insertConsumable(String name) {
        Consumable c = new Consumable(name);
        ListStore.getInstance(getInstrumentation().getContext()).add(c);
    }

    private void deleteConsumables() {
        ListStore.getInstance(getInstrumentation().getContext()).deleteAll();
    }
}
