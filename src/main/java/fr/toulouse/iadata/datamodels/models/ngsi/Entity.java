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
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.toulouse.iadata.datamodels.models.serde.ContextDeserializer;
import fr.toulouse.iadata.datamodels.models.serde.ContextSerializer;
import lombok.Data;
import lombok.experimental.Accessors;
import mil.nga.sf.geojson.Geometry;
import org.springframework.data.annotation.Id;

@Data
@Accessors( prefix = {"_"})
@JsonInclude(Include.NON_NULL)
/**
 * This class represents NGSI Entity described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
public class Entity<T extends EntityMember> extends NGSIElement
{
    
    @Id
    @JsonIgnore
    private String _idTech;
    
    @JsonProperty( "id")
    private URI _uriId;
    
    private String _type;
    
    /////////////////////////////
    // LOCATION FIELDS
    private GeoProperty _location;

    private GeoProperty _observationSpace;
    
    private GeoProperty _operationSpace;
    
    // NESTED FIELDS
    private Map< String, T > _members = new HashMap<>();
    
    // CONTEXT
    @JsonProperty( "@context")
    @JsonDeserialize(using = ContextDeserializer.class)
    @JsonSerialize(using = ContextSerializer.class)
    private List<Context> _contexts;
    
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

}
