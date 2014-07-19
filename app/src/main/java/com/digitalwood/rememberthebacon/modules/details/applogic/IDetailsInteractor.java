package com.digitalwood.rememberthebacon.modules.details.applogic;

import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.IDetailsInteractorCbk;

import java.util.UUID;

/**
 * Created by Andrew on 7/18/2014.
 * Copyright 2014
 */
public interface IDetailsInteractor {

    public boolean saveConsumable(int index, Consumable c);

    public void loadConsumable(int index, IDetailsInteractorCbk cbk);
}
