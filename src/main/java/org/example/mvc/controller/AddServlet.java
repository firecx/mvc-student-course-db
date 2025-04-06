package org.example.mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import org.example.mvc.model.Database;

public class AddServlet extends HttpServlet {
    private Database database;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/add.jsp");
        if (requestDispatcher == null) {
            PrintWriter writer = response.getWriter();

            try {
                writer.println(this.getServletContext().getContextPath());
            } finally {
                writer.close();
            }
            return;
        }
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.database = Database.getInstance();
        database.addStudent(request.getParameter("userid"), request.getParameter("username"));
    }
}
