package ie.atu.modugrip_backend.Controllers;

import ie.atu.modugrip_backend.Models.Coordinates;
import ie.atu.modugrip_backend.Models.GripperData;
import ie.atu.modugrip_backend.Models.ToolStatus;
import ie.atu.modugrip_backend.Models.SliderData;
import ie.atu.modugrip_backend.Services.CoordinateService;
import ie.atu.modugrip_backend.Services.GripperDataService;
import ie.atu.modugrip_backend.Services.SliderDataService;
import ie.atu.modugrip_backend.Services.ToolStatusService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {
    SliderDataService sliderDataService;
    GripperDataService gripperDataService;
    ToolStatusService toolStatusService;
    CoordinateService coordinateService;

    public PublishController(SliderDataService sliderDataService, CoordinateService coordinateService, GripperDataService gripperDataService, ToolStatusService toolStatusService){
        this.sliderDataService = sliderDataService;
        this.coordinateService = coordinateService;
        this.gripperDataService = gripperDataService;
        this.toolStatusService = toolStatusService;
    }

    @PostMapping("/publish-slider")
    public SliderData publishSlider(@RequestBody SliderData sliderData) {
        System.out.println("Publishing Slider data: " + sliderData);
        sliderDataService.publishSliderData(sliderData);
        return sliderData;
    }

    @PostMapping("/publish-gripper")
    public GripperData publishGripper(@RequestBody GripperData gripperData) {
        System.out.println("Publishing Slider data: " + gripperData);
        gripperDataService.publishGripperData(gripperData);
        return gripperData;
    }

    @PostMapping("/publish-tool-status")
    public ToolStatus publishMotor(@RequestBody boolean status) {
        ToolStatus toolStatus = new ToolStatus();
        if(status){
            toolStatus.setToolstatus(1);
        } else if (!status) {
            toolStatus.setToolstatus(0);
        }
        System.out.println("Publishing Motor data: " + status);
        toolStatusService.publishToolStatus(toolStatus);
        return toolStatus;
    }

    @PostMapping("/publish-coordinates")
    public Coordinates publishCoordinates(@RequestBody Coordinates coordinates) {
        System.out.println("Publishing Coordinate data: " + coordinates);
        coordinateService.publishCoordinateValues(coordinates);
        return coordinates;
    }

}
