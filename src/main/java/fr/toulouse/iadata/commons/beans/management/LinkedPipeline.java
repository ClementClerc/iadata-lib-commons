package fr.toulouse.iadata.commons.beans.management;

import lombok.Data;

import java.util.List;

@Data
public class LinkedPipeline
{
    private Application application;
    private String appId;
    private String appName;
    private String groupAppName;
    private String entityType;
    private LinkedPipelineType linkedPipelineType;
    private String baseActivatedKey;
    private String linkedActivatedKey;
    private List<String> customArgs;

    public String getAppName( )
    {
        if ( application != null )
        { return application.getName( ); }
        return appName;
    }

    public String getGroupAppName( )
    {
        if ( application != null )
        { return application.getDirection(); }
        return groupAppName;
    }

    public String getEntityType( )
    {
        if ( application != null )
        { return application.getEntityType(); }
        return entityType;
    }


}
