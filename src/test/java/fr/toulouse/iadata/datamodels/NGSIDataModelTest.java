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
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author cu32980
 */
@RunWith( SpringRunner.class )
public class NGSIDataModelTest 
{
    @Test
    public void DeserializeNGSI1 ( ) throws JsonProcessingException, JSONException
    {
        String strNGSI = "{" +
            " \"id\": \"urn:ngsi-ld:OffStreetParking:Downtown1\"," +
            " \"type\": \"OffStreetParking\"," +
            " \"name\": {" +
            " \"type\": \"Property\"," +
            " \"value\": \"Downtown One\"" +
            " }," +
            " \"availableSpotNumber\": {" +
            " \"type\": \"Property\"," +
            " \"value\": 121," +
            " \"observedAt\": \"2017-07-29T12:05:02Z\"," +
            " \"reliability\": {" +
            " \"type\": \"Property\"," +
            " \"value\": 0.7" +
            " }," +
            " \"providedBy\": {" +
            " \"type\": \"Relationship\"," +
            " \"object\": \"urn:ngsi-ld:Camera:C1\"" +
            " }" +
            " }," +
            " \"totalSpotNumber\": {" +
            " \"type\": \"Property\"," +
            " \"value\": 200" +
            " }," +
            " \"location\": {" +
            " \"type\": \"GeoProperty\"," +
            " \"value\": {" +
            " \"type\": \"Point\"," +
            " \"coordinates\": [-8.5,4.0,4.0]" +
            " }" +
            " }," +
            " \"@context\": [" +
            " \"http://example.org/ngsi-ld/latest/parking.jsonld\"," +
            " \"https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\"" +
            " ]" +
            "} ";
        
        ObjectMapper _objectMapper = new ObjectMapper();
        _objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        Entity entity = _objectMapper.readValue(strNGSI, Entity.class);
        
        String strSerializedJson = _objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, true);
        
    }
    
    @Test
    public void DeserializeNGSI2 ( ) throws JsonProcessingException, JSONException
    {
        String strNGSI = "{\n" +
            "   \"id\":\"urn:ngsi-ld:Vehicle:A4567\",\n" +
            "   \"type\":\"Vehicle\",\n" +
            "   \"speed#1\":{\n" +
            "      \"type\":\"Property\",\n" +
            "      \"value\":55,\n" +
            "      \"source\":{\n" +
            "         \"type\":\"Property\",\n" +
            "         \"value\":\"Speedometer\"\n" +
            "      },\n" +
            "      \"datasetId\":\"urn:ngsi-ld:Property:speedometerA4567-speed\"\n" +
            "   },\n" +
            "   \"speed#2\":{\n" +
            "      \"type\":\"Property\",\n" +
            "      \"value\":54.5,\n" +
            "      \"source\":{\n" +
            "         \"type\":\"Property\",\n" +
            "         \"value\":\"GPS\"\n" +
            "      },\n" +
            "      \"datasetId\":\"urn:ngsi-ld:Property:gpsBxyz123-speed\"\n" +
            "   },\n" +
            "   \"@context\":[\n" +
            "      \"http://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\",\n" +
            "      {\n" +
            "         \"speed#1\":\"http://example.org/speed\",\n" +
            "         \"speed#2\":\"http://example.org/speed\",\n" +
            "         \"source\":\"http://example.org/hasSource\"\n" +
            "      }\n" +
            "   ]\n" +
            "}";
        
        ObjectMapper _objectMapper = new ObjectMapper();
        _objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        Entity entity = _objectMapper.readValue(strNGSI, Entity.class);
        
        String test = _objectMapper.writeValueAsString( entity.getContexts().get(1) );
        
        String strSerializedJson = _objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, true);
    }
    
    
    @Test
    public void DeserializeNGSI3 ( ) throws JsonProcessingException, JSONException
    {
        String strNGSI = "{\n" +
            " \"id\": \"urn:ngsi-ld:Vehicle:A4567\",\n" +
            " \"type\": \"Vehicle\",\n" +
            " \"brandName\": {\n" +
            " \"type\": \"Property\",\n" +
            " \"value\": \"Mercedes\"\n" +
            " },\n" +
            " \"isParked\": {\n" +
            " \"type\": \"Relationship\",\n" +
            " \"object\": \"urn:ngsi-ld:OffStreetParking:Downtown1\",\n" +
            " \"observedAt\": \"2017-07-29T12:00:04Z\",\n" +
            " \"providedBy\": {\n" +
            " \"type\": \"Relationship\",\n" +
            " \"object\": \"urn:ngsi-ld:Person:Bob\"\n" +
            " }\n" +
            " },\n" +
            " \"@context\": [\n" +
            " \"http://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/commonTerms.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/vehicle.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/parking.jsonld\"\n" +
            " ]\n" +
            "} ";
        
        ObjectMapper _objectMapper = new ObjectMapper();
        _objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        Entity entity = _objectMapper.readValue(strNGSI, Entity.class);
        
        String strSerializedJson = _objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, true);
    }
    
    @Test
    public void DeserializeNGSI4 ( ) throws JsonProcessingException, JSONException
    {
        String strNGSI = "{\n" +
            " \"id\": \"urn:ngsi-ld:Vehicle:A4567\",\n" +
            " \"type\": \"Vehicle\",\n" +
            " \"brandName\": \"Mercedes\",\n" +
            " \"isParked\": {\n" +
            " \"type\": \"Relationship\",\n" +
            " \"object\": \"urn:ngsi-ld:OffStreetParking:Downtown1\",\n" +
            " \"observedAt\": \"2017-07-29T12:00:04Z\",\n" +
            " \"providedBy\": {\n" +
            " \"type\": \"Relationship\",\n" +
            " \"object\": \"urn:ngsi-ld:Person:Bob\"\n" +
            " }\n" +
            " },\n" +
            " \"@context\": [\n" +
            " \"http://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/commonTerms.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/vehicle.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/parking.jsonld\"\n" +
            " ]\n" +
            "} ";
        
        ObjectMapper _objectMapper = new ObjectMapper();
        _objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        Entity entity = _objectMapper.readValue(strNGSI, Entity.class);
        
        String strSerializedJson = _objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, true);
    }
    
    @Test
    public void DeserializeSimplifiedNGSI ( ) throws JsonProcessingException, JSONException
    {
        String strNGSI = "{\n" +
            " \"id\": \"urn:ngsi-ld:Vehicle:A4567\",\n" +
            " \"type\": \"Vehicle\",\n" +
            " \"brandName\": \"Mercedes\",\n" +
            " \"isParked\": \"urn:ngsi-ld:OffStreetParking:Downtown1\",\n" +
            " \"@context\": [\n" +
            " \"http://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/commonTerms.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/vehicle.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/parking.jsonld\"\n" +
            " ]\n" +
            "} ";
        
        ObjectMapper _objectMapper = new ObjectMapper();
        _objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        Entity entity = _objectMapper.readValue(strNGSI, Entity.class);
        
        String strSerializedJson = _objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, true);
    }
    
    @Test
    public void DeserializeMultipleAttributeNGSI ( ) throws JsonProcessingException, JSONException
    {
        String strNGSI = "{\n" +
            " \"id\": \"urn:ngsi-ld:Vehicle:A4567\",\n" +
            " \"type\": \"Vehicle\",\n" +
            " \"speed#1\": {\n" +
            " \"type\": \"Property\",\n" +
            " \"value\": 55,\n" +
            " \"source\": {\n" +
            " \"type\": \"Property\",\n" +
            " \"value\": \"Speedometer\"\n" +
            " },\n" +
            " \"datasetId\": \"urn:ngsi-ld:Property:speedometerA4567-speed\" \n" +
            " },\n" +
            " \"speed#2\": {\n" +
            " \"type\": \"Property\",\n" +
            " \"value\": 54.5,\n" +
            " \"source\": {\n" +
            " \"type\": \"Property\",\n" +
            " \"value\": \"GPS\"\n" +
            " },\n" +
            " \"datasetId\": \"urn:ngsi-ld:Property:gpsBxyz123-speed\"\n" +
            " },\n" +
            " \"@context\": [\n" +
            " \"http://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\",\n" +
            " {\n" +
            " \"speed#1\": \"http://example.org/speed\",\n" +
            " \"speed#2\": \"http://example.org/speed\",\n" +
            " \"source\": \"http://example.org/hasSource\"\n" +
            " }\n" +
            " ]\n" +
            "} ";
        
        ObjectMapper _objectMapper = new ObjectMapper();
        _objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        Entity entity = _objectMapper.readValue(strNGSI, Entity.class);
        
        String strSerializedJson = _objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, true);
    }
}
