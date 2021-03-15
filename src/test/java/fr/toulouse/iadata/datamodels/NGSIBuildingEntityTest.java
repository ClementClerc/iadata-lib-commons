package fr.toulouse.iadata.datamodels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.toulouse.iadata.datamodels.models.ngsi.*;
import org.geojson.Point;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.net.URI;
import java.net.URISyntaxException;

public class NGSIBuildingEntityTest
{
    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void setUp()
    {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void buildNGSIEntityTest( ) throws URISyntaxException, JSONException, JsonProcessingException
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
                " \"coordinates\": [-8.5,4.0]" +
                " }" +
                " }," +
                " \"@context\": [" +
                " \"http://example.org/ngsi-ld/latest/parking.jsonld\"," +
                " \"https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\"" +
                " ]" +
                "} ";

        Entity entity = Entity.builder()
                .id( new URI( "urn:ngsi-ld:OffStreetParking:Downtown1"))
                .addSimpleProperty( "type", "OffStreetParking")
                .addMember(Property.builder()
                        .name("name")
                        .value("Downtown One")
                        .build())
                .addMember(Property.builder()
                        .name("availableSpotNumber")
                        .value(121)
                        .observedAt("2017-07-29T12:05:02Z")
                        .addMember( Property.builder()
                                .name("reliability")
                                .value(0.7)
                                .build( ) )
                        .addMember(Relationship.builder()
                                .name("providedBy")
                                .object("urn:ngsi-ld:Camera:C1")
                                .build())
                        .build())
                .addMember(Property.builder()
                        .name("totalSpotNumber")
                        .value(200)
                        .build())
                .location(GeoProperty.builder()
                        .value(new Point(-8.5, 4.0))
                        .build())
                .addContext(Context.builder()
                        .uri("http://example.org/ngsi-ld/latest/parking.jsonld")
                        .build())
                .addContext(Context.builder()
                        .uri("https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld")
                        .build())
                .build();

        String strSerializedJson = objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, JSONCompareMode.LENIENT);
    }

    @Test
    public void buildNGSIEntity2Test( ) throws URISyntaxException, JSONException, JsonProcessingException
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
                " \"coordinates\": [-8.5,4.0]" +
                " }" +
                " }," +
                " \"@context\": [\n" +
                " \"http://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\",\n" +
                " {\n" +
                " \"speed#1\": \"http://example.org/speed\",\n" +
                " \"speed#2\": \"http://example.org/speed\",\n" +
                " \"source\": \"http://example.org/hasSource\"\n" +
                " }\n" +
                " ]\n" +
                "} ";

        Entity entity = Entity.builder()
                .id( new URI( "urn:ngsi-ld:OffStreetParking:Downtown1"))
                .addSimpleProperty( "type", "OffStreetParking")
                .addMember(Property.builder()
                        .name("name")
                        .value("Downtown One")
                        .build())
                .addMember(Property.builder()
                        .name("availableSpotNumber")
                        .value(121)
                        .observedAt("2017-07-29T12:05:02Z")
                        .addMember( Property.builder()
                                .name("reliability")
                                .value(0.7)
                                .build( ) )
                        .addMember(Relationship.builder()
                                .name("providedBy")
                                .object("urn:ngsi-ld:Camera:C1")
                                .build())
                        .build())
                .addMember(Property.builder()
                        .name("totalSpotNumber")
                        .value(200)
                        .build())
                .location(GeoProperty.builder()
                        .value(new Point(-8.5, 4.0))
                        .build())
                .addContext(Context.builder()
                        .uri("http://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld")
                        .build())
                .addContext(Context.builder()
                        .addFieldURI("speed#1", "http://example.org/speed" )
                        .addFieldURI("speed#2", "http://example.org/speed" )
                        .addFieldURI("source", "http://example.org/hasSource" )
                        .build())
                .build();

        String strSerializedJson = objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, JSONCompareMode.LENIENT);
    }

    @Test
    public void buildNGSIEntity3Test( ) throws URISyntaxException, JSONException, JsonProcessingException
    {
        String strNGSI = "{" +
                " \"id\": \"urn:ngsi-ld:OffStreetParking:Downtown1\"," +
                " \"type\": \"OffStreetParking\"," +
                " \"name\": \"Downtown One\"," +
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
                " \"coordinates\": [-8.5,4.0]" +
                " }" +
                " }," +
                " \"@context\": [\n" +
                " \"http://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\",\n" +
                " {\n" +
                " \"speed#1\": \"http://example.org/speed\",\n" +
                " \"speed#2\": \"http://example.org/speed\",\n" +
                " \"source\": \"http://example.org/hasSource\"\n" +
                " }\n" +
                " ]\n" +
                "} ";

        Entity entity = Entity.builder()
                .id(new URI("urn:ngsi-ld:OffStreetParking:Downtown1"))
                .addSimpleProperty( "type", "OffStreetParking")
                .addSimpleProperty( "name", "Downtown One")
                .addMember(Property.builder()
                        .name("availableSpotNumber")
                        .value(121)
                        .observedAt("2017-07-29T12:05:02Z")
                        .addMember( Property.builder()
                                .name("reliability")
                                .value(0.7)
                                .build( ) )
                        .addMember(Relationship.builder()
                                .name("providedBy")
                                .object("urn:ngsi-ld:Camera:C1")
                                .build())
                        .build())
                .addMember(Property.builder()
                        .name("totalSpotNumber")
                        .value(200)
                        .build())
                .location(GeoProperty.builder()
                        .value(new Point(-8.5, 4.0))
                        .build())
                .addContext(Context.builder()
                        .uri("http://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld")
                        .build())
                .addContext(Context.builder()
                        .addFieldURI("speed#1", "http://example.org/speed" )
                        .addFieldURI("speed#2", "http://example.org/speed" )
                        .addFieldURI("source", "http://example.org/hasSource" )
                        .build())
                .build();
        String strSerializedJson = objectMapper.writeValueAsString(entity);
        JSONAssert.assertEquals(strNGSI, strSerializedJson, JSONCompareMode.LENIENT);
    }
}
