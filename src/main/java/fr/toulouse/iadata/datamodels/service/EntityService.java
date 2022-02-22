package fr.toulouse.iadata.datamodels.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.toulouse.iadata.datamodels.exceptions.AbstractEntityException;
import fr.toulouse.iadata.datamodels.exceptions.MalformedEntityIdException;
import fr.toulouse.iadata.datamodels.exceptions.UnrecognizedEntityMemberException;
import fr.toulouse.iadata.datamodels.models.ngsi.*;

import java.net.URISyntaxException;
import java.util.ArrayList;

import fr.toulouse.iadata.datamodels.utils.EntityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class EntityService
{
    @Autowired( required = false )
    private ObjectMapper objectMapper = new ObjectMapper();


    public void addEntityId(Entity entity, String idValue) throws MalformedEntityIdException{
        try {
            entity.setId(idValue);
        } catch (URISyntaxException e) {
            String errorMessage = String.format("[ENTITYSERVICE] unable set entity Id with value {} is not a valid URI",idValue);
            log.error(errorMessage);
            throw new MalformedEntityIdException(errorMessage,e);
        }
    }

    public void copyEntityMemberFromExisting( Entity entity, String strKeyContainingValue, String strAddedKey ) throws UnrecognizedEntityMemberException
    {
        log.debug("[ENTITYSERVICE] copy entity member from key {} to {}", strKeyContainingValue, strAddedKey);
        log.trace("[ENTITYSERVICE] on entity : \n{}", getPrettyPrint( entity ));
        EntityMember memberToCopy = getEntityMemberByPath( entity, strKeyContainingValue );
        if(memberToCopy == null )
        {
            String message = "member "+strKeyContainingValue+" not found in Entity";
            log.debug("[ENTITYSERVICE] {}",message);
            throw new UnrecognizedEntityMemberException(strKeyContainingValue);
        }

        try
        {
            EntityMember memberCopied = memberToCopy.clone();
            addEntityMember( entity, strAddedKey, memberCopied );
            log.debug("[ENTITYSERVICE] Member successfully copied");
        }
        catch ( CloneNotSupportedException e )
        {
            log.error("[ENTITYSERVICE] unable to clone member {}",memberToCopy.getName(),e);
        }

    }

    public void replaceEntityMemberName( Entity entity, String oldKeyPath, String newKeyPath ) throws UnrecognizedEntityMemberException
    {
        log.debug("[ENTITYSERVICE] Replace entity member from key {} to {}", oldKeyPath, newKeyPath);
        log.trace("[ENTITYSERVICE] on entity : \n{}", getPrettyPrint( entity ));

        try
        {
            EntityMember oldEntityMember = getEntityMemberByPath(entity, oldKeyPath);
            removeEntityMember( entity, oldKeyPath );
            addEntityMember(entity, newKeyPath, oldEntityMember);
            log.debug("[ENTITYSERVICE] Successfully replaces member ");

        }
        catch ( Exception e )
        {
            log.debug("[ENTITYSERVICE] Error while replacing entity member.", e);
            throw e;
        }

    }

    public void removeEntityMember( Entity entity, String keyToRemove ) throws UnrecognizedEntityMemberException
    {
        log.debug("[ENTITYSERVICE] remove entityMember with key : ",keyToRemove);
        log.trace("[ENTITYSERVICE] on entity : \n{}", getPrettyPrint( entity ));
        String [] pathToRemove = keyToRemove.split("[.]");
        if ( pathToRemove.length == 1 )
        {
            entity.getMembers( ).remove( keyToRemove );
        }
        else
        {
            String name = pathToRemove[ pathToRemove.length - 1];
            String [] parentPath = Arrays.copyOfRange( pathToRemove, 0, pathToRemove.length - 1);
            EntityMember parentEntity = getEntityMemberByPath( entity, String.join(".", parentPath));
            parentEntity.getMembers().remove( name );
        }
    }


    public EntityMember getEntityMemberByPath( Entity entity, String strPath ) throws UnrecognizedEntityMemberException {
        log.debug("[ENTITYSERVICE] get entityMember with key : ",strPath);
        log.trace("[ENTITYSERVICE] on entity : \n{}", getPrettyPrint( entity ));
        String[] keyPath = strPath.split("[.]");
        EntityMember memberReturn = null;
        if ((keyPath.length == 1))
        {
            if ( entity.getMembers() != null )
            {
                EntityMember entityMember = entity.getMembers().get( keyPath[0]);
                if ( entityMember != null )
                {
                    log.debug("[ENTITYSERVICE] Found EntityMember for key : {}", keyPath[0]);
                    log.trace("[ENTITYSERVICE] Member found : {}",getPrettyPrint(entityMember));
                    return entityMember;
                }
            }
            log.debug("[ENTITYMEMBER] key : {} not found in entity",keyPath[0]);
            throw new UnrecognizedEntityMemberException( strPath );
        }
        else
        {
            EntityMember member = entity.getMembers().get( keyPath[0] );
            for (int i=1;i< keyPath.length; i++){
                if ( member != null )
                {
                    log.debug("[ENTITYSERVICE] Found EntityMember for key : {}", keyPath[0]);
                    log.trace("[ENTITYSERVICE] Member found : {}",getPrettyPrint(member));
                    if ( member.getMembers() != null && member.getMembers().containsKey( keyPath[i]))
                    {
                        member= member.getMembers().get( keyPath[i] );
                    }
                    else
                    {
                        member = null;
                        break;
                    }
                }

            }
            memberReturn = member;
        }
        if (memberReturn == null)
        {
            log.debug("[ENTITYSERVICE] key {} not found in entity", strPath);
            throw new UnrecognizedEntityMemberException(strPath);
        }
        return memberReturn;
    }

    public <T extends AbstractProperty> T getPropertyMemberByPath( Entity entity, String strPath ) throws UnrecognizedEntityMemberException
    {
        log.debug("[ENTITYSERVICE] {}","get member by key : "+ strPath);
        EntityMember member = getEntityMemberByPath( entity, strPath );
        if( member == null)
        {
            throw new UnrecognizedEntityMemberException(strPath);
        }
        else
        {
            if ( member instanceof AbstractProperty )
            {
                return (T) member;
            }
        }
        return null;
    }

    public Relationship getRelationshipMemberByPath(Entity entity, String strPath ) throws UnrecognizedEntityMemberException
    {
        log.debug("[ENTITYSERVICE] get relationship with key : ",strPath);
        log.trace("[ENTITYSERVICE] on entity : \n{}", getPrettyPrint( entity ));
        EntityMember member = getEntityMemberByPath( entity, strPath );
        if( member == null)
        {
            log.error("[ENTITYSERVICE] No relationship found at path : {}", strPath);
            throw new UnrecognizedEntityMemberException(strPath);
        }
        else
        {
            if ( member instanceof Relationship )
            {
                log.debug("[ENTITYSERVICE] Relation found at path : {}", strPath);
                log.trace("[ENTITYSERVICE] Relationship : \n{}", getPrettyPrint( member ));
                return (Relationship)member;
            }
        }
        return null;
    }

    public List<String> getPathLeaves(Entity entity )
    {
        log.debug("[ENTITYSERVICE] Get path leaves");
        log.trace("[ENTITYSERVICE] on entity : \n{}", getPrettyPrint( entity ));
        Map<String,EntityMember> listMember = entity.getMembers();
        if ( listMember != null )
        {
            List<String> pathList = new ArrayList<>();
            for ( EntityMember member : listMember.values() )
            {
                List<String> paths = new ArrayList<>();
                String newKey=member.getName();
                List<String> recursiveResult = fetchMemberRecursive (member, paths, newKey );
                if (recursiveResult.size() != 0){
                    pathList.addAll( recursiveResult );
                }
            }
            log.debug( "[ENTITYSERVICE] Path leaves : " );
            for ( String path : pathList )
            {
                log.debug( "[ENTITYSERVICE] Path :  {} ", path );
            }
            return pathList;
        }
        return null;
    }


    private List<String> fetchMemberRecursive ( EntityMember entityMember, List<String> paths,String oldKey )
    {
        Map<String,EntityMember> memberList  =  entityMember.getMembers();
        paths.add(oldKey);
        if (memberList != null) {
            for (EntityMember member : memberList.values()) {
                String newkey = oldKey + "." + member.getName();
                paths.remove(oldKey);
                if ((member.getMembers() != null) && member.getMembers().size() != 0) {
                    fetchMemberRecursive (member, paths, newkey);
                }else
                    {
                    paths.add(newkey);
                }
            }
        }
        return paths;
    }



    public void addEntityMember( Entity entity, String path, EntityMember member)
    {
        log.debug("[ENTITYSERVICE] Add entity member on path {}", path );
        log.debug("[ENTITYSERVICE] Member : \n{}", getPrettyPrint( member ) );
        log.trace("[ENTITYSERVICE] on entity : \n{}", getPrettyPrint( entity ));
        //Case 1 : check if member is under the root of the JSON
        if (StringUtils.countOccurrencesOf( path, ".") == 0 )
        {
            log.debug("[ENTITYSERVICE] Member is detected on the JSON root" );
            member.setName( path );
            entity.getMembers().put( member.getName( ), member );
        }
        //Member is nested
        else
            {
            String[] tabPath = path.split("[.]");
            EntityMember childEntity = null;
            EntityMember currentParentMember = entity.getMembers().get(tabPath[0]);
            if ( currentParentMember == null )
            {
                log.debug("[ENTITYSERVICE] Member is detected on a nested JSON node" );
                entity.addMember( new Property().builder().name(tabPath[0]).build());
                currentParentMember = entity.getMembers().get(tabPath[0]);
            }
            for (int i = 1; i < tabPath.length; i++)
            {
                if ( i == tabPath.length -1 )
                {
                    member.setName( tabPath[ tabPath.length -1 ] );
                    log.debug("[ENTITYSERVICE] Member is detected on a nested JSON node" );
                    currentParentMember.addMember( member );
                    break;
                }
                else
                {
                    childEntity = currentParentMember.getMembers().get(tabPath[i]);
                    if ( childEntity == null ) {
                        childEntity = new Property().builder().name(tabPath[i]).build();
                    }
                    log.debug("[ENTITYSERVICE] Member is detected on a nested JSON node" );
                    currentParentMember.addMember(childEntity);
                    currentParentMember = childEntity;
                }
            }
        }
    }





    public String convertEntityToJsonString( Entity entity) throws JsonProcessingException
    {
        log.debug("[ENTITYSERVICE] convert entity to json" );

        String json = objectMapper.writeValueAsString(entity);

        log.debug( "[ENTITYSERVICE] Entity as string is : \n{}", entity);

        return json;
    }

    public Entity convertJsonToEntity( String strData ) throws JsonProcessingException
    {
        log.debug("[ENTITYSERVICE] convert json to entity" );
        log.debug("[ENTITYSERVICE] data are : {}", strData );

        return objectMapper
                .readerFor(Entity.class)
                .readValue(strData);
    }
    
    public void entityLogErrorAdder (AbstractEntityException e, Entity entity) {
        
        try {
            log.trace( "[ENTITYSERVICE] Add error field to existing filter field");
            Property property = getPropertyMemberByPath(entity,EntityConstants.KAFKA_STREAM_ERRORS);
            List<String> errors = (List<String>)property.getValue();
            errors.add( e.getMessage() ) ;
            property.setValue(errors);
            log.trace( "[ENTITYSERVICE] New error field is {}", getPrettyPrint( property ));


        } catch (UnrecognizedEntityMemberException ex)
        {
            log.trace( "[ENTITYSERVICE] Error field doesn't exist. A new error field will be added");
            List<String> errorList = new ArrayList();
            errorList.add(e.getMessage( ) );
            Property propertyError = Property.builder()
                    .value(errorList)
                    .build();
            addEntityMember(entity,EntityConstants.KAFKA_STREAM_ERRORS,propertyError);
            log.trace( "[ENTITYSERVICE] New error field is {}", getPrettyPrint( propertyError ));
        }
        
    }

    public void entityLogFilteredAdder(AbstractEntityException e, Entity entity)
    {
        try
        {
            log.trace( "[ENTITYSERVICE] Add filter field to existing filter field");
            Property property = getPropertyMemberByPath(entity, EntityConstants.KAFKA_STREAM_FILTERED);
            List<String> filtered = (List<String>)property.getValue();
            filtered.add( e.getMessage() ) ;
            property.setValue(filtered);
            log.trace( "[ENTITYSERVICE] New filter field is {}", getPrettyPrint( property ));

        }
        catch (UnrecognizedEntityMemberException ex)
        {
            log.trace( "[ENTITYSERVICE] Filter field doesn't exist. A new filtered field will be added");
            List<String> filteredList = new ArrayList();
            filteredList.add(e.getMessage( ) );
            Property propertyFiltered = Property.builder()
                    .value(filteredList)
                    .build();
            addEntityMember(entity,EntityConstants.KAFKA_STREAM_FILTERED,propertyFiltered);
            log.trace( "[ENTITYSERVICE] New error field is {}", getPrettyPrint( propertyFiltered ));
        }
    }

    public String getPrettyPrint( Object object )
    {
        try
        {
            if ( object != null )
            {
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString( object );
            }
            else
            {
                return "NULL";
            }
        }
        catch ( JsonProcessingException e )
        {
            log.error( "[LOG] Unable to write object value as string", e);
            return "[error while writing object as string]";
        }
    }
}
