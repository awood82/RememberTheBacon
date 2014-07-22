package com.digitalwood.rememberthebacon.functional.common;

import android.content.Context;
import android.test.AndroidTestCase;

import com.digitalwood.rememberthebacon.common.ListStoreJsonSerializerTestCommon;
import com.digitalwood.rememberthebacon.common.datastore.ListStoreJsonSerializer;
import com.digitalwood.rememberthebacon.common.model.Consumable;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Andrew on 7/20/2014.
 * Copyright 2014
 */
public class ListStoreJsonSerializerFuncTest extends AndroidTestCase {

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        deleteTestListFile();
    }

    public void testSave_WhenGivenNoObjects_CreatesFile() {
        ListStoreJsonSerializer lsjs = ListStoreJsonSerializerTestCommon.getNewJsonSerializer(getContext());
        ArrayList<Consumable> list = ListStoreJsonSerializerTestCommon.createList(0);

        lsjs.saveList(list);

        assertEquals(true, getContext().deleteFile(ListStoreJsonSerializerTestCommon.getDefaultLocation()));
    }

    public void testSave_WhenGivenOneObject_CreatesFile() {
        ListStoreJsonSerializer lsjs = ListStoreJsonSerializerTestCommon.getNewJsonSerializer(getContext());
        ArrayList<Consumable> list = ListStoreJsonSerializerTestCommon.createList(1);

        lsjs.saveList(list);

        assertEquals(true, getContext().deleteFile(ListStoreJsonSerializerTestCommon.getDefaultLocation()));
    }

    public void testLoad_AfterSavingOneObject_LoadsOneObject() {
        ListStoreJsonSerializer lsjs = ListStoreJsonSerializerTestCommon.getNewJsonSerializer(getContext());
        ArrayList<Consumable> list = ListStoreJsonSerializerTestCommon.createList(1);

        lsjs.saveList(list);
        ArrayList<Consumable> loadedList = lsjs.loadList();

        assertEquals(1, loadedList.size());
    }

    public void testLoad_AfterSavingOneObject_LoadsThatObject() {
        ListStoreJsonSerializer lsjs = ListStoreJsonSerializerTestCommon.getNewJsonSerializer(getContext());
        ArrayList<Consumable> list = ListStoreJsonSerializerTestCommon.createList(1); // Bacon #0

        lsjs.saveList(list);
        ArrayList<Consumable> loadedList = lsjs.loadList();

        assertEquals("Bacon #0", loadedList.get(0).getName());
    }

    public void testLoad_AfterSavingTwoObjects_LoadsThoseObjects() {
        ListStoreJsonSerializer lsjs = ListStoreJsonSerializerTestCommon.getNewJsonSerializer(getContext());
        ArrayList<Consumable> list = ListStoreJsonSerializerTestCommon.createList(2); // Bacon #0-1

        lsjs.saveList(list);
        ArrayList<Consumable> loadedList = lsjs.loadList();

        assertEquals("Bacon #0", loadedList.get(0).getName());
        assertEquals("Bacon #1", loadedList.get(1).getName());
    }

    public void testLoad_AfterSavingOneObjectAndDeletingListFile_LoadsNoObjects() {
        ListStoreJsonSerializer lsjs = ListStoreJsonSerializerTestCommon.getNewJsonSerializer(getContext());
        ArrayList<Consumable> list = ListStoreJsonSerializerTestCommon.createList(1);

        lsjs.saveList(list);
        deleteTestListFile();
        ArrayList<Consumable> loadedList = lsjs.loadList();

        assertEquals(0, loadedList.size());
    }



    private void deleteTestListFile() {
        getContext().deleteFile(ListStoreJsonSerializerTestCommon.getDefaultLocation());
    }
}