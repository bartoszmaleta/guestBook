package com.company.view;

import com.company.model.Entry;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.jtwig.exceptions.JtwigException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class EntriesView {
    private HttpExchange httpExchange;
    private JtwigTemplate template;
    private JtwigModel model;

    public EntriesView(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        this.template = JtwigTemplate.classpathTemplate("templates/guestbook.twig");
        this.model = JtwigModel.newModel();
    }

    public void showEntries(List<Entry> entryList) throws IOException {
        this.model.with("entries", entryList);

        String response = this.template.render(this.model);

        this.httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
