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
            prep.setString(3, "1999");
            prep.setString(4, "1");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "3");
            prep.setString(2, "Harry Potter 3");
            prep.setString(3, "1999");
            prep.setString(4, "1");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "4");
            prep.setString(2, "Harry Potter 4");
            prep.setString(3, "1999");
            prep.setString(4, "1");
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
            prep.setString(2, "Tolkien");
            prep.setString(3, "1920");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

        } else {
            ResultSet rs = stat.executeQuery("select * from books;");
            while (rs.next()) {
                System.out.print("Book = " + rs.getString("id") + " is : ");
                System.out.println(rs.getString("title"));
            }
            rs.close();
        }
        conn.close();

    }
}
