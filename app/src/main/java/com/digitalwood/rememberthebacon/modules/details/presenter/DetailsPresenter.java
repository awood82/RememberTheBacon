package com.digitalwood.rememberthebacon.modules.details.presenter;

import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.IDetailsInteractorCbk;
import com.digitalwood.rememberthebacon.modules.details.applogic.IDetailsInteractor;
import com.digitalwood.rememberthebacon.modules.details.applogic.IDetailsWireframe;
import com.digitalwood.rememberthebacon.modules.details.ui.IDetailsView;

/**
 * Created by Andrew on 7/14/2014.
 * Copyright 2014
 */
public class DetailsPresenter implements IDetailsPresenter, IDetailsInteractorCbk {

    private IDetailsView mView;
    private IDetailsWireframe mWireframe;
    private IDetailsInteractor mInteractor;
    private int mIndex;

    public DetailsPresenter(IDetailsView view, IDetailsWireframe wireframe, IDetailsInteractor interactor) {
        mView = view;
        mWireframe = wireframe;
        mInteractor = interactor;
    }

    @Override
    public void onResume(int index) {
        mIndex = index;
        mInteractor.loadConsumable(index, this);
    }

    @Override
    public void okButtonPressed() {
        mInteractor.saveConsumable(mIndex, new Consumable(mView.getItemName()));
        mWireframe.navigateOkPressed();
    }

    @Override
    public void cancelButtonPressed() {
        mWireframe.navigateCancelPressed();
    }

    @Override
    public void onFinishedLoading(Consumable consumable) {
        if (consumable == null) {
            mView.setTitle("Add New");
        } else {
            mView.setItemName(consumable.getName());
            mView.setTitle("Edit Details");
        }
    }
}
