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

public class UnexpectedPropertyValueException extends AbstractEntityException  {

    private final String _strKey;
    private final String _errorMessage;

    
    public UnexpectedPropertyValueException(String errorMessage,String strKey,Throwable e) {
        _strKey = strKey;
        _errorMessage = errorMessage;
    }
    
    public UnexpectedPropertyValueException(String errorMessage, String strKey) {
        _strKey = strKey;
        _errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return "Error in Processor : " + _strKey + " : " + _errorMessage;
    }
}
