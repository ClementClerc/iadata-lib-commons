/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

import lombok.Data;

/**
 *
 * @author cu32980
 */
@Data
public class UnexpectedPropertyValueException extends AbstractEntityException  {

    private String key;
    
    public UnexpectedPropertyValueException(String strKey, Object propertyValue, Throwable e, String additionnalInfos) {
        super("Error in Processor : " + strKey + " | Unexpected Property value : " + propertyValue.toString() + " | " + additionnalInfos,e);
        key = strKey;
    }

    public UnexpectedPropertyValueException( String strKey, Throwable e, String additionnalInfos) {
        super("Error in Processor : " + strKey + " | " + additionnalInfos,e);
        key = strKey;
    }
    
    public UnexpectedPropertyValueException(String strKey, Object propertyValue, String additionnalInfos) {
        super("Error in Processor : " + strKey + " | Unexpected Property value : " + propertyValue.toString() +" | " + additionnalInfos);
        key = strKey;
    }

    public UnexpectedPropertyValueException(String strKey, Object propertyValue) {
        super("Error in Processor : " + strKey + " | Unexpected Property value : " + propertyValue.toString());
        key = strKey;
    }

    public UnexpectedPropertyValueException(String strKey, String additionnalInfos) {
        super("Error in Processor : " + strKey + " | " + additionnalInfos);
        key = strKey;
    }

    public UnexpectedPropertyValueException(String additionnalInfos) {
        super( additionnalInfos);
    }



}
