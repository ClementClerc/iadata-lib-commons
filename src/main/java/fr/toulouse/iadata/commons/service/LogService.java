package fr.toulouse.iadata.commons.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogService
{
    private ObjectMapper objectMapper = new ObjectMapper();

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

    public void setObjectMapper( ObjectMapper objectMapper )
    {
        this.objectMapper = objectMapper;
    }
}
