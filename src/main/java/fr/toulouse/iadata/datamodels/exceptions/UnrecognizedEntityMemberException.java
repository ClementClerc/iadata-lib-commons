/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

/**
 *
 * @author 
 */
public class UnrecognizedEntityMemberException extends AbstractEntityException {

    private final String key;

    public UnrecognizedEntityMemberException(String strKey) {
        key = strKey;
    }
    
    public String getErrorMessage(){
        return "Unrecognized path : " + key;
    };


}





