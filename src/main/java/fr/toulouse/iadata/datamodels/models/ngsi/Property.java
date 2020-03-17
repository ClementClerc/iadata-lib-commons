/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.ngsi;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.net.URI;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This class represents NGSI Property described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@Accessors( prefix = {"_"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Property extends EntityMember
{
    private static final String TYPE = "Property";
    
    Object _value;
    
    private URI _datasetId;
    
    private String _unitCode;

    @Override
    public void setType() {
       _type = TYPE;
    }
    
}
