package fr.toulouse.iadata.datamodels.models.remapping;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.Data;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to shape api data results based on a SearchPage
 *
 * @author Théophile Orliac
 */
@Value
@Slf4j
public class ApiDataResult {

    int pageNumber;
    int size;
    int totalPages;
    int totalElements;
    long totalHits;


    //Results (searchHits) of the request
    ArrayList<Object> searchHits = new ArrayList<>();

    @JsonSerialize(using = AggregationsSerializer.class)
    AggregationsShell aggregations;

    /**
     * Constructor to instantiate a ApiDataResult from a SearchPage
     *
     * @param sp the SearchPage used for instantiation
     */
    public ApiDataResult(SearchPage sp) {
        log.debug("[APIDATARESULT] build ApiDataResult object" );
        pageNumber = sp.getNumber();
        size = sp.getSize();
        totalPages = sp.getTotalPages();
        totalElements = (int) sp.getTotalElements();
        totalHits = sp.getSearchHits().getTotalHits();
        List<SearchHit> tempList = sp.getSearchHits().getSearchHits();
        searchHits.addAll(tempList);
        aggregations = new AggregationsShell();
        aggregations.setAggregations( (Aggregations)sp.getSearchHits().getAggregations().aggregations());
    }

    /**
     * Transforms a SearchPage to ApiDataResult
     *
     * @param sp                      the SearchPage to transform
     * @param removeFragment          option to remove fragment = true tags
     * @param removeEmptyAggregations option to remove aggregations with empty asMap
     * @return the JsonNode of the ApiDataResult
     */
    public static JsonNode fromSearchPage(SearchPage sp, boolean removeFragment, boolean removeEmptyAggregations) {
        log.debug("[APIDATARESULT] transform searchPage" );
        ApiDataResult res = new ApiDataResult(sp);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        return mapper.valueToTree(res);
    }

    /**
     * Transforms a SearchPage to ApiDataResult with default options
     *
     * @param sp the SearchPage to transform
     * @return the JsonNode of the ApiDataResult
     */
    public static JsonNode fromSearchPage(SearchPage sp) {
        return fromSearchPage(sp, true, true);
    }
}

/**
 * Custom serializer for aggregations
 */
@Slf4j
class AggregationsSerializer extends StdSerializer<AggregationsShell> {
    public AggregationsSerializer() {
        this(null);
    }

    public AggregationsSerializer(Class<AggregationsShell> t) {
        super(t);
    }

    @Override
    public void serialize(AggregationsShell agrShell, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        log.debug("[APIDATARESULT] Serialize aggregation" );

        Aggregations agrs = agrShell.getAggregations();
        if(agrs==null) {
            log.debug("[APIDATARESULT] aggregation is null" );
            return;
        }
        jsonGenerator.writeStartObject();
        for (Aggregation agr : agrs) {
            jsonGenerator.writeObjectFieldStart(agr.getName());
            jsonGenerator.writeStringField("type", agr.getType());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode agrJson = mapper.valueToTree(agr);
            JsonNode buckets = agrJson.at("/buckets");
            if(!buckets.isEmpty()) {
                if (agrShell.isRemoveEmptyAggregations() || agrShell.isRemoveFragment()) {
                    for (JsonNode jn : buckets) {
                        if (jn.get("aggregations").get("asMap").isEmpty()) {
                            ObjectNode on = (ObjectNode) jn;
                            if (agrShell.isRemoveEmptyAggregations())
                                on.remove("aggregations");
                            if (agrShell.isRemoveFragment())
                                on.remove("fragment");
                        }

                    }
                }

                jsonGenerator.writeObjectField("buckets", buckets);
            }
            else{
                jsonGenerator.writeObjectField("value",agrJson.get("value"));

            }
            jsonGenerator.writeEndObject();

        }
        jsonGenerator.writeEndObject();
    }
}

/**
 * Helper Class to pass options to the serializer
 */
@Data
class AggregationsShell {
    Aggregations aggregations;
    boolean removeFragment = true;
    boolean removeEmptyAggregations = true;
}