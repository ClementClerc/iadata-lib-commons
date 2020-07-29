/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

/**
 *
 * @author cu32980
 */

public class UnexpectedPropertyValueException extends AbstractEntityException  {

    private final String key;
    private final String message;

    
    public UnexpectedPropertyValueException(String errorMessage,String strKey,Throwable e) {
        key = strKey;
        message = errorMessage;
    }
    
    public UnexpectedPropertyValueException(String errorMessage, String strKey) {
        key = strKey;
        message = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return "Error in Processor : " + key + " : " + message;
    }
}
