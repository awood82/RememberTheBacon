package com.digitalwood.rememberthebacon.common.view;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Andrew on 8/6/2014.
 * Copyright 2014
 */
public class RememberTheBaconApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Setup Parse.com for saving objects in the cloud
        Parse.enableLocalDatastore(this); // Well... locally for now
        Parse.initialize(
                this,
                "jDGd9RxDbhqSYNigzBItbllUFfclXvg2fAVWSoY9",
                "UyfioaJyqzVqWfWX1lDp15cQNe7KgyTH5KUtSqJc");
    }
}
