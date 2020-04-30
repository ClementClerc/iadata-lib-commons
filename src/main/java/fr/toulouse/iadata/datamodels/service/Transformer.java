package fr.toulouse.iadata.datamodels.service;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Data
@Accessors( prefix = {"_str","_"})
public class Transformer
{
    @Id
    private String _id;
    @Field( name = "key")
    String _strKey;
    @Field( name = "activatedKeys")
    List<String> _activatedKeys;
    @Field( name = "customArgs")
    List<String> _customArgs;
}
