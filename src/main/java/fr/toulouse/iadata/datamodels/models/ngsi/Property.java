/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.ngsi;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import lombok.*;

/**
 * This class represents NGSI Property described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class Property extends AbstractProperty
{
    @Builder
    public Property( AbstractProperty createdAt, AbstractProperty modifiedAt, String name, URI datasetId, String observedAt, Map<String, EntityMember> members, Object value, String unitCode) {
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.name = name;
        this.datasetId = datasetId;
        this.observedAt = observedAt;
        this.type = TYPE;
        this.members = members;
        this.value = value;
        this.unitCode = unitCode;
    }

    private static final String TYPE = "Property";
    
    Object value;
    
    private URI datasetId;
    
    private String unitCode;

    @Override
    public void setType() {
       type = TYPE;
    }

    @Override
    public void modifyValue(Object value) {
        this.value = value;
    }

    public static class PropertyBuilder
    {
        public PropertyBuilder addMember( EntityMember member) {
            if (members == null) {
                members = new HashMap<>();
            }
            if ( member != null )
            {
                members.put( member.getName(), member);
            }
            return this;
        }
    }


    @Override
    public EntityMember clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

}
