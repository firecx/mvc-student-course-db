package org.example.mvc.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.example.mvc.model.Database;


@WebServlet("/add")
public class AddServlet extends HttpServlet {
    private Database dataBase;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.dataBase = Database.getInstance();
        dataBase.addStudent(request.getParameter("userid"),request.getParameter("username"));
    }
}


