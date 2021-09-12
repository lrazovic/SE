package it.uniroma1.client;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Book")
public class Book {
    private String title;
    private String author;
    private String yob;
    private String yop;
    private int id;

    public String getYop() {
        return yop;
    }

    public void setYop(String yop) {
        this.yop = yop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYob() {
        return yob;
    }

    public void setYob(String yob) {
        this.yob = yob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getId() == book.getId() && getTitle().equals(book.getTitle()) && getAuthor().equals(book.getAuthor()) && getYob().equals(book.getYob());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getYob(), getId());
    }

    @Override
    public String toString() {
        return
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publication year='" + yob + '\'' +
                ", id=" + id ;
    }
}
