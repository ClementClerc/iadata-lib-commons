package fr.toulouse.iadata.datamodels.models.ngsi;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * This class represents NGSI Element described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@Accessors( prefix = {"_"})
abstract class NGSIElement
{

    protected EntityMember _createdAt;

    protected EntityMember _modifiedAt;

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.EXISTING_PROPERTY,
            property = "type",
            visible = true,
            defaultImpl = PropertyValue.class )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Property.class, name = "Property"),
            @JsonSubTypes.Type(value = PropertyValue.class, name = "PropertyValue"),
            @JsonSubTypes.Type(value = Relationship.class, name = "Relationship"),
            @JsonSubTypes.Type(value = GeoProperty.class, name = "GeoProperty")})
    public void setCreatedAt( EntityMember value)
    {
        _createdAt = value;
    }

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.EXISTING_PROPERTY,
            property = "type",
            visible = true,
            defaultImpl = PropertyValue.class )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Property.class, name = "Property"),
            @JsonSubTypes.Type(value = PropertyValue.class, name = "PropertyValue"),
            @JsonSubTypes.Type(value = Relationship.class, name = "Relationship"),
            @JsonSubTypes.Type(value = GeoProperty.class, name = "GeoProperty")})
    public void setModifiedAt( EntityMember value)
    {
        _modifiedAt = value;
    }


}
