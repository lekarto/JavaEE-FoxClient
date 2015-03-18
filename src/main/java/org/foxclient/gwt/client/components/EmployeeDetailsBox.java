package org.foxclient.gwt.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import org.foxclient.gwt.client.components.interfaces.DialogBoxResultHandler;
import org.foxclient.gwt.client.entity.Employee;

import java.util.List;

public class EmployeeDetailsBox extends DialogBox {
    private VerticalPanel panel = new VerticalPanel();
    private Label error = new Label("");
    private Button btnOK = new Button("OK");
    private Button btnCancel = new Button("Cancel");
    private TextBox tbFirstName = new TextBox();
    private TextBox tbLastName = new TextBox();
    private RadioButton rbMale = new RadioButton("gender", "Male");
    private RadioButton rbFemale = new RadioButton("gender", "Female");
    private TextBox tbSalary = new TextBox();
    private ListBox lbDepartments = new ListBox();
    private Employee employee;
    private DialogBoxResultHandler owner;
    private List<String> depNames;

    public EmployeeDetailsBox(DialogBoxResultHandler owner, String header, Employee emp, List<String> depNames) {
        employee = emp;
        this.depNames = depNames;
        this.owner = owner;
        setText(header);
        setAnimationEnabled(true);
        setGlassEnabled(true);

        panel.setHeight("150");
        panel.setWidth("350");
        panel.setSpacing(10);
        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        error.getElement().addClassName("errorLabel");

        HorizontalPanel hpFName = new HorizontalPanel();
        HorizontalPanel hpLName = new HorizontalPanel();
        hpFName.add(new Label("First Name:"));
        hpLName.add(new Label(" Last Name:"));
        hpFName.add(tbFirstName);
        hpLName.add(tbLastName);

        HorizontalPanel hpGender = new HorizontalPanel();
        hpGender.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        hpGender.add(new Label("Gender:"));
        VerticalPanel vpGender = new VerticalPanel();
        vpGender.add(rbMale);
        vpGender.add(rbFemale);
        hpGender.setWidth("78%");
        hpGender.add(vpGender);
        hpGender.setCellWidth(vpGender, "70%");

        HorizontalPanel hpSalary = new HorizontalPanel();
        hpSalary.setWidth("78%");
        hpSalary.add(new Label("Salary:"));
        hpSalary.add(tbSalary);
        hpSalary.setCellWidth(tbSalary, "50%");

        HorizontalPanel hpDepartment = new HorizontalPanel();
        hpDepartment.setWidth("78%");
        hpDepartment.add(new Label("Department:"));
        lbDepartments.setWidth("170px");
        updateDepartmentListBox();
        hpDepartment.add(lbDepartments);
        hpDepartment.setCellWidth(lbDepartments, "65%");

        btnOK.getElement().addClassName("btnYes");
        btnCancel.getElement().addClassName("btnNo");
        HorizontalPanel hpButtons = new HorizontalPanel();
        hpButtons.add(btnOK);
        hpButtons.add(btnCancel);

        panel.add(error);
        panel.add(hpFName);
        panel.add(hpLName);
        panel.add(hpGender);
        panel.add(hpSalary);
        panel.add(hpDepartment);
        panel.add(hpButtons);

        fillFields();
        setModal(true);
        setWidget(panel);

        btnOK.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!checkEmployeeDetails()) {
                    error.setText("Incorrect data!");
                } else {
                    updateDepartment();
                    getOwner().dialogBoxValidated(employee);
                    hide();
                }
            }
        });

        btnCancel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                getOwner().dialogBoxCancelled();
                hide();
            }
        });
    }

    private void fillFields() {
        if (employee != null) {
            if (employee.getFirstName() != null)
                tbFirstName.setText(employee.getFirstName());
            if (employee.getLastName() != null)
                tbLastName.setText(employee.getLastName());
            if (employee.Male()) rbMale.setValue(true);
            else rbFemale.setValue(true);

            if (employee.getSalary() != null) {
                tbSalary.setText(employee.getSalary().toString());
            }

            if ((employee != null) && (employee.getDepartment()!=null) &&
                    (depNames.contains(employee.getDepartment()))) {
                lbDepartments.setSelectedIndex(depNames.indexOf(employee.getDepartment())+1);
            } else {
                lbDepartments.setSelectedIndex(0);
            }
        }
    }

    private void updateDepartmentListBox() {
        lbDepartments.clear();
        lbDepartments.addItem("");
        for (String s : depNames) {
            lbDepartments.addItem(s);
        }
    }

    private boolean checkEmployeeDetails() {
        return  (!tbFirstName.getText().equals("") &&
                 !tbLastName.getText().equals("") &&
                (rbMale.getValue() || rbFemale.getValue()) &&
                ( tbSalary.getText().matches("[\\d]{1,10}"))
        );
    }

    private void updateDepartment() {
        employee.setFirstName(tbFirstName.getText());
        employee.setLastName(tbLastName.getText());
        employee.setSex(rbMale.getValue());
        employee.setSalary(Float.parseFloat(tbSalary.getText()));
        employee.setDepartment((lbDepartments.getSelectedIndex()==0)?null:lbDepartments.getSelectedItemText());
    }

    private DialogBoxResultHandler getOwner() {
        return owner;
    }
}
