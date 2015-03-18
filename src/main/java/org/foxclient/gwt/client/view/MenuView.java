package org.foxclient.gwt.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import org.foxclient.gwt.client.entity.Filter;

public class MenuView extends Composite {
    private HorizontalPanel menuPanel = new HorizontalPanel();
    private MainView main;
    
    public MenuView(MainView mainView) {
        main = mainView;
        initWidget(this.menuPanel);
        Anchor mainLink = new Anchor("Main");
        mainLink.addClickHandler(new MainClickHandler());
        mainLink.getElement().setClassName("menuItem");
        Anchor empLink = new Anchor("Employees");
        empLink.addClickHandler(new EmployeeClickHandler());
        empLink.getElement().setClassName("menuItem");
        Anchor depLink = new Anchor("Departments");
        depLink.addClickHandler(new DepartmentClickHandler());
        depLink.getElement().setClassName("menuItem");
        menuPanel.add(mainLink);
        menuPanel.add(empLink);
        menuPanel.add(depLink);
        menuPanel.getElement().addClassName("menu");
    }

    private class MainClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            main.openMainPage();
        }
    }

    private class EmployeeClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            main.openEmployeePage();
        }
    }
    
    private class DepartmentClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            main.openDepartmentPage(null);
        }
    }
}
