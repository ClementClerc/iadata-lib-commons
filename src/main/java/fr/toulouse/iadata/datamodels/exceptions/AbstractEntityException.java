/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.exceptions;

import fr.toulouse.iadata.datamodels.models.ngsi.Entity;
import fr.toulouse.iadata.datamodels.models.ngsi.EntityMember;
import fr.toulouse.iadata.datamodels.models.ngsi.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author loulo
 */
@Data
@NoArgsConstructor
public abstract class AbstractEntityException extends RuntimeException {

        protected Entity entity;

        public AbstractEntityException(Entity entity, String errorMessage, Throwable exception){
               super(errorMessage, exception);
               this.entity=entity;
        }

        public AbstractEntityException(Entity entity, String errorMessage){
                super(errorMessage);
                this.entity=entity;
        }

        public AbstractEntityException( String errorMessage ){
                super(errorMessage);
        }

}
