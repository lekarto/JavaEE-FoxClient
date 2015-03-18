package org.foxclient.gwt.client.answer;

import java.io.Serializable;
import java.util.List;

public class DepartmentsNamesEvent extends DataEvent implements Serializable {
    private List<String> depNames;

    public DepartmentsNamesEvent() { }

    public List<String> getDepNames() {
        return depNames;
    }

    public void setDepNames(List<String> depNames) {
        this.depNames = depNames;
    }
}
