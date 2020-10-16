/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.ngsi;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

/**
 * This class represents NGSI Relationship described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Log4j2
public class Relationship extends EntityMember
{
    private static final String TYPE = "Relationship";


    @Builder
    public Relationship( AbstractProperty createdAt, AbstractProperty modifiedAt, String name, URI datasetId, String observedAt, Map<String, EntityMember> members, Object object) throws URISyntaxException
    {
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.name = name;
        this.datasetId = datasetId;
        this.observedAt = observedAt;
        this.type = TYPE;
        this.members = members;
        this.object = object;
    }

    private Object object;

    @JsonCreator
    public Relationship(@JsonProperty("object") Object object) {

       try
       {
           if ( object instanceof String )
           {
               this.object = new URI( (String)object );
           }
           else
           {
               this.object = object;
           }
       }
       catch (URISyntaxException e )
       {

       }
    }
    
    @Override
    public void setType() {
        type = TYPE;
    }

    @Override
    public EntityMember clone() throws CloneNotSupportedException
    {
        return super.clone();
    }


    public static class RelationshipBuilder
    {
        public RelationshipBuilder addURI( String strUri) throws URISyntaxException
        {
            if ( object == null )
            {
                object = new URI( strUri );
            }
            else if( object instanceof URI )
            {
                List<URI> listURI = new ArrayList<>();
                listURI.add( (URI)object );
                try
                {
                    listURI.add( new URI ( strUri ) );
                    this.object = listURI;
                }
                catch ( URISyntaxException e)
                {
                    log.error("object string " + strUri + " is not an URI", e );
                }
            }
            else if ( object instanceof ArrayList )
            {
                try
                {
                    ((List<URI> )object).add( new URI(strUri) );
                }
                catch ( URISyntaxException e)
                {
                    log.error("object string " + strUri + " is not an URI", e );
                }
            }
            return this;
        }
    }


}
