package com.digitalwood.rememberthebacon.modules.details.applogic;

import com.digitalwood.rememberthebacon.common.datastore.IListStore;
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

    private IListStore mListStore;

    public DetailsInteractor(IListStore listStore) {
        mListStore = listStore;
    }

    @Override
    public void saveConsumable(final String id, final Consumable newConsumable, final IDetailsInteractorSaveCbk cbk) {
        if (newConsumable.getName().isEmpty()) {
            cbk.onFinishedSaving(false, false);
        } else if (id.equals(DetailsFragment.EXTRA_CONSUMABLE_TO_EDIT_NOT_SET)) {
            mListStore.add(newConsumable, new IListStoreAddCbk() {
                @Override
                public void onAddFinished(boolean result) {
                    cbk.onFinishedSaving(result, true);
                }
            });
        //} else if (newConsumable.getName().equals(id)) { //TODO
        //    cbk.onFinishedSaving(false, false);
        } else {
            mListStore.set(id, newConsumable, new IListStoreSetCbk() {
                @Override
                public void onSetFinished(boolean result) {
                    cbk.onFinishedSaving(result, false);
                }
            });
        }
    }

    @Override
    public void loadConsumable(String id, final IDetailsInteractorLoadCbk cbk) {
        if (id.equals(DetailsFragment.EXTRA_CONSUMABLE_TO_EDIT_NOT_SET)) {
            cbk.onFinishedLoading(null);
        } else {
            mListStore.get(id, new IListStoreGetCbk() {
                @Override
                public void onGetFinished(Consumable consumable) {
                    cbk.onFinishedLoading(consumable);
                }
            });
        }
    }
}
