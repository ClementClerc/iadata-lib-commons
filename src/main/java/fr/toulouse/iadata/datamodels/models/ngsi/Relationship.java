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
        if (object instanceof ArrayList){
            try {
                this.object = (List<URI>) object;
            } catch (ClassCastException e) {
                log.error("ClassCastException expected URI List "+ e.getMessage());
            }
        }
        else if( object != null ) {
        this.object = new URI(object.toString());
        }
    }

    private Object object;


    
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
                object = new ArrayList<>();
            }
            try {
                ((List<URI> )object).add( new URI(strUri) );
            } catch (ClassCastException e) {
                log.error("ClassCastException expected URI List " + e.getMessage());
            }
            return this;
        }
    }


}
