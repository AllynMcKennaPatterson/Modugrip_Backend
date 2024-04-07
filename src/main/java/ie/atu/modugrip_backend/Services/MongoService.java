package ie.atu.modugrip_backend.Services;

import com.mongodb.client.*;
import ie.atu.modugrip_backend.Interfaces.ActionScriptRepo;
import ie.atu.modugrip_backend.Models.ScriptModels.ScriptData;
import ie.atu.modugrip_backend.Models.ScriptModels.ScriptString;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.mongodb.client.FindIterable;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoService {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    @Autowired
    private MongoTemplate mongoTemplate;
    public MongoService() {
        // Connect to MongoDB
        mongoClient = MongoClients.create("mongodb://localhost:27017"); // Change this to your MongoDB URI
        database = mongoClient.getDatabase("ActionScript"); // Change this to your database name
        collection = database.getCollection("scripts"); // Change this to your collection name
    }

//    public List<String> getAllJsonStrings() {
//        List<String> jsonStrings = new ArrayList<>();
//
//        // Query to retrieve all documents where the fieldname "jsonString" exists
//        try (MongoCursor<Document> cursor = collection.find(new Document("jsonString", new Document("$exists", true))).iterator()) {
//            while (cursor.hasNext()) {
//                Document document = cursor.next();
//                // Retrieve the value under the fieldname "jsonString" from each document
//                String jsonString = document.getString("jsonString");
//                jsonStrings.add(jsonString);
//            }
//        }
//
//        // Close the MongoDB client
////        mongoClient.close();
//
//        return jsonStrings;
//    }

    public List<Document> getAllDocuments() {
        List<Document> documents = new ArrayList<>();

        // Query to retrieve all documents from the collection
        FindIterable<Document> cursor = collection.find();

        // Iterate over the cursor and add each document to the list
        for (Document document : cursor) {
            documents.add(document);
        }

        // No need to close the cursor explicitly
        // The cursor will be closed automatically when it's fully iterated or closed by the caller

        return documents;
    }

    public void deleteDocumentByName(String name) {
        try {
            String nameWithoutQuotes = name.replaceAll("^\"|\"$", "");
            System.out.println("Name2: " + nameWithoutQuotes);
            Query query = new Query();
            query.addCriteria(Criteria.where("name").is(nameWithoutQuotes));
            mongoTemplate.remove(query, ScriptData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
