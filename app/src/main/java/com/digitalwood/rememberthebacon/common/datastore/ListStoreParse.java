package com.digitalwood.rememberthebacon.common.datastore;

import android.util.Log;

import com.digitalwood.rememberthebacon.common.adapter.ConsumableToParseObjectAdapter;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreAddCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreDeleteAllCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreGetCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreIterCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreSetCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreSizeCbk;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.parse.CountCallback;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Andrew on 8/13/2014.
 * Copyright 2014
 */
public class ListStoreParse implements IListStore {
    private static final String TAG = "ListStoreParse";
    private static final String TEMP_LIST_UUID = "00000000-0000-0000-0000-000000000000"; //TODO

    public void add(final Consumable consumable, final IListStoreAddCbk cbk) {
        // Create list item
        final ParseObject parseObject = ConsumableToParseObjectAdapter.toParseObject(consumable);

        // Save list item on network, or locally if network is not available
        parseObject.pinInBackground(consumable.getId(), new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    //consumable.setId(parseObject.getObjectId());
                    cbk.onAddFinished(true);
                    //parseObject.saveEventually(); // TODO: ???
                } else {
                    // TODO: What to do if local calls fail?
                    e.printStackTrace();
                    cbk.onAddFinished(false);
                }
            }
        });
    }

    @Override
    public void set(String id, final Consumable newConsumable, final IListStoreSetCbk cbk) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ConsumableToParseObjectAdapter.ITEM_TABLE_NAME);
        query.fromLocalDatastore();
        //query.fromPin(id);
        query.getInBackground(id, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    ConsumableToParseObjectAdapter.updateParseObject(parseObject, newConsumable);
                    final ParseObject finalParseObject = parseObject;
                    parseObject.pinInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                cbk.onSetFinished(true);
                                //finalParseObject.saveEventually(); // TODO: ???
                            } else {
                                cbk.onSetFinished(false);
                            }
                        }
                    });
                } else {
                    cbk.onSetFinished(false);
                }
            }
        });
    }

    @Override
    public void get(final String id, final IListStoreGetCbk cbk) {
        // Get list item
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ConsumableToParseObjectAdapter.ITEM_TABLE_NAME);
        query.fromLocalDatastore();
        query.getInBackground(id, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    cbk.onGetFinished(ConsumableToParseObjectAdapter.fromParseObject(parseObject));
                } else {
                    cbk.onGetFinished(null);
                }
            }
        });
    }

    @Override
    public void deleteAll(final IListStoreDeleteAllCbk cbk) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ConsumableToParseObjectAdapter.ITEM_TABLE_NAME);
        query.fromLocalDatastore();
        query.fromPin();
        query.whereEqualTo(ConsumableToParseObjectAdapter.JSON_LIST_ID, TEMP_LIST_UUID);

        final DeleteCallback whenItemLookupIsDone = new DeleteCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    cbk.onDeleteAllFinished(true);
                } else {
                    e.printStackTrace();
                    Log.e(TAG, "Error deleting items");
                    cbk.onDeleteAllFinished(false);
                }
            }
        };

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    ParseObject.unpinAllInBackground(parseObjects, new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                cbk.onDeleteAllFinished(true);
                            } else {
                                e.printStackTrace();
                                Log.e(TAG, "Error deleting items");
                                cbk.onDeleteAllFinished(false);
                            }
                        }
                    });

                    //for (ParseObject parseObject : parseObjects) {
                    //    parseObject.deleteEventually();
                    //}
                } else {
                    Log.e(TAG, "Error getting items");
                    cbk.onDeleteAllFinished(false);
                }
            }
        });
    }

    @Override
    public void listIterator(final IListStoreIterCbk cbk) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ConsumableToParseObjectAdapter.ITEM_TABLE_NAME);
        query.fromLocalDatastore();
        query.whereEqualTo(ConsumableToParseObjectAdapter.JSON_LIST_ID, TEMP_LIST_UUID);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    // Get list items
                    ArrayList<Consumable> listItems = new ArrayList<Consumable>();
                    ListIterator<ParseObject> iter = parseObjects.listIterator();
                    while (iter.hasNext()) {
                        ParseObject parseObject = iter.next();
                        Consumable c = ConsumableToParseObjectAdapter.fromParseObject(parseObject);
                        listItems.add(c);
                    }
                    cbk.onListIteratorFinished(listItems.listIterator());
                } else {
                    Log.e(TAG, "Error getting items");
                    cbk.onListIteratorFinished(new ArrayList<Consumable>().listIterator());
                }
            }
        });
    }

    @Override
    public void size(final IListStoreSizeCbk cbk) {
        // Get list item count
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ConsumableToParseObjectAdapter.ITEM_TABLE_NAME);
        query.fromLocalDatastore();
        query.whereEqualTo(ConsumableToParseObjectAdapter.JSON_LIST_ID, TEMP_LIST_UUID);
        query.countInBackground(new CountCallback() {
            @Override
            public void done(int count, ParseException e) {
                if (e == null) {
                    cbk.onSizeFinished(count);
                } else {
                    Log.e(TAG, "Error getting count");
                    cbk.onSizeFinished(0);
                }
            }
        });
    }

    @Override
    public void serialize(IListStoreSerializer serializer) {
        //serializer.saveList(mConsumables);
    }

    @Override
    public void deserialize(IListStoreSerializer serializer) {
        //mConsumables = serializer.loadList();
    }
}
