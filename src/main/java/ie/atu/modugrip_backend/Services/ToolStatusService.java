package ie.atu.modugrip_backend.Services;

import ie.atu.modugrip_backend.Clients.PublishServiceClient;
import ie.atu.modugrip_backend.Models.ToolStatus;
import org.springframework.stereotype.Service;

@Service
public class ToolStatusService {

    PublishServiceClient publishServiceClient;

    public ToolStatusService(PublishServiceClient publishServiceClient){
        this.publishServiceClient = publishServiceClient;
    }

    public void publishToolStatus(ToolStatus toolStatus){
        System.out.println("Tool Data Service: "+ toolStatus.getToolstatus());

        publishServiceClient.publishToolStatus(toolStatus);
    }
}
