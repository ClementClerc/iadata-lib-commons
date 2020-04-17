package fr.toulouse.iadata.datamodels.service;

import fr.toulouse.iadata.datamodels.models.ngsi.Entity;

public interface IDataEntityConverter
{
    String convertToEntityJsonString( String strData );
}
