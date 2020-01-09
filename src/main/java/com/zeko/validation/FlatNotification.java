package com.zeko.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class FlatNotification implements Note {

    private List<String> messages;
    protected String convertFieldName;
    protected Map<String, String> customErrorMessages;

    FlatNotification(List<String> messages) {
        this.messages = messages;
        if (messages == null) {
            this.messages = new ArrayList<>();
        }
    }

    FlatNotification(List<String> messages, String convertFieldName) {
        this(messages);
        this.convertFieldName = convertFieldName;
    }

    FlatNotification(List<String> messages, String convertFieldName, Map<String, String> customErrorMessages) {
        this(messages);
        this.convertFieldName = convertFieldName;
        this.customErrorMessages = customErrorMessages;
    }

    @Override
    public void addMessage(String owner, String message) {
        messages.add(message);
    }

    @Override
    public Map<String, List<String>> getMessages() {
        return null;
    }

    @Override
    public boolean shouldConvertFieldName() {
        return this.convertFieldName != null;
    }

    @Override
    public String getConvertFieldName() {
        return this.convertFieldName;
    }

    @Override
    public Map<String, String> getCustomErrorMessages() {
        return customErrorMessages;
    }

}
