package org.foxclient.gwt.client.answer;

import java.io.Serializable;

public class DataEvent implements Serializable {
    private Integer status;
    private String message;

    public DataEvent() { }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
