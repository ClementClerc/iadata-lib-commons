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
 * This class represents NGSI Relationship described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@Accessors( prefix = {"_"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Relationship extends EntityMember
{
    private static final String TYPE = "Relationship";
    
    private URI _object;
    
    @Override
    public void setType() {
        _type = TYPE;
    }
}
