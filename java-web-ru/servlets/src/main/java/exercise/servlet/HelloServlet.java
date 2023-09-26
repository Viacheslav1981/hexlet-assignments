package io.hexlet.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // String message = "Hello from Jakarta Servlet!";
     //   String[] users = {"Mike", "Nina"};
     //   req.setAttribute("message", message);
     //   req.setAttribute("users", users);

      //  req.getRequestDispatcher("/WEB-INF/users.jsp").forward(req, resp);
      //  Object name = req.getAttribute("name");
        String name = req.getParameter("name");

        req.setAttribute("message","Hello, " + name + "!");

        req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);


    }




}
