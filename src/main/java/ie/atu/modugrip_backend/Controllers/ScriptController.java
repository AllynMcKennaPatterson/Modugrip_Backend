package ie.atu.modugrip_backend.Controllers;

//import ie.atu.modugrip_backend.Interfaces.ActionScriptRepo;
import ie.atu.modugrip_backend.Models.ScriptModels.Action;
import ie.atu.modugrip_backend.Models.ScriptModels.Data;
import ie.atu.modugrip_backend.Models.ScriptModels.Script;
import ie.atu.modugrip_backend.Models.ScriptModels.ScriptString;
import ie.atu.modugrip_backend.Services.ScriptService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ScriptController {

    ScriptService scriptService;

    public ScriptController(ScriptService scriptService){
        this.scriptService = scriptService;
    }

    @PostMapping("/execute-script")
    public String executeScript(@RequestBody Script[] script) throws InterruptedException {
        for (Script s : script) {
            String actionType = s.getData().getActionType();
            int index = s.getData().getIndex();
            switch (actionType) {
                case "slider":
                    Action sliderAction = s.getData().getAction();
                    scriptService.processSliderAction(index, sliderAction);
                    break;
                case "delay":
                    int delay = s.getData().getDelay();
                    scriptService.processDelayAction(index, delay);
                    break;
                case "endEffector":
                    Action endEffectorAction = s.getData().getAction();
                    scriptService.processEndEffectorAction(index, endEffectorAction);
                    break;
                default:
                    // Handle unknown action type
                    break;
            }
        }
        return "success";
    }

    @PostMapping("/save-script")
    public void saveScript(@RequestBody String script) throws IOException {
        scriptService.saveScriptToMongo(script);
    }
}