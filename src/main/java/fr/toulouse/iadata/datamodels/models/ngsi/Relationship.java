/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.ngsi;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * This class represents NGSI Relationship described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors( prefix = {"_"})
@NoArgsConstructor
public class Relationship extends EntityMember
{
    private static final String TYPE = "Relationship";

    @Builder
    public Relationship( EntityMember createdAt, EntityMember modifiedAt, String name, URI datasetId, String observedAt, Map<String, EntityMember> members, String object) throws URISyntaxException
    {
        _createdAt = createdAt;
        _modifiedAt = modifiedAt;
        _name = name;
        _datasetId = datasetId;
        _observedAt = observedAt;
        _type = TYPE;
        _members = members;
        _object = new URI(object);
    }

    private URI _object;
    
    @Override
    public void setType() {
        _type = TYPE;
    }

    @Override
    public EntityMember clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
