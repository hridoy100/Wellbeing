package com.example.healthapp;

public class SingleElement {
    String title;
    String type;
    String link;

    public SingleElement(String title, String type, String link) {
        this.title = title;
        this.type = type;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
