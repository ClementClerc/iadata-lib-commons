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

    private final String processorKey;
    private final String entityKey;
    private final String errorMessage;

    public UnexpectedKeyPathException(String strEntityKey, String strProcessorKey) {
        processorKey = strProcessorKey;
        entityKey = strEntityKey;
        errorMessage = null;
    }

    public UnexpectedKeyPathException(String entityKey, String processorKey, String errorMessage ) {
        this.processorKey = processorKey;
        this.entityKey = entityKey;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        String strErrorMessage =  "Unexpected key path for key : " + entityKey + " in processor " + processorKey;

        if ( errorMessage != null )
        {
            strErrorMessage = strErrorMessage + ". " + this.errorMessage;
        }
        return  strErrorMessage;
    }
}
