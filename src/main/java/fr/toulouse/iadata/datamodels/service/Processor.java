package fr.toulouse.iadata.kafkaprocessors.service;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Data
@Accessors( prefix = {"_str","_"})
public class Processor
{
    @Id
    private String _id;
    @Field( name = "key")
    String _strKey;
    @Field( name = "customArgs")
    List<String> _customArgs;
    @Field( name = "activatedKeys")
    List<String> _activatedKeys;
}
