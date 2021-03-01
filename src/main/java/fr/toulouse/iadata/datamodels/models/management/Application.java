package fr.toulouse.iadata.datamodels.models.management;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Document( indexName = "iadata-conf-kafka-streams-applications")
public class Application
{
    @Id
    private String id;
    private String name;
    private String direction;
    private String entityType;
    private String updateDelay;
    private String libelle;
    private String referent;
    private List<String> oldKeyNames = new ArrayList<>();
    private List<String> newKeyNames = new ArrayList<>();
    @Field( type= FieldType.Nested )
    private List<Processor> processors = new ArrayList<>();
    @Field( type= FieldType.Nested )
    private Transformer transformer;
    private String dataType;
    @Field( type= FieldType.Boolean)
    private Boolean isModified;
    private String frequence;

    public String getIndexName( )
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "iadata-");
        stringBuilder.append( dataType );
        stringBuilder.append( "-");
        stringBuilder.append( direction );
        stringBuilder.append( "-");
        stringBuilder.append( name );
        return stringBuilder.toString();
    }

    public String getInIndexName( )
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "data-");
        stringBuilder.append( direction );
        stringBuilder.append( "-");
        stringBuilder.append( name );
        return stringBuilder.toString();
    }
    
    public String getDLQIndexName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("dlq-iadata-");
        stringBuilder.append(dataType);
        stringBuilder.append("-");
        stringBuilder.append(direction);
        stringBuilder.append("-");
        stringBuilder.append(name);
        return stringBuilder.toString();
    }

    public String getDataType( )
    {
        if ( dataType == null )
        {
            return "data";
        }
        return dataType;
    }

    public Map<String,String > getMapOldNewKeys( )
    {
        return IntStream.range( 0, oldKeyNames.size() ).boxed().collect( Collectors.toMap( i -> oldKeyNames.get( i ), i -> newKeyNames.get( i ) ));
    }


}
