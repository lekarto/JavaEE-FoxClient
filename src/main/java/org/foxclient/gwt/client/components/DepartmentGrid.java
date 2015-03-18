package org.foxclient.gwt.client.components;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import org.foxclient.gwt.client.entity.Department;

public class DepartmentGrid extends Grid {
    private final static String[] HEADER  = {
            "№",
            "Name",
            "Average Salary",
            "Employees",
            "", // edit
            ""  // delete
    };
    private final static int COLS = HEADER.length;

    public DepartmentGrid() {
        this("");
    }
    public DepartmentGrid(String cssClassName) {
        super(1, COLS);
        if ((cssClassName != null) && (!cssClassName.equals("")))
            this.getElement().addClassName(cssClassName);
        for (int i = 0; i < COLS; i++) {
            this.setText(0, i, HEADER[i]);
        }
    }

    public void addRow(Department department, Image edit, Image del) {
        if (department == null) return;
        int lastIndex = this.numRows;
        int col = 0;
        this.resizeRows(lastIndex+1);
        this.setText(lastIndex, col++, Integer.toString(lastIndex));
        this.setText(lastIndex, col++, ((department.getName()==null)?"":department.getName()));
        this.setText(lastIndex, col++, ((department.getAverageSalary()==null)?"-":department.getAverageSalary().toString()));
        this.setText(lastIndex, col++, ((department.getEmployeeCount()==null)?"-":department.getEmployeeCount().toString()));
        if (edit != null) {
            this.setWidget(lastIndex, col++, edit);
        }
        if (del != null) {
            this.setWidget(lastIndex, col++, del);
        }
    }
}
