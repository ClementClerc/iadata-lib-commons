package fr.toulouse.iadata.datamodels.service;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors( prefix = {"_str","_n","_"})
public class Processor
{
    @Id
    private String _id;
    @Field( name = "key")
    String _strKey;
    @Field( name = "customArgs")
    List<String> _customArgs = new ArrayList<>() ;
    @Field( name = "activatedKeys")
    List<String> _activatedKeys = new ArrayList<>();
    @Field( name = "order")
    int _nOrder;
}
