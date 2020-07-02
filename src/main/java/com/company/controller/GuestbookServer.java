package com.company.controller;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class GuestbookServer {
    private HttpServer server;

    public GuestbookServer() throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(8001), 0);
        setRoutes(this.server);
    }

    private void setRoutes(HttpServer httpServer) {
        httpServer.createContext("/guestbook", new GuestbookController());
        httpServer.setExecutor(null);
    }

    public HttpServer getServer() {
        return this.server;
    }
}
