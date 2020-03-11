/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.model;

public class Relationship
{
    String _key;

    Object _value;

    public Relationship()
    {

    }

    public Relationship( String strKey, String strValue )
    {
        _key = strKey;
        _value = strValue;
    }

    public String getKey( )
    {
        return _key;
    }

    public void setKey( String key )
    {
        _key = key;
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
