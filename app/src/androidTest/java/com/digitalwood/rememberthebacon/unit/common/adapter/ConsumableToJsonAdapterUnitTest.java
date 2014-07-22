package com.digitalwood.rememberthebacon.unit.common.adapter;

import com.digitalwood.rememberthebacon.common.adapter.ConsumableToJsonAdapter;
import com.digitalwood.rememberthebacon.common.model.Consumable;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Andrew on 7/21/2014.
 * Copyright 2014
 */
public class ConsumableToJsonAdapterUnitTest extends TestCase {
    public void testToJson_PassingNormalConsumable_ReturnsValidJson() {
        Consumable c = new Consumable("Bacon");

        JSONObject json = null;
        try {
            json = ConsumableToJsonAdapter.toJson(c);
        } catch (JSONException e) {
            fail("Threw exception");
        }

        assertEquals("{\"id\":\"" + c.getId().toString() + "\",\"bought\":false,\"name\":\"Bacon\"}", json.toString());
    }

    public void testToJson_ConsumableWithNoName_ReturnsValidJson() {
        Consumable c = new Consumable("");

        JSONObject json = null;
        try {
            json = ConsumableToJsonAdapter.toJson(c);
        } catch (JSONException e) {
            fail("Threw exception");
        }

        assertEquals("{\"id\":\"" + c.getId().toString() + "\",\"bought\":false,\"name\":\"\"}", json.toString());
    }

}
