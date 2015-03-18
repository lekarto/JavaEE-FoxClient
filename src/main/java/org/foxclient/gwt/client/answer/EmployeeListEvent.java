package org.foxclient.gwt.client.answer;

import org.foxclient.gwt.client.entity.Employee;

import java.io.Serializable;
import java.util.List;

public class EmployeeListEvent extends DataEvent implements Serializable {
    private List<Employee> employees;

    public EmployeeListEvent() { }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
