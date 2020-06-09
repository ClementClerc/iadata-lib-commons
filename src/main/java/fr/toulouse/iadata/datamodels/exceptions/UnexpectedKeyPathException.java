/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

/**
 *
 * @author cu32980
 */

public class UnexpectedKeyPathException extends AbstractEntityException  {

    private final String _strKey;
    private final String _errorMessage;


    public UnexpectedKeyPathException(String errorMessage, String strKey, Throwable e) {
        _strKey = strKey;
        _errorMessage = errorMessage;
    }

    public UnexpectedKeyPathException(String errorMessage, String strKey) {
        _strKey = strKey;
        _errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return "Error in Processor : " + _strKey + " : " + _errorMessage;
    }
}
