package helper;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;

public class db {

    private static MongoClient mongoClient = MongoClients.create();
    private static MongoDatabase mongoDatabase = mongoClient.getDatabase("sparkle");
    private static MongoCollection<Document> userCollection = mongoDatabase.getCollection("user");

    public static void insertDb(Document document, String where) {
        if (where.equals("user")) {
            userCollection.insertOne(document);
        }
    }

    public static Document getUser(String id) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("userID", id);
        Document user = userCollection.find(whereQuery).first();
        return user;
    }

    public static boolean checkUser(String id) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("userID", id);
        Document checkUser = userCollection.find(whereQuery).first();
        return checkUser != null;
    }

    public static void createUser(String id) {
        // Document doc = new Document("Test", 1);
        // helper.db.insertDb(doc, "user");
        Document newUser = new Document("userID", id)
                .append("money", "100");
        userCollection.insertOne(newUser);
    }
}
