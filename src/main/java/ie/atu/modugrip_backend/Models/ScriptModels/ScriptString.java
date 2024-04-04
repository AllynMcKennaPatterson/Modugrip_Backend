package ie.atu.modugrip_backend.Models.ScriptModels;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "scripts")
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class ScriptString {
    @Id
    private String id;
//    private String jsonString;
    private String name;
    private List<Data> action;

    public void setAction(List<Data> action) {
        this.action = action;
    }
}
