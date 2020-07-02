package com.company.controller;

import com.company.dao.GuestbookDao;
import com.company.dao.GuestbookDaoDb;
import com.company.model.Entry;
import com.company.model.Guestbook;
import com.company.view.EntriesView;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuestbookController implements HttpHandler {
    private final GuestbookDao guestbookDao;
    private Guestbook guestbook;
    private List<Entry> entries;
    private EntriesView entriesView;

    public GuestbookController() {
        this.guestbookDao = new GuestbookDaoDb();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        this.entriesView = new EntriesView(exchange);
        String method = exchange.getRequestMethod();

        if (method.equals("GET")) {
            guestbook = guestbookDao.loadGuestbook();
            entries = guestbook.getEntries();
            entriesView.showEntries(entries);
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");

            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);
            String message = (String) inputs.get("message");
            String name = (String) inputs.get("name");

            Entry newEntry = createNewEntry(message, name);
            addNewEntryToEntires(newEntry);
            addNewEntryToDao(newEntry);

            guestbook = guestbookDao.loadGuestbook();
            entries = guestbook.getEntries();
            entriesView.showEntries(entries);
        }
    }

    private void addNewEntryToDao(Entry entry) {
        guestbookDao.addEntry(entry);
    }

    private void addNewEntryToEntires(Entry entry) {
        entries.add(entry);
    }

    private Entry createNewEntry(String content, String name) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return new Entry(content, name, currentDateTime);

    }

    private Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }
}
