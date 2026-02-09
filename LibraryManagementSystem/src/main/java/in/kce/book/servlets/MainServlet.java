package in.kce.book.servlets;

import java.io.IOException;
import in.kce.book.bean.*;
import in.kce.book.dao.AuthorDAO;
import in.kce.book.service.Adminstrator;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        String op = req.getParameter("operation");

        if (op.equals("AddBook")) {

            AuthorBean a = new AuthorDAO().getAuthor(req.getParameter("authorName"));
            if (a == null) {
                res.sendRedirect("Invalid.html");
                return;
            }

            BookBean b = new BookBean();
            b.setIsbn(req.getParameter("isbn"));
            b.setBookName(req.getParameter("bookName"));
            b.setBookType(req.getParameter("bookType").charAt(0));
            b.setCost(Float.parseFloat(req.getParameter("cost")));
            b.setAuthor(a);

            String result = new Adminstrator().addBook(b);

            if (result.equals("SUCCESS"))
                res.sendRedirect("Menu.html");
            else
                res.sendRedirect("Invalid.html");
        }

        if (op.equals("Search")) {
            BookBean b = new Adminstrator().viewBook(req.getParameter("isbn"));
            if (b == null)
                res.sendRedirect("Invalid.html");
            else {
                req.getSession().setAttribute("book", b);
                req.getRequestDispatcher("ViewServlet").forward(req, res);
            }
        }
    }
}
