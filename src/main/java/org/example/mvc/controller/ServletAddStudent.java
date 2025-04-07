package org.example.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.example.mvc.model.Database;


@WebServlet("/addStudent")
public class ServletAddStudent extends HttpServlet {
    private Database dataBase;
    private String returnPath = "pages/pagesAddStudent.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(!request.getParameter("userid").isEmpty() && !request.getParameter("username").isEmpty()) {
            this.dataBase = Database.getInstance();
            String path = request.getContextPath();
            dataBase.addStudent(request.getParameter("userid"),request.getParameter("username"));
            request.setAttribute("message", "Студент успешно добавлен!");
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

