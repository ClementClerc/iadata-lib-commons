/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

import fr.toulouse.iadata.datamodels.models.ngsi.AbstractProperty;
import fr.toulouse.iadata.datamodels.models.ngsi.Property;
import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author cu32980
 */
@Data
@Accessors( prefix = "_")
public class MalformedPropertyException extends AbstractEntityException  {
    
    private AbstractProperty _property;
    private String _strMessage;

    public MalformedPropertyException(String strMessage, AbstractProperty property, Throwable e )
    {
        _property = property;
        _strMessage = strMessage;

    }
    
    public MalformedPropertyException( String strMessage, AbstractProperty property )
    {
        _property = property;
        _strMessage = strMessage;
    }

    @Override
    public String getErrorMessage() {
        return "error message :"+ _strMessage +" . exception occurs caused by Malformed Property : "+_property.getValue().toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
