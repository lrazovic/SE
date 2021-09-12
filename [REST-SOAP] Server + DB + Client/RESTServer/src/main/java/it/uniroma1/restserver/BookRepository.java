package it.uniroma1.restserver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/books")
@Produces("text/xml")
public class BookRepository {

    private Connection conn;

    public void setConnection(String pos) {
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn
                    = DriverManager.getConnection("jdbc:sqlite:" + pos);
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("{bookId}")
    public Book getBook(@PathParam("bookId") int bookId) {
        return findById(bookId);
    }

    @GET
    @Path("/list")
    public Books getBooks() {
        return findAll();
    }

    private Books findAll() {
        PreparedStatement stat = null;
        Book fl = null;
        List<Book> list = new ArrayList<>();
        try {
            stat = conn.prepareStatement("select * from books");
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                fl = new Book();
                fl.setId(Integer.parseInt(rs.getString("id")));
                fl.setTitle(rs.getString("title"));
                fl.setYob(rs.getString("yob"));
                list.add(fl);
                Logger.getLogger(BookRepository.class.getName()).log(Level.INFO, "Accessed : " + fl);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Books(list);
    }

    private Book findById(int id) {
        PreparedStatement stat = null;
        Book fl = null;
        try {
            stat = conn.prepareStatement("select * from books, authors where books.id = ? and books.authorId = authors.authorId");
            stat.setString(1, String.valueOf(id));
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                fl = new Book();
                fl.setId(Integer.parseInt(rs.getString("id")));
                fl.setTitle(rs.getString("title"));
                fl.setAuthor(rs.getString("name"));
                fl.setYob(rs.getString("yob"));
                fl.setYop(rs.getString("year"));
                Logger.getLogger(BookRepository.class.getName()).log(Level.INFO, "Accessed : " + fl);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fl;
    }

}