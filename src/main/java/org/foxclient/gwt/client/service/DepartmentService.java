package org.foxclient.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.foxclient.gwt.client.entity.Department;
import org.foxclient.gwt.client.entity.Filter;

import java.util.List;

@RemoteServiceRelativePath("departmentservice")
public interface DepartmentService extends RemoteService, GenericService<Department, Filter> {
}