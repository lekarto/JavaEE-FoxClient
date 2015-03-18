package org.foxclient.gwt.server;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.foxclient.gwt.client.answer.DepartmentListEvent;
import org.foxclient.gwt.client.answer.DataEvent;
import org.foxclient.gwt.client.entity.Department;
import org.foxclient.gwt.client.entity.Filter;
import org.foxclient.gwt.client.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl extends AbstractServiceImpl<Department, Filter> implements DepartmentService {

    public String getRequestURL() {
        return "http://localhost:8080/foxrestful/rest/departments";
    }

    @Override
    public Integer getId(Department object) {
        return object.getId();
    }

    public DataEvent getAll() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        WebResource resource = client.resource(getRequestURL());
        ClientResponse response = resource.get(ClientResponse.class);
        DepartmentListEvent event = new DepartmentListEvent();
        if ((response.getStatus() == 200) && response.hasEntity()) {
            event.setStatus(200);
            event.setDepartments(response.getEntity(new GenericType<List<Department>>() { }));
        } else {
            event.setStatus(response.getStatus());
            event.setMessage("Some error");
        }
        return event;
    }

    @Override
    public DataEvent getFiltered(Filter filter) {
        return null;
    }
}