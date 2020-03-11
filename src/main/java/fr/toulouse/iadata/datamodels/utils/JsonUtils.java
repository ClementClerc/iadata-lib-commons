/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author cu33443
 */
public class JsonUtils
{

    private static ObjectMapper _objectMapper = new ObjectMapper( );

    /**
     * @param strJson
     * @return
     * @throws JsonProcessingException
     */
    public static JsonNode convertJsonStringToJson( String strJson ) throws JsonProcessingException
    {
        try
        {
            return _objectMapper.readTree( strJson.getBytes( "UTF-8" ) );
        }
        catch ( IOException ex )
        {
            Logger.getLogger( JsonUtils.class.getName( ) ).log( Level.SEVERE, null, ex );
            return null;
        }

    }

    /**
     * @param jsonNode
     * @return
     */
    public static Map< String, String > convertJsonToMap( JsonNode jsonNode )
    {
        return _objectMapper.convertValue( jsonNode, new TypeReference< Map< String, String > >( )
        {
        } );
    }

    /**
     * @param strJson
     * @return
     * @throws JsonProcessingException
     */
    public static Map< String, String > convertJsonStringToMap( String strJson ) throws JsonProcessingException
    {
        return convertJsonToMap( convertJsonStringToJson( strJson ) );
    }

    /**
     * @param mapJson
     * @return
     * @throws JsonProcessingException
     */
    public static String convertMapToJsonString( Map< String, String > mapJson ) throws JsonProcessingException
    {
        return _objectMapper.writeValueAsString( mapJson );
    }

    /**
     * @param jsonNode
     * @param mapJson
     * @return
     * @throws JsonProcessingException
     */
    public static String convertJsonToJsonString( JsonNode jsonNode ) throws JsonProcessingException
    {
        return _objectMapper.writeValueAsString( jsonNode );
    }

    /**
     * @param map
     * @return
     * @throws JsonProcessingException
     */
    public static JsonNode convertMapToJson( Map< String, String > map ) throws JsonProcessingException
    {
        return _objectMapper.valueToTree( map );
    }

    /**
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static JsonNode convertToJsonNode( Object object ) throws JsonProcessingException
    {
        return _objectMapper.valueToTree( object );
    }

    /**
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    // public static JsonNode accumulate( List<JsonNode> listJsonNode ) throws JsonProcessingException
    // {
    // ObjectNode childNode1 = _objectMapper.createObjectNode();
    // for ( JsonNode jsonNode : listJsonNode )
    // {
    // childNode1.
    // }
    // }
    // accumulate
}
