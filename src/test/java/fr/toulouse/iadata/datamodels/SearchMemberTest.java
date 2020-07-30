package fr.toulouse.iadata.datamodels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.toulouse.iadata.datamodels.exceptions.UnrecognizedEntityMemberException;
import fr.toulouse.iadata.datamodels.models.ngsi.*;
import fr.toulouse.iadata.datamodels.service.EntityService;
import org.geojson.Point;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith( SpringRunner.class )
public class SearchMemberTest
{
    private static ObjectMapper objectMapper;
//    @Autowired
//    private static EntityService entityService;

    @BeforeClass
    public static void setUp()
    {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void searchMemberTest( ) throws URISyntaxException, JSONException, JsonProcessingException
    {


        Entity entity = Entity.builder()
                .id("urn:ngsi-ld:OffStreetParking:Downtown1")
                .addSimpleProperty( "type", "OffStreetParking")
                .addMember(Property.builder()
                        .name("a")
                        .value(0)
                        .build())
                .addMember(Property.builder()
                        .name("b")
                        .value(0)
                        .addMember( Property.builder()
                                .name("ba")
                                .value(0)
                                .addMember(Property.builder()
                                        .name("baa")
                                        .value(0)
                                        .build())
                                .addMember(Property.builder()
                                        .name("bab")
                                        .value(0)
                                        .build())
                                .build( ) )
                        .addMember(Property.builder()
                                .name("bb")
                                .value(0)
                                .build())
                        .build())
                .addMember(Property.builder()
                        .name("c")
                        .value(0)
                        .addMember(Property.builder()
                                .name("ca")
                                .value(0)
                                .build())
                        .build())

                .build();

        List<String> memberNamesList= new ArrayList<>();
        memberNamesList.add("a");
        memberNamesList.add("b.ba.baa");
        memberNamesList.add("b.ba.bab");
        memberNamesList.add("b.bb");
        memberNamesList.add("c.ca");




        List<String> EntityStructure = (new EntityService()).getPaths(entity);


        Assert.assertNotEquals(EntityStructure,memberNamesList);
//        String strSerializedJson = _objectMapper.writeValueAsString(entity);
//        JSONAssert.assertEquals(strNGSI, strSerializedJson, JSONCompareMode.LENIENT);
    }

//
}