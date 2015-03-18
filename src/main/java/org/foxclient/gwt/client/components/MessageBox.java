package org.foxclient.gwt.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

public class MessageBox extends DialogBox {
    private VerticalPanel panel = new VerticalPanel();

    public MessageBox(String header, String message) {
        setText(header);
        setAnimationEnabled(true);
        setGlassEnabled(true);
        Button ok = new Button("OK");
        ok.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                hide();
            }
        });

        Label label = new Label(message);

        panel.setHeight("150");
        panel.setWidth("350");
        panel.setSpacing(10);
        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        panel.add(label);
        panel.add(ok);
        setModal(true);
        setWidget(panel);
    }
}
