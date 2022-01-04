package fr.toulouse.iadata.datamodels.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.toulouse.iadata.datamodels.exceptions.AbstractEntityException;
import fr.toulouse.iadata.datamodels.exceptions.UnrecognizedEntityMemberException;
import fr.toulouse.iadata.datamodels.models.ngsi.*;

import java.util.ArrayList;

import fr.toulouse.iadata.datamodels.utils.EntityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class EntityService
{
    private static final Logger logger = LoggerFactory.getLogger(EntityService.class);

    private ObjectMapper objectMapper = new ObjectMapper( );

    @Autowired
    private LogService logService;

    public void copyEntityMemberFromExisting( Entity entity, String strKeyContainingValue, String strAddedKey ) throws UnrecognizedEntityMemberException
    {
        log.debug("[ENTITYSERVICE] copy entity member");
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
            String strNewPath[] = strAddedKey.split("[.]");
            addEntityMember( entity, strAddedKey, memberCopied );
        }
        catch ( CloneNotSupportedException e )
        {
            log.error("[ENTITYSERVICE] unable to clone member {}",memberToCopy.getName());
        }

    }

    public void replaceEntityMemberName( Entity entity, String oldKeyPath, String newKeyPath ) throws UnrecognizedEntityMemberException
    {
        EntityMember oldEntityMember = getEntityMemberByPath(entity, oldKeyPath);
        removeEntityMember( entity, oldKeyPath );
        addEntityMember(entity, newKeyPath, oldEntityMember);
    }

    public void removeEntityMember( Entity entity, String keyToRemove ) throws UnrecognizedEntityMemberException
    {
        String message = "remove EntityMember with key : "+keyToRemove;
        log.debug("[ENTITYSERVICE] {}",message);
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
        String[] keyPath = strPath.split("[.]");
        EntityMember memberReturn = null;
        if ((keyPath.length == 1))
        {
            if ( entity.getMembers() != null )
            {
                EntityMember entityMember = entity.getMembers().get( keyPath[0]);
                if ( entityMember != null )
                {
                    String message = "Found EntityMember for key : "+keyPath[0];
                    log.debug("[ENTITYSERVICE] {}",message);
                    log.trace("[ENTITYSERVICE] {}",logService.getPrettyPrint(entity));
                    return entityMember;
                }
            }
            String message = "key : "+keyPath[0]+" not found in entity";
            log.debug("[ENTITYMEMBER] {}",message);
            log.trace("[ENTITYMEMBER] {}",logService.getPrettyPrint(entity));
            throw new UnrecognizedEntityMemberException(strPath );
        }
        else
        {
            EntityMember member = entity.getMembers().get( keyPath[0] );
            for (int i=1;i< keyPath.length; i++){
                if ( member != null )
                {
                    if ( member.getMembers() != null && member.getMembers().containsKey( keyPath[i])){
                        String message = "Found EntityMember for key : "+keyPath[i];
                        log.debug("[ENTITYMEMBER] {}",message);
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
            String message = "key : "+strPath+" not found in entity";
            log.debug("[ENTITYSERVICE] {}",message);
            log.trace("[ENTITYSERVICE] {}",logService.getPrettyPrint(entity));
            throw new UnrecognizedEntityMemberException(strPath);
        }
        return memberReturn;
    }

    public <T extends AbstractProperty> T getPropertyMemberByPath( Entity entity, String strPath ) throws UnrecognizedEntityMemberException

    {
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
        EntityMember member = getEntityMemberByPath( entity, strPath );
        if( member == null)
        {
            throw new UnrecognizedEntityMemberException(strPath);
        }
        else
        {
            if ( member instanceof Relationship )
            {
                return (Relationship)member;
            }
        }
        return null;
    }

    public List<String> getPathLeaves(Entity entity )
    {
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
        log.debug("[ENTITYSERVICE] add member to entity with key : {}", path );
        //Case 1 : check if member is under the root of the JSON
        if (StringUtils.countOccurrencesOf( path, ".") == 0 )
        {
            member.setName( path );
            entity.getMembers().put( member.getName( ), member );
        }
        //Member is nested
        else {
            String[] tabPath = path.split("[.]");
            EntityMember childEntity = null;
            EntityMember currentParentMember = entity.getMembers().get(tabPath[0]);
            if ( currentParentMember == null )
            {
                entity.addMember( new Property().builder().name(tabPath[0]).build());
                currentParentMember = entity.getMembers().get(tabPath[0]);
            }
            for (int i = 1; i < tabPath.length; i++)
            {
                if ( i == tabPath.length -1 )
                {
                    member.setName( tabPath[ tabPath.length -1 ] );
                    currentParentMember.addMember( member );
                    break;
                }
                else
                {
                    childEntity = currentParentMember.getMembers().get(tabPath[i]);
                    if ( childEntity == null ) {
                        childEntity = new Property().builder().name(tabPath[i]).build();
                    }
                    currentParentMember.addMember(childEntity);
                    currentParentMember = childEntity;
                }
            }
        }
    }





    public String convertEntityToJsonString( Entity entity) throws JsonProcessingException
    {
        log.debug("[ENTITYSERVICE] convert entity to json" );

        return objectMapper.writeValueAsString(entity);
    }

    public Entity convertJsonToEntity( String strData ) throws JsonProcessingException
    {
        log.debug("[ENTITYSERVICE] convert json to entity" );

        return objectMapper
                .readerFor(Entity.class)
                .readValue(strData);
    }
    
    public void entityLogErrorAdder (AbstractEntityException e, Entity entity) {
        
        logger.error( e.getMessage());
        try {
            Property property = getPropertyMemberByPath(entity,EntityConstants.KAFKA_STREAM_ERRORS);
            List<String> errors = (List<String>)property.getValue();
            errors.add( e.getMessage() ) ;
            property.setValue(errors);
            
        } catch (UnrecognizedEntityMemberException ex) {
            List<String> errorList = new ArrayList();
            errorList.add(e.getMessage( ) );
            addEntityMember(entity,EntityConstants.KAFKA_STREAM_ERRORS,Property.builder()
                                        .value(errorList)
                                        .build());
        }
        
    }

    public void entityLogFilteredAdder(AbstractEntityException e, Entity entity)
    {
        logger.debug( e.getMessage());
        try {
            Property property = getPropertyMemberByPath(entity, EntityConstants.KAFKA_STREAM_FILTERED);
            List<String> errors = (List<String>)property.getValue();
            errors.add( e.getMessage() ) ;
            property.setValue(errors);

        } catch (UnrecognizedEntityMemberException ex) {
            List<String> errorList = new ArrayList();
            errorList.add(e.getMessage( ) );
            addEntityMember(entity,EntityConstants.KAFKA_STREAM_FILTERED,Property.builder()
                    .value(errorList)
                    .build());
        }
    }
    
    
}
