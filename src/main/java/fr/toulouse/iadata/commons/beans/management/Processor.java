package fr.toulouse.iadata.commons.beans.management;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class Processor
{
    @Id
    private String id;
    String key;
    List<String> customArgs = new ArrayList<>() ;
    List<String> activatedKeys = new ArrayList<>();
    List<String> additionalCustomArgs = new ArrayList<>();
    int order;
}
