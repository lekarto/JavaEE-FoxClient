package org.foxclient.gwt.client.impl;

import  com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import org.foxclient.gwt.client.service.EmployeeService;
import org.foxclient.gwt.client.service.EmployeeServiceAsync;
import org.foxclient.gwt.client.view.EmployeeView;
import org.foxclient.gwt.client.view.MainView;
import org.foxclient.gwt.client.view.abstraction.AbstractView;

public class EmployeeServiceClient {
    private EmployeeServiceAsync service;
    private EmployeeView view;
    private MainView mainPage;
    
    public EmployeeServiceClient(String url, MainView mainPage) {
        service = GWT.create(EmployeeService.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) service;
        endpoint.setServiceEntryPoint(url);
        this.mainPage = mainPage;
        showEmployeeView();
    }
    
    public AbstractView getView() {
        return view;
    }

    public void showEmployeeView() {
        view = new EmployeeView(this);
        mainPage.updateContent(view);
    }

    public EmployeeServiceAsync getService() {
        return service;
    }
}
