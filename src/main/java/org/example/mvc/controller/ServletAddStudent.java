package org.example.mvc.controller;

import java.io.IOException;

import org.example.mvc.model.Database;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add/student")
public class ServletAddStudent extends HttpServlet {
    private Database dataBase = Database.getInstance();
    private String returnPath = "/WEB-INF/pages/add/student.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        String username = request.getParameter("username");
        if (userid.isEmpty() || username.isEmpty()) {
            request.setAttribute("message", "Ошибка: поля пустые!");
        } else {
            dataBase.addStudent(userid, username);
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
