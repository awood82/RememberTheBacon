package com.digitalwood.rememberthebacon.modules.list.presenter;

import com.digitalwood.rememberthebacon.R;
import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.list.IGroceryListInteractorCbk;
import com.digitalwood.rememberthebacon.modules.list.applogic.IGroceryListInteractor;
import com.digitalwood.rememberthebacon.modules.list.applogic.IGroceryListWireframe;
import com.digitalwood.rememberthebacon.modules.list.ui.IGroceryListView;

import java.util.ArrayList;

/**
 * Created by Andrew on 7/11/2014.
 * Copyright 2014
 */
public class GroceryListPresenter implements IGroceryListPresenter, IGroceryListInteractorCbk {

    private IGroceryListView mView;
    private IGroceryListWireframe mWireframe;
    private IGroceryListInteractor mInteractor;

    public GroceryListPresenter(IGroceryListView view, IGroceryListWireframe wireframe, IGroceryListInteractor interactor) {
        mView = view;
        mWireframe = wireframe;
        mInteractor = interactor;
    }

    @Override
    public void onResume() {
        mView.setTitle("Grocery List");
        mInteractor.loadConsumables(this);
    }

    @Override
    public void onPause() {
        mInteractor.saveConsumables(this);
    }

    @Override
    public void onFinishedLoading(ArrayList<Consumable> consumables) {
        mView.setItems(consumables);
    }

    @Override
    public void onFinishedSaving() {
        mView.toast(R.string.list_saved_toast);
    }

    @Override
    public void onAddPressed() {
        mWireframe.navigateAddPressed();
    }

    @Override
    public void onItemClicked(final Consumable consumable) {
        mInteractor.toggleConsumableBought(consumable, this);
    }

    @Override
    public void onItemLongClicked(final Consumable consumable) {
        mWireframe.navigateListItemPressed(consumable);
    }

    @Override
    public void onFinishedTogglingBought(final Consumable consumable) {
        mView.toggleItemBought(consumable);
    }
}
