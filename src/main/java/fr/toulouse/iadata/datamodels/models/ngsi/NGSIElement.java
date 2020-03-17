package fr.toulouse.iadata.datamodels.models.ngsi;

import lombok.Data;

/**
 * This class represents NGSI Element described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
public class NGSIElement
{
    // COMMON MEMBERS
    private String _createdAt;

    private String _modifiedAt;
}
