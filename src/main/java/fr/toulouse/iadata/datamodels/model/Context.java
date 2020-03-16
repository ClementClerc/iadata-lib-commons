/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author cu32980
 */
@Data
@Accessors( prefix = {"_"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Context 
{
    @JsonValue
    private URI _uri;
    
    //@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
    @JsonIgnore
    private Map<String, URI> _mapFieldsUri = new HashMap<>();
    
    @JsonAnyGetter
    public Map<String, URI> getMapFieldsUri() {
        return _mapFieldsUri;
    }
    
}
