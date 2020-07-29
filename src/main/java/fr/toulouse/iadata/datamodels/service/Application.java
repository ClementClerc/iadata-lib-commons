package fr.toulouse.iadata.datamodels.service;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;

@Data
@Document( indexName = "iadata-conf-kafka-streams-applications")
public class Application
{
    @Id
    private String id;
    private String name;
    private String direction;
    private String entityType;
    private List<String> oldKeyNames = new ArrayList<>();
    private List<String> newKeyNames = new ArrayList<>();
    @Field( type= FieldType.Nested )
    private List<Processor> processors = new ArrayList<>();
    @Field( type= FieldType.Nested )
    private Transformer transformer;
    private String dataType;
    @Field( type= FieldType.Boolean)
    private Boolean isModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getDataType( )
    {
        if ( dataType == null )
        {
            return "data";
        }
        return dataType;
    }
}
