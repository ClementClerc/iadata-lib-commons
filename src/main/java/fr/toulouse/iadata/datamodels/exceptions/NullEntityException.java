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
public class NullEntityException extends AbstractEntityException {




    public NullEntityException(Throwable e) {
        super("Input entity is null", e);

    }
    public NullEntityException() {
        super( "Input entity is null" );

    }

}





