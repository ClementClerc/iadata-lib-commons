/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.model;

import lombok.Data;
import lombok.experimental.Accessors;
import mil.nga.sf.geojson.Geometry;

/**
 *
 * @author cu32980
 */
@Data
@Accessors( prefix = {"_"})
public class Geolocation
{
    private String _type;
    
    private Geometry _coordinates;
}
