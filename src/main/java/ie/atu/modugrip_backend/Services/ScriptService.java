package ie.atu.modugrip_backend.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ie.atu.modugrip_backend.Clients.PublishServiceClient;
import ie.atu.modugrip_backend.Interfaces.ActionScriptRepo;
import ie.atu.modugrip_backend.Models.GripperData;
import ie.atu.modugrip_backend.Models.ScriptModels.*;
import ie.atu.modugrip_backend.Models.SliderData;

import ie.atu.modugrip_backend.Models.ToolStatus;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ScriptService {

    PublishServiceClient publishServiceClient;
    ActionScriptRepo scriptRepo;

    private final MongoCollection<Document> collection;

    MongoService mongoService;
    private final ObjectMapper objectMapper;

    public ScriptService(PublishServiceClient publishServiceClient, ActionScriptRepo scriptRepo, ObjectMapper objectMapper, MongoService mongoService, MongoCollection<Document> collection){
        this.publishServiceClient = publishServiceClient;
        this.scriptRepo = scriptRepo;
        this.objectMapper = objectMapper;
        this.mongoService = mongoService;
        this.collection = collection;
    }

    public void saveScriptToMongo(String json) throws IOException {
        System.out.println("JSON: " + json);

        ObjectMapper objectMapper = new ObjectMapper();
        ScriptData scriptString = objectMapper.readValue(json, ScriptData.class);
        if (scriptString.getAction().isEmpty()) {
            System.out.println("Script is empty");
        } else {
            scriptString.setName(scriptString.getName());
            scriptString.setAction(scriptString.getAction());
            scriptRepo.save(scriptString);
        }
    }

    public List<ScriptString> getAllScripts() {
        List<ScriptString> allScripts = new ArrayList<>();

        // Query to retrieve all documents from the collection
        List<Document> documents = mongoService.getAllDocuments();

        // Convert each document to ScriptString object
        for (Document doc : documents) {
            ScriptString scriptString = new ScriptString();

            scriptString.setId(doc.getObjectId("_id").toString());
            // Set the name value
            if (doc.containsKey("name")) {
                scriptString.setName(doc.getString("name"));
            }

            if (doc.containsKey("action")) {
                scriptString.setAction((List<Data>) doc.get("action"));
            }
            allScripts.add(scriptString);
        }

        return allScripts;
    }


    public void processSliderAction(int index, Action action) {
        // Process slider action
        SliderData sd = new SliderData();
        System.out.println("Processing slider action at index " + index + ": " + action.getVal1());
        sd.setServo1(Integer.parseInt(action.getVal1()));
        sd.setServo2(Integer.parseInt(action.getVal2()));
        sd.setServo3(Integer.parseInt(action.getVal3()));
        sd.setServo5(Integer.parseInt(action.getVal5()));
        publishServiceClient.publishSlider(sd);
    }

    public void processGripperAction(int index, int width) {
        GripperData gd = new GripperData();
        gd.setServo4(width);
        publishServiceClient.publishGripper(gd);
    }

    public void processToolStatus(int status){
        ToolStatus ts = new ToolStatus();
        ts.setToolstatus(status);
        publishServiceClient.publishToolStatus(ts);
    }

    public void processDelayAction(int index, int delay) throws InterruptedException {
        // Process delay action
        System.out.println("Processing delay action at index " + index + ": delay = " + delay);
        Thread.sleep(delay);
    }



    public void processEndEffectorAction(int index, Action action) {
        // Process end effector action
        System.out.println("Processing end effector action at index " + index + ": " + action.toString());
    }


    public void deleteScriptByName(String name){
        mongoService.deleteDocumentByName(name);
    }
}
