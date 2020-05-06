/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

/**
 *
 * @author loulo
 */
public class MalformedKeyPathException extends AbstractEntityException {

    private final String _strMessage;

    public MalformedKeyPathException(String strMessage,Throwable e) {
        _strMessage = strMessage;
    }
        public MalformedKeyPathException(String strMessage) {
        _strMessage = strMessage;
    }
    
    
    

    @Override
    public String getErrorMessage() {
        return _strMessage;
    }
    
}
