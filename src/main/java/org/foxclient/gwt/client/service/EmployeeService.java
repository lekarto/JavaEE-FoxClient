package org.foxclient.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.foxclient.gwt.client.answer.DataEvent;
import org.foxclient.gwt.client.entity.Employee;
import org.foxclient.gwt.client.entity.Filter;

@RemoteServiceRelativePath("employeeservice")
public interface EmployeeService extends GenericService<Employee, Filter> {
    DataEvent getDepartmentsNames();
}
