package com.digitalwood.rememberthebacon.modules.details.presenter;

import com.digitalwood.rememberthebacon.modules.details.applogic.IDetailsInteractor;
import com.digitalwood.rememberthebacon.modules.details.applogic.IDetailsWireframe;
import com.digitalwood.rememberthebacon.modules.details.ui.IDetailsView;

/**
 * Created by Andrew on 7/14/2014.
 * Copyright 2014
 */
public class DetailsPresenter implements IDetailsPresenter {

    private IDetailsView mView;
    private IDetailsWireframe mWireframe;
    private IDetailsInteractor mInteractor;

    public DetailsPresenter(IDetailsView view, IDetailsWireframe wireframe, IDetailsInteractor interactor) {
        mView = view;
        mWireframe = wireframe;
        mInteractor = interactor;
    }

    //TODO: Add buttons for OK and Cancel, and call wireframe's methods
}
