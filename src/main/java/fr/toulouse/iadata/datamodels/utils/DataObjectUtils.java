/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.toulouse.iadata.datamodels.models.ngsi.Entity;
import fr.toulouse.iadata.datamodels.models.ngsi.Property;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author cu33443
 */
public class DataObjectUtils
{
    private static ObjectMapper _objectMapper = new ObjectMapper( );

    public static Entity convertJsonNodeToDataObject(JsonNode jsonNode )
    {
        Entity entity = new Entity( );
        List< Property > listProperties = new ArrayList<>( );

        Map< String, String > mapProperties = JsonUtils.convertJsonToMap( jsonNode );

        for ( Entry< String, String > entry : mapProperties.entrySet( ) )
        {
            Property property = new Property( );
            //property.setKey( entry.getKey( ) );
            property.setValue( entry.getValue( ) );
            listProperties.add( property );
        }
        //entity.setProperties( listProperties );

        return entity;
    }

    public static String convertDataObjectToJsonString( Entity entity) throws JsonProcessingException
    {
        return _objectMapper.writeValueAsString(entity);
    }


}
