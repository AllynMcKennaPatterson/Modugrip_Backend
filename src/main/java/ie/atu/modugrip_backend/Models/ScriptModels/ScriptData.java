package ie.atu.modugrip_backend.Models.ScriptModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "scripts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScriptData {
    @Id
    private String id;

    private String name;
    private List<Object> action;


    // Getters and setters
    // Implement them accordingly
}
