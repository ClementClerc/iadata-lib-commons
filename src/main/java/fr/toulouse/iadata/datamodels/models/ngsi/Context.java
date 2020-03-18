/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.ngsi;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * This class represents NGSI Context (@context) described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@Accessors( prefix = {"_"})
@NoArgsConstructor
@AllArgsConstructor
public class Context 
{
    private URI _uri;

    private Map<String, URI> _mapFieldsUri = new HashMap<>();

    public Map<String, URI> getMapFieldsUri() {
        return _mapFieldsUri;
    }

    @Builder
    public Context( String uri, Map<String, URI> mapFieldsUri) throws URISyntaxException
    {
        if ( uri != null )
        {
            _uri = new URI( uri);
        }
        _mapFieldsUri = mapFieldsUri;
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
