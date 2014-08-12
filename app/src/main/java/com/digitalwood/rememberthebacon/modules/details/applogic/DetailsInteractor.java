package com.digitalwood.rememberthebacon.modules.details.applogic;

import android.content.Context;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreAddCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreGetCbk;
import com.digitalwood.rememberthebacon.common.datastore.callbacks.IListStoreSetCbk;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.handlers.IDetailsInteractorLoadCbk;
import com.digitalwood.rememberthebacon.modules.details.handlers.IDetailsInteractorSaveCbk;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsFragment;

/**
 * Created by Andrew on 7/18/2014.
 * Copyright 2014
 */
public class DetailsInteractor implements IDetailsInteractor {

    private Context mContext;

    public DetailsInteractor(Context context) {
        mContext = context;
    }

    @Override
    public void saveConsumable(int index, Consumable c, final IDetailsInteractorSaveCbk cbk) {
        if (c.getName().isEmpty()) {
            cbk.onFinishedSaving(false, false);
        } else if (index == DetailsFragment.EXTRA_CONSUMABLE_INDEX_NOT_SET) {
            ListStore.getInstance(mContext).add(c, new IListStoreAddCbk() {
                @Override
                public void onAddFinished(boolean result) {
                    cbk.onFinishedSaving(result, true);
                }
            });
        } else {
            ListStore.getInstance(mContext).set(index, c, new IListStoreSetCbk() {
                @Override
                public void onSetFinished(boolean result) {
                    cbk.onFinishedSaving(result, false);
                }
            });
        }
    }

    @Override
    public void loadConsumable(int index, final IDetailsInteractorLoadCbk cbk) {
        if (index == DetailsFragment.EXTRA_CONSUMABLE_INDEX_NOT_SET) {
            cbk.onFinishedLoading(null);
        } else {
            ListStore.getInstance(mContext).get(index, new IListStoreGetCbk() {
                @Override
                public void onGetFinished(Consumable consumable) {
                    cbk.onFinishedLoading(consumable);
                }
            });
        }
    }
}
