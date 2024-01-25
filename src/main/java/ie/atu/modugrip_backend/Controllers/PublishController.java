package ie.atu.modugrip_backend.Controllers;

import ie.atu.modugrip_backend.Models.Coordinates;
import ie.atu.modugrip_backend.Models.SliderData;
import ie.atu.modugrip_backend.Services.CoordinateService;
import ie.atu.modugrip_backend.Services.SliderDataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {
    SliderDataService sliderDataService;
    CoordinateService coordinateService;

    public PublishController(SliderDataService sliderDataService, CoordinateService coordinateService){
        this.sliderDataService = sliderDataService;
        this.coordinateService = coordinateService;
    }

    @PostMapping("/publish-slider")
    public SliderData publishSlider(@RequestBody SliderData sliderData) {
        System.out.println("Publishing Slider data: " + sliderData);
        sliderDataService.publishSliderData(sliderData);
        return sliderData;
    }

    @PostMapping("/publish-coordinates")
    public Coordinates publishCoordinates(@RequestBody Coordinates coordinates) {
        System.out.println("Publishing Coordinate data: " + coordinates);
        coordinateService.publishCoordinateValues(coordinates);
        return coordinates;
    }

}
