package in.kce.book.service;

import in.kce.book.bean.BookBean;
import in.kce.book.dao.BookDAO;

public class Adminstrator {

    public String addBook(BookBean b) {
        if (b == null || b.getIsbn().isEmpty() || b.getBookName().isEmpty()
                || b.getAuthor() == null || b.getCost() <= 0)
            return "INVALID";

        int r = new BookDAO().createBook(b);
        return (r > 0) ? "SUCCESS" : "FAILURE";
    }

    public BookBean viewBook(String isbn) {
        return new BookDAO().fetchBook(isbn);
    }
}
