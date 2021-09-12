package it.uniroma1.client;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Books")
public class Books {
    private List<Book> books;

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
