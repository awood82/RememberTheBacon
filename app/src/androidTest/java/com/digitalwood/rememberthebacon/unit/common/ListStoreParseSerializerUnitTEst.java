package com.digitalwood.rememberthebacon.unit.common;

import android.test.AndroidTestCase;

import com.digitalwood.rememberthebacon.common.ListStoreJsonSerializerTestCommon;
import com.digitalwood.rememberthebacon.common.ListStoreParseSerializerTestCommon;
import com.digitalwood.rememberthebacon.common.datastore.ListStoreJsonSerializer;
import com.digitalwood.rememberthebacon.common.datastore.ListStoreParseSerializer;
import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/29/2014.
 * Copyright 2014
 */
public class ListStoreParseSerializerUnitTest extends AndroidTestCase {
    public void testSaveList_WhenGivenNoObjects_SavesNoObjects() {
        ListStoreParseSerializer lsps = ListStoreParseSerializerTestCommon.getNewParseSerializer(getContext());
        ArrayList<Consumable> list = ListStoreParseSerializerTestCommon.createList(0);

        int numSaved = lsps.saveList(list);

        assertEquals(0, numSaved);
    }

    public void testSaveList_WhenGivenOneObject_SavesOneObject() {
        ListStoreParseSerializer lsps = ListStoreParseSerializerTestCommon.getNewParseSerializer(getContext());
        ArrayList<Consumable> list = ListStoreParseSerializerTestCommon.createList(1);

        int numSaved = lsps.saveList(list);

        assertEquals(1, numSaved);
    }
}
