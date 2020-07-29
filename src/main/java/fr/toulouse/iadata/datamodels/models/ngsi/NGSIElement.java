package fr.toulouse.iadata.datamodels.models.ngsi;

import lombok.Data;

/**
 * This class represents NGSI Element described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
abstract class NGSIElement
{

    protected AbstractProperty createdAt;

    protected AbstractProperty modifiedAt;

    public void setCreatedAt( AbstractProperty value)
    {
        createdAt = value;
    }

    public void setModifiedAt( AbstractProperty value)
    {
        modifiedAt = value;
    }


}
