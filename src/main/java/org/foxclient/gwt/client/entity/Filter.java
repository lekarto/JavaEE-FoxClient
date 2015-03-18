package org.foxclient.gwt.client.entity;

import java.io.Serializable;

public class Filter implements Serializable {
    public static String DEPARTMENT_ID = "dep";
    public Integer departmentID = null;
    public static String DEPARTMENT_NAME = "dep_name";
    public String departmentName = null;
    public static String SEX = "sex";
    public Boolean sex        = null;
    public static String MIN_SALARY = "minSalary";
    public Float minSalary    = null;
    public static String MAX_SALARY = "maxSalary";
    public Float maxSalary    = null;
    
    public Filter() {}
    
    public String toString() {
        return departmentID+" - " + sex + " - " + minSalary+"|"+maxSalary;
    }
    
    public boolean parseDepartment(String dep) {
        if ((dep != null))
            if ((dep.matches("[\\d]{1,10}"))) {
                departmentID = new Integer(dep);
                return true;
            } else {
                return false;
            }
        else return true;
    }
    
    public boolean parseSex(String sex) {
        if (sex != null)
            if (sex.toLowerCase().equals("m")) {
                this.sex = Employee.MALE;
                return true;
            } else if (sex.toLowerCase().equals("f")) {
                this.sex = Employee.FEMALE;
                return true;
            } else {
                return false;
            }
        else return true;
    }
    
    public boolean parseMinSalary(String salary) {
        if (salary != null) {
            if (salary.matches("[\\d]{1,38}")) {
                minSalary = new Float(salary);
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }        
    }

    public boolean parseMaxSalary(String salary) {
        if (salary != null) {
            if (salary.matches("[\\d]{1,38}")) {
                maxSalary = new Float(salary);
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean parseDepartmentName(String name) {
        departmentName = name;
        return true;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Float getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Float minSalary) {
        this.minSalary = minSalary;
    }

    public Float getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Float maxSalary) {
        this.maxSalary = maxSalary;
    }
}
