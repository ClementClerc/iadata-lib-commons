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
 * @author 
 */
@Data
public class SerdeException extends AbstractEntityException {

    private String data;

    public SerdeException(Entity entity,String strData, String additionnalInfos) {
        super(entity, "Unable to deserialize data " + strData + " | Reason : "+ additionnalInfos);
        data = strData;
    }

    public SerdeException(Entity entity,String strData) {
        super(entity, "Unable to deserialize data " + strData);
        data = strData;
    }
}





