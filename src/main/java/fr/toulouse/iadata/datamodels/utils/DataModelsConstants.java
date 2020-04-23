package fr.toulouse.iadata.datamodels.utils;

public class DataModelsConstants
{
    public static final String NGSI_PAYLOAD_SPEC_1 = "{" +
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
            " \"value\": 0.7" +
            "trueReliability\": {\n" +
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


    public static final String NGSI_PAYLOAD_SPEC_2 = "{\n" +
            "   \"id\":\"urn:ngsi-ld:Vehicle:A4567\",\n" +
            "   \"type\":\"Vehicle\",\n" +
            "   \"speed#1\":{\n" +
            "      \"type\":\"Property\",\n" +
            "      \"value\":55,\n" +
            "      \"source\":{\n" +
            "         \"type\":\"Property\",\n" +
            "         \"value\":\"Speedometer\"\n" +
            "      },\n" +
            "      \"datasetId\":\"urn:ngsi-ld:Property:speedometerA4567-speed\"\n" +
            "   },\n" +
            "   \"speed#2\":{\n" +
            "      \"type\":\"Property\",\n" +
            "      \"value\":54.5,\n" +
            "      \"source\":{\n" +
            "         \"type\":\"Property\",\n" +
            "         \"value\":\"GPS\"\n" +
            "      },\n" +
            "      \"datasetId\":\"urn:ngsi-ld:Property:gpsBxyz123-speed\"\n" +
            "   },\n" +
            "   \"@context\":[\n" +
            "      \"http://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\",\n" +
            "      {\n" +
            "         \"speed#1\":\"http://example.org/speed\",\n" +
            "         \"speed#2\":\"http://example.org/speed\",\n" +
            "         \"source\":\"http://example.org/hasSource\"\n" +
            "      }\n" +
            "   ]\n" +
            "}";

    public static final String NGSI_PAYLOAD_SPEC_3  = "{\n" +
        " \"id\": \"urn:ngsi-ld:Vehicle:A4567\",\n" +
        " \"type\": \"Vehicle\",\n" +
        " \"brandName\": {\n" +
        " \"type\": \"Property\",\n" +
        " \"value\": \"Mercedes\"\n" +
        " },\n" +
        " \"isParked\": {\n" +
        " \"type\": \"Relationship\",\n" +
        " \"object\": \"urn:ngsi-ld:OffStreetParking:Downtown1\",\n" +
        " \"observedAt\": \"2017-07-29T12:00:04Z\",\n" +
        " \"providedBy\": {\n" +
        " \"type\": \"Relationship\",\n" +
        " \"object\": \"urn:ngsi-ld:Person:Bob\"\n" +
        " }\n" +
        " },\n" +
        " \"@context\": [\n" +
        " \"http://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\",\n" +
        " \"http://example.org/ngsi-ld/commonTerms.jsonld\",\n" +
        " \"http://example.org/ngsi-ld/vehicle.jsonld\",\n" +
        " \"http://example.org/ngsi-ld/parking.jsonld\"\n" +
        " ]\n" +
        "} ";

    public static final String NGSI_PAYLOAD_SPEC_4 = "{\n" +
            " \"id\": \"urn:ngsi-ld:Vehicle:A4567\",\n" +
            " \"type\": \"Vehicle\",\n" +
            " \"brandName\": \"Mercedes\",\n" +
            " \"isParked\": {\n" +
            " \"type\": \"Relationship\",\n" +
            " \"object\": \"urn:ngsi-ld:OffStreetParking:Downtown1\",\n" +
            " \"observedAt\": \"2017-07-29T12:00:04Z\",\n" +
            " \"providedBy\": {\n" +
            " \"type\": \"Relationship\",\n" +
            " \"object\": \"urn:ngsi-ld:Person:Bob\"\n" +
            " }\n" +
            " },\n" +
            " \"@context\": [\n" +
            " \"http://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/commonTerms.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/vehicle.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/parking.jsonld\"\n" +
            " ]\n" +
            "} ";

    public static final String NGSI_PAYLOAD_SPEC_5 = "{\n" +
            " \"id\": \"urn:ngsi-ld:Vehicle:A4567\",\n" +
            " \"type\": \"Vehicle\",\n" +
            " \"brandName\": \"Mercedes\",\n" +
            " \"isParked\": \"urn:ngsi-ld:OffStreetParking:Downtown1\",\n" +
            " \"@context\": [\n" +
            " \"http://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/commonTerms.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/vehicle.jsonld\",\n" +
            " \"http://example.org/ngsi-ld/parking.jsonld\"\n" +
            " ]\n" +
            "} ";

    public static final String NGSI_PAYLOAD_SPEC_6 = "{\n" +
            " \"id\": \"urn:ngsi-ld:Vehicle:A4567\",\n" +
            " \"type\": \"Vehicle\",\n" +
            " \"speed#1\": {\n" +
            " \"type\": \"Property\",\n" +
            " \"value\": 55,\n" +
            " \"source\": {\n" +
            " \"type\": \"Property\",\n" +
            " \"value\": \"Speedometer\"\n" +
            " },\n" +
            " \"datasetId\": \"urn:ngsi-ld:Property:speedometerA4567-speed\" \n" +
            " },\n" +
            " \"speed#2\": {\n" +
            " \"type\": \"Property\",\n" +
            " \"value\": 54.5,\n" +
            " \"source\": {\n" +
            " \"type\": \"Property\",\n" +
            " \"value\": \"GPS\"\n" +
            " },\n" +
            " \"datasetId\": \"urn:ngsi-ld:Property:gpsBxyz123-speed\"\n" +
            " },\n" +
            " \"@context\": [\n" +
            " \"http://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld\",\n" +
            " {\n" +
            " \"speed#1\": \"http://example.org/speed\",\n" +
            " \"speed#2\": \"http://example.org/speed\",\n" +
            " \"source\": \"http://example.org/hasSource\"\n" +
            " }\n" +
            " ]\n" +
            "} ";


    public static final String NGSI_PAYLOAD_SPEC_7 = "{\n" +
            "  \"location\": {\n" +
            "    \"value\": {\n" +
            "      \"type\": \"Point\",\n" +
            "      \"coordinates\": [\n" +
            "        -8.5,\n" +
            "        4\n" +
            "      ]\n" +
            "    },\n" +
            "    \"type\": \"GeoProperty\"\n" +
            "  },\n" +
            "  \"id\": \"urn:ngsi-ld:OffStreetParking:Downtown1\",\n" +
            "  \"type\": \"OffStreetParking\",\n" +
            "  \"@context\": [\n" +
            "    \"http://example.org/ngsi-ld/latest/parking.jsonld\",\n" +
            "    \"https://uri.etsi.org/gsi-ld/v1/ngsi-ld-core-context.jsonld\"\n" +
            "  ],\n" +
            "  \"name\": {\n" +
            "    \"value\": \"Downtown One\",\n" +
            "    \"type\": \"Property\"\n" +
            "  },\n" +
            "  \"totalSpotNumber\": {\n" +
            "    \"value\": \"200\",\n" +
            "    \"type\": \"Property\"\n" +
            "  },\n" +
            "  \"availableSpotNumber\": {\n" +
            "    \"value\": 121,\n" +
            "    \"type\": \"Property\",\n" +
            "    \"observedAt\": \"2017-07-29T12:05:02Z\",\n" +
            "    \"providedBy\": {\n" +
            "      \"object\": \"urn:ngsi-ld:Camera:C1\",\n" +
            "      \"type\": \"Relationship\"\n" +
            "    },\n" +
            "    \"reliability\": {\n" +
            "      \"value\": 0.7,\n" +
            "      \"type\": \"Property\",\n" +
            "      \"trueReliability\": {\n" +
            "        \"value\": \"0.3\",\n" +
            "        \"type\": \"Property\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "} ";

    public static final String NGSI_PAYLOAD_SPECIAL_CHAR = "{\n" +
            "  \"location\": {\n" +
            "    \"value\": {\n" +
            "      \"type\": \"Point\",\n" +
            "      \"coordinates\": [\n" +
            "        -8.5,\n" +
            "        4\n" +
            "      ]\n" +
            "    },\n" +
            "    \"type\": \"GeoProperty\"\n" +
            "  },\n" +
            "  \"id\": \"urn:ngsi-ld:OffStreetParking:Downtown1\",\n" +
            "  \"type\": \"OffStreetParking\",\n" +
            "  \"@context\": [\n" +
            "    \"http://example.org/ngsi-ld/latest/parking.jsonld\",\n" +
            "    \"https://uri.etsi.org/gsi-ld/v1/ngsi-ld-core-context.jsonld\"\n" +
            "  ],\n" +
            "  \"name\": {\n" +
            "    \"value\": \"Downtown One\",\n" +
            "    \"type\": \"Property\"\n" +
            "  },\n" +
            "  \"totalSpotNumber\": {\n" +
            "    \"value\": \"°°°\",\n" +
            "    \"type\": \"Property\"\n" +
            "  },\n" +
            "  \"availableSpotNumber\": {\n" +
            "    \"value\": 121,\n" +
            "    \"type\": \"Property\",\n" +
            "    \"observedAt\": \"2017-07-29T12:05:02Z\",\n" +
            "    \"providedBy\": {\n" +
            "      \"object\": \"urn:ngsi-ld:Camera:C1\",\n" +
            "      \"type\": \"Relationship\"\n" +
            "    },\n" +
            "    \"reliability\": {\n" +
            "      \"value\": 0.7,\n" +
            "      \"type\": \"Property\",\n" +
            "      \"trueReliability\": {\n" +
            "        \"value\": \"0.3\",\n" +
            "        \"type\": \"Property\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "} ";







}
