package ie.atu.modugrip_backend.Clients;

import feign.Headers;
import ie.atu.modugrip_backend.Models.SliderData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "publish-service", url = "https://industrial.api.ubidots.com/api/v1.6/devices/robot/")
public interface PublishServiceClient {
    @PostMapping("")
    @Headers("X-Auth-Token: BBUS-R9TZ8sPj4ctPgpHE6HEOCIsvAwixSx")
    void publishSlider(@RequestBody SliderData sliderData);
}
