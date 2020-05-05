/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

import fr.toulouse.iadata.datamodels.models.ngsi.Entity;
import fr.toulouse.iadata.datamodels.models.ngsi.EntityMember;
import fr.toulouse.iadata.datamodels.models.ngsi.Property;
import java.util.List;

/**
 *
 * @author 
 */
public class UnrecognizedEntityMemberException extends AbstractEntityException {

    private final String _strKey;

    public UnrecognizedEntityMemberException(String strKey) {
        _strKey = strKey;
    }
    
    public String getErrorMessage(){
        return "Unrecognized path : " + _strKey;
    };


}





