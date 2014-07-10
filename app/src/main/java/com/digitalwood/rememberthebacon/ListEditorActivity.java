package com.digitalwood.rememberthebacon;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Andrew on 7/9/2014.
 * Copyright 2014
 */
public class ListEditorActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ListEditorFragment();
    }
}
