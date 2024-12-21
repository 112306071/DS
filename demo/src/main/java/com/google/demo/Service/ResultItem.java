package com.google.demo.Service;

public class ResultItem {
    private String title;
    private String url;

    // Constructor
    public ResultItem(String title, String url) {
        this.title = title;
        this.url = url;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for URL
    public String getUrl() {
        return url;
    }

    // Setter for URL
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ResultItem{" +
               "title='" + title + '\'' +
               ", url='" + url + '\'' +
               '}';
    }
}
