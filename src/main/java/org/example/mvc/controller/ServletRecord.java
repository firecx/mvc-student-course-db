package org.example.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.example.mvc.model.DataBaseSQLite;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/record")
public class ServletRecord extends HttpServlet {
    private String returnPath = "/WEB-INF/pages/record.jsp";

    private DataBaseSQLite dataBaseSQLite = DataBaseSQLite.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String courseName = request.getParameter("courseName");
        String studentName = request.getParameter("studentName");

        if (courseName.isEmpty() || studentName.isEmpty()) {
            request.setAttribute("message", "Все поля должны быть заполнены!");
        } else {
            try {
                dataBaseSQLite.insertDataRecord(courseName, studentName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("message", "Студент успешно записан на курс!");
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
