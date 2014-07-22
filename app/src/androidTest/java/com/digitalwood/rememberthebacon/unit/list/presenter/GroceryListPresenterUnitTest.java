package com.digitalwood.rememberthebacon.unit.list.presenter;

import com.digitalwood.rememberthebacon.modules.list.IGroceryListInteractorCbk;
import com.digitalwood.rememberthebacon.modules.list.applogic.GroceryListInteractor;
import com.digitalwood.rememberthebacon.modules.list.presenter.GroceryListPresenter;
import com.digitalwood.rememberthebacon.modules.list.ui.IGroceryListView;

import junit.framework.TestCase;

import static org.mockito.Mockito.*;

/**
 * Created by Andrew on 7/21/2014.
 * Copyright 2014
 */
public class GroceryListPresenterUnitTest extends TestCase {
    public void testOnResume_CallsLoadConsumables() {
        IGroceryListView mockView = mock(IGroceryListView.class);
        GroceryListInteractor mockInteractor = mock(GroceryListInteractor.class);
        GroceryListPresenter presenter = new GroceryListPresenter(mockView, null, mockInteractor);

        presenter.onResume();

        verify(mockInteractor, times(1)).loadConsumables((IGroceryListInteractorCbk)anyObject());
    }

    public void testOnPause_CallsSaveConsumables() {
        IGroceryListView mockView = mock(IGroceryListView.class);
        GroceryListInteractor mockInteractor = mock(GroceryListInteractor.class);
        GroceryListPresenter presenter = new GroceryListPresenter(mockView, null, mockInteractor);

        presenter.onPause();

        verify(mockInteractor, times(1)).saveConsumables((IGroceryListInteractorCbk)anyObject());
    }
}
