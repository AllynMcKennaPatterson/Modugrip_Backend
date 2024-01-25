package ie.atu.modugrip_backend.Services;

import ie.atu.modugrip_backend.Clients.PublishServiceClient;
import ie.atu.modugrip_backend.Models.Coordinates;
import ie.atu.modugrip_backend.Models.SliderData;
import org.springframework.stereotype.Service;

@Service
public class CoordinateService {

    PublishServiceClient publishServiceClient;

    public CoordinateService(PublishServiceClient publishServiceClient){
        this.publishServiceClient = publishServiceClient;
    }

    public void publishCoordinateValues(Coordinates coordinates){
        System.out.println("Coordinate Values Service: "+ coordinates);
//        publishServiceClient.publishSlider(sliderData);

    }
}
