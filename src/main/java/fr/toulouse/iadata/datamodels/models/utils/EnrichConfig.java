package fr.toulouse.iadata.datamodels.models.utils;

import fr.toulouse.iadata.datamodels.models.management.Application;
import fr.toulouse.iadata.datamodels.models.management.LinkedPipeline;
import lombok.Data;

import java.util.List;

@Data
public class EnrichConfig
{
    private String applicationDirection;
    private String applicationName;
    private Application application;
    private List<LinkedPipeline> linkedPipelines;
    public String datasetEnrichName;

    public void fill( Application application )
    {
        this.application = application;
    }

    public String getTopicIn( )
    {
        StringBuilder stringBuilderTopicIn = new StringBuilder();
        stringBuilderTopicIn.append("enrich-")
                .append( getTopicSuffix() );
        return stringBuilderTopicIn.toString();
    }

    public String getTopicOut( )
    {
        StringBuilder stringBuilderTopicIn = new StringBuilder();
        stringBuilderTopicIn.append("iadata-enrich-")
                .append( getTopicSuffix() );
        return stringBuilderTopicIn.toString();
    }

    private String getTopicSuffix( )
    {
        StringBuilder stringBuilderTopicSuffix = new StringBuilder();
        stringBuilderTopicSuffix.append( getThematique( ) )
            .append( "-")
            .append( datasetEnrichName );

        return stringBuilderTopicSuffix.toString();
    }

    public String getApplicationId( )
    {
        StringBuilder stringBuilderTopicIn = new StringBuilder();
        stringBuilderTopicIn.append("iadata-stream-enrich-")
                .append( getTopicSuffix() );
        return stringBuilderTopicIn.toString();
    }

    public String getGroupId( )
    {
        StringBuilder stringBuilderTopicIn = new StringBuilder();
        stringBuilderTopicIn.append("iadata-group-enrich-")
                .append( getTopicSuffix() );
        return stringBuilderTopicIn.toString();
    }

    private String getThematique( )
    {
        return "thematique";
    }
}
