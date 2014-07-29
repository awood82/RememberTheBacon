package com.digitalwood.rememberthebacon.modules.list.ui;

import android.support.v4.app.Fragment;

import com.digitalwood.rememberthebacon.common.view.SingleFragmentActivity;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

public class GroceryListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        Parse.initialize(
                this,
                "jDGd9RxDbhqSYNigzBItbllUFfclXvg2fAVWSoY9",
                "UyfioaJyqzVqWfWX1lDp15cQNe7KgyTH5KUtSqJc");

        return new GroceryListFragment();
    }

}
