package org.example.lambda;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import org.example.lambda.config.DynamoConfig;
import org.example.lambda.model.Address;
import org.example.lambda.util.TestContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.EventHandler;

import static org.junit.jupiter.api.Assertions.*;

class RestLambdaTest {

    private RestLambda restLambda;

    @BeforeEach
    private void init() {
        restLambda = new RestLambda();
        DynamoDBMapper instance = DynamoConfig.getInstance();
        CreateTableRequest createTableRequest = instance.generateCreateTableRequest(Address.class);
        createTableRequest.withProvisionedThroughput(new ProvisionedThroughput(10L, 10L));
        try {
            DynamoConfig.dynamoDB().deleteTable(createTableRequest.getTableName());
        } catch (ResourceNotFoundException rnfe) {
            System.out.println("Table not found creating.");
        }
        DynamoConfig.dynamoDB().createTable(createTableRequest);
    }


    @Test
    void testCreate() {
        // Given
        Address address = new Address();
        address.setLine1("Line 1");
        address.setLine2("Line 2");
        address.setLine3("Line 3");
        address.setLocation("Location");

        // When
        Address createdAddress = restLambda.create(address, new TestContext());

        // Then
        Assertions.assertNotNull(createdAddress.getId(), "Address Created Successfully");
//        Assertions.assertNotNull(createdAddress.getLine1(),"my change goes here");
    }
}