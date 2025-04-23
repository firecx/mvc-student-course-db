package org.example.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.example.mvc.model.DataBaseSQLite;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add/course")
public class ServletAddCourse extends HttpServlet {
    private String returnPath = "/WEB-INF/pages/add/course.jsp";

    private DataBaseSQLite dataBaseSQLite = DataBaseSQLite.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String courseName = request.getParameter("coursename");
        String duration = request.getParameter("coursename"); //заменить
        String description = request.getParameter("coursename"); //заменить
        String price = request.getParameter("coursename"); //заменить
        if (courseName.isEmpty()) {
            request.setAttribute("message", "Ошибка: поля пустые!");
        } else {
            try {
                dataBaseSQLite.insertDataCourse(courseName, duration, description, price);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("message", "Курс успешно добавлен!");
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
