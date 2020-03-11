/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.toulouse.iadata.datamodels.model.DataObject;
import fr.toulouse.iadata.datamodels.model.DataObjectGeoJson;
import fr.toulouse.iadata.datamodels.model.Property;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.FeatureConverter;

/**
 * @author cu33443
 */
public class DataObjectUtils
{
    private static ObjectMapper _objectMapper = new ObjectMapper( );

    public static DataObjectGeoJson convertGeoJsonToDataObject( String strGeoJson ) throws JsonProcessingException
    {
        DataObjectGeoJson dataObject = new DataObjectGeoJson( );
        List< Property > listProperties = new ArrayList<>( );

        // FeatureCollection featureCollection = FeatureConverter.toFeatureCollection( strGeoJson );
        Feature feature = FeatureConverter.toFeature( strGeoJson );

        for ( Entry< String, Object > prop : feature.getProperties( ).entrySet( ) )
        {
            Property property = new Property( );
          //  property.setKey( prop.getKey( ) );
            property.setValue( prop.getValue( ).toString( ) );
            listProperties.add( property );
        }
        //dataObject.setProperties( listProperties );
        dataObject.setGeometry( feature.getGeometry( ) );

        return dataObject;
    }

    public static DataObject convertJsonNodeToDataObject( JsonNode jsonNode ) throws JsonProcessingException
    {
        DataObject dataObject = new DataObject( );
        List< Property > listProperties = new ArrayList<>( );

        Map< String, String > mapProperties = JsonUtils.convertJsonToMap( jsonNode );

        for ( Entry< String, String > entry : mapProperties.entrySet( ) )
        {
            Property property = new Property( );
            //property.setKey( entry.getKey( ) );
            property.setValue( entry.getValue( ) );
            listProperties.add( property );
        }
        //dataObject.setProperties( listProperties );

        return dataObject;
    }

    public static String convertDataObjectToGeoJsonString( DataObjectGeoJson dataObject ) throws JsonProcessingException
    {
        Feature feature = new Feature( );
        //feature.setProperties( dataObject.getPropertiesAsMapObject( ) );
        feature.setGeometry( dataObject.getGeometry( ) );

        return _objectMapper.writeValueAsString( feature );
    }

    public static String convertDataObjectToJsonString( DataObject dataObject ) throws JsonProcessingException
    {
        return _objectMapper.writeValueAsString( dataObject );
    }

}
