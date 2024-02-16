package org.georgiancollege.week06;

import java.sql.*;

public class DBUtility {
    // create variables to hold username, password and connection url
    private static String user = DBCreds.findUser();
    private static String pass = DBCreds.findPass();
    private static String connectURL = "jdbc:mysql://172.31.22.43:3306/" + user;

    // a static function which inserts the book data to db
    // this will return the generated book id
    public static int insertBookIntoDB(Book book) throws SQLException {
        int bookId = -1;
        ResultSet resultSet = null;

        // create the sql string to insert data
        String sql = "INSERT INTO books (book_name, author, genre, price, is_available) VALUES (?, ?, ?, ?, ?);";

        // try with resource block
        try(
                Connection conn = DriverManager.getConnection(connectURL, user, pass);
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[]{"bookId"});
                ){
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setBoolean(5, book.isAvailable());

            // execute the insert statement
            preparedStatement.executeUpdate();

            // get the new book id from the result set
            resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                bookId = resultSet.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                resultSet.close();
            }
        }

        return bookId;
    }
}
