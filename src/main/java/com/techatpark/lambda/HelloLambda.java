package com.techatpark.lambda;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * Simple Lambda Function.
 */
public class HelloLambda {
    /**
     * Say Hello.
     * @param user
     * @param context
     * @return welcomeText
     */
    public String message(final User user, final Context context) {
        return "Hello " + user.getName() + "!";
    }
}
