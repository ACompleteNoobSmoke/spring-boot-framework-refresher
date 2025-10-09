package com.acompletenoobsmoke.refresher.books;

import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.ArrayList;
import java.util.List;

public class BookHandler {

    private static List<BookRouteConfig.Book> bookRouteConfigs;

    static {
        bookRouteConfigs = new ArrayList<>();
        bookRouteConfigs.add(new BookRouteConfig.Book("Spider-Man", "Stan Lee"));
        bookRouteConfigs.add(new BookRouteConfig.Book("The Stand", "Steven King"));
        bookRouteConfigs.add(new BookRouteConfig.Book("Watchmen", "Alan Moore"));

    }

    public ServerResponse getAllBooks(ServerRequest request) {
        return ServerResponse.ok().body(bookRouteConfigs);
    }

    public ServerResponse getBookByID(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        id--;
        if (id < 0 || id >= bookRouteConfigs.size()) {
            return ServerResponse.badRequest().body("id out of range");
        }
        return ServerResponse.ok().body(bookRouteConfigs.get(id));
    }
}
