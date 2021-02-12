package org.example.lambda.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;

public final class DynamoService {

    /**
     * THROUGHPUT.
     */
    private static final Long THROUGHPUT = 10L;
    private DynamoService() {
    }

    /**
     * DDDD.
     * @param args {@link String[]}
     */
    public static void main(final String[] args) {

        /* Read the name from command args */
        final String tableName = "MyTabl8333";

        System.out.format("Creating table \"%s\" with a simple primary key:"
                + " \"Name\".\n", tableName);

        CreateTableRequest request = new CreateTableRequest()
                .withAttributeDefinitions(
                        new AttributeDefinition("Name", ScalarAttributeType.S))
                .withKeySchema(new KeySchemaElement("Name", KeyType.HASH))
                .withProvisionedThroughput(
                        new ProvisionedThroughput(THROUGHPUT, THROUGHPUT))
                .withTableName(tableName);

        AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                "http://localhost:8000",
                                Regions.US_EAST_1.getName()))
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(
                                "fakeMyKeyId",
                                "fakeSecretAccessKey")))
                .build();

        try {
            CreateTableResult result = ddb.createTable(request);
            System.out.println(result.getTableDescription().getTableName());
        } catch (AmazonServiceException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Done!");
    }
}
