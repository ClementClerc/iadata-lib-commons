package fr.toulouse.iadata.datamodels.service;

import fr.toulouse.iadata.datamodels.utils.DataModelsConstants;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Data
@Accessors( prefix = {"_str","_"})
@Document( indexName = "iadata-conf-kafka-streams-applications")
@RestResource( rel = "applications" )
public class Application
{
    @Id
    private String id;
    @Field( name = "name")
    private String _strName;
    @Field( name = "direction")
    private String _strDirection;
    @Field( name = "entityType")
    private String _entityType;
    @Field( name = "oldKeyNames")
    private List<String> _oldKeyNames = new ArrayList<>();
    @Field( name = "newKeyNames")
    private List<String> _newKeyNames = new ArrayList<>();
    @Field( name="processors", type= FieldType.Nested )
    private List<Processor> _processors = new ArrayList<>();
    @Field( name="transformer", type= FieldType.Nested )
    private Transformer _transformer;
    @Field( name="dataType")
    private String _dataType;
    @Field( name="isModified", type= FieldType.Boolean)
    private Boolean _isModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getDataType( )
    {
        if ( _dataType == null )
        {
            return "data";
        }
        return _dataType;
    }
}
