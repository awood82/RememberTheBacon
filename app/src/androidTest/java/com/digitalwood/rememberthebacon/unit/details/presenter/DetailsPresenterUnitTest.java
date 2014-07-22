package com.digitalwood.rememberthebacon.unit.details.presenter;

import android.test.AndroidTestCase;

import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.applogic.DetailsInteractor;
import com.digitalwood.rememberthebacon.modules.details.applogic.DetailsWireframe;
import com.digitalwood.rememberthebacon.modules.details.applogic.IDetailsInteractor;
import com.digitalwood.rememberthebacon.modules.details.applogic.IDetailsWireframe;
import com.digitalwood.rememberthebacon.modules.details.presenter.DetailsPresenter;
import com.digitalwood.rememberthebacon.modules.details.ui.DetailsFragment;
import com.digitalwood.rememberthebacon.modules.details.ui.IDetailsView;

import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

/**
 * Created by Andrew on 7/18/2014.
 * Copyright 2014
 */
public class DetailsPresenterUnitTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        System.setProperty("dexmaker.dexcache", getContext().getCacheDir().getPath());
    }

    public void testOkButtonPressed_WhenNameIsSetInView_ItemWithThatNameIsSaved() {
        IDetailsView mockView = mock(IDetailsView.class);
        IDetailsWireframe mockWireframe = mock(DetailsWireframe.class);
        IDetailsInteractor mockInteractor = mock(DetailsInteractor.class);
        when(mockView.getItemName()).thenReturn("Eggs");
        DetailsPresenter presenter = new DetailsPresenter(mockView, mockWireframe, mockInteractor);

        presenter.okButtonPressed();

        //verify(mockInteractor).saveConsumable(anyInt(), eq( eggs ));
        ArgumentCaptor<Consumable> captor = ArgumentCaptor.forClass(Consumable.class);
        verify(mockInteractor).saveConsumable(anyInt(), captor.capture());
        assertEquals("Eggs", captor.getValue().getName());
    }

}
