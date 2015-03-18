package org.foxclient.gwt.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.foxclient.gwt.client.entity.Employee;
import org.foxclient.gwt.client.entity.Filter;

public interface EmployeeServiceAsync extends GenericServiceAsync<Employee, Filter> {
    void getDepartmentsNames(AsyncCallback callback);
}