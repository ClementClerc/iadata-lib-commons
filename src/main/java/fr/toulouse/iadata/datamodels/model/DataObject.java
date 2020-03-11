/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.aspectj.asm.internal.Relationship;
import org.springframework.data.annotation.Id;

/**
 * @author cu33443
 */
public class DataObject
{
    
    @Id
    private String _strId;
    
    private String _strType;
    
    private String _strCreatedAt;
    
    private String _strModifiedAt;
    
    // LOCATION FIELDS
    private GeoProperty _location;

    private GeoProperty _observationSpace;
    
    private GeoProperty _operationSpace;
    
    // NESTED FIELDS

    private List< Property > _properties;
    
    private List< Relationship > _relationships;
    
    


}
