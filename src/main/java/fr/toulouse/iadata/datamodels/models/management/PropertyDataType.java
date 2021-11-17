package fr.toulouse.iadata.datamodels.models.management;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "table_property_data_type")
public class PropertyDataType
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_property_data_type")
    private Long id;
    @Column(name = "type")
    private String type;

}
