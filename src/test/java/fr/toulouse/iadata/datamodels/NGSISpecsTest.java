/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.toulouse.iadata.datamodels.models.ngsi.Entity;
import fr.toulouse.iadata.datamodels.utils.DataModelsConstants;
import org.json.JSONException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author cu32980
 */
@RunWith( SpringRunner.class )
public class NGSISpecsTest
{
    private static ObjectMapper objectMapper;

    @BeforeClass
    public static void setUp()
    {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    @Test
    public void DeserializeNGSI1 ( ) throws JsonProcessingException, JSONException
    {
        String strNGSI = DataModelsConstants.NGSI_PAYLOAD_SPEC_1;
        Entity entity = objectMapper.readValue(strNGSI, Entity.class);
        String strSerializedJson = objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, true);
    }
    
    @Test
    public void DeserializeNGSI2 ( ) throws JsonProcessingException, JSONException
    {
        String strNGSI = DataModelsConstants.NGSI_PAYLOAD_SPEC_2;
        Entity entity = objectMapper.readValue(strNGSI, Entity.class);
        String strSerializedJson = objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, true);
    }
    
    
    @Test
    public void DeserializeNGSI3 ( ) throws JsonProcessingException, JSONException
    {
        String strNGSI = DataModelsConstants.NGSI_PAYLOAD_SPEC_3;
        Entity entity = objectMapper.readValue(strNGSI, Entity.class);
        
        String strSerializedJson = objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, true);
    }
    
    @Test
    public void DeserializeNGSI4 ( ) throws JsonProcessingException, JSONException
    {
        String strNGSI = DataModelsConstants.NGSI_PAYLOAD_SPEC_4;
        ObjectMapper _objectMapper = new ObjectMapper();
        _objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        Entity entity = _objectMapper.readValue(strNGSI, Entity.class);
        
        String strSerializedJson = _objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, true);
    }
    
    @Test
    public void DeserializeSimplifiedNGSI ( ) throws JsonProcessingException, JSONException
    {
        String strNGSI = DataModelsConstants.NGSI_PAYLOAD_SPEC_5;
        Entity entity = objectMapper.readValue(strNGSI, Entity.class);
        
        String strSerializedJson = objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, true);
    }
    
    @Test
    public void DeserializeMultipleAttributeNGSI ( ) throws JsonProcessingException, JSONException
    {
        String strNGSI = DataModelsConstants.NGSI_PAYLOAD_SPEC_6;
        Entity entity = objectMapper.readValue(strNGSI, Entity.class);
        
        String strSerializedJson = objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, true);
    }
}
