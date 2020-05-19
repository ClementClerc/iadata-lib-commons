/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

import fr.toulouse.iadata.datamodels.models.ngsi.Entity;
import fr.toulouse.iadata.datamodels.models.ngsi.EntityMember;
import fr.toulouse.iadata.datamodels.models.ngsi.Property;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author 
 */
@Accessors(prefix={"_"})
@Data
public class FatalException extends Exception  {

    private String _entityKey;
    private Entity _entity;
    private String _strTopic;
    
    public FatalException(String entityKey, Entity entity, String strTopic){
        
        _entity = entity;
        _entityKey=entityKey;
        _strTopic = strTopic;
    }
         


}





