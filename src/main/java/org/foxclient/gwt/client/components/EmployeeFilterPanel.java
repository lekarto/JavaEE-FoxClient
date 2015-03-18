package org.foxclient.gwt.client.components;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import org.foxclient.gwt.client.entity.Filter;

import java.util.List;

public class EmployeeFilterPanel {
    private Filter filter;
    public Panel panel;
    private List<String> depNames;
    private ListBox lbDepartments = new ListBox();
    private RadioButton rbMale = new RadioButton("gender", "Male");
    private RadioButton rbFemale = new RadioButton("gender", "Female");
    private RadioButton rbBoth = new RadioButton("gender", "Both");
    private Button btnFilter = new Button("Filter");

    public EmployeeFilterPanel(Filter filter, List<String> departments, ClickHandler filterClick) {
        this.filter = filter;
        depNames = departments;
        panel = createPanel();
        btnFilter.addClickHandler(filterClick);
        update(filter);
        updateDepartments(depNames);
    }

    public Panel getEmployeeFilterPanel() {
        return panel;
    }

    private Panel createPanel() {
        VerticalPanel vPanel = new VerticalPanel();
        vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        vPanel.add(new Label("Filters"));
        vPanel.setWidth("50%");
        HorizontalPanel panel = new HorizontalPanel();
        panel.setWidth("100%");
        panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

        FlexTable layout = new FlexTable();
        layout.setCellSpacing(6);
        FlexTable.FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
        cellFormatter.setColSpan(0, 0, 2);
        cellFormatter.setHorizontalAlignment(0, 0,
                HasHorizontalAlignment.ALIGN_CENTER);
        HorizontalPanel genderPanel = new HorizontalPanel();
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        genderPanel.add(rbBoth);
        layout.setHTML(1, 0, "Gender");
        layout.setWidget(1, 1, genderPanel);
        layout.setHTML(2, 0, "Department");
        layout.setWidget(2, 1, lbDepartments);
        lbDepartments.setWidth("100%");
        DecoratorPanel decPanel = new DecoratorPanel();
        decPanel.setWidget(layout);

        panel.add(decPanel);
        panel.add(decPanel);
        btnFilter.getElement().addClassName("btnFilter");
        panel.add(btnFilter);
        vPanel.add(panel);
        return vPanel;
    }

    public Filter getActualFilter() {
        Filter actualFilter = new Filter();
        if (rbMale.getValue() || rbFemale.getValue())
            filter.setSex(rbMale.getValue());
        if (!lbDepartments.getSelectedValue().equals(""))
            filter.setDepartmentName(lbDepartments.getSelectedValue());
        this.filter = actualFilter;
        return actualFilter;
    }

    public void update(Filter filter) {
        this.filter = filter;
        if (filter != null) {
            if (filter.getSex()==null) {
                rbBoth.setValue(true);
            } else {
                rbMale.setValue(filter.getSex());
                rbFemale.setValue(!filter.getSex());
            }
        } else {
            rbBoth.setValue(true);
        }
        updateDepartments(depNames);
    }

    public void updateDepartments(List<String> list) {
        depNames = list;
        lbDepartments.clear();
        lbDepartments.addItem("");
        lbDepartments.setSelectedIndex(0);
        if (depNames != null) {
            for (String s : depNames) {
                lbDepartments.addItem(s);
            }
            if ((filter!=null)&& (filter.getDepartmentName()!=null) &&
                    depNames.contains(filter.getDepartmentName())) {
                lbDepartments.setSelectedIndex(depNames.indexOf(filter.getDepartmentName())+1);
            }
        }
    }
}
