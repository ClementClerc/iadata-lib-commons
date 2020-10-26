/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.ngsi;

import java.net.MalformedURLException;
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
import fr.toulouse.iadata.datamodels.exceptions.MalformedEntityIdException;
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
    public Relationship( AbstractProperty createdAt, AbstractProperty modifiedAt, String name, URI datasetId, String observedAt, Map<String, EntityMember> members, Object object)
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
    public Relationship(@JsonProperty("object") Object object)
    {
        if ( object instanceof String )
        {
            try
            {
                this.object = new URI( (String)object );
            }
            catch ( URISyntaxException e )
            {
                throw new MalformedEntityIdException( (String)object, e );
            }
        }
        else
        {
            this.object = object;
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
        public RelationshipBuilder addURI( URI uri)
        {
            if ( object == null )
            {
                object = uri;
            }
            else if( object instanceof URI )
            {
                List<URI> listURI = new ArrayList<>();
                listURI.add( (URI)object );
                listURI.add( uri );
                this.object = listURI;
            }
            else if ( object instanceof ArrayList )
            {
                ((List<URI> )object).add( uri );
            }
            return this;
        }
    }


}
