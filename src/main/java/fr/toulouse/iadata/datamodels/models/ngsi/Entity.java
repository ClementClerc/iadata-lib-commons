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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.toulouse.iadata.datamodels.models.serde.ContextDeserializer;
import fr.toulouse.iadata.datamodels.models.serde.ContextSerializer;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * This class represents NGSI Entity described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors( prefix = {"_"})
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Entity extends NGSIElement
{

    @Id
    @JsonIgnore
    private String _idTech;

    private URI _id;
    
    private String _type;
    
    /////////////////////////////
    // LOCATION FIELDS
    private GeoProperty _location;

    private GeoProperty _observationSpace;
    
    private GeoProperty _operationSpace;
    
    // NESTED FIELDS
    private Map< String, EntityMember > _members = new HashMap<>();
    
    // CONTEXT
    @JsonProperty( "@context")
    @JsonDeserialize(using = ContextDeserializer.class)
    @JsonSerialize(using = ContextSerializer.class)
    private List<Context> _contexts;

    @Builder
    public Entity( String idTech, String id, String type, String createdAt, String modifiedAt, GeoProperty location, GeoProperty observationSpace, GeoProperty operationSpace, Map<String, EntityMember > members, List<Context> contexts) throws URISyntaxException
    {
        _createdAt = createdAt;
        _modifiedAt = modifiedAt;
        _idTech = idTech;
        _id = new URI( id );
        _type = type;
        _location = location;
        _observationSpace = observationSpace;
        _operationSpace = operationSpace;
        _members = members;
        _contexts = contexts;

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
    @JsonAnySetter
    public void setMember( String name, EntityMember value) {
        value.setName( name );
	    _members.put(name, value);
    }

    @JsonIgnore
    public void addMember( EntityMember member )
    {
        _members.put( member.getName( ), member);
    }

    @JsonIgnore
    public EntityMember getMember( String strMemberName )
    {
        return _members.get( strMemberName);
    }
    
    @JsonAnyGetter
    public Map<String,EntityMember > getMembers( ) {
        return _members;
    }

    public void setId(String id ) throws URISyntaxException
    {
            _id = new URI(id);
    }

    public static class EntityBuilder
    {

        public EntityBuilder addMember( EntityMember member) {
            if ( members == null )
            {
                members = new HashMap<>();
            }
            members.put( member.getName( ), member );
            return this;
        }

        public EntityBuilder addSimpleProperty( String strPropertyName, String strPropertyValue ) {
            if ( members == null )
            {
                members = new HashMap<>();
            }
            PropertyValue propertyValue = PropertyValue.builder()
                    .value( strPropertyValue)
                    .name( strPropertyName )
                    .build();
            members.put( propertyValue.getName( ), propertyValue );
            return this;
        }

        public EntityBuilder addContext( Context context) {
            if ( contexts == null )
            {
                contexts = new ArrayList<>();
            }
            contexts.add( context );
            return this;
        }
    }

}
