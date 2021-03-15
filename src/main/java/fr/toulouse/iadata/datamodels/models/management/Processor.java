package fr.toulouse.iadata.datamodels.models.management;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class Processor
{
    @Id
    private String id;
    String key;
    List<String> customArgs = new ArrayList<>() ;
    List<String> activatedKeys = new ArrayList<>();
    int order;
}
