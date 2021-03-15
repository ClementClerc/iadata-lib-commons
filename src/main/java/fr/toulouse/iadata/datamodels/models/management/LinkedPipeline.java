package fr.toulouse.iadata.datamodels.models.management;

import lombok.Data;

import java.util.Map;

@Data
public class LinkedPipeline
{
    private String appName;
    private String groupAppName;
    private LinkedPipelineType linkedPipelineType;
    private Map<Object,Object> linkedParams;
}
