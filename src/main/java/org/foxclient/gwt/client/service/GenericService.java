package org.foxclient.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import org.foxclient.gwt.client.answer.DataEvent;
import org.foxclient.gwt.client.entity.Filter;

public interface GenericService<T, F> extends RemoteService {
    DataEvent getAll();
    DataEvent delete(T object);
    DataEvent update(T object);
    DataEvent add(T object);
    DataEvent getFiltered(F filter);
}
