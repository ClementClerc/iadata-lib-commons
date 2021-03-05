package fr.toulouse.iadata.datamodels.models.management;

import lombok.Data;

@Data
public class Key
{
    private String oldKey;
    private String newKey;
    private boolean enrichable;
}
