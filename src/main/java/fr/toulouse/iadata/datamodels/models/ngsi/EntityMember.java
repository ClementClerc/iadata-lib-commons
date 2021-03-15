/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.ngsi;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import lombok.*;

/**
 * This class represents NGSI Entity Member, aka Property, GeoProperty, Relationship who all are described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>.
 * This abstraction isn't listed in the ref.
 */
@Data
@EqualsAndHashCode(callSuper=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
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

public abstract class EntityMember extends NGSIElement implements Cloneable
{ 
    protected String name;
    
    protected URI datasetId;
    
    protected String observedAt;

    protected String type;
    
    // NESTED FIELDS
    protected Map< String, EntityMember > members = new HashMap<>();

    protected abstract void setType( );

    @JsonAnySetter
    public void setMember( String name, EntityMember value) {
        value.setName( name );
	members.put(name, value);
    }

    @JsonIgnore
    public void addMember( EntityMember member )
    {
        if (members == null) {
            members = new HashMap<>();
        }
        members.put( member.getName( ), member);
    }

    public EntityMember getMember( String strMemberName )
    {
        return members.get( strMemberName);
    }

    @JsonAnyGetter
    public Map<String,EntityMember> getMembers( ) {
        if ( members == null )
        {
            members = new HashMap<>();
        }
        return members;
    }
    
    @JsonIgnore
    public String getName( )
    {
        return name;
    }


    @Override
    public EntityMember clone( ) throws CloneNotSupportedException
    {
        return (EntityMember)super.clone();

    }
}
