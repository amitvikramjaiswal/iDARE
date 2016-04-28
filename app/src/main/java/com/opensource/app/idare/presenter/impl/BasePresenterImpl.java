package com.opensource.app.idare.presenter.impl;

import com.opensource.app.idare.presenter.presenters.BasePresenter;
import com.opensource.app.idare.service.ServiceFacade;
import com.opensource.app.idare.service.impl.ServiceFacadeImpl;

/**
 * Created by amitvikramjaiswal on 27/04/16.
 */
public class BasePresenterImpl implements BasePresenter {
    @Override
    public ServiceFacade getServiceFacade() {
        return ServiceFacadeImpl.getServiceFacade();
    }
}
