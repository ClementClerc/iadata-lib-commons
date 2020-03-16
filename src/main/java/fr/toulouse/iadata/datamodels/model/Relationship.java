/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.net.URI;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors( prefix = {"_"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Relationship extends Member
{
    private static final String TYPE = "Relationship";
    
    private URI _object;
    
    @Override
    public void setType() {
        _type = TYPE;
    }
}
