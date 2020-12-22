package fr.toulouse.iadata.datamodels.models.management;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.toulouse.iadata.datamodels.models.ngsi.Context;
import fr.toulouse.iadata.datamodels.models.serde.ContextDeserializer;
import fr.toulouse.iadata.datamodels.models.serde.ContextSerializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
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
    private List<String> linkedPipelines = new ArrayList<>();
    @JsonIgnore
    private List<Object> _links = new ArrayList<Object>();

    public void addLinkedPipelines( String pipelineName )
    {
        linkedPipelines.add( pipelineName );
    }


}
