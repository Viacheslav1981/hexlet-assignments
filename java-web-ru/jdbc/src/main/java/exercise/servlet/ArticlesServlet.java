package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class ArticlesServlet extends HttpServlet {

    // private int count = 0;
    private int countNext = 0;
    private int num = 0;
    private int countPrev = 0;

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                try {
                    showArticles(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException, ServletException, SQLException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");


        int page = (request.getParameter("page") == null) ? 1 : Integer.parseInt(request.getParameter("page"));

        if (page < 1) {
            page = 1;
        }
        request.setAttribute("countNext", page + 1);
        request.setAttribute("countPrev", page - 1);


        // BEGIN
        List<Map<String, String>> articles = new ArrayList<>();


        //  String query = "SELECT id, name FROM companies LIMIT ?";
        // String query = "SELECT id, title, body FROM articles";
        String query = "SELECT id, title, body FROM articles order by id LIMIT 10 OFFSET ?";
        // String query = "SELECT id, title, body FROM articles LIMIT 10";


        PreparedStatement preparedStatement = connection.prepareStatement(query);


        if (page == 1) {
            preparedStatement.setInt(1, 0);
        } else {
            preparedStatement.setInt(1, (page - 1) * 10);

        }

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            articles.add(Map.of(
                    "id", resultSet.getString("id"),
                    "title", resultSet.getString("title"),
                    "body", resultSet.getString("body")
            ));
        }

        request.setAttribute("articles", articles);

        //  /articles?page=2

        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
        // TemplateEngineUtil.render("articles?page=" + num, request, response);
        countNext++;

    }

    private void showArticle(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String action = getAction(request);

        String title = "";
        String body = "";
        int id = 0;
        try {
            String query = "SELECT id, title, body FROM articles WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(action));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                title = rs.getString("title");
                body = rs.getString("body");
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        if (id == 0) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // END
        request.setAttribute("body", body);
        request.setAttribute("title", title);
        TemplateEngineUtil.render("articles/show.html", request, response);
    }


}
