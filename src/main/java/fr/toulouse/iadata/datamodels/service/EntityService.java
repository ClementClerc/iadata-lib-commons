package fr.toulouse.iadata.datamodels.service;

import fr.toulouse.iadata.datamodels.models.ngsi.AbstractProperty;
import fr.toulouse.iadata.datamodels.models.ngsi.Entity;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class EntityService
{
    public void consumeOnNamedProperties(Entity entity, String strPropertyName, Consumer<AbstractProperty> consumer )
    {
        Stream<AbstractProperty> streamProperties = flatten( entity );
        streamProperties
                .filter( property -> property.getName().equals( strPropertyName ))
                .forEach( consumer );
    }


    private Stream<AbstractProperty> flatten ( Entity entity )
    {
        return entity.getMembers().values( )
                .stream()
                .filter( obj -> obj instanceof AbstractProperty)
                .map( obj -> (AbstractProperty) obj)
                .flatMap( this::flatten );
    }

     private Stream<AbstractProperty> flatten(AbstractProperty property) {
        if (property.getMembers( ) == null || property.getMembers( ).isEmpty( ) )
        {
            return Stream.of(property);
        }

        return Stream.concat(Stream.of( property ),
                property.getMembers().values().stream()
                        .filter( member -> member instanceof AbstractProperty)
                        .map( obj -> (AbstractProperty) obj)
                        .flatMap( this::flatten ) );
    }
}
