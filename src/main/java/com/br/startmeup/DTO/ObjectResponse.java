package com.br.startmeup.DTO;

import com.br.startmeup.Enum.StatusEnum;

public class ObjectResponse<T> {

    private StatusEnum status;

    private T object;

    private String message;

    public StatusEnum isStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
