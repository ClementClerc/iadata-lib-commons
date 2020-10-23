/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

import fr.toulouse.iadata.datamodels.models.ngsi.Entity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author cu32980
 */
@Data
public class MalformedConfigurationException extends AbstractEntityException  {

    private String key;


    public MalformedConfigurationException(Entity entity, Throwable e ,String strKey, String additionnalInfos) {
        super(entity,"Error in : " + strKey + " | " + additionnalInfos,e );
        key = strKey;
    }

    public MalformedConfigurationException(Entity entity, String strKey, String additionnalInfos) {
        super(entity,"Error in : " + strKey + " | " + additionnalInfos);
        key = strKey;
    }

    public MalformedConfigurationException(Entity entity, Exception e, String strKey) {
        super(entity,"Error in : " + strKey, e );
        key = strKey;
    }

    public MalformedConfigurationException(Entity entity, String strKey) {
        super(entity,"Error in : " + strKey);
        key = strKey;
    }



}
