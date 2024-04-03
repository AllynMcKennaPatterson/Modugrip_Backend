package ie.atu.modugrip_backend.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ie.atu.modugrip_backend.Clients.PublishServiceClient;
import ie.atu.modugrip_backend.Interfaces.ActionScriptRepo;
import ie.atu.modugrip_backend.Models.ScriptModels.Action;
import ie.atu.modugrip_backend.Models.ScriptModels.Data;
import ie.atu.modugrip_backend.Models.ScriptModels.Script;
import ie.atu.modugrip_backend.Models.ScriptModels.ScriptString;
import ie.atu.modugrip_backend.Models.SliderData;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ScriptService {

    PublishServiceClient publishServiceClient;
    ActionScriptRepo scriptRepo;
    private final ObjectMapper objectMapper;

    public ScriptService(PublishServiceClient publishServiceClient, ActionScriptRepo scriptRepo, ObjectMapper objectMapper){
        this.publishServiceClient = publishServiceClient;
        this.scriptRepo = scriptRepo;
        this.objectMapper = objectMapper;
    }

    public void saveScriptToMongo(String json) throws IOException {
        System.out.println("JSON: " + json);
        ScriptString script = new ScriptString();
        script.setJsonString(json);
        scriptRepo.save(script);
    }

    public void processSliderAction(int index, Action action) {
        // Process slider action
        SliderData sd = new SliderData();
        System.out.println("Processing slider action at index " + index + ": " + action.getVal1());
        sd.setServo1(Integer.parseInt(action.getVal1()));
        sd.setServo2(Integer.parseInt(action.getVal2()));
        sd.setServo3(Integer.parseInt(action.getVal3()));
        sd.setServo4(Integer.parseInt(action.getVal4()));
        sd.setServo5(Integer.parseInt(action.getVal5()));
        publishServiceClient.publishSlider(sd);
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

}
