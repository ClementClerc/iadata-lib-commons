package fr.toulouse.iadata.datamodels.models.management;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "table_property_metadata_category")
@NoArgsConstructor
public class PropertyMetadataCategory
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_property_metadata_category")
    private Long id;
    @Column(name = "category")
    private String category;
}