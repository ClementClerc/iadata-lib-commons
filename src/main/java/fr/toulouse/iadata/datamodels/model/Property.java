/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.model;

public class Property
{
    String _strName;
    
    String _strType;

    Object _value;
    
    String _strDateSetId;
    
    String _strObservedAt;
    
    String _strCreatedAt;
    
    String _strModifiedAt;
    
    String _strInstanceId;
    
    String _strUnitCode;
    
    

    public Property()
    {

    }


    public Object getValue( )
    {
        return _value;
    }

    public void setValue( Object value )
    {
        _value = value;
    }

}
