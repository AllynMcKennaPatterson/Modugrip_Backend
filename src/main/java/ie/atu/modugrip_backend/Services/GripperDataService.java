package ie.atu.modugrip_backend.Services;

import ie.atu.modugrip_backend.Clients.PublishServiceClient;
import ie.atu.modugrip_backend.Models.GripperData;
import ie.atu.modugrip_backend.Models.SliderData;
import org.springframework.stereotype.Service;

@Service
public class GripperDataService {
    PublishServiceClient publishServiceClient;

    public GripperDataService(PublishServiceClient publishServiceClient){
        this.publishServiceClient = publishServiceClient;
    }

    public void publishGripperData(GripperData gripperData){
        System.out.println("GRipper Data Service: "+ gripperData);
        publishServiceClient.publishGripper(gripperData);
    }
}
