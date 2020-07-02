package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class Guestbook {
    private List<Entry> entries;

    public Guestbook() {
        this.entries = new ArrayList<>();
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }
}
