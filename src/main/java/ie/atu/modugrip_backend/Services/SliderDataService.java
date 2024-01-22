package ie.atu.modugrip_backend.Services;

import ie.atu.modugrip_backend.Clients.PublishServiceClient;
import ie.atu.modugrip_backend.Models.SliderData;
import org.springframework.stereotype.Service;

@Service
public class SliderDataService {

    PublishServiceClient publishServiceClient;

    public SliderDataService(PublishServiceClient publishServiceClient){
        this.publishServiceClient = publishServiceClient;
    }

    public void publishSliderData(SliderData sliderData){
        System.out.println("Slider Data Service: "+ sliderData);
        publishServiceClient.publishSlider(sliderData);
    }
}
