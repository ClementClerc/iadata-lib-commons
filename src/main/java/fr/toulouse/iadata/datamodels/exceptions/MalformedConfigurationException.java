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
    private final List<String> customArgs;
    private final List<String> activatedKeys;
    private final String errorMessage;
    private String exceptionError;



    public MalformedConfigurationException(Entity entity, String errorMessage,String strKey, List<String> customArgs,List<String> activatedKeys,Throwable e) {
        key = strKey;
        this.customArgs = customArgs;
        this.activatedKeys = activatedKeys;
        this.errorMessage = errorMessage;
        this.exceptionError = e.getMessage();
    }
    
    public MalformedConfigurationException(Entity entity, String errorMessage,String strKey, List<String> customArgs,List<String> activatedKeys) {
        key = strKey;
        this.customArgs = customArgs;
        this.activatedKeys = activatedKeys;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return "Error in Processor : " + key + " : " + errorMessage + " : CustomArgs : "
                + customArgs.stream().collect(Collectors.joining(","))
                + " : CustomArgs : " + activatedKeys.stream().collect(Collectors.joining(","));
    }
}
