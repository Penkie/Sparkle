package helper;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;

public class db {

    private static MongoClient mongoClient = MongoClients.create();
    private static MongoDatabase mongoDatabase = mongoClient.getDatabase("sparkle");
    private static MongoCollection<Document> userCollection = mongoDatabase.getCollection("user");
    private static MongoCollection<Document> farmCollection = mongoDatabase.getCollection("farm");

    public static Document getUser(String id) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("userID", id);
        return userCollection.find(whereQuery).first();
    }

    public static Document getFarm(String id) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("userID", id);
        return farmCollection.find(whereQuery).first();
    }

    public static boolean checkUser(String id) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("userID", id);
        Document checkUser = userCollection.find(whereQuery).first();
        return checkUser != null;
    }

    public static void createUser(String id) {
        Document newUser = new Document("userID", id)
                .append("sparks", "100")
                .append("minionsSlot", 5)
                .append("woodlvl", 0)
                .append("stonelvl", 0)
                .append("fishlvl", 0);
        Document newFarm = new Document("userID", id)
                .append("wood", 0)
                .append("stone", 0)
                .append("fish", 0);

        userCollection.insertOne(newUser);
        farmCollection.insertOne(newFarm);
    }

    public static void updateUser(String id, String where, String field, Object newOne) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("userID", id);
        BasicDBObject update = new BasicDBObject();
        update.put("$set", new BasicDBObject("wood", newOne));
        if (where.equals("user")) {
            userCollection.updateOne(whereQuery, update);
        } else if (where.equals("farm")) {
            farmCollection.updateOne(whereQuery, update);
        }
    }
}
