/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.ngsi;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents NGSI Context (@context) described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Context 
{
    private URI uri;

    private Map<String, URI> mapFieldsUri = new HashMap<>();

    public Map<String, URI> getMapFieldsUri() {
        return mapFieldsUri;
    }

    @Builder
    public Context( String uri, Map<String, URI> mapFieldsUri) throws URISyntaxException
    {
        if ( uri != null )
        {
            this.uri = new URI( uri);
        }
        this.mapFieldsUri = mapFieldsUri;
    }

    public static class ContextBuilder
    {

        public ContextBuilder addFieldURI( String strField, String strUri) throws URISyntaxException
        {
            if ( mapFieldsUri == null )
            {
                mapFieldsUri = new HashMap<>();
            }
            mapFieldsUri.put( strField, new URI(strUri) );
            return this;
        }
    }
}
