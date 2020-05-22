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
public class SerdeException extends AbstractEntityException {

    private final String _strData;

    public SerdeException(String strData) {
        _strData = strData;
    }
    
    public String getErrorMessage(){
        return "Unable to deserialize data " + _strData;
    };


}





