package fr.toulouse.iadata.commons.beans.management;

import lombok.Data;

@Data
public class Key
{
    private String oldKey;
    private String newKey;
    private boolean enrichable;
}
