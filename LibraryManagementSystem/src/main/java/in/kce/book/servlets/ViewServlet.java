package in.kce.book.servlets;

import java.io.*;
import in.kce.book.bean.BookBean;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        BookBean b = (BookBean) req.getSession().getAttribute("book");

        out.println("<h2>Book Details</h2>");
        out.println("Book : " + b.getBookName() + "<br>");
        out.println("Author : " + b.getAuthor().getAuthorName() + "<br>");
        out.println("Cost : " + b.getCost());
    }
}
