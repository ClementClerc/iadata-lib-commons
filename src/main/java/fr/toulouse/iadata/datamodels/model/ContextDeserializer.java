/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cu32980
 */
public class ContextDeserializer extends JsonDeserializer<List<Context> > 
{

    @Override
    public List<Context> deserialize(JsonParser jsonParser, DeserializationContext dc) throws IOException, JsonProcessingException
    {
        
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
