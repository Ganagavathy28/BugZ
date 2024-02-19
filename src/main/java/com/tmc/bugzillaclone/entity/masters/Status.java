package com.tmc.bugzillaclone.entity.masters;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "status_master")
public class Status {
    @Id
    private String id;

    private String statusTitle;

    private boolean status;

    public Status() {}

    public Status(String statusTitle, boolean status) {
        this.statusTitle = statusTitle;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status [id=" + id + ", statusTitle=" + statusTitle + ", status=" + status + "]";
    }
}
