package fr.toulouse.iadata.datamodels.service;

import fr.toulouse.iadata.datamodels.utils.DataModelsConstants;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Accessors( prefix = {"_str","_"})
@Document( indexName = "kafka-streams-applications")
public class Application
{
    @Id
    private String _id;
    @Field( name = "name")
    private String _strName;
    @Field( name = "direction")
    private String _strDirection;
    @Field( name = "serdeClassName")
    private String _serdeClassName;
    @Field( name = "mapKeyNames")
    private Map<String,String> _mapKeyNames = new HashMap<>();
    @Field( name = "oldKeyNames")
    private List<String> _oldKeyNames = new ArrayList<>();
    @Field( name = "newKeyNames")
    private List<String> _newKeyNames = new ArrayList<>();;
    @Field( name="processors", type= FieldType.Nested )
    private List<Processor> _processors = new ArrayList<>();

}
