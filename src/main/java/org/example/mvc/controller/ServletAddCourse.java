package org.example.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.example.mvc.model.Database;


@WebServlet("/addCourse")
public class ServletAddCourse extends HttpServlet {
    private Database dataBase;
    private String returnPath = "WEB-INF/pages/pagesAddCourse.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(!request.getParameter("courseid").isEmpty() && !request.getParameter("coursename").isEmpty()) {
            this.dataBase = Database.getInstance();
            dataBase.addCourse(request.getParameter("courseid"),request.getParameter("coursename"));
            request.setAttribute("message", "Курс успешно добавлен!");
            request.getRequestDispatcher(returnPath).forward(request, response);
        }
        else {
            request.setAttribute("message", "Ошибка: поля пустые!");
            request.getRequestDispatcher(returnPath).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(returnPath).forward(request, response);
    }
}


