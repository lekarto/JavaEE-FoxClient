package org.foxclient.gwt.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import org.foxclient.gwt.client.entity.Filter;
import org.foxclient.gwt.client.service.DepartmentService;
import org.foxclient.gwt.client.service.DepartmentServiceAsync;
import org.foxclient.gwt.client.view.DepartmentView;
import org.foxclient.gwt.client.view.MainView;
import org.foxclient.gwt.client.view.abstraction.AbstractView;

public class DepartmentServiceClient {
    private DepartmentServiceAsync service;
    private AbstractView view;
    private MainView mainPage;
    private Filter filter;

    public DepartmentServiceClient(String url, MainView mainPage, Filter filter) {
        this.mainPage = mainPage;
        this.filter = filter;
        service = GWT.create(DepartmentService.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) service;
        endpoint.setServiceEntryPoint(url);
        showDepartmentView();
    }

    public AbstractView getView() {
        return view;
    }

    public DepartmentServiceAsync getService() {
        return service;
    }

    public void showDepartmentView() {
        view = new DepartmentView(this, filter);
        mainPage.updateContent(view);
    }
}
