package fr.toulouse.iadata.datamodels.models.management;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.HashMap;
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
    private String category;
    private String entityType;
    private String updateDelay;
    private String libelle;
    private String infos;
    private String referent;
    private List<Key> listKeys = new ArrayList<>();
    @Field(type = FieldType.Nested)
    private List<Processor> processors = new ArrayList<>();
    @Field(type = FieldType.Nested)
    private Transformer transformer;
    private String dataType;
    @Field(type = FieldType.Boolean)
    private Boolean isModified;
    private Boolean isSensible = false;
    private String frequence;
    @Field(type = FieldType.Nested)
    private List<LinkedPipeline> listLinkedPipelines = new ArrayList<>();

    public String getDesignation() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(direction);
        stringBuilder.append("-");
        stringBuilder.append(name);
        return stringBuilder.toString();
    }

    public String getIndexName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("iadata-");
        stringBuilder.append(dataType);
        stringBuilder.append("-");
        stringBuilder.append(direction);
        stringBuilder.append("-");
        stringBuilder.append(name);
        return stringBuilder.toString();
    }

    public String getDatasetIndexName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("iadata-dataset-");
        stringBuilder.append(dataType);
        stringBuilder.append("-");
        stringBuilder.append(direction);
        stringBuilder.append("-");
        stringBuilder.append(name);
        return stringBuilder.toString();
    }

    public String getInIndexName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("data-");
        stringBuilder.append(direction);
        stringBuilder.append("-");
        stringBuilder.append(name);
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

    public String getDataType() {
        if (dataType == null) {
            return "data";
        }
        return dataType;
    }

    public List<String> getOldKeyNames() {
        return listKeys.stream().map(key -> key.getOldKey()).collect(Collectors.toList());
    }


    public List<String> getNewKeyNames() {
        return listKeys.stream().map(key -> key.getNewKey()).collect(Collectors.toList());
    }

    public Map<String,String > getMapOldNewKeys( )
    {
        List<String> oldKeyNames = getOldKeyNames();
        List<String> newKeyNames = getNewKeyNames();
        if (getOldKeyNames() != null &&  getNewKeyNames() != null){
            return IntStream.range(0, listKeys.size()).boxed().collect(Collectors.toMap(i -> oldKeyNames.get(i), i -> newKeyNames.get(i)));
        }
        return new HashMap<>();
    }
}
