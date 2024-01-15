package ro.uvt.info.Services;


import ro.uvt.info.Classes.Book;

import java.io.IOException;

public interface Observer {
    void update(Book book) throws IOException;
}