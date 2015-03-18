package org.foxclient.gwt.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import org.foxclient.gwt.client.components.interfaces.DialogBoxResultHandler;
import org.foxclient.gwt.client.entity.Department;

public class DepartmentDetailsBox extends DialogBox {
    private VerticalPanel panel = new VerticalPanel();
    private Label error = new Label("");
    private Button btnOK = new Button("OK");
    private Button btnCancel = new Button("Cancel");
    private TextBox tbName = new TextBox();
    private Department department;
    private DialogBoxResultHandler owner;

    public DepartmentDetailsBox(DialogBoxResultHandler owner, String header, Department dep) {
        department = dep;
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

        HorizontalPanel hpName = new HorizontalPanel();
        Label lblName = new Label("Name");
        lblName.setWidth("80px");
        hpName.add(new Label("Name:"));
        if ((department != null) && (department.getName() != null)) {
            tbName.setText(department.getName());
        }
        hpName.add(tbName);

        btnOK.getElement().addClassName("btnYes");
        btnCancel.getElement().addClassName("btnNo");
        HorizontalPanel hpButtons = new HorizontalPanel();
        hpButtons.add(btnOK);
        hpButtons.add(btnCancel);

        panel.add(error);
        panel.add(hpName);
        panel.add(hpButtons);
        setModal(true);
        setWidget(panel);

        btnOK.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!checkDepartmentDetails()) {
                    error.setText("Incorrect data!");
                } else {
                    updateDepartment();
                    getOwner().dialogBoxValidated(department);
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

    private boolean checkDepartmentDetails() {
        return  (!tbName.getText().equals(""));
    }

    private void updateDepartment() {
        department.setName(tbName.getText());
    }

    private DialogBoxResultHandler getOwner() {
        return owner;
    }
}