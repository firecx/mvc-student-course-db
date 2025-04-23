package org.example.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.example.mvc.model.DataBaseSQLite;
import org.example.mvc.model.Database;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add/student")
public class ServletAddStudent extends HttpServlet {
    private String docBase;
    private Database dataBase;

    @Override
    public void init() {
        try {
            super.init();
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        docBase = getServletContext().getRealPath("/");
        dataBase = Database.getInstance(docBase);
    }

    private String returnPath = "/WEB-INF/pages/add/student.jsp";

    private DataBaseSQLite dataBaseSQLite = DataBaseSQLite.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userid");
        String userName = request.getParameter("username");
        if (userId.isEmpty() || userName.isEmpty()) {
            request.setAttribute("message", "Ошибка: поля пустые!");
        } else {
            dataBase.addStudent(userId, userName);
            try {
                dataBaseSQLite.insertData("students", userName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("message", "Студент успешно добавлен!");
        }
        try {
            request.getRequestDispatcher(returnPath).forward(request, response);
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(returnPath).forward(request, response);
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
