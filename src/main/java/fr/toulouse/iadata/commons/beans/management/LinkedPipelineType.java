package fr.toulouse.iadata.commons.beans.management;

import java.util.Arrays;

public enum LinkedPipelineType
{
    GEO_NEAREST("geo_nearest"),
    MOST_RECENT("most_recent"),
    EXACT_MATCH("exact_match"),
    INSIDE_GEOSHAPE( "inside_geoshape"),
    DATE_MATCHER("date_matcher");
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