package fr.toulouse.iadata.datamodels.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.toulouse.iadata.datamodels.models.ngsi.AbstractProperty;
import fr.toulouse.iadata.datamodels.models.ngsi.Entity;
import fr.toulouse.iadata.datamodels.models.ngsi.EntityMember;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Component
public class EntityService
{
    private ObjectMapper _objectMapper = new ObjectMapper( );

    public void copyEntityMemberFromExisting( Entity entity, String strKeyContainingValue, String strAddedKey )

    {
        EntityMember memberToCopy = getEntityMemberByPath( entity, strKeyContainingValue);
        try
        {
            EntityMember memberCopied = memberToCopy.clone();
            String strNewPath[] = strAddedKey.split("[.]");
            String[] parentPath = Arrays.copyOf( strNewPath, strNewPath.length - 1);
            String strNewName = strNewPath[ strNewPath.length - 1];
            memberCopied.setName( strNewName );
            addEntityMember( entity, parentPath, memberCopied );
        }
        catch ( CloneNotSupportedException e )
        {

        }
    }
    
//    public void copyEntityMemberFromExisting( Entity entity, String strKeyContainingValue, String strAddedKey )
//    {
//        String[] strKeyContainingValuePath = strKeyContainingValue.split("[.]");
//
//        String[] strAddedKeyPath= strAddedKey.split("[.]");
//        if ((strKeyContainingValuePath.length == 1) || (!strKeyContainingValuePath[0].equals(strAddedKeyPath[0]))){
//
//            entity.getMembers().get( strKeyContainingValuePath[0]);
//            entity.setMember( strAddedKeyPath[0],entity.getMembers( ).get( strKeyContainingValuePath[0] ));
//            
//            strKeyContainingValuePath[0]=strAddedKeyPath[0];
//        }
//            EntityMember member = entity.getMembers().get( strKeyContainingValuePath[0] );
//            EntityMember newMember = entity.getMembers().get( strKeyContainingValuePath[0] );
//
//            for (int i=1;i< strKeyContainingValuePath.length; i++){
//                if ( member.getMembers().containsKey( strKeyContainingValuePath[i]) && !strKeyContainingValuePath[i].equals(strAddedKeyPath[i])){
//
//                    
//                    
//
//                    newMember.addMember(member.getMembers( ).get( strKeyContainingValuePath[i] ));
//                    newMember.setName(strAddedKeyPath[i]);
//                    
//
//                    newMember=member.getMembers().get(strAddedKeyPath[i]);
//                }else{
//                    newMember=member.getMembers().get(strKeyContainingValuePath[i]);
//                }
//                member=member.getMembers().get(strKeyContainingValuePath[i]);
//                
//            
//            }
//    }

    public <T extends EntityMember> T getEntityMemberByPath( Entity entity, String strPath, Class<T> className )

    {
        EntityMember member = getEntityMemberByPath( entity, strPath );

        if ( className.isInstance( member ) )
        {
            return (T)member;
        }
        return null;
    }


        

    public void replaceEntityMemberName( Entity entity, String oldKey, String newKey )
    {
        String[] oldKeyPath = oldKey.split("[.]");
        String[] newKeyPath= newKey.split("[.]");
        if ((oldKeyPath.length == 1) || (!oldKeyPath[0].equals(newKeyPath[0]))){
            entity.getMembers().get( oldKeyPath[0]).setName(newKeyPath[0] );
            entity.getMembers().put( newKeyPath[0],entity.getMembers( ).get( oldKeyPath[0] ));
            entity.getMembers().remove(oldKeyPath[0]);
            oldKeyPath[0]=newKeyPath[0];
        }
            EntityMember member = entity.getMembers().get( oldKeyPath[0] );
            for (int i=1;i< oldKeyPath.length; i++){
                if ( member.getMembers().containsKey( oldKeyPath[i]) && !oldKeyPath[i].equals(newKeyPath[i])){

                    member.getMembers().get(oldKeyPath[i]).setName(newKeyPath[i]);
                    member.getMembers().put(newKeyPath[i],member.getMembers( ).get( oldKeyPath[i] ));
                    member.getMembers().remove(oldKeyPath[i]);
                }
                member=member.getMembers().get(newKeyPath[i]);
            }
    }



    public void addEntityMember( Entity entity, String[] parentPath, EntityMember member)
    {
        if ( parentPath.length == 0 )
        {
            entity.addMember( member );
        }
        else
        {
            EntityMember currentMember = entity.getMember( parentPath[0]);
            for ( int i = 1; i < parentPath.length ; i++)
            {
                currentMember = currentMember.getMember( parentPath[i]);
            }
            currentMember.addMember( member );
        }
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

    private EntityMember getEntityMemberByPath( Entity entity, String strPath )
    {
        String[] keyPath = strPath.split("[.]");
        if ((keyPath.length == 1))
        {
            return entity.getMembers().get( keyPath[0]);
        }
        else
        {
            EntityMember member = entity.getMembers().get( keyPath[0] );
            for (int i=1;i< keyPath.length; i++){
                if ( member.getMembers().containsKey( keyPath[i])){

                    member= member.getMembers().get( keyPath[i] );
                }
                else
                {
                    return null;
                }
            }
            return member;
        }

    }


    public String convertEntityToJsonString( Entity entity) throws JsonProcessingException
    {
        return _objectMapper.writeValueAsString(entity);
    }

    public Entity convertJsonToEntity( String strData ) throws JsonProcessingException
    {
        return _objectMapper
                .readerFor(Entity.class)
                .readValue(strData);
    }
}