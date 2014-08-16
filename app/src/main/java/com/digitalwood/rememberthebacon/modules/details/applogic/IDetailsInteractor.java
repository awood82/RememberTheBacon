package com.digitalwood.rememberthebacon.modules.details.applogic;

import com.digitalwood.rememberthebacon.common.model.Consumable;
import com.digitalwood.rememberthebacon.modules.details.handlers.IDetailsInteractorLoadCbk;
import com.digitalwood.rememberthebacon.modules.details.handlers.IDetailsInteractorSaveCbk;

/**
 * Created by Andrew on 7/18/2014.
 * Copyright 2014
 */
public interface IDetailsInteractor {

    void saveConsumable(String id, Consumable newConsumable, IDetailsInteractorSaveCbk cbk);

    void loadConsumable(String id, IDetailsInteractorLoadCbk cbk);
}
