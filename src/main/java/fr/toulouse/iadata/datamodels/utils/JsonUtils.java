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
import fr.toulouse.iadata.datamodels.models.ngsi.Property;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cu33443
 */
public class JsonUtils
{

    private static ObjectMapper objectMapper = new ObjectMapper( );

    /**
     * @param strJson
     * @return
     * @throws JsonProcessingException
     */
    public static JsonNode convertJsonStringToJson( String strJson ) throws JsonProcessingException
    {
        try
        {
            return objectMapper.readTree( strJson.getBytes( "UTF-8" ) );
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
        return objectMapper.convertValue( jsonNode, new TypeReference< Map< String, String > >( )
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
        return objectMapper.writeValueAsString( mapJson );
    }

    /**
     * @param jsonNode
     * @param mapJson
     * @return
     * @throws JsonProcessingException
     */
    public static String convertJsonToJsonString( JsonNode jsonNode ) throws JsonProcessingException
    {
        return objectMapper.writeValueAsString( jsonNode );
    }

    /**
     * @param map
     * @return
     * @throws JsonProcessingException
     */
    public static JsonNode convertMapToJson( Map< String, String > map ) throws JsonProcessingException
    {
        return objectMapper.valueToTree( map );
    }

    /**
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static JsonNode convertToJsonNode( Object object ) throws JsonProcessingException
    {
        return objectMapper.valueToTree( object );
    }
    
    
    
    public static JsonNode jsonNodeChecker (JsonNode jsonNode, String strKey, Pattern pattern) throws JsonProcessingException{
        
        String [] path = strKey.split("[.]");
        
        for (String strPath : path){
            Matcher matcher = pattern.matcher(strPath);
            if (matcher.matches()){
                if (matcher.group(2).equals("[") && matcher.group(4).equals("]")){
                    int integer = Integer.parseInt(matcher.group(3));
                    jsonNode=jsonNode.get(matcher.group(1)).get(integer);
                }

            }
            else if (jsonNode.get(strPath) == null){
                return null;
            }
            else{
                jsonNode=jsonNode.get(strPath);
            }
        }
        return jsonNode;
    }
     
    public static Property jsonNodeValueChecker(Map<String,JsonNode> uplinkMessageMap , String strPropertyMapKey){
                
        if (uplinkMessageMap.get(strPropertyMapKey) != null ){
            
            if (uplinkMessageMap.get(strPropertyMapKey).isDouble()){
                return Property.builder( ).name(strPropertyMapKey).value(uplinkMessageMap.get(strPropertyMapKey).asDouble()).build();
            }
            if (uplinkMessageMap.get(strPropertyMapKey).isTextual()){
                return Property.builder( ).name(strPropertyMapKey).value(uplinkMessageMap.get(strPropertyMapKey).textValue()).build();
            }
            if (uplinkMessageMap.get(strPropertyMapKey).isLong()){
                return Property.builder( ).name(strPropertyMapKey).value(uplinkMessageMap.get(strPropertyMapKey).asLong()).build();
            }
            if (uplinkMessageMap.get(strPropertyMapKey).isInt()){
                return Property.builder( ).name(strPropertyMapKey).value(uplinkMessageMap.get(strPropertyMapKey).asInt()).build();
            }
            if (uplinkMessageMap.get(strPropertyMapKey).isFloat()){
                return Property.builder( ).name(strPropertyMapKey).value(uplinkMessageMap.get(strPropertyMapKey).floatValue()).build();
            }
            
       }
        return null;
       
    }

    /**
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    // public static JsonNode accumulate( List<JsonNode> listJsonNode ) throws JsonProcessingException
    // {
    // ObjectNode childNode1 = objectMapper.createObjectNode();
    // for ( JsonNode jsonNode : listJsonNode )
    // {
    // childNode1.
    // }
    // }
    // accumulate
}
