package org.example.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.example.mvc.model.DAO.CourseDAO;
import org.example.mvc.model.DAO.RecordDAO;
import org.example.mvc.model.DAO.StudentDAO;
import org.example.mvc.model.units.Course;
import org.example.mvc.model.units.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add/record")
public class ServletRecord extends HttpServlet {
    private String returnPath = "/WEB-INF/pages/add/record.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String courseName = request.getParameter("courseName");
        String studentName = request.getParameter("studentName");

        if (courseName.isEmpty() || studentName.isEmpty()) {
            request.setAttribute("message", "Все поля должны быть заполнены!");
        }
        else {
            Course course;
            Student student;
            
            try {
                course = new CourseDAO().getCourseByName(courseName);                
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (course == null){
                request.setAttribute("message", "Курс не найден!");
            }
            else {
                try {
                    student = new StudentDAO().getStudentByName(studentName);
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                if (student == null) {
                    request.setAttribute("message", "Студент не найден!");
                }
                else {
                    try{
                        new RecordDAO().createRecord(course, student);
                    }
                    catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    
                    request.setAttribute("message", "Успешная запись!");
                } 
            }
        }
        try {
            request.getRequestDispatcher(returnPath).forward(request, response);
        }catch (ServletException e) {
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
