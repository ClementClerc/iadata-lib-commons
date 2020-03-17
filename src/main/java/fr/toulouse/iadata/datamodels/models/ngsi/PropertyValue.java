/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.ngsi;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

/**
 * This class represents NGSI Property Value described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
public class PropertyValue extends EntityMember
{
    private static final String TYPE = "PropertyValue";
    
    @JsonValue
    private String value;
    
    @Override
    public void setType() {
       _type = TYPE;
    }
    
    public PropertyValue( String strValue )
    {
        value = strValue;
    }
}
