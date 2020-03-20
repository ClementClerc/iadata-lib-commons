package fr.toulouse.iadata.datamodels.models.ngsi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * This class represents NGSI Element described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@Accessors( prefix = {"_"})
abstract class NGSIElement
{
    // COMMON MEMBERS
    protected String _createdAt;

    protected String _modifiedAt;
}
