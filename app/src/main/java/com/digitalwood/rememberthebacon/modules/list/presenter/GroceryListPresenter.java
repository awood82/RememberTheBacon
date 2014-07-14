package com.digitalwood.rememberthebacon.modules.list.presenter;

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
        mInteractor.loadConsumables(20, this);
    }

    @Override
    public void onAddPressed() {
        mWireframe.navigateAddPressed();
    }

    @Override
    public void onItemClicked(int position) {
        Consumable item = mInteractor.getConsumableAt(position);
        mWireframe.navigateListItemPressed();
    }

    @Override
    public void onFinishedLoading(ArrayList<Consumable> consumables) {
        mView.setItems(consumables);
    }
}
