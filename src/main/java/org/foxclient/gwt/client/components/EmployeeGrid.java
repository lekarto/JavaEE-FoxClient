package org.foxclient.gwt.client.components;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import org.foxclient.gwt.client.entity.Employee;

public class EmployeeGrid extends Grid {
    private final static String[] HEADER  = {
            "№",
            "First Name",
            "Last Name",
            "Gender",
            "Salary",
            "Department",
            "", // edit
            ""  // delete
    };
    private final static int COLS = 8;
//    private String cssClass;

    public EmployeeGrid() {
        this("");
    }
    public EmployeeGrid(String cssClassName) {
        super(1, COLS);
//        this.cssClass = cssClass;
        if ((cssClassName != null) && (!cssClassName.equals("")))
            this.getElement().addClassName(cssClassName);
        createHeader();
    }

    private void createHeader() {
        for (int i = 0; i < COLS; i++) {
            this.setText(0, i, HEADER[i]);
        }
    }

    public void addRow(Employee employee, Image edit, Image del) {
        if (employee == null) return;
        int lastIndex = this.numRows;
        this.resizeRows(lastIndex+1);
        this.setText(lastIndex, 0, ""+lastIndex);
        this.setText(lastIndex, 1, ((employee.getFirstName()==null)?"": employee.getFirstName()));
        this.setText(lastIndex, 2, ((employee.getLastName()==null)?"": employee.getLastName()));
        this.setText(lastIndex, 3, ((employee.Male())?"M":"F"));
        this.setText(lastIndex, 4,  ((employee.getSalary()==null)?"-": employee.getSalary().toString()));
        this.setText(lastIndex, 5, ((employee.getDepartment()==null)?"-":employee.getDepartment()));
        if (edit != null) {
            this.setWidget(lastIndex, 6, edit);
        }
        if (del != null) {
            this.setWidget(lastIndex, 7, del);
        }
    }

    @Override
    public void clear() {
        this.resizeRows(1);
    }
}