/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.ngsi;

import lombok.*;
import lombok.experimental.Accessors;
import org.geojson.GeoJsonObject;
import org.geojson.Geometry;

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
public class GeoProperty extends EntityMember
{
    private static final String TYPE = "GeoProperty";

    GeoJsonObject _value;

    @Builder
    public GeoProperty(String createdAt, String modifiedAt, String name, URI datasetId, String observedAt, Map<String, EntityMember> members, GeoJsonObject value) {
        super(createdAt, modifiedAt, name, datasetId, observedAt, TYPE, members);
        _value = value;
    }

    @Override
    public void setType() {
        _type = TYPE;
    }

}
