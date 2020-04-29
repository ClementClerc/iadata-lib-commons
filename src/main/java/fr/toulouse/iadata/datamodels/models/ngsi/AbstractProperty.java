package fr.toulouse.iadata.datamodels.models.ngsi;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractProperty extends EntityMember
{
    public abstract Object getValue();

    @JsonIgnore
    public abstract void modifyValue( Object value );
}
