/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.serde;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fr.toulouse.iadata.datamodels.models.ngsi.Context;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map.Entry;
import static net.bytebuddy.implementation.FixedValue.value;

/**
 * Serializer for NGSI Context obj
 * This serializer handles multiples types of contexts; array of simple strings, array of JSON objects, simple string and JSON object.
 */
public class ContextSerializer extends JsonSerializer<List<Context> >
{
    @Override
    public void serialize(List<Context> listContext, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
    {
        if ( listContext.size() > 1 )
        {
            jsonGenerator.writeStartArray();
        }
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
        if ( listContext.size() > 1 )
        {
            jsonGenerator.writeEndArray();
        }
    }
}
