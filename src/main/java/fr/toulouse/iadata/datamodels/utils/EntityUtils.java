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
import fr.toulouse.iadata.datamodels.models.ngsi.EntityMember;
import fr.toulouse.iadata.datamodels.models.ngsi.Property;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author cu33443
 */
public class EntityUtils
{
    public Property propertyValueUnitBuilder(String strMemberName, Map<String,JsonNode> uplinkMessageMap ){
              
        if( uplinkMessageMap.get(strMemberName) != null && uplinkMessageMap.get(strMemberName).get("unit") != null && uplinkMessageMap.get(strMemberName).get("value") != null ){
            
        
        return Property.builder( ).name( strMemberName )
                        .addMember(Property.builder( ).name(strMemberName + "unit")
                        .value(uplinkMessageMap.get(strMemberName).get("unit").textValue()).build())
                        .addMember(Property.builder( ).name(strMemberName + "value")
                        .value(uplinkMessageMap.get(strMemberName).get("value").asDouble()).build())
                        .build();
        }
        return null;
    }
    
    


}
