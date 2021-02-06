package com.techatpark.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.Map;

/**
 * DDD.
 */
public class HelloLambdaRest implements
        RequestHandler<Map<String, Object>, APIGatewayProxyResponseEvent> {

    /**
     * Status.
     */
    private static final int HTTP_STATUS_OK = 200;

    /**
     * DDDD.
     * @param stringObjectMap
     * @param context
     * @return res
     */
    @Override
    public APIGatewayProxyResponseEvent handleRequest(
            final Map<String, Object> stringObjectMap, final Context context) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(HTTP_STATUS_OK)
                .withBody(":) MAHHSHSH JSJJSJ :)")
                .withIsBase64Encoded(false);
    }
}
