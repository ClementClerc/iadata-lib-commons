package fr.toulouse.iadata.datamodels.models.management;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Transformer
{
    @Id
    private String id;
    String key;
    List<String> activatedKeys;
    List<String> customArgs;
    List<String> uuidSourceKeys;
}
