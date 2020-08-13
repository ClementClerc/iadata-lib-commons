package fr.toulouse.iadata.datamodels.models.management;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@Document( indexName = "iadata-conf-property-metadata", createIndex = true)
public class PropertyMetadata
{
    @Id
    private String id;
    private String key;
    private String label;
    private Boolean categorical;
    private List<String> categories;
    private PropertyDataType propertyDataType;
}
