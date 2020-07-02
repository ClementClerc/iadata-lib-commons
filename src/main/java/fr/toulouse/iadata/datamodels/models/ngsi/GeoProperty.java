/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.ngsi;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.Accessors;
import org.geojson.GeoJsonObject;

import java.net.URI;
import java.util.Map;

/**
 * This class represents NGSI GeoProperty described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors( prefix = {"_"})
@NoArgsConstructor
public class GeoProperty extends AbstractProperty
{
    private static final String TYPE = "GeoProperty";

    GeoJsonObject _value;

    @Builder
    public GeoProperty(EntityMember createdAt, EntityMember modifiedAt, String name, URI datasetId, String observedAt, Map<String, EntityMember> members, GeoJsonObject value) {
        _createdAt = createdAt;
        _modifiedAt = modifiedAt;
        _name = name;
        _datasetId = datasetId;
        _observedAt = observedAt;
        _type = TYPE;
        _members = members;
        _value = value;
    }

    @Override
    public void setType() {
        _type = TYPE;
    }

    @Override
    public EntityMember clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    @Override
    public void modifyValue(Object value) {
        _value = (GeoJsonObject) value;
    }
}
