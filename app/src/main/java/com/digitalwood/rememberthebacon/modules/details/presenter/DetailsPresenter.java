package com.digitalwood.rememberthebacon.modules.details.presenter;

import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.handlers.IDetailsInteractorLoadCbk;
import com.digitalwood.rememberthebacon.modules.details.applogic.IDetailsInteractor;
import com.digitalwood.rememberthebacon.modules.details.applogic.IDetailsWireframe;
import com.digitalwood.rememberthebacon.modules.details.handlers.IDetailsInteractorSaveCbk;
import com.digitalwood.rememberthebacon.modules.details.ui.IDetailsView;

/**
 * Created by Andrew on 7/14/2014.
 * Copyright 2014
 */
public class DetailsPresenter
        implements IDetailsPresenter, IDetailsInteractorLoadCbk, IDetailsInteractorSaveCbk {

    private IDetailsView mView;
    private IDetailsWireframe mWireframe;
    private IDetailsInteractor mInteractor;
    private int mIndex;
    private String mIdOfConsumableToEdit;
    private Consumable mConsumableToEdit;

    public DetailsPresenter(IDetailsView view, IDetailsWireframe wireframe, IDetailsInteractor interactor) {
        mView = view;
        mWireframe = wireframe;
        mInteractor = interactor;
    }

    @Override
    public void onResume(String id) {
        mIdOfConsumableToEdit = id;
        mInteractor.loadConsumable(id, this);
        mView.showKeyboard();
    }

    @Override
    public void onPause() {
        mView.hideKeyboard();
    }

    @Override
    public void okButtonPressed() {
        Consumable updatedConsumable = new Consumable(mConsumableToEdit);
        updatedConsumable.setName(mView.getItemName());
        mInteractor.saveConsumable(mIdOfConsumableToEdit, updatedConsumable, this);
    }

    @Override
    public void cancelButtonPressed() {
        mWireframe.navigateCancelPressed();
    }

    @Override
    public void onFinishedLoading(Consumable consumable) {
        mConsumableToEdit = consumable;
        if (consumable == null) {
            //mConsumableToEdit = new Consumable();
            //mConsumableToEdit.setId(mIdOfConsumableToEdit);
            mView.setTitle("Add New");
        } else {
            //mConsumableToEdit = consumable;
            mView.setItemName(consumable.getName());
            mView.setTitle("Edit Details");
        }
    }

    @Override
    public void onFinishedSaving(boolean wasSuccess, boolean wasAdded) {
        if (wasSuccess == true) {
            mWireframe.navigateOkPressed();
        } else {
            mWireframe.navigateCancelPressed();
        }
    }
}
