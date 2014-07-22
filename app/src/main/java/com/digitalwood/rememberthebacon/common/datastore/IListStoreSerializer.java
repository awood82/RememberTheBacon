package com.digitalwood.rememberthebacon.common.datastore;

import com.digitalwood.rememberthebacon.common.model.Consumable;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Andrew on 7/21/2014.
 * Copyright 2014
 */
public interface IListStoreSerializer {
    public int saveList(ArrayList<Consumable> list);
    public ArrayList<Consumable> loadList();
}
