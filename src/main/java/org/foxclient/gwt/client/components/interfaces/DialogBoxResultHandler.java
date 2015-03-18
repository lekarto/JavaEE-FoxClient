package org.foxclient.gwt.client.components.interfaces;

public  interface DialogBoxResultHandler<T> {
    void dialogBoxValidated (T object);
    void dialogBoxCancelled ();
}