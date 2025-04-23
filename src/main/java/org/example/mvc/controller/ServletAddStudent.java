package org.example.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.example.mvc.model.DataBaseSQLite;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add/student")
public class ServletAddStudent extends HttpServlet {
    private String returnPath = "/WEB-INF/pages/add/student.jsp";

    private DataBaseSQLite dataBaseSQLite = DataBaseSQLite.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("username");
        String userEmail = request.getParameter("useremail");
        String userPhone = request.getParameter("userphone");
        if (userName.isEmpty() || (userPhone.isEmpty() && userEmail.isEmpty())) {
            request.setAttribute("message", "Ошибка: поля пустые!");
        } else {
            try {
                dataBaseSQLite.insertDataStudent(userName, userPhone, userEmail);
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
