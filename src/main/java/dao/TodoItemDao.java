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

    private static DBCollection dbCollection = null;

    private static Gson gson = new Gson();

    static {
        try {
            MongoClient client = new MongoClient();
            DB db = client.getDB(TodoConstants.DB_NAME);
            dbCollection = db.getCollection(TodoConstants.COLLECTION_NAME);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public boolean create(TodoItem todoItem) throws UnknownHostException {
        DBObject object = new BasicDBObject();
        object.put("title", todoItem.getTitle());
        object.put("content", todoItem.getContent());
        object.put("comment", todoItem.getComment());
        object.put("finished", TodoConstants.FinishFlag.NOT_FINISHED);
        object.put("deleted", TodoConstants.DeleteFlag.NOT_DELETED);
        object.put("predictFinishTime", todoItem.getPredictFinishTime());
        object.put("createTime", new Date().getTime());
        object.put("modifyTIme", new Date().getTime())

        WriteResult wr = dbCollection.insert(object);
        return wr.getN() > 0;
    }

    public boolean update(TodoItem todoItem) {
        DBObject object = new BasicDBObject();
        object.put("id", todoItem);

        WriteResult wr = dbCollection.remove(object);
        if (wr.getN() > 0) {
            return dbCollection.insert().getN() > 0;
        }
        return false;
    }

    public boolean delete(int id) {
        DBObject object = new BasicDBObject();
        object.put("id", id);

        WriteResult wr = dbCollection.remove(object);
        return wr.getN() > 0;
    }

    public List<TodoItem> getList(int days, int finished) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -days);

        cal.getTimeInMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH));

        cal = Calendar.getInstance();
        Date endDate = sdf.parse(cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH));

        DBObject object = new BasicDBObject();
        BasicDBList basicDBList = new BasicDBList();

        basicDBList.add(new BasicDBObject("createTime", new BasicDBObject("$ge", startDate.getTime())));
        basicDBList.add(new BasicDBObject("modifyTime", new BasicDBObject("$le", endDate.getTime())));

        object.put("$or", basicDBList);
        object.put("deleted", TodoConstants.DeleteFlag.NOT_DELETED);
        object.put("finished", finished);

        List<TodoItem> todoItemList = new ArrayList<TodoItem>();
        DBCursor cursor = dbCollection.find(object);
        while(cursor.hasNext()) {
            DBObject dbObject = cursor.next();

            TodoItem todoItem = new TodoItem();
            todoItem.setId(dbObject.get("id").toString());
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
