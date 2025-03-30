package Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.ivt.mvc.Task;
import ru.ivt.mvc.TaskDataBase;
import ru.ivt.mvc.TaskView;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    private TaskDataBase dataBase;
    private TaskView view;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        Task task = new Task(dataBase.getNextId(), request.getParameter("username"));
        int id = dataBase.addTask(task);
        view.showMessage("Задача добавлена. ID:"+String.valueOf(id));
    }
}


