package com.digitalwood.rememberthebacon.unit.common;

import android.test.AndroidTestCase;

import com.digitalwood.rememberthebacon.common.ListStoreJsonSerializerTestCommon;
import com.digitalwood.rememberthebacon.common.datastore.ListStoreJsonSerializer;
import com.digitalwood.rememberthebacon.common.model.Consumable;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/20/2014.
 * Copyright 2014
 */
public class ListStoreJsonSerializerUnitTest extends AndroidTestCase {

    public void testSaveList_WhenGivenNoObjects_SavesNoObjects() {
        ListStoreJsonSerializer lsjs = ListStoreJsonSerializerTestCommon.getNewJsonSerializer(getContext());
        ArrayList<Consumable> list = ListStoreJsonSerializerTestCommon.createList(0);

        int numSaved = lsjs.saveList(list);

        assertEquals(0, numSaved);
    }

    public void testSaveList_WhenGivenOneObject_SavesOneObject() {
        ListStoreJsonSerializer lsjs = ListStoreJsonSerializerTestCommon.getNewJsonSerializer(getContext());
        ArrayList<Consumable> list = ListStoreJsonSerializerTestCommon.createList(1);

        int numSaved = lsjs.saveList(list);

        assertEquals(1, numSaved);
    }

    //public void testLoadList_WhenGivenOneObject_Calls

}
