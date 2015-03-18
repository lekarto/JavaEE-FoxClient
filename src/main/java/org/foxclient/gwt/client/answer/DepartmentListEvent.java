package org.foxclient.gwt.client.answer;

import org.foxclient.gwt.client.entity.Department;

import java.io.Serializable;
import java.util.List;

public class DepartmentListEvent extends DataEvent implements Serializable {
    private List<Department> departments;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
