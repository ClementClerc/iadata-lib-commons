/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

import fr.toulouse.iadata.datamodels.models.ngsi.AbstractProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author cu32980
 */
@Data
public class MalformedPropertyException extends AbstractEntityException  {
    
    private AbstractProperty property;
    private String message;

    public MalformedPropertyException(String strMessage, AbstractProperty property, Throwable e )
    {
        this.property = property;
        message = strMessage;

    }
    
    public MalformedPropertyException( String strMessage, AbstractProperty property )
    {
        this.property = property;
        message = strMessage;
    }

    @Override
    public String getErrorMessage() {
        return "error message :"+ message +" . exception occurs caused by Malformed Property : "+ property.getValue().toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
