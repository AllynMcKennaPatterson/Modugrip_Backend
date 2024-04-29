package ie.atu.modugrip_backend.Clients;

import ie.atu.modugrip_backend.Interceptor.FeignConfig;
import ie.atu.modugrip_backend.Models.CoordsAngles;
import ie.atu.modugrip_backend.Models.GripperData;
import ie.atu.modugrip_backend.Models.SliderData;
import ie.atu.modugrip_backend.Models.ToolStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "publish-service", url = "https://industrial.api.ubidots.com/api/v1.6/devices/robot/", configuration = {FeignConfig.class})
public interface PublishServiceClient {
    @PostMapping("")
    void publishSlider(@RequestBody SliderData sliderData);

    @PostMapping("")
    void publishGripper(@RequestBody GripperData gripperData);

    @PostMapping("")
    void publishToolStatus(@RequestBody ToolStatus toolStatus);

    @PostMapping("")
    void publishCoords(@RequestBody CoordsAngles coordsAngles);
}
