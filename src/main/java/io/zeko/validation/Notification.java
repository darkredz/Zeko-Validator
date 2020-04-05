package io.zeko.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notification implements Note {

    protected final Map<String, List<String>> fieldMessages;
    protected String convertFieldName;
    protected Map<String, String> customErrorMessages;

    public Notification() {
        this(new HashMap<>());
    }

    public Notification(String convertFieldName) {
        this(new HashMap<>());
        this.convertFieldName = convertFieldName;
    }

    public Notification(String convertFieldName, Map<String, String> customErrorMessages) {
        this(new HashMap<>());
        this.convertFieldName = convertFieldName;
        this.customErrorMessages = customErrorMessages;
    }

    protected Notification(Map<String, List<String>> fieldMessages) {
        this.fieldMessages = fieldMessages;
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

    public void addMessage(String owner, String message) {
        fieldMessages.putIfAbsent(owner, new ArrayList<>());
        fieldMessages.get(owner).add(message);
    }

    public List<String> getMessages(String field) {
        return fieldMessages.get(field);
    }

    public Map<String, List<String>> getMessages() {
        return fieldMessages;
    }

    public boolean isNotEmpty() {
        return !fieldMessages.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notification that = (Notification) o;

        return !(fieldMessages != null ? !fieldMessages.equals(that.fieldMessages) : that.fieldMessages != null);

    }

    @Override
    public int hashCode() {
        return fieldMessages != null ? fieldMessages.hashCode() : 0;
    }
}
