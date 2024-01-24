package ie.atu.modugrip_backend.Controllers;

import ie.atu.modugrip_backend.Models.SliderData;
import ie.atu.modugrip_backend.Services.SliderDataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {
    SliderDataService sliderDataService;

    public PublishController(SliderDataService sliderDataService){
        this.sliderDataService = sliderDataService;
    }

    @PostMapping("/publish-slider")
    public SliderData publishSlider(@RequestBody SliderData sliderData) {
        System.out.println("Publishing Slider data: " + sliderData);
        sliderDataService.publishSliderData(sliderData);
        return sliderData;
    }

}
