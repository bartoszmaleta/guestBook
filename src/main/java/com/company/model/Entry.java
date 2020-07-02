package com.company.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Entry {
    private String content;
    private String name;
    private LocalDateTime date;

    public Entry(String content, String name, LocalDateTime date) {
        this.content = content;
        this.name = name;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public Entry setContent(String content) {
        this.content = content;
        return this;
    }

    public String getName() {
        return name;
    }

    public Entry setName(String name) {
        this.name = name;
        return this;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return this.date.format(formatter);
    }

    public Entry setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }
}
