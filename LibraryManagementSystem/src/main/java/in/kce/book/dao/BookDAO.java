package in.kce.book.dao;

import java.sql.*;
import in.kce.book.bean.*;
import in.kce.book.util.DButil;

public class BookDAO {

    public int createBook(BookBean b) {
        try {
            Connection con = DButil.getDBConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Book_Tbl (ISBN, Book_title, Book_type, Author_code, Book_cost) VALUES (?,?,?,?,?)");

            ps.setString(1, b.getIsbn());
            ps.setString(2, b.getBookName());
            ps.setString(3, String.valueOf(b.getBookType()));
            ps.setInt(4, b.getAuthor().getAuthorCode());
            ps.setFloat(5, b.getCost());

            return ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    public BookBean fetchBook(String isbn) {
        try {
            Connection con = DButil.getDBConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT b.ISBN, b.Book_title, b.Book_type, b.Book_cost, " +
                "a.Author_code, a.Author_name, a.Contact_no " +
                "FROM Book_Tbl b JOIN Author_Tbl a " +
                "ON b.Author_code=a.Author_code WHERE b.ISBN=?");

            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                BookBean b = new BookBean();
                b.setIsbn(rs.getString(1));
                b.setBookName(rs.getString(2));
                b.setBookType(rs.getString(3).charAt(0));
                b.setCost(rs.getFloat(4));

                AuthorBean a = new AuthorBean();
                a.setAuthorCode(rs.getInt(5));
                a.setAuthorName(rs.getString(6));
                a.setContactNo(rs.getLong(7));

                b.setAuthor(a);
                return b;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}
