package fr.toulouse.iadata.datamodels.models.management;

import java.util.Arrays;

public enum UpdateDelay {
    ONETIME("onetime",0),
    DAILY("daily",86400),
    WEEKLY("weekly",604800),
    MONTHLY("monthly",2592000),
    QUARTERLY("quarterly",7776000),
    HALFYEARLY("halfyearly",15552000),
    YEARLY("yearly",31536000);

    private String key;
    private long nbSec;

    private String getKey( )
    {
        return this.key;
    }

    public long getNbSec( )
    {
        return this.nbSec;
    }

    UpdateDelay( String key, long nbSec)
    {
        this.key = key;
        this.nbSec = nbSec;
    }

    public static UpdateDelay findByKey(final String key){
        return Arrays.stream(values()).filter(value -> value.getKey().equals(key)).findFirst().orElse(null);
    }
}
