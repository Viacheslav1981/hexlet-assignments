package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.util.Map;

import static exercise.App.getUsers;

import exercise.Users;

public class SessionServlet extends HttpServlet {

    private Users users = getUsers();

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        if (request.getRequestURI().equals("/login")) {
            showLoginPage(request, response);
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        switch (request.getRequestURI()) {
            case "/login" -> login(request, response);
            case "/logout" -> logout(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showLoginPage(HttpServletRequest request,
                               HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Map<String, String> user = users.findByEmail(email);
        String id = user.get("id");
        session.setAttribute("user", user);


        if ((user != null) && password.equals("password")) {
           // session.setAttribute("user", user);
            session.setAttribute("userId", id);
            session.setAttribute("flash", "Вы успешно вошли");
            response.sendRedirect("/");
        } else {
         //   RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            session.setAttribute("flash", "Неверные логин или пароль");
            response.setStatus(422);

            response.setStatus(422);
            session.setAttribute("email", user.get("email"));

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request, response);

       //     response.sendRedirect("/login.jsp");
         //   session.setAttribute("email", user.get("email"));
        //    response.sendError(422);




            //   request.setAttribute("email", email);
         //  response.sendRedirect("/login.jsp");
          //  RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
           /// requestDispatcher.forward(request, response);
          //  return;

        }
    }

    private void logout(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {

        // BEGIN
        HttpSession session = request.getSession();
        // Удаляем атрибут из сессии
        session.removeAttribute("userId");
        session.setAttribute("flash", "Вы успешно вышли");
        response.sendRedirect("/");

        // END
    }
}
