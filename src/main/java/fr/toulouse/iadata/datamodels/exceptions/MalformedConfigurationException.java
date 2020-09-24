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
    private final String customArgsSize;
    private final String activatedKeySize;
    private Throwable exception ;

    private String exceptionError;



    public MalformedConfigurationException(Entity entity, String customArgsSize, String activatedKeySize,String strKey, List<String> customArgs,List<String> activatedKeys,Throwable e) {
        key = strKey;
        this.customArgs = customArgs;
        this.activatedKeys = activatedKeys;
        this.exceptionError = e.getMessage();
        this.customArgsSize = customArgsSize;
        this.activatedKeySize = activatedKeySize;
        this.exception = e;
    }
    
    public MalformedConfigurationException(Entity entity, String customArgsSize, String activatedKeySize,String strKey, List<String> customArgs,List<String> activatedKeys) {
        key = strKey;
        this.customArgs = customArgs;
        this.activatedKeys = activatedKeys;
        this.customArgsSize = customArgsSize;
        this.activatedKeySize = activatedKeySize;
    }

    @Override
    public String getErrorMessage() {
        if(activatedKeys == null){
            activatedKeys.add("null");
        }
        if(customArgs == null){
            customArgs.add("null");
        }

        if ( exception != null ){
            return "Error in Processor : " + key + "ERROR message : " +exception.getMessage() + ", expected size " + customArgsSize +" for customArgs : CustomArgs : "
                    + customArgs.stream().collect(Collectors.joining(","))
                    + ", expected size "+ activatedKeySize+" for activatedKeys : activatedKeys : " + activatedKeys.stream().collect(Collectors.joining(","));
        }
        return "Error in Processor : " + key + ", expected size " + customArgsSize +" for customArgs : CustomArgs : "
                + customArgs.stream().collect(Collectors.joining(","))
                + ", expected size "+ activatedKeySize+" for activatedKeys : activatedKeys : " + activatedKeys.stream().collect(Collectors.joining(","));
    }
}
