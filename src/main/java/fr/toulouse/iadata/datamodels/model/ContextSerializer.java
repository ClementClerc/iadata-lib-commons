/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map.Entry;
import static net.bytebuddy.implementation.FixedValue.value;

/**
 *
 * @author cu32980
 */
public class ContextSerializer extends JsonSerializer<List<Context> > 
{

    @Override
    public void serialize(List<Context> listContext, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException 
    {
        jsonGenerator.writeStartArray();
        for (Context context : listContext )
        {
            if ( context.getUri() != null )
            {
                jsonGenerator.writeString( context.getUri( ).toString( ) );
            }
            if ( context.getMapFieldsUri() != null && context.getMapFieldsUri().size() > 0)
            {
                jsonGenerator.writeStartObject();
                for ( Entry<String,URI> entry : context.getMapFieldsUri().entrySet( ) )
                {
                    jsonGenerator.writeStringField( entry.getKey( ), entry.getValue().toString( ) );
                }
                jsonGenerator.writeEndObject();
            }
        }
        
        jsonGenerator.writeEndArray();
    }

}
