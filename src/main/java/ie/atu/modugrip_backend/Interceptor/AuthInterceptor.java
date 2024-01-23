package ie.atu.modugrip_backend.Interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component

public class AuthInterceptor implements RequestInterceptor {

    @Value("${my.apitoken}")
    private String apiToken;
    @Override
    public void apply(RequestTemplate requestTemplate){
        requestTemplate.header("X-Auth-Token", apiToken);
        requestTemplate.header("Content-Type", "application/json");
    }
}
