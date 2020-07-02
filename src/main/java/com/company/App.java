package com.company;

import com.company.controller.GuestbookServer;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        try {
            HttpServer httpServer = new GuestbookServer().getServer();
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
