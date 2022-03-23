package fr.toulouse.iadata.commons.beans.management;

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