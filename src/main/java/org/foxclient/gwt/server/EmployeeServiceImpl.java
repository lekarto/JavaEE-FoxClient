package org.foxclient.gwt.server;

import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.foxclient.gwt.client.answer.DepartmentsNamesEvent;
import org.foxclient.gwt.client.entity.Filter;
import org.foxclient.gwt.client.service.EmployeeService;
import com.sun.jersey.api.client.*;
import org.foxclient.gwt.client.entity.Employee;
import org.foxclient.gwt.client.answer.EmployeeListEvent;
import org.foxclient.gwt.client.answer.DataEvent;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

public class EmployeeServiceImpl extends AbstractServiceImpl<Employee, Filter> implements EmployeeService {

    @Override
    public String getRequestURL() {
        return "http://localhost:8080/foxrestful/rest/employees";
    }

    @Override
    public Integer getId(Employee object) {
        return object.getId();
    }

    public DataEvent getAll() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        
        WebResource resource = client.resource(getRequestURL());
        ClientResponse response = resource.get(ClientResponse.class);
        EmployeeListEvent event = new EmployeeListEvent();
        if ((response.getStatus() == 200) && response.hasEntity()) {
            event.setStatus(200);
            event.setEmployees(response.getEntity(new GenericType<List<Employee>>() {}));
        } else {
            event.setStatus(response.getStatus());
            event.setMessage("Some error");
        }
        return event;
    }

    @Override
    public DataEvent add(Employee employee) {
        DataEvent event = new DataEvent();
        if (employee != null) {
            ClientConfig clientConfig =  new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create(clientConfig);
            WebResource webResource = client.resource(getRequestURL());
            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).
                    post(ClientResponse.class, employee);
            if ((response.getStatus() == 200)) {
                event.setStatus(200);
                event.setMessage("Success");
            } else {
                event.setStatus(response.getStatus());
                event.setMessage("Some error");
            }
        } else {
            event.setStatus(400);
            event.setMessage("No data");
        }
        return event;
    }

    @Override
    public DataEvent getDepartmentsNames() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        WebResource resource = client.resource("http://localhost:8080/foxrestful/rest/departments/names");
        ClientResponse response = resource.get(ClientResponse.class);
        DepartmentsNamesEvent event = new DepartmentsNamesEvent();
        if ((response.getStatus() == 200) && response.hasEntity()) {
            event.setStatus(200);
            event.setDepNames(response.getEntity(new GenericType<List<String>>() { }));
        } else {
            event.setStatus(response.getStatus());
            event.setMessage("Some error");
        }
        return event;
    }

    @Override
    public DataEvent getFiltered(Filter filter) {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        if (filter.getSex() != null)
            params.add(Filter.SEX, filter.getSex()?"m":"f");
        if ((filter.getDepartmentName()!=null) && (!filter.getDepartmentName().equals("")))
            params.add(Filter.DEPARTMENT_NAME, filter.getDepartmentName());
        if (filter.getMinSalary()!=null)
            params.add(Filter.MIN_SALARY, filter.getMinSalary().toString()+".00");
        if (filter.getMaxSalary()!=null)
            params.add(Filter.MAX_SALARY, filter.getMaxSalary().toString()+".00");

        WebResource resource = client.resource(getRequestURL());
        ClientResponse response = resource.queryParams(params).get(ClientResponse.class);
        EmployeeListEvent event = new EmployeeListEvent();
        if ((response.getStatus() == 200) && response.hasEntity()) {
            event.setStatus(200);
            event.setEmployees(response.getEntity(new GenericType<List<Employee>>() {}));
        } else {
            event.setStatus(response.getStatus());
            event.setMessage("Some error");
        }
        return event;
    }
}
