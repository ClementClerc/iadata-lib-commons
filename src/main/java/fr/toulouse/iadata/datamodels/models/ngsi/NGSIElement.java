package fr.toulouse.iadata.datamodels.models.ngsi;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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

    protected AbstractProperty _createdAt;

    protected AbstractProperty _modifiedAt;

    public void setCreatedAt( AbstractProperty value)
    {
        _createdAt = value;
    }

    public void setModifiedAt( AbstractProperty value)
    {
        _modifiedAt = value;
    }


}
