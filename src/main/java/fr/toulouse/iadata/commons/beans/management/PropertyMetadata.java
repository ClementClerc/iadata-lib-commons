package fr.toulouse.iadata.commons.beans.management;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "table_property_metadata")
public class PropertyMetadata
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_property_metadata")
    private Long id;
    @Column(name = "name")
    private String key;
    @Column(name = "label")
    private String label;
    @Column(name = "categorical")
    private Boolean categorical;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="id_property_metadata", nullable=false)
    @RestResource(exported = false)
    private List<PropertyMetadataCategory> categories;
    @ManyToOne
    @JoinColumn(name="id_property_data_type", nullable=false)
    @RestResource(exported = false)
    private PropertyDataType propertyDataType;
    @Transient
    private List<String> linkedPipelines = new ArrayList<>();

    public void addLinkedPipelines( String pipelineName )
    {
        linkedPipelines.add( pipelineName );
    }


}
