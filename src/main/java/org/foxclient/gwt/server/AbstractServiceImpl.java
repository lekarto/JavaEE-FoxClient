package org.foxclient.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.foxclient.gwt.client.answer.DataEvent;
import org.foxclient.gwt.client.service.GenericService;

import javax.ws.rs.core.MediaType;


public abstract class AbstractServiceImpl<T, F> extends RemoteServiceServlet implements GenericService<T, F> {
    public abstract String getRequestURL();
    public abstract Integer getId(T object);

    public DataEvent delete(T object) {
        DataEvent event = new DataEvent();
        if ((object != null) && (getId(object) != null)) {
            Client client = Client.create();
            WebResource webResource = client.resource(getRequestURL() + "/" + getId(object));
            ClientResponse response = webResource.delete(ClientResponse.class);
            event.setStatus(response.getStatus());
            event.setMessage("Success");
        } else {
            event.setStatus(404);
            event.setMessage("Not found");
        }
        return event;
    }

    @Override
    public DataEvent update(T object) {
        DataEvent event = new DataEvent();
        if ((object != null) && (getId(object) != null)) {
            ClientConfig clientConfig =  new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create(clientConfig);
            WebResource webResource = client.resource(getRequestURL() + "/" + getId(object));
            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).
                    put(ClientResponse.class, object);
            event.setStatus(response.getStatus());
            event.setMessage("Success");
        } else {
            event.setStatus(404);
            event.setMessage("Not found");
        }
        return event;
    }

    @Override
    public DataEvent add(T object) {
        DataEvent event = new DataEvent();
        if (object != null) {
            ClientConfig clientConfig =  new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create(clientConfig);
            WebResource webResource = client.resource(getRequestURL());
            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).
                                                  post(ClientResponse.class, object);
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
}
