package org.example.lambda;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import org.example.lambda.config.DynamoConfig;
import org.example.lambda.model.Address;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple Lambda Function.
 */
public class RestLambda {

    /**
     * LOG.
     */
    private static final Logger LOG = Logger.getLogger("MMLambda");

    /**
     * DDDD.
     * @param address {@link Address}
     * @param context {@link Context}
     * @return {@link Address}
     */
    public Address create(final Address address, final Context context) {

        LOG.log(Level.INFO, context.getFunctionName());
        try {
            DynamoDBMapper instance = DynamoConfig.getInstance();
            instance.save(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        address.setLine1("my change goes here");
        return address;
    }
}
