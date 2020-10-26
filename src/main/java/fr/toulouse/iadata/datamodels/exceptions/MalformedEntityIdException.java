/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

import lombok.Data;

@Data
public class MalformedEntityIdException extends AbstractEntityException
{
    private final String id;

    public MalformedEntityIdException(String id, Throwable e) {
        super("Malformed id Uri : " + id, e);
        this.id = id;
    }

    public MalformedEntityIdException(String id) {
        super( "Malformed id Uri : " + id );
        this.id = id;
    }
}





