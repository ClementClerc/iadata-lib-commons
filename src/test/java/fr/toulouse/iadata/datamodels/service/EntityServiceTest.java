package fr.toulouse.iadata.datamodels.service;

import fr.toulouse.iadata.datamodels.exceptions.SerdeException;
import fr.toulouse.iadata.datamodels.exceptions.UnrecognizedEntityMemberException;
import fr.toulouse.iadata.datamodels.models.ngsi.*;
import fr.toulouse.iadata.datamodels.utils.DataModelsConstants;
import fr.toulouse.iadata.datamodels.utils.DataModelsTestConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest( classes = {EntityService.class})
public class EntityServiceTest
{
    @Autowired
    private EntityService entityService;

    @Test
    public void testCopyEntityMemberFromExisting( ) throws Exception
    {
        Entity entity = entityService.convertJsonToEntity( DataModelsConstants.NGSI_PAYLOAD_SPEC_1 );

        //testOK
        entityService.copyEntityMemberFromExisting( entity, "totalSpotNumber", "totalSpotNumber2");
        JSONAssert.assertEquals( entityService.convertEntityToJsonString( entity ), DataModelsTestConstants.NGSI_PAYLOAD_SPEC_1_COPIED_TOTAL_SPOT_NUMBER , JSONCompareMode.STRICT);

        //test wrong path
        Assertions.assertThrows(
                UnrecognizedEntityMemberException.class,
                () -> {entityService.copyEntityMemberFromExisting( entity, "totalSpotNumberFalse", "totalSpotNumber2");;});
    }

    @Test
    public void testReplaceEntityMemberName( ) throws Exception
    {
        Entity entity = entityService.convertJsonToEntity( DataModelsConstants.NGSI_PAYLOAD_SPEC_1 );

        //testOK
        entityService.replaceEntityMemberName( entity, "totalSpotNumber", "totalSpotNumber2");
        JSONAssert.assertEquals( entityService.convertEntityToJsonString( entity ), DataModelsTestConstants.NGSI_PAYLOAD_SPEC_1_REPLACED_TOTAL_SPOT_NUMBER , JSONCompareMode.STRICT);

        //test wrong path
        Assertions.assertThrows(
                UnrecognizedEntityMemberException.class,
                () -> {entityService.replaceEntityMemberName( entity, "totalSpotNumberFalse", "totalSpotNumber3");});

        Entity entity2 = entityService.convertJsonToEntity( DataModelsConstants.NGSI_PAYLOAD_SPEC_1 );

        //testOK
        entityService.replaceEntityMemberName( entity2, "availableSpotNumber.providedBy", "availableSpotNumber.providedBy2");
        JSONAssert.assertEquals( entityService.convertEntityToJsonString( entity2 ), DataModelsTestConstants.NGSI_PAYLOAD_SPEC_1_REPLACED_PROVIDED_BY , JSONCompareMode.STRICT);

        Entity entity3 = entityService.convertJsonToEntity( DataModelsConstants.NGSI_PAYLOAD_SPEC_1 );

        //testOK
        entityService.replaceEntityMemberName( entity3, "availableSpotNumber.providedBy", "newRoot.providedBy");
        JSONAssert.assertEquals( entityService.convertEntityToJsonString( entity3 ), DataModelsTestConstants.NGSI_PAYLOAD_SPEC_1_REPLACED_NEW_ROOT_PROVIDED_BY , JSONCompareMode.STRICT);



    }


    @Test
    public void testGetPropertyMemberByPath( ) throws Exception
    {
        Entity entity = entityService.convertJsonToEntity( DataModelsConstants.NGSI_PAYLOAD_SPEC_1 );

        //test OK
        AbstractProperty property = entityService.getPropertyMemberByPath( entity, "totalSpotNumber");
        Assertions.assertTrue( property instanceof Property);

        //test wrong path
        Assertions.assertThrows(
                UnrecognizedEntityMemberException.class,
                () -> {entityService.getPropertyMemberByPath( entity, "totalSpotNumberFalse");});
    }

    @Test
    public void testGetRelationshipMemberByPath( ) throws Exception
    {
        Entity entity = entityService.convertJsonToEntity( DataModelsConstants.NGSI_PAYLOAD_SPEC_1 );

        //test OK
        EntityMember relationship = entityService.getRelationshipMemberByPath( entity, "availableSpotNumber.providedBy");
        Assertions.assertTrue( relationship instanceof Relationship );

        //test wrong path
        Assertions.assertThrows(
                UnrecognizedEntityMemberException.class,
                () -> {entityService.getRelationshipMemberByPath( entity, "availableSpotNumber.providedByFalse");});
    }

    @Test
    public void testGetMemberByPath( ) throws Exception
    {
        Entity entity = entityService.convertJsonToEntity( DataModelsConstants.NGSI_PAYLOAD_SPEC_1 );

        //test OK
        EntityMember relationship = entityService.getEntityMemberByPath( entity, "availableSpotNumber.providedBy");
        Assertions.assertTrue( relationship instanceof Relationship );

        EntityMember property = entityService.getEntityMemberByPath( entity, "totalSpotNumber");
        Assertions.assertTrue( property instanceof Property );

        //test wrong path
        Assertions.assertThrows(
                UnrecognizedEntityMemberException.class,
                () -> {entityService.getEntityMemberByPath( entity, "totalSpotNumberFalse");});

        Assertions.assertThrows(
                UnrecognizedEntityMemberException.class,
                () -> {entityService.getEntityMemberByPath( entity, "availableSpotNumber.providedByFalse");});
    }

    @Test
    public void testGetPaths( ) throws Exception
    {
        Entity entity = entityService.convertJsonToEntity( DataModelsConstants.NGSI_PAYLOAD_SPEC_1 );

        List<String> paths = entityService.getPathLeaves( entity );

        List<String> expectedPaths = new ArrayList<>( );
        expectedPaths.add( "availableSpotNumber.providedBy");
        expectedPaths.add( "availableSpotNumber.reliability.trueReliability");
        expectedPaths.add( "name");
        expectedPaths.add( "totalSpotNumber");

        Assertions.assertLinesMatch( expectedPaths, paths );
    }

    @Test
    public void testEntityErrorLogAdder( ) throws Exception
    {
        Entity entity = entityService.convertJsonToEntity( DataModelsConstants.NGSI_PAYLOAD_SPEC_1 );

        entityService.entityLogErrorAdder( new SerdeException( "ERROR"), entity);

        JSONAssert.assertEquals( entityService.convertEntityToJsonString( entity ), DataModelsTestConstants.NGSI_PAYLOAD_SPEC_1_ERROR_1, JSONCompareMode.STRICT);

        entityService.entityLogErrorAdder( new SerdeException( "ERROR2"), entity);

        JSONAssert.assertEquals( entityService.convertEntityToJsonString( entity ), DataModelsTestConstants.NGSI_PAYLOAD_SPEC_1_ERROR_2, JSONCompareMode.STRICT);

    }

}
