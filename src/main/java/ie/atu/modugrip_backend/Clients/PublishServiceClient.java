package ie.atu.modugrip_backend.Clients;

import feign.Headers;
import ie.atu.modugrip_backend.Interceptor.FeignConfig;
import ie.atu.modugrip_backend.Models.SliderData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "publish-service", url = "https://industrial.api.ubidots.com/api/v1.6/devices/robot/", configuration = {FeignConfig.class})
public interface PublishServiceClient {
    @PostMapping("")
    void publishSlider(@RequestBody SliderData sliderData);
}
