package ie.atu.modugrip_backend.Services;

import ie.atu.modugrip_backend.Clients.PublishServiceClient;
import ie.atu.modugrip_backend.InverseKinematics.InverseKinematics;
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
        InverseKinematics ik = new InverseKinematics(30, 10, coordinates.getY(), coordinates.getZ());
        System.out.println("Y: " + coordinates.getY());
        System.out.println("Z: " + coordinates.getZ());
        calcAngles(ik);
    }

    public void calcAngles(InverseKinematics ik){
        System.out.println("IK object: " + ik);
        ik.negPosAngleCalc();
    }
}
