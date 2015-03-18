package org.foxclient.gwt.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface HTMLResource extends ClientBundle {
    public static final HTMLResource Instance = GWT.create(HTMLResource.class);

    @Source("main.html")
    public TextResource getMainHtml();
}
