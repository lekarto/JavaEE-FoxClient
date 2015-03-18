package org.foxclient.gwt.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import org.foxclient.gwt.client.answer.DataEvent;
import org.foxclient.gwt.client.answer.DepartmentsNamesEvent;
import org.foxclient.gwt.client.answer.EmployeeListEvent;
import org.foxclient.gwt.client.components.*;
import org.foxclient.gwt.client.components.interfaces.DialogBoxResultHandler;
import org.foxclient.gwt.client.impl.EmployeeServiceClient;
import org.foxclient.gwt.client.entity.Employee;
import org.foxclient.gwt.client.view.abstraction.AbstractView;

import java.util.List;

public class EmployeeView extends AbstractView {
    private VerticalPanel vPanel = new VerticalPanel();
    private EmployeeServiceClient controller;
    private Label lbl;
    private EmployeeGrid grid = new EmployeeGrid("employees");
    private List<Employee> employees;
    private List<String> departmentNames = null;
    private EmployeeDetailsBox detailsBox;
    private Employee empForCorrections;

    public EmployeeView(EmployeeServiceClient service) {
        controller = service;
        initWidget(vPanel);
        controller.getService().getAll(new GetEmployeesCallback());
        controller.getService().getDepartmentsNames(new GetDepNamesCallback());
        vPanel.setWidth("100%");
        vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setWidth("80%");
        hPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        lbl = new Label("Table of employees");
        hPanel.add(lbl);
        hPanel.setCellWidth(lbl, "60%");
        Button btnNew = new Button("New");
        btnNew.getElement().addClassName("btnNew blue");
        btnNew.addClickHandler(new EmployeeNewClick());
        hPanel.add(btnNew);
        vPanel.add(hPanel);
        vPanel.add(grid);
    }
    
    public void updateLabel(String text) {
        lbl.setText(text);
    }

    public void updateEmployees(List<Employee> list) {
        if (list != null) {
            employees = list;
            grid.clear();
            for (int i = 0; i < list.size(); i++) {
                Image edit = new Image("images/edit.png");
                edit.setTitle("Edit");
                edit.addClickHandler(new EmployeeEditClick(i));
                Image del = new Image("images/del.png");
                del.setTitle("Delete");
                del.addClickHandler(new EmployeeDelClick(i));
                grid.addRow(list.get(i), edit, del);
            }
        }
    }

    private class EmployeeNewClick implements ClickHandler, DialogBoxResultHandler {
        @Override
        public void onClick(ClickEvent event) {
            empForCorrections = new Employee();
            detailsBox = new EmployeeDetailsBox(this, "New department", empForCorrections, departmentNames);
            detailsBox.center();
        }
        @Override
        public void dialogBoxValidated(Object object) {
            controller.getService().add((Employee) object, new EmployeeCUDCallback("Added"));
        }
        @Override
        public void dialogBoxCancelled() { }
    }

    private class EmployeeEditClick implements ClickHandler, DialogBoxResultHandler {
        private int row;
        public EmployeeEditClick(int row) {
            this.row = row;
        }
        @Override
        public void onClick(ClickEvent event) {
            if (row < employees.size()) {
                empForCorrections = employees.get(row);
                detailsBox = new EmployeeDetailsBox(this, "New department", empForCorrections, departmentNames);
                detailsBox.center();
            }
        }
        @Override
        public void dialogBoxValidated(Object object) {
            controller.getService().update((Employee) object, new EmployeeCUDCallback("Updated"));
        }
        @Override
        public void dialogBoxCancelled() { }
    }

    private class EmployeeDelClick implements ClickHandler, DialogBoxResultHandler {
        private int row;
        public EmployeeDelClick(int row) {
            this.row = row;
        }
        @Override
        public void onClick(ClickEvent event) {
            if (row < employees.size()) {
                empForCorrections = employees.get(row);
                new ChoiceBox(this, "Delete?", "Do you really want to delete employee "+empForCorrections.getFullName()+"?").center();
            }
        }
        @Override
        public void dialogBoxValidated(Object object) {
            controller.getService().delete(empForCorrections, new EmployeeCUDCallback("Deleted"));
        }
        @Override
        public void dialogBoxCancelled() { }
    }

    private class GetEmployeesCallback implements AsyncCallback {
        @Override
        public void onFailure(Throwable caught) {
            updateLabel("Server fail");
        }
        @Override
        public void onSuccess(Object result) {
            if (result instanceof EmployeeListEvent) {
                EmployeeListEvent event = (EmployeeListEvent)result;
                if (event.getStatus() == 200) {
                    updateEmployees(event.getEmployees());
                } else {
                    updateLabel(event.getMessage());
                }
            }
        }
    }

    private class GetDepNamesCallback implements AsyncCallback {
        @Override
        public void onFailure(Throwable caught) { }
        @Override
        public void onSuccess(Object result) {
            if (result instanceof DepartmentsNamesEvent) {
                DepartmentsNamesEvent event = (DepartmentsNamesEvent)result;
                if (event.getStatus() == 200) {
                    departmentNames = event.getDepNames();
                }
            }
        }
    }

    private class EmployeeCUDCallback implements AsyncCallback {
        private String action;
        public EmployeeCUDCallback(String action) {
            this.action = action;
        }
        @Override
        public void onFailure(Throwable caught) {
            new MessageBox("Error", "Server Error. Try to repeat later!").center();
        }

        @Override
        public void onSuccess(Object result) {
            if (result instanceof DataEvent) {
                DataEvent event = (DataEvent)result;
                if (( (event.getStatus() == 200) || // Create, Update
                        (event.getStatus() == 201)  )  // Delete
                        && (empForCorrections != null) ) {
                    MessageBox myDialog = new MessageBox(action, "Employee \""+empForCorrections.getFullName()+"\" was successfully "+action.toLowerCase()+"!");
                    myDialog.center();
                    controller.showEmployeeView();
                } else if (event.getStatus() >= 500) { // server error
                    onFailure(null);
                } else {
                    new MessageBox("Not Found", "Employee \""+empForCorrections.getFullName()+"\" not found!").center();
                }
            }
        }
    }
}
