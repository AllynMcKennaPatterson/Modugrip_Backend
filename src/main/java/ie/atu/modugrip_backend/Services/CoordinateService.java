package ie.atu.modugrip_backend.Services;

import ie.atu.modugrip_backend.Clients.PublishServiceClient;
import ie.atu.modugrip_backend.InverseKinematics.InverseKinematics;
import ie.atu.modugrip_backend.Models.Coordinates;
import ie.atu.modugrip_backend.Models.CoordsAngles;
import ie.atu.modugrip_backend.Models.SliderData;
import org.springframework.stereotype.Service;

import static java.lang.Math.sqrt;

@Service
public class CoordinateService {

    PublishServiceClient publishServiceClient;
    SliderDataService sliderDataService;

    public CoordinateService(PublishServiceClient publishServiceClient, SliderDataService sliderDataService){
        this.publishServiceClient = publishServiceClient;
        this.sliderDataService = sliderDataService;
    }

    public void publishCoordinateValues(Coordinates coordinates){
        InverseKinematics ik = new InverseKinematics(26, 25, coordinates.getX()+5, coordinates.getY() - 15,coordinates.getZ()+5, 0);
        int baseAngle = ik.calcBaseAngle();
        double hypotenuse = sqrt(((coordinates.getZ() * coordinates.getZ()) + (coordinates.getX() * coordinates.getX())));
        ik.setHyp(hypotenuse);
        CoordsAngles ca = calcAngles(ik);
        ca.setServo5(baseAngle);
        if(ca.getServo1() != 0 && ca.getServo2() != 0){
            publishServiceClient.publishCoords(ca);
        }
    }

    public CoordsAngles calcAngles(InverseKinematics ik){
        System.out.println("IK object: " + ik);
        return ik.negPosAngleCalc();
    }
}
