package it.uniroma1.gRPCServer;

import io.grpc.stub.StreamObserver;
import it.uniroma1.gRPCExam.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BookListImpl extends BookListGrpc.BookListImplBase {
    private final Logger logger;
    private final Connection conn;

    public BookListImpl(String databasePath) {
        // Create the Logger
        logger = Logger.getLogger(BookListImpl.class.getName());
        // Open the connection to the DB
        conn = setConnection(databasePath);
    }

    public Connection setConnection(String databasePath) {
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
                logger.log(Level.SEVERE, ex.toString());
            }
            return DriverManager.getConnection("jdbc:sqlite:" + databasePath);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.toString());
        }
        return null;
    }

    @Override
    public void getBookDetails(BookId request, StreamObserver<Book> responseObserver) {
        logger.log(Level.INFO, "gRPC getBookDetails() requested");
        PreparedStatement stat;
        Book.Builder bookBuilder = Book.newBuilder();
        try {
            String query = "select * from books, authors where books.authorId = authors.authorId and books.id = ?";
            stat = conn.prepareStatement(query);
            stat.setString(1, request.getId());
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                bookBuilder.setId(rs.getInt("id"));
                bookBuilder.setTitle(rs.getString("title"));
                bookBuilder.setPublication(rs.getString("yob"));
                bookBuilder.setAuthor(rs.getString("name"));
                Book book = bookBuilder.build();
                rs.close();
                responseObserver.onNext(book);
                responseObserver.onCompleted();
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.toString());
        }
    }

    @Override
    public void getBooks(Input request, StreamObserver<Book> responseObserver) {
        logger.log(Level.INFO, "gRPC getBooks() requested");
        PreparedStatement stat;
        List<Book> booklist = new ArrayList<>();
        try {
            stat = conn.prepareStatement("select * from books, authors where books.authorId = authors.authorId");
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                Book.Builder bookBuilder = Book.newBuilder();
                bookBuilder.setId(rs.getInt("id"));
                bookBuilder.setTitle(rs.getString("title"));
                bookBuilder.setPublication(rs.getString("yob"));
                bookBuilder.setAuthor(rs.getString("name"));
                Book book = bookBuilder.build();
                booklist.add(book);
            }
            rs.close();
            for (Book book : booklist) {
                responseObserver.onNext(book);
            }
            responseObserver.onCompleted();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.toString());
        }
    }

}