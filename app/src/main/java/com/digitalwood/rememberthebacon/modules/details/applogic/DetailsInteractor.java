package com.digitalwood.rememberthebacon.modules.details.applogic;

import android.content.Context;

import com.digitalwood.rememberthebacon.common.datastore.ListStore;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.IDetailsInteractorCbk;
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
    public boolean saveConsumable(int index, Consumable c) {
        if (c.getName().isEmpty()) {
            return false;
        }

        if (index == DetailsFragment.EXTRA_CONSUMABLE_INDEX_NOT_SET) {
            return ListStore.getInstance(mContext).add(c);
        } else {
            return ListStore.getInstance(mContext).set(index, c);
        }
    }

    @Override
    public void loadConsumable(int index, IDetailsInteractorCbk cbk) {
        if (index == DetailsFragment.EXTRA_CONSUMABLE_INDEX_NOT_SET) {
            cbk.onFinishedLoading(null);
        } else {
            Consumable c = ListStore.getInstance(mContext).get(index);
            cbk.onFinishedLoading(c);
        }
    }
}
