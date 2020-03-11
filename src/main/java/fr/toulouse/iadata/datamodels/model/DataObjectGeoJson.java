/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import mil.nga.sf.geojson.Geometry;

/**
 * @author cu33443
 */
public class DataObjectGeoJson extends DataObject
{
    @JsonInclude( JsonInclude.Include.NON_NULL )
    @JsonIgnore
    Geometry _geometry;

    String _strFeatureCollectionName;

    @JsonProperty( "location" )
    public Geometry getGeometry( )
    {
        return _geometry;
    }

    @JsonIgnore
    public void setGeometry( Geometry _geometry )
    {
        this._geometry = _geometry;
    }

    public String getFeatureCollectionName( )
    {
        return _strFeatureCollectionName;
    }

    @JsonIgnore
    public void setFeatureCollectionName( String strFeatureCollectionName )
    {
        _strFeatureCollectionName = strFeatureCollectionName;
    }

}
