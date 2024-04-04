package ie.atu.modugrip_backend.Services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCollection<Document> mongoCollection() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017"); // Change this to your MongoDB URI
        MongoDatabase database = mongoClient.getDatabase("ActionScript"); // Change this to your database name
        return database.getCollection("scripts"); // Change this to your collection name
    }
}