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

    public UnexpectedKeyPathException(String strEntityKey, String strProcessorKey) {
        processorKey = strProcessorKey;
        entityKey = strEntityKey;
    }

    @Override
    public String getErrorMessage() {
        return "Unexpected key path for key : " + entityKey + " in processor " + processorKey;
    }
}
