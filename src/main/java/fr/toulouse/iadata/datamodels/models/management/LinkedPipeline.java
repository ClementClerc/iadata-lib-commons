package fr.toulouse.iadata.datamodels.models.management;

import lombok.Data;

import java.util.Map;

@Data
public class LinkedPipeline
{
    private String appId;
    private String appName;
    private String groupAppName;
    private String entityType;
    private LinkedPipelineType linkedPipelineType;
    private String baseActivatedKey;
    private String linkedActivatedKey;
}
