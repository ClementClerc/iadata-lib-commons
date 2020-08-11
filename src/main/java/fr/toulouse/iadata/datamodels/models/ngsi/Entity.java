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
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

/**
 * This class represents NGSI Entity described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Entity extends NGSIElement
{
    @Id
    private URI id;

    private AbstractProperty type;
    
    /////////////////////////////
    // LOCATION FIELDS
    private GeoProperty location;

    private GeoProperty observationSpace;
    
    private GeoProperty operationSpace;
    
    // NESTED FIELDS
    private Map< String, EntityMember > members = new HashMap<>();
    
    // CONTEXT
    @JsonProperty( "@context")
    @JsonDeserialize(using = ContextDeserializer.class)
    @JsonSerialize(using = ContextSerializer.class)
    private List<Context> _contexts;

    @Builder
    public Entity( String id, AbstractProperty type, GeoProperty location, GeoProperty observationSpace, GeoProperty operationSpace, Map<String, EntityMember > members, List<Context> contexts) throws URISyntaxException
    {
        this.id = new URI( id );
        this.type = type;
        this.location = location;
        this.observationSpace = observationSpace;
        this.operationSpace = operationSpace;
        this.members = members;
        _contexts = contexts;

    }

    @JsonAnySetter
    public void setMember( String name, EntityMember value) {
        value.setName( name );
	    members.put(name, value);
    }

    @JsonIgnore
    public void addMember( EntityMember member )
    {
        if ( members == null )
        {
            members = new HashMap<>();
        }
        members.put( member.getName( ), member);
    }

    @JsonIgnore
    public EntityMember getMember( String strMemberName )
    {
        return members.get( strMemberName);
    }
    
    @JsonAnyGetter
    public Map<String,EntityMember > getMembers( ) {
        if ( members == null ) { members = new HashMap<>();}
        return members;
    }

    public void setId(String id ) throws URISyntaxException
    {
            this.id = new URI(id);
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

        public EntityBuilder addMemberByPath( String strPath, EntityMember member )
        {
            if ( members == null )
            {
                members = new HashMap<>();
            }

            //Case 1 : check if member is under the root of the JSON
            if (StringUtils.countOccurrencesOf( strPath, ".") == 0 )
            {
                member.setName( strPath );
                members.put( member.getName( ), member );
            }
            //Member is nested
            else
            {
                String[] tabPath = strPath.split( "[.]");
                EntityMember parentMember = null;
                for ( int i =0 ; i< tabPath.length - 1; i++ )
                {
                    parentMember = members.get(tabPath[i]);
                    if (parentMember == null ){
                        parentMember = new Property().builder().name(tabPath[i]).build();
                        members.put( tabPath[i], parentMember );

                    }

                }
                member.setName( tabPath[ tabPath.length -1 ] );
                parentMember.addMember( member  );

            }
            return this;
        }

        public EntityBuilder addSimpleProperty( String strPropertyName, Object value ) {
            if ( members == null )
            {
                members = new HashMap<>();
            }
            PropertyValue propertyValue = PropertyValue.builder()
                    .value( value )
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

    public void setType(AbstractProperty type) {
            this.type = type;
        }
    //
//        public Entity clone() throws CloneNotSupportedException {
//        Entity entity = (Entity)super.clone();
//        return entity;
//    }

}
