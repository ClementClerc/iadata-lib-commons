/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

import fr.toulouse.iadata.datamodels.models.ngsi.AbstractProperty;
import fr.toulouse.iadata.datamodels.models.ngsi.Entity;
import fr.toulouse.iadata.datamodels.models.ngsi.EntityMember;
import fr.toulouse.iadata.datamodels.models.ngsi.Property;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author cu32980
 */

public class MalformedConfigurationException extends AbstractEntityException  {

    private final String _strKey;
    private final List<String> _customArgs;
    private final List<String> _activatedKeys;
    private final String _errorMessage;
    private String _exceptionError;

    
    public MalformedConfigurationException(Entity entity, String errorMessage,String strKey, List<String> customArgs,List<String> activatedKeys,Throwable e) {
        _strKey = strKey;
        _customArgs = customArgs;
        _activatedKeys = activatedKeys;
        _errorMessage = errorMessage;
        _exceptionError = e.getMessage();
    }
    
    public MalformedConfigurationException(Entity entity, String errorMessage,String strKey, List<String> customArgs,List<String> activatedKeys) {
        _strKey = strKey;
        _customArgs = customArgs;
        _activatedKeys = activatedKeys;
        _errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return "Error in Processor : " + _strKey + " : " + _errorMessage + " : CustomArgs : "
                + _customArgs.stream().collect(Collectors.joining(",")) 
                + " : CustomArgs : " + _activatedKeys.stream().collect(Collectors.joining(","));
    }
}
