package org.example.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.example.mvc.model.DAO.CourseDAO;
import org.example.mvc.model.units.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add/course")
public class ServletAddCourse extends HttpServlet {
    private String returnPath = "/WEB-INF/pages/add/course.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String courseName = request.getParameter("name");
        String description = request.getParameter("description");
        String duration = request.getParameter("duration");
        String price = request.getParameter("price");
        if (courseName.isEmpty() || description.isEmpty() || duration.isEmpty() || price.isEmpty()) {
            request.setAttribute("message", "Заполните все поля!");
        } else {
            try {
                new CourseDAO().createCourse(new Course(courseName, description, duration, Integer.parseInt(price)));
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
