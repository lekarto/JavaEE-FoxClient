package org.foxclient.gwt.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import org.foxclient.gwt.client.components.interfaces.DialogBoxResultHandler;

public class ChoiceBox extends DialogBox {
    private VerticalPanel panel = new VerticalPanel();
    private Button btnYes = new Button("Yes");
    private Button btnNo = new Button("No");
    private DialogBoxResultHandler owner;

    public ChoiceBox(DialogBoxResultHandler owner, String header, String message) {
        this.owner = owner;
        setText(header);
        setAnimationEnabled(true);
        setGlassEnabled(true);

        Label label = new Label(message);

        panel.setHeight("150");
        panel.setWidth("350");
        panel.setSpacing(10);
        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        panel.add(label);
        btnYes.getElement().addClassName("btnYes");
        btnNo.getElement().addClassName("btnNo");
        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.add(btnYes);
        hPanel.add(btnNo);
        panel.add(hPanel);
        setModal(true);
        setWidget(panel);

        btnYes.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                getOwner().dialogBoxValidated(null);
                hide();
            }
        });

        btnNo.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                getOwner().dialogBoxCancelled();
                hide();
            }
        });
    }

    private DialogBoxResultHandler getOwner() {
        return owner;
    }
}
