/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

import fr.toulouse.iadata.datamodels.models.ngsi.Entity;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author cu32980
 */

public class MalformedConfigurationException extends AbstractEntityException  {

    private final String key;
    private Throwable exception ;

    private String errorMessage;



    public MalformedConfigurationException(Entity entity,String strKey, String errorMessage,Throwable e) {
        key = strKey;
        this.errorMessage = errorMessage;
        this.exception = e;
    }
    
    public MalformedConfigurationException(Entity entity,String strKey, String errorMessage) {
        key = strKey;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage()
    {
        return "Error in processor : " + key + " : " + errorMessage;
    }
}
