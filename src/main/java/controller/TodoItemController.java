package controller;

import domain.TodoItem;
import service.TodoItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

/**
 * Created by chenxiaoshun on 14-6-29.
 */
public class TodoItemController extends HttpServlet{

    private TodoItemService todoItemService = new TodoItemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        TodoItem todoItem = new TodoItem();
        switch (action) {
            case "create":
                todoItem.setTitle(req.getParameter("title"));
                todoItem.setContent(req.getParameter("content"));
                todoItem.setComment(req.getParameter("comment"));

                todoItemService.create(todoItem);
                break;
            case "update":
                todoItem.setTitle(req.getParameter("title"));
                todoItem.setContent(req.getParameter("content"));
                todoItem.setComment(req.getParameter("comment"));
                todoItem.setId(req.getParameter("id"));

                todoItemService.update(todoItem);
                break;
            case "delete":
                todoItemService.delete(req.getParameter("id"));
                break;
            case "finish":
                todoItem
        }

        int days = Integer.valueOf(req.getParameter("days"));
        int finished = Integer.valueOf(req.getParameter("finished"));
        List<TodoItem> todoItemList = new ArrayList<TodoItem>();
        try {
            todoItemList = todoItemService.getList(days, finished);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        req.setAttribute("todoItemList", todoItemList);
        req.setAttribute("days", days);
        req.setAttribute("finished", finished);
        req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
    }
}
