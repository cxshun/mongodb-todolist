package dao;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.sun.tools.javac.comp.Todo;
import constants.TodoConstants;
import domain.TodoItem;
import org.bson.BSONObject;

import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chenxiaoshun on 14-6-28.
 */
public class TodoItemDao {

    private static DB db = null;

    private Gson gson = new Gson();

    static {
        try {
            MongoClient client = new MongoClient("localhost", 27017);
            db = client.getDB(TodoConstants.DB_NAME);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * create a new todoItem
     * @param todoItem
     * @return
     */
    public boolean create(TodoItem todoItem) {
        DBObject object = new BasicDBObject();
        object.put("title", todoItem.getTitle());
        object.put("content", todoItem.getContent());
        object.put("comment", todoItem.getComment());
        object.put("finished", TodoConstants.FinishFlag.NOT_FINISHED);
        object.put("deleted", TodoConstants.DeleteFlag.NOT_DELETED);
        object.put("predictFinishTime", todoItem.getPredictFinishTime());
        object.put("createTime", todoItem.getCreateTime());
        object.put("modifyTime", todoItem.getModifyTime());

        db.getCollection(TodoConstants.COLLECTION_NAME).insert(object);
        return true;
    }

    /**
     * update todoItem's content
     * here we retrieve the old item from the mongodb in order to get the createTime
     * then set it to the new item,make it like update operation
     * @param todoItem
     * @return
     */
    public boolean update(TodoItem todoItem) {
        DBObject object = new BasicDBObject();
        object.put("id", todoItem.getId());

        DBCollection dbCollection = db.getCollection(TodoConstants.COLLECTION_NAME);
        //set old createTime to the new object,in order to make it like update
        DBObject existedObject = dbCollection.findOne(object);

        TodoItem newTodoItem = new TodoItem();
        newTodoItem.setCreateTime(Long.valueOf(existedObject.get("createTime").toString()));
        newTodoItem.setComment(todoItem.getComment());
        newTodoItem.setPredictFinishTime(Long.valueOf(existedObject.get("predictFinishTime").toString()));
        newTodoItem.setTitle(existedObject.get("title").toString());
        newTodoItem.setContent(todoItem.getContent());
        newTodoItem.setCreateTime(Long.valueOf(existedObject.get("createTime").toString()));
        newTodoItem.setModifyTime(Long.valueOf(existedObject.get("modifyTime").toString()));
        newTodoItem.setFinished(Integer.valueOf(existedObject.get("finish").toString()));
        newTodoItem.setDeleted(Integer.valueOf(existedObject.get("deleted").toString()));

        dbCollection.remove(object);
        return create(todoItem);
    }

    /**
     * Set task as finish
     * @param id    task id
     * @return
     */
    public boolean finish(String id) {
        DBObject object = new BasicDBObject();
        object.put("id", id);

        DBCollection dbCollection = db.getCollection(TodoConstants.COLLECTION_NAME);
        DBObject existedObject = dbCollection.findOne(object);

        TodoItem todoItem = new TodoItem();
        todoItem.setComment(existedObject.get("comment").toString());
        todoItem.setPredictFinishTime(Long.valueOf(existedObject.get("predictFinishTime").toString()));
        todoItem.setTitle(existedObject.get("title").toString());
        todoItem.setContent(existedObject.get("content").toString());
        todoItem.setCreateTime(Long.valueOf(existedObject.get("createTime").toString()));
        todoItem.setModifyTime(Long.valueOf(existedObject.get("modifyTime").toString()));
        todoItem.setFinished(1);
        todoItem.setDeleted(0);

        dbCollection.remove(object);
        return create(todoItem);
    }

    /**
     * delete a todoitem
     * just make deleted mark to 1
     * not physically delete
     * @param id
     * @return
     */
    public boolean delete(String id) {
        DBObject object = new BasicDBObject();
        object.put("id", id);

        db.getCollection(TodoConstants.COLLECTION_NAME).remove(object);
        return true;
    }

    /**
     * get todoitem list by days
     * for 1,only return today's todoitem list
     * for 2,return yesterday's and today's
     * etc.
     * @param days  days that need to be retrieve
     * @param finished whether or not to get the finished item
     * @return
     * @throws java.text.ParseException
     */
    public List<TodoItem> getList(int days, int finished) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -(days - 1));

        cal.getTimeInMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH)
            +" 00:00:00");

        cal = Calendar.getInstance();
        Date endDate = sdf.parse(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + (cal.get(Calendar.DAY_OF_MONTH) + 1)
            + " 00:00:00");

        DBObject object = new BasicDBObject();
        BasicDBList basicDBList = new BasicDBList();

        basicDBList.add(new BasicDBObject("createTime", new BasicDBObject("$gte", startDate.getTime())));
        basicDBList.add(new BasicDBObject("modifyTime", new BasicDBObject("$lte", endDate.getTime())));

        object.put("$and", basicDBList);
        object.put("deleted", TodoConstants.DeleteFlag.NOT_DELETED);
        object.put("finished", finished);

        List<TodoItem> todoItemList = new ArrayList<TodoItem>();
        DBCursor cursor = db.getCollection(TodoConstants.COLLECTION_NAME).find(object);
        while(cursor.hasNext()) {
            DBObject dbObject = cursor.next();

            TodoItem todoItem = new TodoItem();
            todoItem.setId(dbObject.get("_id").toString());
            todoItem.setContent(dbObject.get("content").toString());
            todoItem.setComment(dbObject.get("comment") != null?dbObject.get("comment").toString():"");
            todoItem.setModifyTime(Long.valueOf(dbObject.get("modifyTime").toString()));
            todoItem.setCreateTime(Long.valueOf(dbObject.get("createTime").toString()));
            todoItem.setPredictFinishTime(Long.valueOf(dbObject.get("predictFinishTime").toString()));
            todoItem.setTitle(dbObject.get("title").toString());

            todoItemList.add(todoItem);
        }

        return todoItemList;
    }

}
