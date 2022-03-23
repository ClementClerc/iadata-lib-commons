package fr.toulouse.iadata.commons.beans.management.usecase;

import fr.toulouse.iadata.commons.beans.management.Application;
import fr.toulouse.iadata.commons.beans.management.LinkedPipeline;
import fr.toulouse.iadata.commons.beans.management.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;

@Data
@Document( indexName = "iadata-conf-usecases")
public class UseCase
{
    @Id
    private String id;
    private String title;
    private String description;
    private String category;
    private String technicalLabel;
    private String allowReading;
    private String allowUpdating;
    @Field(type = FieldType.Nested)
    private Application mainPipeline;
    @Field(type = FieldType.Nested)
    private List<LinkedPipeline> listLinkedPipelines = new ArrayList<>();
    private String enrichProcessId;
    private EnrichStatus enrichStatus;
}