/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

import lombok.Data;

/**
 *
 * @author 
 */
@Data
public class UnrecognizedEntityMemberException extends AbstractEntityException {

    private final String key;

    public UnrecognizedEntityMemberException(String strKey, Throwable e, String additionnalInfos) {
        super("Unrecognized path : " + strKey + " | " + additionnalInfos, e);
        key = strKey;
    }

    public UnrecognizedEntityMemberException( String strKey, Throwable e) {
        super("Unrecognized path : " + strKey, e);
        key = strKey;
    }
    public UnrecognizedEntityMemberException(String strKey, String additionnalInfos) {
        super( "Unrecognized path : " + strKey + " | " + additionnalInfos);
        key = strKey;
    }
    public UnrecognizedEntityMemberException( String strKey) {
        super( "Unrecognized path : " + strKey);
        key = strKey;
    }

}





