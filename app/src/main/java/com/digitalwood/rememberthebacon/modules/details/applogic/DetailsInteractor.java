package com.digitalwood.rememberthebacon.modules.details.applogic;

import android.content.Context;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
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
    public void saveConsumable(int index, Consumable c, IDetailsInteractorSaveCbk cbk) {
        if (c.getName().isEmpty()) {
            cbk.onFinishedSaving(false, false);
        } else if (index == DetailsFragment.EXTRA_CONSUMABLE_INDEX_NOT_SET) {
            cbk.onFinishedSaving(ListStore.getInstance(mContext).add(c), true);
        } else {
            cbk.onFinishedSaving(ListStore.getInstance(mContext).set(index, c), false);
        }
    }

    @Override
    public void loadConsumable(int index, IDetailsInteractorLoadCbk cbk) {
        if (index == DetailsFragment.EXTRA_CONSUMABLE_INDEX_NOT_SET) {
            cbk.onFinishedLoading(null);
        } else {
            cbk.onFinishedLoading(ListStore.getInstance(mContext).get(index));
        }
    }
}
