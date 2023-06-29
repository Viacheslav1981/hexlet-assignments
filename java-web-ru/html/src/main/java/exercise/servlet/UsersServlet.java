package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);

    }

    private List getUsers() throws JsonProcessingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Path path = Paths.get("src/main/resources/users.json").toAbsolutePath().normalize();
        Files.readString(path);

        JsonNode jsonNode = objectMapper.readTree(Files.readString(path));

        List<String> users = new ArrayList<>();

        for (int i = 0; i < jsonNode.size(); i++) {
            String id = String.valueOf(jsonNode.get(i).get("id"));
            String firstName = String.valueOf(jsonNode.get(i).get("firstName"));
            String lastName = String.valueOf(jsonNode.get(i).get("lastName"));
            String email = String.valueOf(jsonNode.get(i).get("email"));

            String user = id + " " + firstName + " " + lastName + " " + email;
            user = user.replaceAll("[\"”]", "");
            users.add(user);

        }

        return users;
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {

        PrintWriter pw = response.getWriter();


        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application | Users</title>
                        
                    </head>
                    <body>
                    <table>
                        
                """);

        for (Object user : getUsers()) {
            String[] str = user.toString().split(" ");
            String id = str[0];
            String fullName = str[1] + " " + str[2];

            body.append("<tr> <td> <a href=\"/users/" + id + "\">" + id + "</a> </td>");
            body.append("<td>" + fullName + "</td> </tr>");
        }

        body.append("""
                    
                      </table>
                    </body>
                </html>
                """);

        response.setContentType("text/html;charset=UTF-8");
        pw.println(body);


    }

    private void showUser(HttpServletRequest request,
                          HttpServletResponse response,
                          String id)
            throws IOException {

        PrintWriter pw = response.getWriter();

        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application | Users</title>
                        
                    </head>
                    <body>
                    <table>
                        
                """);

        int count = 0;

        for (Object user : getUsers()) {
            String[] str = user.toString().split(" ");
            String userId = str[0];
            String fullName = str[1] + " " + str[2];
            String email = str[3];

            if (userId.equals(id)) {
                count++;
                // pw.println(id + " " + fullName + " " + email);
                body.append("<tr> <td>" + id + "</td>");
                body.append("<td>" + fullName + "</td> ");
                body.append("<td>" + email + "</td> </tr>");

                body.append("""
                              </table>
                            </body>
                        </html>
                        """);
            }

        }

        if (count == 0) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        response.setContentType("text/html;charset=UTF-8");
        pw.println(body);

    }
}
