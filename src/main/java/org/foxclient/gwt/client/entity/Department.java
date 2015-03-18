package org.foxclient.gwt.client.entity;

import java.io.Serializable;

public class Department implements Serializable {
    private Integer id;
    private String name;
    private Float averageSalary = 0f;
    private Integer employeeCount = 0;

    public Department() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAverageSalary() {
        return Math.round(100.0 * averageSalary) / 100.0f;
    }

    public void setAverageSalary(Float averageSalary) {
        this.averageSalary = averageSalary;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }
}
