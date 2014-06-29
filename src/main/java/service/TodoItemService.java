package service;

import dao.TodoItemDao;
import domain.TodoItem;

import java.text.ParseException;
import java.util.List;

/**
 * Created by chenxiaoshun on 14-6-29.
 */
public class TodoItemService {

    private TodoItemDao todoItemDao = new TodoItemDao();

    /**
     * create new todoitem
     * @param todoItem
     * @return
     */
    public boolean create(TodoItem todoItem) {
       return todoItemDao.create(todoItem);
    }

    /**
     * update the selected item
     * @param todoItem
     * @return
     */
    public boolean update(TodoItem todoItem) {
        return todoItemDao.update(todoItem);
    }

    /**
     * delete the selected todoitem
     * @param id
     * @return
     */
    public boolean delete(String id) {
        return todoItemDao.delete(id);
    }

    /**
     * return todoitem list according to the days given
     * eg:1 return just today's list
     * 2 return yesterday's and today's
     * @param days
     * @param finished
     * @return
     * @throws ParseException
     */
    public List<TodoItem> getList(int days,int finished) throws ParseException {
        return todoItemDao.getList(days, finished);
    }

}
