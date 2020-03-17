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

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This class represents NGSI Entity Member, aka Property, GeoProperty, Relationship who all are described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>.
 * This abstraction isn't listed in the ref.
 */
@Data
@Accessors( prefix = {"_"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntityMember<T extends EntityMember> extends NGSIElement
{ 
    protected String _name;
    
    protected URI _datasetId;
    
    protected String _observedAt;

    protected String _type;
    
    // NESTED FIELDS
    protected Map< String, T > _members = new HashMap<>();
    
    protected void setType( ){};
    
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
    @JsonAnySetter
    public void setMember( String name, T value) {
        value.setName( name );
	_members.put(name, value);
    }
    
    @JsonAnyGetter
    public Map<String,T> getMembers( ) {
        return _members;
    }
    
    @JsonIgnore
    public String getName( )
    {
        return _name;
    }
}