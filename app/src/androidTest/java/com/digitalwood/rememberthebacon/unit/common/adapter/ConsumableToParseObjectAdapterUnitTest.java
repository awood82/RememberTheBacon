package com.digitalwood.rememberthebacon.unit.common.adapter;

import com.digitalwood.rememberthebacon.common.adapter.ConsumableToJsonAdapter;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.parse.ParseObject;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Andrew on 7/21/2014.
 * Copyright 2014
 */
public class ConsumableToParseObjectAdapterUnitTest extends TestCase {
    /* TODO: Not sure how to test the values in a ParseObject
    public void testToParseObject_PassingNormalConsumable_ReturnsValidParseObject() {
        Consumable c = new Consumable("Bacon");

        ParseObject parseObject = null;
        parseObject = ConsumableToParseObjectAdapter.toParseObject(c);

        assertEquals("{\"id\":\"" + c.getId().toString(), parseObject.getJSONObject("id").toString());
        assertEquals("{\"bought\":false", parseObject.getJSONObject("bought").toString());
        assertEquals("{\"name\":Bacon", parseObject.getJSONObject("name").toString());
        //assertEquals("{\"id\":\"" + c.getId().toString() + "\",\"bought\":false,\"name\":\"Bacon\"}", parseObject.toString());
    }

    public void testToParseObject_ConsumableWithNoName_ReturnsValidParseObject() {
        Consumable c = new Consumable("");

        ParseObject parseObject = null;
        parseObject = ConsumableToParseObjectAdapter.toParseObject(c);

        assertEquals("{\"id\":\"" + c.getId().toString() + "\",\"bought\":false,\"name\":\"\"}", parseObject.toString());
    }*/
}
