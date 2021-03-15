package fr.toulouse.iadata.datamodels.models.ngsi;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.toulouse.iadata.datamodels.service.EntityService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.ArrayList;

@SpringBootTest( classes = {EntityService.class})
public class RelationshipTest
{
    @Autowired
    private EntityService entityService;

    private ObjectMapper objectMapper = new ObjectMapper();

    public final static String NGSI_ENTITY_WITH_TWO_RELATIONSHIPS  = "{\n" +
                "      \"id\": \"urn:ngsi-ld:Device:0aeaed0a-77b7-49fb-ad2b-80b759b29848\",\n" +
                "      \"type\": \"Device\",\n" +
                "      \"refStreetlight\": {\n" +
                "        \"type\": \"Relationship\",\n" +
                "        \"object\": \"urn:ngsi-ld:Streetlight:4eb57872-e300-413e-93df-20c067fd0942\"\n" +
                "      },\n" +
                "      \"refCountingArea\": {\n" +
                "        \"type\": \"Relationship\",\n" +
                "        \"object\": [\n" +
                "          \"urn:ngsi-ld:CountingArea:8973f076-6a0b-4236-9764-97175469773f\",\n" +
                "          \"urn:ngsi-ld:CountingArea:18ed7f1b-a099-487a-baeb-f6be8daee57c\",\n" +
                "          \"urn:ngsi-ld:CountingArea:8b6ac472-e736-45b6-8b64-a7d443f240cb\"\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";

    public static final String RELATIONSHIP_1 = "{\n" +
                                    "        \"type\": \"Relationship\",\n" +
                                    "        \"object\": \"urn:ngsi-ld:Streetlight:4eb57872-e300-413e-93df-20c067fd0942\"\n" +
                                    "      }\n";

    public static final String RELATIONSHIP_2 = "{\n" +
            "        \"type\": \"Relationship\",\n" +
            "        \"object\": [\n" +
            "          \"urn:ngsi-ld:CountingArea:8973f076-6a0b-4236-9764-97175469773f\",\n" +
            "          \"urn:ngsi-ld:CountingArea:18ed7f1b-a099-487a-baeb-f6be8daee57c\",\n" +
            "          \"urn:ngsi-ld:CountingArea:8b6ac472-e736-45b6-8b64-a7d443f240cb\"\n" +
            "        ]\n" +
            "      }\n";

    @Test
    public void testDeserializeRelationship( ) throws Exception
    {
        Entity entity = entityService.convertJsonToEntity( NGSI_ENTITY_WITH_TWO_RELATIONSHIPS );
        Relationship simpleURIRelationship =  entityService.getRelationshipMemberByPath(  entity,"refStreetlight" );
        Relationship tripleURIRelationship =  entityService.getRelationshipMemberByPath(  entity,"refCountingArea" );
        Assertions.assertThat( simpleURIRelationship.getObject() instanceof URI ).isTrue();
        Assertions.assertThat( tripleURIRelationship.getObject() instanceof ArrayList ).isTrue();
        Assertions.assertThat( (ArrayList)(tripleURIRelationship.getObject())).size().isEqualTo( 3 );
    }

    @Test
    public void testBuildRelationship( ) throws Exception
    {
        Relationship relationship = Relationship.builder()
                .addURI( new URI( "urn:ngsi-ld:Streetlight:4eb57872-e300-413e-93df-20c067fd0942") )
                .name( "refStreetlight")
                .build();

        JSONAssert.assertEquals( objectMapper.writeValueAsString( relationship ), RELATIONSHIP_1, JSONCompareMode.STRICT );

        Relationship relationship2 = Relationship.builder()
                .addURI( new URI( "urn:ngsi-ld:CountingArea:8973f076-6a0b-4236-9764-97175469773f"))
                .addURI( new URI( "urn:ngsi-ld:CountingArea:18ed7f1b-a099-487a-baeb-f6be8daee57c"))
                .addURI( new URI( "urn:ngsi-ld:CountingArea:8b6ac472-e736-45b6-8b64-a7d443f240cb"))
                .name( "refStreetlight")
                .build();

        JSONAssert.assertEquals( objectMapper.writeValueAsString( relationship2 ), RELATIONSHIP_2, JSONCompareMode.STRICT );

    }
}
