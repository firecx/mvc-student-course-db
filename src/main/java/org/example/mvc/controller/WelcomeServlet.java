package org.example.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class WelcomeServlet extends HttpServlet {
    public WelcomeServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        try {
            writer.printf("<h1>Welcome from %s</h1>", this.getClass().getSimpleName());
        } finally {
            writer.close();
        }

    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        response.setContentType("text/html");
//        PrintWriter writer = response.getWriter();
//
//        String name = request.getParameter("username");
//        String age = request.getParameter("userage");
//        String gender = request.getParameter("gender");
//        String country = request.getParameter("country");
//        String[] courses = request.getParameterValues("courses");
//
//        try {
//            writer.println("<p>Name: " + name + "</p>");
//            writer.println("<p>Age: " + age + "</p>");
//            writer.println("<p>Gender: " + gender + "</p>");
//            writer.println("<p>Country: " + country + "</p>");
//            writer.println("<h4>Course</h4>");
//            for(String course: courses)
//                writer.println("<li>" + course + "</li>");
//        } finally {
//            writer.close();
//        }
//    }
}