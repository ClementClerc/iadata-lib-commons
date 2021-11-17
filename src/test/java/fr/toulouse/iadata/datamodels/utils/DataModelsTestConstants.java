package fr.toulouse.iadata.datamodels.utils;

public class DataModelsTestConstants
{
    public final static String NGSI_PAYLOAD_SPEC_1_COPIED_TOTAL_SPOT_NUMBER = "{" +
        " \"id\": \"urn:ngsi-ld:OffStreetParking:Downtown1\"," +
        " \"type\": \"OffStreetParking\"," +
        " \"name\": {" +
        " \"type\": \"Property\"," +
        " \"value\": \"Downtown One\"" +
        " }," +
        " \"availableSpotNumber\": {" +
        " \"type\": \"Property\"," +
        " \"value\": 121," +
        " \"observedAt\": \"2017-07-29T12:05:02Z\"," +
        " \"reliability\": {" +
        " \"type\": \"Property\"," +
        " \"value\": 0.7,\n" +
        " \"trueReliability\": {\n" +
        "        \"value\": \"0.3\",\n" +
        "        \"type\": \"Property\"\n" +
        "      }\n" +
        " }," +
        " \"providedBy\": {" +
        " \"type\": \"Relationship\"," +
        " \"object\": \"urn:ngsi-ld:Camera:C1\"" +
        " }" +
        " }," +
        " \"totalSpotNumber\": {" +
        " \"type\": \"Property\"," +
        " \"value\": 200" +
        " }," +
        " \"totalSpotNumber2\": {" +
        " \"type\": \"Property\"," +
        " \"value\": 200" +
        " }," +
        " \"location\": {" +
        " \"type\": \"GeoProperty\"," +
        " \"value\": {" +
        " \"type\": \"Point\"," +
        " \"coordinates\": [-8.5,4.0]" +
        " }" +
        " }," +
        " \"@context\": [" +
        " \"http://example.org/ngsi-ld/latest/parking.jsonld\"," +
        " \"https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\"" +
        " ]" +
        "} ";

    public final static String NGSI_PAYLOAD_SPEC_1_REPLACED_TOTAL_SPOT_NUMBER = "{" +
            " \"id\": \"urn:ngsi-ld:OffStreetParking:Downtown1\"," +
            " \"type\": \"OffStreetParking\"," +
            " \"name\": {" +
            " \"type\": \"Property\"," +
            " \"value\": \"Downtown One\"" +
            " }," +
            " \"availableSpotNumber\": {" +
            " \"type\": \"Property\"," +
            " \"value\": 121," +
            " \"observedAt\": \"2017-07-29T12:05:02Z\"," +
            " \"reliability\": {" +
            " \"type\": \"Property\"," +
            " \"value\": 0.7,\n" +
            " \"trueReliability\": {\n" +
            "        \"value\": \"0.3\",\n" +
            "        \"type\": \"Property\"\n" +
            "      }\n" +
            " }," +
            " \"providedBy\": {" +
            " \"type\": \"Relationship\"," +
            " \"object\": \"urn:ngsi-ld:Camera:C1\"" +
            " }" +
            " }," +
            " \"totalSpotNumber2\": {" +
            " \"type\": \"Property\"," +
            " \"value\": 200" +
            " }," +
            " \"location\": {" +
            " \"type\": \"GeoProperty\"," +
            " \"value\": {" +
            " \"type\": \"Point\"," +
            " \"coordinates\": [-8.5,4.0]" +
            " }" +
            " }," +
            " \"@context\": [" +
            " \"http://example.org/ngsi-ld/latest/parking.jsonld\"," +
            " \"https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\"" +
            " ]" +
            "} ";

    public final static String NGSI_PAYLOAD_SPEC_1_REPLACED_PROVIDED_BY = "{" +
            " \"id\": \"urn:ngsi-ld:OffStreetParking:Downtown1\"," +
            " \"type\": \"OffStreetParking\"," +
            " \"name\": {" +
            " \"type\": \"Property\"," +
            " \"value\": \"Downtown One\"" +
            " }," +
            " \"availableSpotNumber\": {" +
            " \"type\": \"Property\"," +
            " \"value\": 121," +
            " \"observedAt\": \"2017-07-29T12:05:02Z\"," +
            " \"reliability\": {" +
            " \"type\": \"Property\"," +
            " \"value\": 0.7,\n" +
            " \"trueReliability\": {\n" +
            "        \"value\": \"0.3\",\n" +
            "        \"type\": \"Property\"\n" +
            "      }\n" +
            " }," +
            " \"providedBy2\": {" +
            " \"type\": \"Relationship\"," +
            " \"object\": \"urn:ngsi-ld:Camera:C1\"" +
            " }" +
            " }," +
            " \"totalSpotNumber\": {" +
            " \"type\": \"Property\"," +
            " \"value\": 200" +
            " }," +
            " \"location\": {" +
            " \"type\": \"GeoProperty\"," +
            " \"value\": {" +
            " \"type\": \"Point\"," +
            " \"coordinates\": [-8.5,4.0]" +
            " }" +
            " }," +
            " \"@context\": [" +
            " \"http://example.org/ngsi-ld/latest/parking.jsonld\"," +
            " \"https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\"" +
            " ]" +
            "} ";

    public final static String NGSI_PAYLOAD_SPEC_1_REPLACED_NEW_ROOT_PROVIDED_BY = "{" +
            " \"id\": \"urn:ngsi-ld:OffStreetParking:Downtown1\"," +
            " \"type\": \"OffStreetParking\"," +
            " \"name\": {" +
            " \"type\": \"Property\"," +
            " \"value\": \"Downtown One\"" +
            " }," +
            " \"availableSpotNumber\": {" +
            " \"type\": \"Property\"," +
            " \"value\": 121," +
            " \"observedAt\": \"2017-07-29T12:05:02Z\"," +
            " \"reliability\": {" +
            " \"type\": \"Property\"," +
            " \"value\": 0.7,\n" +
            " \"trueReliability\": {\n" +
            "        \"value\": \"0.3\",\n" +
            "        \"type\": \"Property\"\n" +
            "      }\n" +
            " }" +
            " }," +
            " \"newRoot\": {" +
            " \"type\": \"Property\"," +
            " \"providedBy\": {" +
            " \"type\": \"Relationship\"," +
            " \"object\": \"urn:ngsi-ld:Camera:C1\"" +
            " }" +
            " }," +
            " \"totalSpotNumber\": {" +
            " \"type\": \"Property\"," +
            " \"value\": 200" +
            " }," +
            " \"location\": {" +
            " \"type\": \"GeoProperty\"," +
            " \"value\": {" +
            " \"type\": \"Point\"," +
            " \"coordinates\": [-8.5,4.0]" +
            " }" +
            " }," +
            " \"@context\": [" +
            " \"http://example.org/ngsi-ld/latest/parking.jsonld\"," +
            " \"https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\"" +
            " ]" +
            "} ";

    public final static String NGSI_PAYLOAD_SPEC_1_ERROR_1 = "{\n" +
            "   \"id\":\"urn:ngsi-ld:OffStreetParking:Downtown1\",\n" +
            "   \"type\":\"OffStreetParking\",\n" +
            "   \"location\":{\n" +
            "      \"type\":\"GeoProperty\",\n" +
            "      \"value\":{\n" +
            "         \"type\":\"Point\",\n" +
            "         \"coordinates\":[\n" +
            "            -8.5,\n" +
            "            4.0\n" +
            "         ]\n" +
            "      }\n" +
            "   },\n" +
            "   \"@context\":[\n" +
            "      \"http://example.org/ngsi-ld/latest/parking.jsonld\",\n" +
            "      \"https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\"\n" +
            "   ],\n" +
            "   \"availableSpotNumber\":{\n" +
            "      \"observedAt\":\"2017-07-29T12:05:02Z\",\n" +
            "      \"type\":\"Property\",\n" +
            "      \"value\":121,\n" +
            "      \"providedBy\":{\n" +
            "         \"object\":\"urn:ngsi-ld:Camera:C1\",\n" +
            "         \"type\":\"Relationship\"\n" +
            "      },\n" +
            "      \"reliability\":{\n" +
            "         \"type\":\"Property\",\n" +
            "         \"value\":0.7,\n" +
            "         \"trueReliability\":{\n" +
            "            \"type\":\"Property\",\n" +
            "            \"value\":\"0.3\"\n" +
            "         }\n" +
            "      }\n" +
            "   },\n" +
            "   \"name\":{\n" +
            "      \"type\":\"Property\",\n" +
            "      \"value\":\"Downtown One\"\n" +
            "   },\n" +
            "   \"totalSpotNumber\":{\n" +
            "      \"type\":\"Property\",\n" +
            "      \"value\":200\n" +
            "   },\n" +
            "   \"kafkaStreamErrors\":{\n" +
            "      \"type\":\"Property\",\n" +
            "      \"value\":[\n" +
            "         \"ERROR\"\n" +
            "      ]\n" +
            "   }\n" +
            "}";


    public final static String NGSI_PAYLOAD_SPEC_1_ERROR_2 = "{\n" +
            "   \"id\":\"urn:ngsi-ld:OffStreetParking:Downtown1\",\n" +
            "   \"type\":\"OffStreetParking\",\n" +
            "   \"location\":{\n" +
            "      \"type\":\"GeoProperty\",\n" +
            "      \"value\":{\n" +
            "         \"type\":\"Point\",\n" +
            "         \"coordinates\":[\n" +
            "            -8.5,\n" +
            "            4.0\n" +
            "         ]\n" +
            "      }\n" +
            "   },\n" +
            "   \"@context\":[\n" +
            "      \"http://example.org/ngsi-ld/latest/parking.jsonld\",\n" +
            "      \"https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\"\n" +
            "   ],\n" +
            "   \"availableSpotNumber\":{\n" +
            "      \"observedAt\":\"2017-07-29T12:05:02Z\",\n" +
            "      \"type\":\"Property\",\n" +
            "      \"value\":121,\n" +
            "      \"providedBy\":{\n" +
            "         \"object\":\"urn:ngsi-ld:Camera:C1\",\n" +
            "         \"type\":\"Relationship\"\n" +
            "      },\n" +
            "      \"reliability\":{\n" +
            "         \"type\":\"Property\",\n" +
            "         \"value\":0.7,\n" +
            "         \"trueReliability\":{\n" +
            "            \"type\":\"Property\",\n" +
            "            \"value\":\"0.3\"\n" +
            "         }\n" +
            "      }\n" +
            "   },\n" +
            "   \"name\":{\n" +
            "      \"type\":\"Property\",\n" +
            "      \"value\":\"Downtown One\"\n" +
            "   },\n" +
            "   \"totalSpotNumber\":{\n" +
            "      \"type\":\"Property\",\n" +
            "      \"value\":200\n" +
            "   },\n" +
            "   \"kafkaStreamErrors\":{\n" +
            "      \"type\":\"Property\",\n" +
            "      \"value\":[\n" +
            "         \"ERROR\",\n" +
            "         \"ERROR\"\n" +
            "      ]\n" +
            "   }\n" +
            "}";

}
