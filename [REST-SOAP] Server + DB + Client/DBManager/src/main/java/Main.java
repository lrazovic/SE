import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:" + args[0]);
        Statement stat = conn.createStatement();

        if (args[1].equals("create")) {
            stat.executeUpdate("drop table if exists authors;");
            stat.executeUpdate("drop table if exists books;");
            stat.executeUpdate("create table authors (authorId, name, year);");
            stat.executeUpdate("create table books (id, title, yob, authorId);");

            // Add Books
            PreparedStatement prep = conn.prepareStatement("insert into books values (?, ?, ?, ?);");

            prep.setString(1, "1");
            prep.setString(2, "Harry Potter");
            prep.setString(3, "1999");
            prep.setString(4, "1");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "2");
            prep.setString(2, "Harry Potter 2");
            prep.setString(3, "2000");
            prep.setString(4, "1");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "3");
            prep.setString(2, "Harry Potter 3");
            prep.setString(3, "2001");
            prep.setString(4, "1");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "4");
            prep.setString(2, "Harry Potter 4");
            prep.setString(3, "2002");
            prep.setString(4, "1");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "7");
            prep.setString(2, "Harry Potter 5");
            prep.setString(3, "2003");
            prep.setString(4, "1");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "8");
            prep.setString(2, "Harry Potter 6");
            prep.setString(3, "2004");
            prep.setString(4, "1");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "9");
            prep.setString(2, "Harry Potter 7");
            prep.setString(3, "2005");
            prep.setString(4, "1");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "5");
            prep.setString(2, "Il Signore degli Anelli");
            prep.setString(3, "1930");
            prep.setString(4, "2");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "6");
            prep.setString(2, "Lo Hobbit");
            prep.setString(3, "1925");
            prep.setString(4, "2");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "10");
            prep.setString(2, "Il Codice da Vinci");
            prep.setString(3, "2003");
            prep.setString(4, "3");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            //Add Authors
            prep = conn.prepareStatement("insert into authors values (?, ?, ?);");

            prep.setString(1, "1");
            prep.setString(2, "J.K. Rowling");
            prep.setString(3, "1960");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "2");
            prep.setString(2, "J. R. R. Tolkien");
            prep.setString(3, "1920");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "3");
            prep.setString(2, "Dan Brown");
            prep.setString(3, "1964");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

        } else if (args[1].equals("all")) {
            ResultSet rs = stat.executeQuery("select * from books, authors where books.authorId = authors.authorId");
            while (rs.next()) {
                System.out.print("Book = " + rs.getString("id") + " is : ");
                System.out.println(rs.getString("title"));
                System.out.print("Author = " + rs.getString("authorId") + " is : ");
                System.out.println(rs.getString("name"));
            }
            rs.close();
        } else {
            String query = "select * from books, authors where books.authorId = authors.authorId and books.id = ?";
            PreparedStatement single = conn.prepareStatement(query);
            single.setString(1, args[1]);
            ResultSet rs = single.executeQuery();
            while (rs.next()) {
                System.out.print("Book = " + rs.getString("id") + " is : ");
                System.out.println(rs.getString("title"));
                System.out.print("Author = " + rs.getString("authorId") + " is : ");
                System.out.println(rs.getString("name"));
            }
            rs.close();
        }
        conn.close();

    }
}
