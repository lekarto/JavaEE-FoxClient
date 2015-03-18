package org.foxclient.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import org.foxclient.gwt.client.view.MainView;

public class FoxEntryPoint implements EntryPoint {
    public void onModuleLoad() {
        MainView mainView = new MainView();
        RootPanel.get().add(mainView);
    }
}
