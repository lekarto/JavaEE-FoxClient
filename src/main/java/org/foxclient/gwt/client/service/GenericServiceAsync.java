package org.foxclient.gwt.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GenericServiceAsync<T, F> {
    void getAll(AsyncCallback callback);
    void delete(T object, AsyncCallback callback);
    void update(T object, AsyncCallback callback);
    void add(T object, AsyncCallback callback);
    void getFiltered(F filter, AsyncCallback callback);
}
