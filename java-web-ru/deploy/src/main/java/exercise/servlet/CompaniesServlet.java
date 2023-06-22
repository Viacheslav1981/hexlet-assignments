package exercise.servlet;

import exercise.Data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;

import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        PrintWriter pw = response.getWriter();
        //pw.println("test!!");
        // request.getParameter()
        String queryString = request.getQueryString();
        String param = request.getParameter("search");
        // pw.write(queryString);
        // pw.write(param);

        //  pw.println(queryString);
        // pw.println(param);

        List<String> companies = Data.getCompanies();
        int count = 0;
        for (String company : companies) {
            if (queryString != null) {
                if (company.contains(param)) {
                    pw.println(company);
                    count++;
                }
            } else {
                pw.println(company);
                count++;
            }
        }

        if (count == 0) {
            pw.println("Companies not found");
        }

        pw.close();

    }
}
