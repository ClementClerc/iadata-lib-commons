/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

/**
 *
 * @author cu32980
 */
@Data
public class PropertyValue extends Member
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
