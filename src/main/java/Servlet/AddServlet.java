package Servlet;

import Date.DataBase;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/add")
public class AddServlet extends HttpServlet {
    private DataBase dataBase;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.dataBase = DataBase.getInstance();
        dataBase.addStudent(request.getParameter("userid"),request.getParameter("username"));
    }
}


