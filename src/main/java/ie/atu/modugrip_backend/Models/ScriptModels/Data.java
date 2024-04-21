package ie.atu.modugrip_backend.Models.ScriptModels;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
    private String actionType;
    private int index;
    private Action action;
    private Integer delay;

    private boolean status;

    private int width;

    // Getters and setters
    @JsonProperty("actionType")
    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @JsonProperty("index")
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @JsonProperty("action")
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @JsonProperty("delay")
    public int getDelay() {
        return delay;
    }

    @JsonProperty("width")
    public int getWidth(){ return width; }

    @JsonProperty("status")
    public boolean getStatus(){return status;}

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
