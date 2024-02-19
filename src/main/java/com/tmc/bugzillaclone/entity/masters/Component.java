package com.tmc.bugzillaclone.entity.masters;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "component_master")
public class Component {
    @Id
    private String id;

    private String componentTitle;

    private boolean status;

    // Constructor
    public Component(String componentTitle, boolean status) {
        this.componentTitle = componentTitle;
        this.status = status;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getComponentTitle() {
        return componentTitle;
    }

    public void setComponentTitle(String componentTitle) {
        this.componentTitle = componentTitle;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Component [id=" + id + ", componentTitle=" + componentTitle + ", status=" + status + "]";
    }
}
