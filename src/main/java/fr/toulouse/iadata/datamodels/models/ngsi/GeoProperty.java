/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.ngsi;

import lombok.Data;
import lombok.experimental.Accessors;
import mil.nga.sf.geojson.Geometry;

/**
 * This class represents NGSI GeoProperty described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@Accessors( prefix = {"_"})
public class GeoProperty extends EntityMember
{
    private static final String TYPE = "GeoProperty";

    //GeoLocation _value;
    Geometry _value;
    
    @Override
    public void setType() {
        _type = TYPE;
    }

}
