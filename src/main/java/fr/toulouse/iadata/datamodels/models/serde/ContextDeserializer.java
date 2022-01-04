/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.serde;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fr.toulouse.iadata.datamodels.models.ngsi.Context;
import fr.toulouse.iadata.datamodels.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Deserializer for NGSI Context obj
 * This deserializer handles multiples types of contexts; array of simple strings, array of JSON objects, simple string and JSON object.
 */
@Slf4j
public class ContextDeserializer extends JsonDeserializer<List<Context> >
{

    @Autowired
    private LogService logService;

    @Override
    public List<Context> deserialize(JsonParser jsonParser, DeserializationContext dc) throws IOException, JsonProcessingException
    {
        log.debug("[CONTEXT] deserialize context from entity");
        
        List<Context> listContext = new ArrayList<>();
        JsonNode node = jsonParser.getCodec().readTree( jsonParser );
        
        if ( node.isArray( ) )
        {
            ArrayNode array = (ArrayNode)node;
            for (JsonNode jsonNode : array ) 
            {
                Context context = new Context();
                if ( jsonNode.isValueNode() )
                {
                    try
                    {
                        context.setUri(new URI( jsonNode.textValue( ) ) );
                    }
                    catch ( URISyntaxException e )
                    {

                        String message = "Object in Context is not an URI: "+jsonNode.textValue();
                        log.debug("[ENTITYMEMBER] {}",message);
                        log.trace("[ENTITYMEMBER] {}",logService.getPrettyPrint(e.getStackTrace()));
                    }


                }
                else
                {
                    Map<String,URI> mapFieldsContext = new HashMap<>();

                    Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.fields();
                    while (iter.hasNext()) {
                        Map.Entry<String, JsonNode> entry = iter.next();
                        try
                        {
                            mapFieldsContext.put( entry.getKey(), new URI( entry.getValue().textValue( ) ) );
                        }
                        catch ( URISyntaxException e )
                        {
                            String message = "Objects in Context are not all URI: "+jsonNode.textValue();
                            log.debug("[ENTITYMEMBER] {}",message);
                            log.trace("[ENTITYMEMBER] {}",logService.getPrettyPrint(e.getStackTrace()));
                        }
                    }
                    context.setMapFieldsUri( mapFieldsContext );
                }
                listContext.add( context );
            }
        }
       
        
        return listContext;
        
    }
    
}
