package org.foxclient.gwt.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import org.foxclient.gwt.client.entity.Filter;
import org.foxclient.gwt.client.impl.DepartmentServiceClient;
import org.foxclient.gwt.client.impl.EmployeeServiceClient;
import org.foxclient.gwt.client.resources.HTMLResource;
import org.foxclient.gwt.client.view.abstraction.AbstractView;

public class MainView extends Composite {
    private VerticalPanel vPanel = new VerticalPanel();
    private VerticalPanel contentPanel;
    
    public MainView() {
        initWidget(vPanel);
        vPanel.getElement().addClassName("wrapper");
        vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        MenuView menu = new MenuView(this);
        vPanel.add(menu);
        vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_DEFAULT);
        contentPanel = new VerticalPanel();
        contentPanel.setWidth("100%");
        vPanel.add(contentPanel);
        openMainPage();
    }

    public void updateContent(AbstractView view) {
        contentPanel.clear();
        contentPanel.add(view);
    }
    
    public void openEmployeePage() {
        contentPanel.clear();
        EmployeeServiceClient service = new EmployeeServiceClient(GWT.getHostPageBaseURL()+"employeeservice", this);
        contentPanel.add(service.getView());
    }

    public void openDepartmentPage(Filter filter) {
        contentPanel.clear();
        DepartmentServiceClient service = new DepartmentServiceClient(GWT.getHostPageBaseURL()+"departmentservice", this, filter);
    }

    public void openMainPage() {
        contentPanel.clear();
        HTMLPanel helloPage = new HTMLPanel(HTMLResource.Instance.getMainHtml().getText());
        helloPage.setWidth("90%");
        contentPanel.add(helloPage);
    }
}
