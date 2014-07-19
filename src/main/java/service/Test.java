package service;

import com.mongodb.*;
import constants.TodoConstants;
import org.bson.BSONObject;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

/**
 * Created by chenxiaoshun on 14-7-19.
 */
public class Test {

    private static MongoClient mongoClient = null;
    private static DB db = null;
    static {
        try {
            mongoClient = new MongoClient("localhost");
            db = mongoClient.getDB(TodoConstants.DB_NAME);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        for (int i = 0; i < 100; i ++) {
            DBCollection col = db.getCollection(TodoConstants.COLLECTION_NAME);
            DBObject object = new BasicDBObject();
            object.put("test" + i, i);
            object.put("title", i);
            object.put("content", i);
            object.put("comment", i);
            object.put("finished", i);
            object.put("deleted", i);
            object.put("predictFinishTime", i);
            object.put("createTime", i);
            object.put("modifyTime", i);

            col.insert(object);
        }
        mongoClient.close();
    }

}
