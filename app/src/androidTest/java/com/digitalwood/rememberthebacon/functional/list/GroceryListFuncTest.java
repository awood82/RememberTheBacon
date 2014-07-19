package com.digitalwood.rememberthebacon.functional.list;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsActivity;
import com.digitalwood.rememberthebacon.modules.list.ui.GroceryListActivity;
import com.digitalwood.rememberthebacon.R;

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
/*
    public void testUiAddNew_WhenPressed_StartsDetailsActivity() {
        Instrumentation.ActivityMonitor am = getInstrumentation().addMonitor(
                DetailsActivity.class.getName(), null, false);
        GroceryListActivity activity = getActivity();

        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
        getInstrumentation().invokeMenuActionSync(activity, R.id.menu_item_new_consumable, 0);

        Activity newActivity = getInstrumentation().waitForMonitorWithTimeout(am, MONITOR_TIMEOUT_MS);
        if (newActivity != null) {
            newActivity.finish();
        }
        assertTrue(getInstrumentation().checkMonitorHit(am, 1));
    }

    public void testUi_LongClickingAListItem_StartsDetailActivity() {
        Instrumentation.ActivityMonitor am = getInstrumentation().addMonitor(
                DetailsActivity.class.getName(), null, false);
        insertConsumable("Bacon");
        GroceryListActivity activity = getActivity();

        // By using key presses instead of ListView's listItemClick, I can catch
        // GOTCHA errors such as listView items being focusable
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_PAGE_DOWN);
        KeyEvent longPress = new KeyEvent(SystemClock.uptimeMillis(), SystemClock.uptimeMillis() + 3000, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_CENTER, 1);
        //getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);
        getInstrumentation().sendKeySync(longPress);

        Activity newActivity = getInstrumentation().waitForMonitorWithTimeout(am, MONITOR_TIMEOUT_MS);
        if (newActivity != null) {
            newActivity.finish();
        }
        deleteConsumables();
        assertTrue(getInstrumentation().checkMonitorHit(am, 1));
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
