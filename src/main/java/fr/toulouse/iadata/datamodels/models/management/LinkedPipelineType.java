package fr.toulouse.iadata.datamodels.models.management;

import java.util.Arrays;

public enum LinkedPipelineType
{
    GEO_NEAREST("geo_nearest"),
    MOST_RECENT("most_recent"),
    EXACT_MATCH("exact_match");

    private String key;

    private String getKey( )
    {
        return this.key;
    }

    LinkedPipelineType( String key)
    {
        this.key = key;
    }

    public static LinkedPipelineType findByKey(final String key){
        return Arrays.stream(values()).filter(value -> value.getKey().equals(key)).findFirst().orElse(null);
    }
}