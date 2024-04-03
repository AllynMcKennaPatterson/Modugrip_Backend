package ie.atu.modugrip_backend.Interfaces;

import ie.atu.modugrip_backend.Models.ScriptModels.Data;
import ie.atu.modugrip_backend.Models.ScriptModels.Script;
import ie.atu.modugrip_backend.Models.ScriptModels.ScriptString;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ActionScriptRepo extends MongoRepository<ScriptString, String> {
}
