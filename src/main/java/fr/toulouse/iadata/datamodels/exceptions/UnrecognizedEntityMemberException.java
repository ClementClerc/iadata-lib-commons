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
public class UnrecognizedEntityMemberException extends AbstractEntityException {

    private final String key;

    public UnrecognizedEntityMemberException(Entity entity, String strKey, Throwable e, String additionnalInfos) {
        super( entity ,"Unrecognized path : " + strKey + " | " + additionnalInfos, e);
        key = strKey;
    }

    public UnrecognizedEntityMemberException(Entity entity, String strKey, Throwable e) {
        super( entity ,"Unrecognized path : " + strKey, e);
        key = strKey;
    }
    public UnrecognizedEntityMemberException(Entity entity, String strKey, String additionnalInfos) {
        super( entity ,"Unrecognized path : " + strKey + " | " + additionnalInfos);
        key = strKey;
    }
    public UnrecognizedEntityMemberException(Entity entity, String strKey) {
        super( entity ,"Unrecognized path : " + strKey);
        key = strKey;
    }

}





