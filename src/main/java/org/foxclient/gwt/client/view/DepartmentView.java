package org.foxclient.gwt.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import org.foxclient.gwt.client.answer.DepartmentListEvent;
import org.foxclient.gwt.client.answer.DataEvent;
import org.foxclient.gwt.client.components.ChoiceBox;
import org.foxclient.gwt.client.components.DepartmentDetailsBox;
import org.foxclient.gwt.client.components.DepartmentGrid;
import org.foxclient.gwt.client.components.MessageBox;
import org.foxclient.gwt.client.components.interfaces.DialogBoxResultHandler;
import org.foxclient.gwt.client.entity.Department;
import org.foxclient.gwt.client.entity.Filter;
import org.foxclient.gwt.client.impl.DepartmentServiceClient;
import org.foxclient.gwt.client.view.abstraction.AbstractView;

import java.util.List;

public class DepartmentView extends AbstractView {
    private VerticalPanel vPanel = new VerticalPanel();
    private DepartmentServiceClient controller;
    private Label lbl;
    private DepartmentGrid grid = new DepartmentGrid("departments");
    private List<Department> departments;
    private DepartmentDetailsBox detailsBox;
    private Department depForCorrections;
    private Filter filter;

    public DepartmentView(DepartmentServiceClient service, Filter filter) {
        controller = service;
        this.filter = filter;
        initWidget(vPanel);
        controller.getService().getAll(new GetDepartmentsCallback());
        vPanel.setWidth("100%");
        grid.setWidth("90%");
        vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setWidth("80%");
        hPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        lbl = new Label("Table of all departments");
        hPanel.add(lbl);
        hPanel.setCellWidth(lbl, "60%");
        Button btnNew = new Button("New");
        btnNew.getElement().addClassName("btnNew blue");
        btnNew.addClickHandler(new DepartmentNewClick());
        hPanel.add(btnNew);
        vPanel.add(hPanel);
        vPanel.add(grid);
    }

    public void updateLabel(String text) {
        this.lbl.setText(text);
    }

    private void updateDepartments(List<Department> list) {
        if (list != null) {
            departments = list;
            for (int i = 0; i < list.size(); i++) {
                Image edit = new Image("images/edit.png");
                edit.setTitle("Edit");
                edit.addClickHandler(new DepartmentEditClick(i));
                Image del = new Image("images/del.png");
                del.setTitle("Delete");
                del.addClickHandler(new DepartmentDelClick(i));
                grid.addRow(list.get(i), edit, del);
            }
        }
    }

    private class DepartmentNewClick implements ClickHandler, DialogBoxResultHandler {
        @Override
        public void onClick(ClickEvent event) {
            depForCorrections = new Department();
            detailsBox = new DepartmentDetailsBox(this, "New department", depForCorrections);
            detailsBox.center();
        }
        @Override
        public void dialogBoxValidated(Object object) {
            controller.getService().add((Department)object, new DepartmentCUDCallback("Added"));
        }
        @Override
        public void dialogBoxCancelled() { }
    }

    private class DepartmentEditClick implements ClickHandler, DialogBoxResultHandler {
        private int row;
        public DepartmentEditClick(int row) {
            this.row = row;
        }
        @Override
        public void onClick(ClickEvent event) {
            if (row < departments.size()) {
                depForCorrections = departments.get(row);
                detailsBox = new DepartmentDetailsBox(this, "Edit department", depForCorrections);
                detailsBox.center();
            }
        }
        @Override
        public void dialogBoxValidated(Object object) {
            controller.getService().update((Department) object, new DepartmentCUDCallback("Updated"));
        }
        @Override
        public void dialogBoxCancelled() { }
    }

    private class DepartmentDelClick implements ClickHandler, DialogBoxResultHandler {
        private int row;
        public DepartmentDelClick(int row) {
            this.row = row;
        }
        @Override
        public void onClick(ClickEvent event) {
            if (row < departments.size()) {
                depForCorrections = departments.get(row);
                new ChoiceBox(this, "Delete?", "Do you really want to delete department "+depForCorrections.getName()+"?").center();
            }
        }
        @Override
        public void dialogBoxValidated(Object object) {
            controller.getService().delete(depForCorrections, new DepartmentCUDCallback("Deleted"));
        }
        @Override
        public void dialogBoxCancelled() { }
    }



    private class GetDepartmentsCallback implements AsyncCallback {
        @Override
        public void onFailure(Throwable caught) {
            updateLabel("Server fail");
        }
        @Override
        public void onSuccess(Object result) {
            if (result instanceof DepartmentListEvent) {
                DepartmentListEvent event = (DepartmentListEvent)result;
                if (event.getStatus() == 200) {
                    updateDepartments(event.getDepartments());
                } else {
                    updateLabel(event.getMessage());
                }
            }
        }
    }

    private class DepartmentCUDCallback implements AsyncCallback {
        private String action;
        public DepartmentCUDCallback(String action) {
            this.action = action;
        }
        @Override
        public void onFailure(Throwable caught) {
            MessageBox myDialog = new MessageBox("Error", "Server Error. Try to repeat later!");
            myDialog.center();
        }
        @Override
        public void onSuccess(Object result) {
            if (result instanceof DataEvent) {
                DataEvent event = (DataEvent)result;
                if (( (event.getStatus() == 200) || // Create, Update
                      (event.getStatus() == 201)  )  // Delete
                   && (depForCorrections != null) ) {
                    MessageBox myDialog = new MessageBox(action, "Department \""+depForCorrections.getName()+"\" was successfully "+action.toLowerCase()+"!");
                    myDialog.center();
                    controller.showDepartmentView();
                } else if (event.getStatus() >= 500) { // server error
                    onFailure(null);
                } else {
                    MessageBox myDialog = new MessageBox("Not Found", "Department \""+depForCorrections.getName()+"\" not found!");
                    myDialog.center();
                }
            }
        }
    }
}
