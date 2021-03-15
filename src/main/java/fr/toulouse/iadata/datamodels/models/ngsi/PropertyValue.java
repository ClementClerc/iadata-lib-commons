/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.ngsi;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This class represents NGSI Property Value described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class PropertyValue extends AbstractProperty
{
    private static final String TYPE = "PropertyValue";
    
    @JsonValue
    private Object value;
    
    @Override
    public void setType() {
       type = TYPE;
    }

    public PropertyValue( String strValue )
    {
        value = strValue;
    }

    @Builder
    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }


    @Override
    public EntityMember clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    @Override
    public void modifyValue(Object value) {
        this.value = value;
    }
}
