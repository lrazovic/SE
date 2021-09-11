package it.uniroma1.restserver;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Books")
public class Books {
    private List<Book> books;

    public Books(List<Book> books) {
        this.books = books;
    }
    public Books() {
        this.books = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Books{" +
                "books=" + books.toString() +
                '}';
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}