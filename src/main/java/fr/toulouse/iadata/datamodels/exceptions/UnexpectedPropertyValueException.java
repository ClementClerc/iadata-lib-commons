/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

import fr.toulouse.iadata.datamodels.models.ngsi.Entity;
import lombok.Data;

/**
 *
 * @author cu32980
 */
@Data
public class UnexpectedPropertyValueException extends AbstractEntityException  {

    private String key;
    private String message;

    
    public UnexpectedPropertyValueException(Entity entity, String strKey, Object propertyValue, Throwable e, String additionnalInfos) {
        super(entity,"Error in Processor : " + strKey + " | Unexpected Property value : " + propertyValue.toString() + " | " + additionnalInfos,e);
        key = strKey;
    }

    public UnexpectedPropertyValueException(Entity entity, String strKey, Throwable e, String additionnalInfos) {
        super(entity,"Error in Processor : " + strKey + " | " + additionnalInfos,e);
        key = strKey;
    }
    
    public UnexpectedPropertyValueException(Entity entity,String strKey, Object propertyValue, String additionnalInfos) {
        super(entity,"Error in Processor : " + strKey + " | Unexpected Property value : " + propertyValue.toString() +" | " + additionnalInfos);
        key = strKey;
    }

    public UnexpectedPropertyValueException(Entity entity,String strKey, Object propertyValue) {
        super(entity,"Error in Processor : " + strKey + " | Unexpected Property value : " + propertyValue.toString());
        key = strKey;
    }

    public UnexpectedPropertyValueException( Entity entity, String strKey, String additionnalInfos) {
        super(entity,"Error in Processor : " + strKey + " | " + additionnalInfos);
        key = strKey;
    }



}
